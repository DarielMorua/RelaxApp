package com.example.relaxapp.views.profesionales

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.relaxapp.R
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.rememberImagePainter
import com.example.relaxapp.TokenManager

import com.example.relaxapp.bottomnavigationbar.BottomNavigationBar
import com.example.relaxapp.bottomnavigationbar.Routes
import com.example.relaxapp.views.chat.ChatDetailsMessages
import com.example.relaxapp.views.doctordetails.DoctorDetailView
import com.example.relaxapp.views.profesionales.conexionChat.ChatProfesional


@Composable
fun ProfessionalView(navController: NavController, viewModel: ProfessionalViewModel) {
    val context = LocalContext.current
    val isLoading by viewModel.isLoading.collectAsState()
    val professionals by viewModel.professionals.collectAsState()
    val chats by viewModel.chats.collectAsState()
    val rol by viewModel.rol.collectAsState()

    // Cargar datos según el rol
    LaunchedEffect(key1 = true) {
        if (rol == "Profesional") {
            Log.d("ProfessionalView", "Cargando chats para el profesional")
            viewModel.loadProfessionalChats()
        } else {
            Log.d("ProfessionalView", "Cargando lista de profesionales")
            viewModel.loadProfessionals()
        }
    }

    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White)
        ) {
            // Encabezado de la pantalla según el rol
            Text(
                text = if (rol == "Profesional") "Chats con Usuarios" else "Profesionales",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.Center
            )

            // Mostrar indicador de carga mientras se obtienen los datos
            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                // Vista para el rol Profesional
                if (rol == "Profesional") {
                    if (chats.isNotEmpty()) {
                        LazyColumn(
                            contentPadding = PaddingValues(16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            items(chats.filter { it._id != null && it.professional != null }) { chat ->
                                ChatCardForProfessional(
                                    chat = chat,
                                    onClick = {
                                        val userRole = "Profesional"
                                        navController.navigate("chatView/${chat._id}/$userRole") {
                                            launchSingleTop = true
                                        }
                                    }
                                )
                            }
                        }
                    } else {
                        NoDataView(message = "No tienes chats disponibles.")
                    }
                } else {
                    // Vista para el rol Usuario
                    if (professionals.isEmpty()) {
                        NoDataView(message = "No hay profesionales disponibles.")
                    } else {
                        LazyColumn(
                            contentPadding = PaddingValues(16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            items(professionals) { professional ->
                                ProfessionalCard(
                                    professional = professional,
                                    onAddFavorite = { viewModel.addFavorite(professional._id) },
                                    onClick = {
                                        navController.navigate("doctor_detail/${professional._id}") {
                                            launchSingleTop = true
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProfessionalCard(professional: Professional, onAddFavorite: () -> Unit, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberImagePainter(
                    data = professional.photo,
                    builder = {
                        placeholder(R.drawable.dummy)
                        error(R.drawable.dummy)
                    }
                ),
                contentDescription = professional.name,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = professional.name,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.Filled.FavoriteBorder,
                contentDescription = "Add to Favorites",
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onAddFavorite() },
                tint = Color.Gray
            )
        }
    }
}

@Composable
fun ChatCardForProfessional(chat: ChatDetailsMessages, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(16.dp))

            Column {
                // Nombre del usuario
                chat.user?.name?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
                // Último mensaje
                Text(
                    text = "Último mensaje: ${chat.messages.lastOrNull()?.content ?: "Sin mensajes"}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun NoDataView(message: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            color = Color.Gray,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
    }
}
