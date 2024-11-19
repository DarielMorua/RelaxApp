package com.example.relaxapp.views.doctordetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.relaxapp.R
import com.example.relaxapp.bottomnavigationbar.BottomNavigationBar
import com.example.relaxapp.bottomnavigationbar.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun DoctorDetailView(navController: NavController) {
    val sampleReviews = listOf(
        Review("Jhon", 5, "Gran doctor, me ayudó mucho y me sacó de muchos problemas."),
        Review("Maria", 4, "Excelente atención, muy amable y profesional."),
        Review("Carlos", 5, "Un doctor increíble, resolvió mi caso rápidamente."),
        Review("Ana", 3, "Bueno, pero podría ser más puntual."),
        Review("Luis", 5, "El mejor doctor que he visitado.")
    )
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalles del Doctor") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
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
            // Sección de información del doctor
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Imagen del doctor
                Image(
                    painter = painterResource(id = R.drawable.dummy),
                    contentDescription = "Doctor Image",
                    modifier = Modifier
                        .size(72.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(16.dp))

                // Información del doctor
                Column {
                    Text(text = "Dr. Simi", style = MaterialTheme.typography.titleLarge)
                    Text(
                        text = "Valoración: 4.8⭐",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                    Text(
                        text = "Tiempo en la Aplicación: 6 Meses",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Descripción del doctor
            Text(
                text = "Acerca de mí",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Doctor apasionado por ayudar a las personas, siempre hago mi trabajo ya que no hay persona a la que no puedas ayudar.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Sección de reseñas
            Text(
                text = "Reseñas",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(sampleReviews) { review ->
                    ReviewCard(review)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón de chatear
            Button(
                onClick =  { navController.navigate(Routes.ChatView) },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(0.9f),
                shape = RoundedCornerShape(24.dp)
            ) {
                Text(text = "¡Chatea conmigo!")
            }
            Button(
                onClick =  { navController.navigate(Routes.DoctorScheduleView) },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(0.9f),
                shape = RoundedCornerShape(24.dp)
            ) {
                Text(text = "Horarios Disponibles")
            }
        }
    }
}



@Composable
fun ReviewCard(review: Review) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .width(250.dp)
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = review.author,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "⭐".repeat(review.rating),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Yellow
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = review.comment,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}



