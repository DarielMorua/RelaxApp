package com.example.relaxapp.views.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
        test1dataclass(
            "¡Mejora tu Salud Mental!",
            "Explora herramientas para mejorar tu bienestar emocional",
            R.drawable.dummy,
            "Siguiente"
        ),

        test1dataclass(
            "Chatea con Profesionales",
            "Conéctate con expertos en salud mental",
            R.drawable.dummy,
            "Siguiente"
        ),
        test1dataclass(
            "Encuentra la Calma",
            "Relájate con ejercicios guiados",
            R.drawable.dummy,
            "¡Comencemos!"
        )
    )
    val pageState = remember { mutableStateOf(0) }


}

