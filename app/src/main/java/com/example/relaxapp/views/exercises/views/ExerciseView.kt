package com.example.relaxapp.views.exercises.views

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.relaxapp.R
import com.example.relaxapp.bottomnavigationbar.BottomNavigationBar
import com.example.relaxapp.views.exercises.viewmodel.ExerciseViewModel
import com.example.relaxapp.views.mainmenu.views.ExerciseCard
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

val imagenesRespiracion = listOf(
    R.drawable.resp2,  // Primera imagen
    R.drawable.resp3,  // Segunda imagen
    R.drawable.resp4,  // Tercera imagen
    R.drawable.resp5   // Cuarta imagen
)

val imagenesMeditacion = listOf(
    R.drawable.medi1,  // Primera imagen
    R.drawable.medit2,  // Segunda imagen
    R.drawable.medit3,  // Tercera imagen
    R.drawable.medit4   // Cuarta imagen
)

@Composable
fun ExerciseView(viewModel: ExerciseViewModel, navController: NavController) {
    val context = LocalContext.current
    val exerciseViewModel: ExerciseViewModel =
        viewModel(factory = ExerciseViewModel.ExerciseViewModelFactory(context))
    val categories = exerciseViewModel.categories
    val isLoading = exerciseViewModel.isLoading

    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)

    LaunchedEffect(Unit) {
        Log.d("ExerciseView", "Obteniendo ejercicios por categoría")
        exerciseViewModel.getExercisesByCategory()
    }

    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) { innerPadding ->
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = {
                exerciseViewModel.getExercisesByCategory()
            }
        ) {
            if (isLoading) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                LazyColumn(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .background(Color.White)
                ) {
                    categories.forEach { category ->
                        item {
                            Text(
                                text = category.name,
                                style = MaterialTheme.typography.headlineMedium,
                                color = Color.Gray,
                                modifier = Modifier.padding(16.dp),
                                fontSize = 25.sp
                            )
                        }

                        item {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .horizontalScroll(rememberScrollState())
                                    .padding(horizontal = 16.dp),
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                category.exercises.take(5).forEach { exercise ->
                                    ExerciseCard(
                                        title = exercise.title,
                                        imageUrl = exercise.image,
                                        shortDescription = exercise.shortDescription,
                                        modifier = Modifier
                                            .clickable {
                                                Log.d(
                                                    "ExerciseView",
                                                    "Navegando a la vista de detalle del ejercicio ${exercise.id}"
                                                )
                                                navController.navigate("exerciseDetailCategory/${exercise.id}")
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
}
