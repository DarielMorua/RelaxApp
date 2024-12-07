package com.example.relaxapp.views.mainmenu

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.relaxapp.R
import com.example.relaxapp.TokenManager
import com.example.relaxapp.bottomnavigationbar.BottomNavigationBar
import com.example.relaxapp.bottomnavigationbar.Routes
import com.example.relaxapp.views.signup.AshGray
import com.example.relaxapp.views.signup.CambridgeBlue
import com.example.relaxapp.views.signup.CarolinaBlue
import com.example.relaxapp.views.signup.Nunito
import com.example.relaxapp.views.signup.Oxygen
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState


val CustomTypography = Typography(

// titulo
    headlineLarge = TextStyle(
        fontFamily = Nunito,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
        color = CarolinaBlue
    ),
    // boton
    headlineMedium = TextStyle(
        fontFamily = Nunito,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        color = Color.White
    ),
    // cuerpo
    headlineSmall = TextStyle(
        fontFamily = Oxygen,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        color = CambridgeBlue
    ),
    // caption
    labelSmall = TextStyle(
        fontFamily = Oxygen,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        color = AshGray
    )
)

val emojis = listOf("\uD83D\uDE04", "\uD83D\uDE42", "😐", "☹\uFE0F", "\uD83D\uDE22","😭")

val imagenes = listOf(
    R.drawable.resp1,  // Primera imagen
    R.drawable.esti2,  // Segunda imagen
    R.drawable.medi1    // Tercera imagen
)

@Composable
fun MainMenu(viewModel: MainMenuViewModel, navController: NavController) {
    val context = LocalContext.current
    val mainMenuViewModel: MainMenuViewModel =
        viewModel(factory = MainMenuViewModel.MainMenuViewModelFactory(context))
    val exercises = mainMenuViewModel.exercises
    val isLoading = mainMenuViewModel.isLoading
    val tokenManager = remember { TokenManager(context) }
    val userId = tokenManager.getUserId()

    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)

    var selectedEmoji by remember { mutableStateOf<String?>(null) }
    var canSelectEmoji by remember { mutableStateOf(true) }
    var timeRemaining by remember { mutableStateOf<Long>(0L) }

    LaunchedEffect(Unit) {
        mainMenuViewModel.getRecommendedExercises()
    }

    // Verifica si ya han pasado las 5 horas o no
    LaunchedEffect(selectedEmoji) {
        canSelectEmoji = mainMenuViewModel.canSelectEmoji()

        if (!canSelectEmoji) {
            val timestamp = System.currentTimeMillis()
            val lastTimestamp = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
                .getLong("emoji_timestamp", 0L)

            val timePassed = timestamp - lastTimestamp
            val timeLeft = (5 * 60 * 60 * 1000) - timePassed

            if (timeLeft > 0) {
                timeRemaining = timeLeft
            }
        }
    }

    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) { innerPadding ->
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = {
                Log.d("MainMenu", "Refresh triggered")
                mainMenuViewModel.getRecommendedExercises() // Llamada para refrescar los ejercicios
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF7F7F7))
                    .padding(innerPadding)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Logo y nombre de la app
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_logo),
                        contentDescription = "Logo",
                        modifier = Modifier.size(60.dp)
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = stringResource(id = R.string.app_name),
                        style = MaterialTheme.typography.headlineLarge,
                        color = Color.Black,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Profile Icon",
                        tint = Color.Black,
                        modifier = Modifier
                            .size(50.dp)
                            .clickable {
                                if (!userId.isNullOrEmpty()) {
                                    navController.navigate("profileView/$userId")
                                } else {
                                    Log.e("MainMenu", "User ID is missing")
                                }
                            }
                    )
                }

                // Emociones
                Text(
                    text = stringResource(id = R.string.how_are_you),
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.Gray,
                    fontSize = 25.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    emojis.forEach { emoji ->
                        val targetSize = if (selectedEmoji == emoji) 70.dp else 50.dp
                        val animatedSize by animateDpAsState(
                            targetValue = targetSize,
                            animationSpec = androidx.compose.animation.core.spring(
                                dampingRatio = 0.4f,
                                stiffness = 200f
                            )
                        )

                        val scale by animateFloatAsState(
                            targetValue = if (selectedEmoji == emoji) 1.2f else 1f,
                            animationSpec = androidx.compose.animation.core.spring(
                                dampingRatio = 0.4f,
                                stiffness = 200f
                            )
                        )

                        Button(
                            onClick = {
                                if (canSelectEmoji) {
                                    selectedEmoji = emoji
                                    mainMenuViewModel.onEmojiSelected(emoji)
                                    mainMenuViewModel.submitEmotion(emoji)
                                    Toast.makeText(context, "Emoción guardada.", Toast.LENGTH_LONG).show()
                                } else {
                                    // Mostrar el tiempo restante si no se puede seleccionar
                                    val timeLeft = timeRemaining / 1000
                                    val hours = timeLeft / 3600
                                    val minutes = (timeLeft % 3600) / 60
                                    Toast.makeText(
                                        context,
                                        "Todavía no han pasado las 5 horas. Quedan $hours horas y $minutes minutos.",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            },
                            shape = RoundedCornerShape(percent = 50),
                            modifier = Modifier
                                .size(animatedSize)
                                .graphicsLayer(scaleX = scale, scaleY = scale),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                        ) {
                            Text(
                                text = emoji,
                                fontSize = if (selectedEmoji == emoji) 30.sp else 24.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Ejercicios recomendados
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.recommendedExercises),
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color.Gray,
                        modifier = Modifier.padding(bottom = 16.dp),
                        fontSize = 25.sp
                    )

                    if (isLoading) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                    } else {
                        if (exercises.isNotEmpty()) {
                            LazyRow(
                                contentPadding = PaddingValues(horizontal = 16.dp),
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                items(exercises) { exercise ->
                                    ExerciseCard(
                                        title = exercise.title,
                                        imageUrl = exercise.image,
                                        shortDescription = exercise.shortDescription,
                                        modifier = Modifier
                                            .size(250.dp)
                                            .clickable {
                                                Log.d("MainMenu", "Navegando a: exerciseDetail/${exercise.id}")
                                                navController.navigate("exerciseDetail/${exercise.id}")
                                            }
                                    )
                                }
                            }
                        } else {
                            Text(
                                text = "No exercises available",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Gray,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        }
                    }
                }
            }
        }
    }
}
