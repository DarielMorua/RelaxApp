package com.example.relaxapp.views.doctordetails

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.relaxapp.bottomnavigationbar.BottomNavigationBar
import com.example.relaxapp.views.profesionales.Review
import com.example.relaxapp.views.doctordetails.viewmodels.DoctorDetailViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoctorDetailView(
    navController: NavController,
    viewModel: DoctorDetailViewModel,
    professionalId: String
) {
    // Observa los datos del ViewModel
    val professional = viewModel.professional
    val reviews = viewModel.reviews
    val isLoading = viewModel.isLoading

    // Llama al método para cargar los datos cuando se monta la vista
    LaunchedEffect(professionalId) {
        viewModel.loadProfessionalDetails(professionalId)
    }

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
        // Muestra un indicador de carga mientras se obtienen los datos
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            // Contenido principal
            professional?.let { prof ->
                LazyColumn(
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding(16.dp)
                        .fillMaxSize()
                ) {
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = rememberImagePainter(prof.photo),
                                contentDescription = "Doctor Image",
                                modifier = Modifier
                                    .size(72.dp)
                                    .clip(CircleShape)
                            )
                            Spacer(modifier = Modifier.width(16.dp))

                            Column {
                                Text(text = prof.name, style = MaterialTheme.typography.titleLarge)
                                Text(
                                    text = "Valoración: ${"%.1f".format(prof.score)}⭐",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.Gray
                                )
                                Text(
                                    text = "Teléfono: ${prof.phone}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.Gray
                                )
                            }
                        }
                    }

                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = prof.description,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray
                        )
                    }

                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Reseñas",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }

                    item {
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            items(reviews) { review ->
                                ReviewCard(review)
                            }
                        }
                    }

                    // Botón para crear un nuevo chat
                    item {
                        Spacer(modifier = Modifier.height(16.dp)) // Espacio antes del botón
                        Button(
                            onClick = {
                                // Llamamos a createChat y pasamos el ID del profesional
                                viewModel.createChat(professionalId) { chatId ->
                                    // Aquí recibimos el chatId y podemos navegar a la vista de ChatView
                                    Log.d("Chat", "Chat creado con ID: $chatId")

                                    // Asumimos que 'userRole' es "User" para este ejemplo
                                    val userRole = "User" // o usa una variable o ViewModel para obtener el rol

                                    // Navegar al ChatView pasando chatId y userRole
                                    navController.navigate("chatView/$chatId/$userRole")
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            colors = ButtonDefaults.buttonColors(Color(0xFF6200EE))
                        ) {
                            Text(
                                text = "¡Chatea Conmigo!",
                                color = Color.White,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }

                    // Botón para navegar directamente al ChatView (sin crear un nuevo chat)
                    item {
                        Spacer(modifier = Modifier.height(16.dp)) // Espacio antes del botón
                        Button(
                            onClick = {
                                // Navegar al ChatView directamente
                                // En este caso, estamos pasando un chatId de ejemplo (puedes modificar esto según tu lógica)
                                val chatId = "someChatId" // Aquí deberías usar un ID de chat existente
                                val userRole = "User" // El rol del usuario
                                navController.navigate("chatView/$chatId/$userRole")
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            colors = ButtonDefaults.buttonColors(Color(0xFF6200EE))
                        ) {
                            Text(
                                text = "Ir al Chat",
                                color = Color.White,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            } ?: run {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Error al cargar los detalles del profesional.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Red
                    )
                }
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
            .width(200.dp)
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Usuario: ${review.userId.name}")
            Text(text = "Comentario: ${review.comment}")
            Text(text = "Puntuación: ⭐${review.score}")
        }
    }
}
