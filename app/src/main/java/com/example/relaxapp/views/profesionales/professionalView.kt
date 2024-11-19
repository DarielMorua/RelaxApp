package com.example.relaxapp.views.profesionales

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar

import com.example.relaxapp.bottomnavigationbar.BottomNavigationBar
import com.example.relaxapp.bottomnavigationbar.Routes
import com.example.relaxapp.views.doctordetails.DoctorDetailView


@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun ProfessionalView(navController: NavController) {
    // Lista de profesionales de ejemplo
    val professionals = listOf(
        Professional("Dr. Simi", R.drawable.dummy),
        Professional("Dr. Amigo", R.drawable.dummy),
        Professional("Dra. Lailín", R.drawable.dummy),
        Professional("Dr. Doom", R.drawable.dummy),
        Professional("Dr. Bonzi", R.drawable.dummy)
    )

    // Contenedor principal con color de fondo
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7)) // Color de fondo
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Profesionales")
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack, // Icono de retroceso
                                contentDescription = "Back"
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = {

                        }) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search"
                            )
                        }
                    }
                )
            },
            bottomBar = { BottomNavigationBar(navController = navController) }
        ) { innerPadding ->
            LazyColumn(
                contentPadding = innerPadding,
                modifier = Modifier
                    .fillMaxSize() // Asegura que la lista ocupe todo el espacio disponible
            ) {
                // Itera sobre la lista de profesionales
                items(professionals) { professional ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .clickable { navController.navigate(Routes.DoctorDetailView)
                            }

                    ) {
                        // Imagen
                        Image(
                            painter = painterResource(id = professional.imageResId),
                            contentDescription = professional.name,
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                                .border(2.dp, Color.Gray, CircleShape)
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        // Nombre
                        Text(
                            text = professional.name,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                }
            }
        }
    }
}
