package com.example.relaxapp.views.exercises

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.relaxapp.R
import com.example.relaxapp.bottomnavigationbar.BottomNavigationBar
import com.example.relaxapp.bottomnavigationbar.Routes

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
fun ExerciseView(navController: NavController) {
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
            // Sección superior
            item {
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.exercises),
                        style = MaterialTheme.typography.headlineLarge,
                        color = Color(26, 204, 181, 255),
                        fontSize = 50.sp,
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 16.dp),
                        textAlign = TextAlign.Center
                    )
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Profile Icon",
                        tint = Color.Black,
                        modifier = Modifier
                            .size(50.dp)
                            .clickable {
                                navController.navigate(Routes.ProfileView)
                            }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
            }

            // Ejercicios de respiración
            item {
                Text(
                    text = stringResource(id = R.string.exercisesBre),
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 16.dp),
                    fontSize = 25.sp
                )
            }
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    imagenesRespiracion.forEach { imageResId ->
                        Button(
                            onClick = {
                                // Manejo del clic en la imagen
                            },
                            shape = RoundedCornerShape(percent = 45),
                            modifier = Modifier.size(250.dp),
                            colors = ButtonDefaults.buttonColors(Color(0, 0, 0, 0))
                        ) {
                            Image(
                                painter = painterResource(id = imageResId),
                                contentDescription = "Imagen respiracion",
                                modifier = Modifier.fillMaxSize() // Llenar el espacio dentro del botón
                            )
                        }
                    }
                }
            }

            // Ejercicios de meditación
            item {
                Text(
                    text = stringResource(id = R.string.exercisesMed),
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 16.dp),
                    fontSize = 25.sp
                )
            }
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    imagenesMeditacion.forEach { imageResId ->
                        Button(
                            onClick = {
                                // Manejo del clic en la imagen
                            },
                            shape = RoundedCornerShape(percent = 45),
                            modifier = Modifier.size(250.dp),
                            colors = ButtonDefaults.buttonColors(Color(0, 0, 0, 0))
                        ) {
                            Image(
                                painter = painterResource(id = imageResId),
                                contentDescription = "Imagen meditación",
                                modifier = Modifier.fillMaxSize() // Llenar el espacio dentro del botón
                            )
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Ejercicio de Estiramiento",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 16.dp),
                    fontSize = 25.sp
                )
            }
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    imagenesMeditacion.forEach { imageResId ->
                        Button(
                            onClick = {
                                // Manejo del clic en la imagen
                            },
                            shape = RoundedCornerShape(percent = 45),
                            modifier = Modifier.size(250.dp),
                            colors = ButtonDefaults.buttonColors(Color(0, 0, 0, 0))
                        ) {
                            Image(
                                painter = painterResource(id = imageResId),
                                contentDescription = "Imagen relajación",
                                modifier = Modifier.fillMaxSize() // Llenar el espacio dentro del botón
                            )
                        }
                    }
                }
            }

            // Ejemplo para una nueva sección:
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Ejercicio de relajación guiada con música o sonidos",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 16.dp),
                    fontSize = 25.sp
                )
            }
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    imagenesMeditacion.forEach { imageResId ->
                        Button(
                            onClick = {
                                // Manejo del clic en la imagen
                            },
                            shape = RoundedCornerShape(percent = 45),
                            modifier = Modifier.size(250.dp),
                            colors = ButtonDefaults.buttonColors(Color(0, 0, 0, 0))
                        ) {
                            Image(
                                painter = painterResource(id = imageResId),
                                contentDescription = "Imagen relajación",
                                modifier = Modifier.fillMaxSize() // Llenar el espacio dentro del botón
                            )
                        }
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Ejercicio para reduciar la ansiedad",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 16.dp),
                    fontSize = 25.sp
                )
            }
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    imagenesMeditacion.forEach { imageResId ->
                        Button(
                            onClick = {
                                // Manejo del clic en la imagen
                            },
                            shape = RoundedCornerShape(percent = 45),
                            modifier = Modifier.size(250.dp),
                            colors = ButtonDefaults.buttonColors(Color(0, 0, 0, 0))
                        ) {
                            Image(
                                painter = painterResource(id = imageResId),
                                contentDescription = "Imagen relajación",
                                modifier = Modifier.fillMaxSize() // Llenar el espacio dentro del botón
                            )
                        }
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Ejercicios para relajar la mente",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 16.dp),
                    fontSize = 25.sp
                )
            }
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    imagenesMeditacion.forEach { imageResId ->
                        Button(
                            onClick = {
                                // Manejo del clic en la imagen
                            },
                            shape = RoundedCornerShape(percent = 45),
                            modifier = Modifier.size(250.dp),
                            colors = ButtonDefaults.buttonColors(Color(0, 0, 0, 0))
                        ) {
                            Image(
                                painter = painterResource(id = imageResId),
                                contentDescription = "Imagen relajación",
                                modifier = Modifier.fillMaxSize() // Llenar el espacio dentro del botón
                            )
                        }
                    }
                }
            }
        }
    }
}

