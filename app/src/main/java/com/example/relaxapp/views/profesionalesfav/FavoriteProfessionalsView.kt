package com.example.relaxapp.views.profesionalesfav

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.relaxapp.bottomnavigationbar.BottomNavigationBar
import com.example.relaxapp.views.profesionales.ProfessionalCard
import com.example.relaxapp.views.profesionales.ProfessionalViewModel

@Composable
fun FavoriteProfessionalsView(
    navController: NavController,
    viewModel: ProfessionalViewModel,
    userId: String
) {
    val isLoading by viewModel.isLoading.collectAsState()
    val favorites by viewModel.professionals.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadFavorites(userId)
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
            // Título
            Text(
                text = "Tus Profesionales Favoritos",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.Center
            )

            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }
            else if (favorites.isEmpty()) {
                Text(
                    text = "No tienes profesionales favoritos.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Gray,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 20.dp)
                )
            }
            else {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(favorites) { professional ->
                        ProfessionalCard(
                            professional = professional,
                            onAddFavorite = {  },
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
