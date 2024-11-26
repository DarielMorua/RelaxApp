package com.example.relaxapp.views.notifications

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import androidx.wear.compose.material.FractionalThreshold
import androidx.wear.compose.material.rememberSwipeableState
import androidx.wear.compose.material.swipeable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.IntOffset
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalWearMaterialApi::class)
@Composable
fun NotificationCard(
    id: String,
    title: String,
    message: String,
    date: String,
    seen: Boolean,
    onDismiss: () -> Unit,
    viewModel: NotificationViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val swipeState = rememberSwipeableState(initialValue = 0)
    val sizePx = with(LocalDensity.current) { 200.dp.toPx() } // Ancho del swipeable
    val anchors = mapOf(0f to 0, -sizePx to 1) // Puntos de anclaje para el swipeable

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .height(100.dp)
    ) {
        // Fondo con el botón de borrar
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Red)
                .padding(end = 16.dp),
            contentAlignment = Alignment.CenterEnd // Alinea el botón al final
        ) {
            IconButton(
                onClick = {
                    Log.d("NotificationCard", "Delete button clicked")
                    Log.d("NotificationCard", "Notification ID: $id")
                    viewModel.deleteNotification(notificationId = id)
                    onDismiss() // Ejecuta la acción de borrar
                },
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color.White)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Eliminar",
                    tint = Color.Red
                )
            }
        }

        // Tarjeta deslizable
        Card(
            modifier = Modifier
                .fillMaxSize()
                .offset { IntOffset(swipeState.offset.value.toInt(), 0) }
                .swipeable(
                    state = swipeState,
                    anchors = anchors,
                    thresholds = { _, _ -> FractionalThreshold(0.3f) },
                    orientation = Orientation.Horizontal
                ),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Start
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "$message - $date",
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Start
                )

                if (!seen) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "NEW",
                        style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.primary),
                        textAlign = TextAlign.Start
                    )
                }
            }
        }
    }
}