package com.example.relaxapp.views.exercises.excerciseDetails

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.ActivityInfo
import android.util.Log
import android.webkit.ConsoleMessage
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.VideoView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.relaxapp.R
import com.example.relaxapp.bottomnavigationbar.BottomNavigationBar
import com.example.relaxapp.views.mainmenu.ExcerciseResponse
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun ExerciseDetailView(navController: NavController, exercise: ExcerciseResponse) {
    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) { innerPadding ->
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White)
        ) {
            // Top section
            item {
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "ArrowBack Icon",
                        tint = Color.Black,
                        modifier = Modifier
                            .size(50.dp)
                            .clickable {
                                navController.popBackStack()
                            }
                    )
                    Text(
                        text = "Exercise Details",
                        style = MaterialTheme.typography.headlineLarge,
                        color = Color(26, 204, 181, 255),
                        fontSize = 30.sp,
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 16.dp),
                        textAlign = TextAlign.Center
                    )
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Profile Icon",
                        tint = Color.Black,
                        modifier = Modifier.size(50.dp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Exercise details
            item {
                Text(
                    text = exercise.title, // Usar el título del ejercicio
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color(26, 204, 181, 255),
                    fontSize = 20.sp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                AsyncImage(
                    model = exercise.image, // Mostrar la imagen del ejercicio
                    contentDescription = exercise.title,
                    modifier = Modifier.size(300.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Descripción del Ejercicio
            item {
                Text(
                    text = exercise.longDescription, // Mostrar descripción larga
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.DarkGray,
                    fontSize = 15.sp,
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                )
            }

            // Video de YouTube
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Video de Ejercicio",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.Gray,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                AndroidView(
                    factory = { context ->
                        YouTubePlayerView(context).apply {
                            addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                                override fun onReady(youTubePlayer: YouTubePlayer) {
                                    // Usar el URL del video del ejercicio
                                    val videoId = extractYouTubeVideoId(exercise.urlVideo)
                                    if (videoId != null) {
                                        youTubePlayer.loadVideo(videoId, 0f)
                                    }
                                }
                            })
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(horizontal = 16.dp)
                )
            }
        }
    }
}

// Función para extraer el ID de un video de YouTube
fun extractYouTubeVideoId(url: String): String? {
    return Regex("v=([a-zA-Z0-9_-]+)").find(url)?.groupValues?.get(1)
}
