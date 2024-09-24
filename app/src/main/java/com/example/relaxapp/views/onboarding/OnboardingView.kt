package com.example.relaxapp.views.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.relaxapp.R



@Composable
fun OnboardingView(onFinish: () -> Unit) {
    val pages = listOf(
        OnboardingPage(
            "¡Mejora tu Salud Mental!",
            "Explora herramientas para mejorar tu bienestar emocional",
            R.drawable.dummy, // Imagen de fondo para esta página
            "Siguiente"
        ),
        OnboardingPage(
            "Chatea con Profesionales",
            "Conéctate con expertos en salud mental",
            R.drawable.dummy,
            "Siguiente"
        ),
        OnboardingPage(
            "Encuentra la Calma",
            "Relájate con ejercicios guiados",
            R.drawable.dummy,
            "¡Comencemos!"
        )
    )
    val pageState = remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Image(
            painter = painterResource(id = pages[pageState.value].imageRes),
            contentDescription = pages[pageState.value].title,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f) // Para ocupar el espacio disponible
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = pages[pageState.value].title,
                color = Color.Black,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = pages[pageState.value].description,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 32.dp)
            )

            Button(
                onClick = {
                    if (pageState.value < pages.size - 1) {
                        pageState.value += 1
                    } else {
                        onFinish()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .padding(bottom = 32.dp),
               // colors = ButtonDefaults.buttonColors(
                  //  backgroundColor = Color(0xFFFFC107), // Color personalizado
                    //contentColor = Color.White
             //   ),
                shape = MaterialTheme.shapes.medium // Botones redondeados
            ) {
                Text(
                    text = pages[pageState.value].buttonText,
                )
            }
        }
    }
}

