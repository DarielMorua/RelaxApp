package com.example.relaxapp.views.doctorschedule.views

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.relaxapp.bottomnavigationbar.BottomNavigationBar
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.tileprovider.tilesource.TileSourceFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoctorScheduleView(navController: NavController) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Ubicación y Horarios") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        },
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                OpenStreetMapView(
                    latitude = 28.6353,
                    longitude = -106.0889
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = "Teléfono",
                    modifier = Modifier.size(24.dp),
                    tint = Color.Black
                )
                Spacer(modifier = Modifier.width(8.dp))
                ClickableText(
                    text = AnnotatedString("614 666 616"),
                    style = MaterialTheme.typography.bodyLarge,
                    onClick = {
                        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:614666616"))
                        context.startActivity(intent)
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Horarios disponibles:",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text("Lunes: 10:00 - 16:00", style = MaterialTheme.typography.bodyMedium)
                Text("Martes: 10:00 - 16:00", style = MaterialTheme.typography.bodyMedium)
                Text("Miércoles: 10:00 - 16:00", style = MaterialTheme.typography.bodyMedium)
                Text("Jueves: 10:00 - 16:00", style = MaterialTheme.typography.bodyMedium)
                Text("Viernes: 10:00 - 14:00", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Composable
fun OpenStreetMapView(latitude: Double, longitude: Double) {
    AndroidView(
        factory = { context ->
            MapView(context).apply {
                setTileSource(TileSourceFactory.MAPNIK)
                controller.setZoom(15.0)
                controller.setCenter(GeoPoint(latitude, longitude))
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}
