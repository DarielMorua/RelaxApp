package com.example.relaxapp.views.profile.personaldata

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.relaxapp.R
import com.example.relaxapp.bottomnavigationbar.BottomNavigationBar
import com.example.relaxapp.bottomnavigationbar.Routes
import com.example.relaxapp.views.profile.ProfileViewModel

@Composable
fun PersonalDataView(navController: NavController, userId: String?) {
    val context = LocalContext.current
    val profileViewModel: ProfileViewModel = viewModel(factory = ProfileViewModel.ProfileViewModelFactory(context))
    val user by profileViewModel.user
    val isLoading by profileViewModel.isLoading

    // Llamada a la API para obtener los detalles del usuario, si es necesario
    LaunchedEffect(userId) {
        if (userId != null) {
            profileViewModel.getUserDetails(userId) // Obtener los datos usando el userId
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
            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .clickable {
                            navController.navigate("profileView/$userId")
                        }
                        .size(35.dp)
                )

                Text(
                    text = stringResource(id = R.string.personaldata),
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color(26, 204, 181, 255),
                    fontSize = 28.sp,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Asegurarse de que el usuario esté cargado antes de mostrar los datos
            if (user != null) {
                // Nombre
                DataRow(label = stringResource(id = R.string.name), value = user?.name)

                // Apellido
                DataRow(label = stringResource(id = R.string.lastname), value = user?.lastname)

                // Email
                DataRow(label = stringResource(id = R.string.email), value = user?.email)

                // Teléfono
                DataRow(label = stringResource(id = R.string.phone_number), value = user?.phone)

                // País
                DataRow(label = stringResource(id = R.string.country), value = user?.country)

            }
        }
    }
}

@Composable
fun DataRow(label: String, value: String?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(Color(0xFFF5F5F5)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(text = label, fontWeight = FontWeight.Bold)
            Text(text = value ?: "Cargando...")
        }
    }
}



