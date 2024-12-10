package com.example.relaxapp.views.aboutus

import android.os.Bundle
import android.webkit.WebView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.relaxapp.TokenManager
import com.example.relaxapp.bottomnavigationbar.BottomNavigationBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutUsView(navController: NavController) {
    val aboutUsViewModel: AboutUsViewModel = viewModel()
    val aboutUsInfo by aboutUsViewModel.getAboutUsInfo().observeAsState()

    val tokenManager = TokenManager(LocalContext.current)
    val userId = tokenManager.getUserId()

    // Contenido principal de la pantalla
    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Acerca de",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center // Centra el título
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate("profileView/$userId") // Navega al perfil
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Black,
                            modifier = Modifier.size(35.dp) // Tamaño del ícono
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                aboutUsInfo?.firstOrNull()?.let { info ->
                    Text(
                        text = "Integrante 1: ${info.dariel}",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Integrante 2: ${info.juvey}",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Materia: ${info.materia}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Descripción: ${info.descripcion}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    // WebView para GitHub de Dariel
                    GitHubSection(link = info.linkdariel, label = "GitHub de Dariel")
                    Spacer(modifier = Modifier.height(16.dp))

                    // WebView para GitHub de Juvey
                    GitHubSection(link = info.linkjuvey, label = "GitHub de Juvey")
                } ?: run {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    )
}

@Composable
fun GitHubSection(link: String, label: String) {
    Text(
        text = label,
        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
        color = MaterialTheme.colorScheme.primary
    )
    Spacer(modifier = Modifier.height(4.dp))
    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        factory = { context ->
            WebView(context).apply {
                loadUrl(link)
            }
        }
    )
}