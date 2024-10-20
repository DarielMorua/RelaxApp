package com.example.relaxapp.views.mainmenu

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
import androidx.compose.foundation.layout.width
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
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.relaxapp.R
import com.example.relaxapp.bottomnavigationbar.BottomNavigationBar
import com.example.relaxapp.bottomnavigationbar.Routes
import com.example.relaxapp.views.signup.AshGray
import com.example.relaxapp.views.signup.CambridgeBlue
import com.example.relaxapp.views.signup.CarolinaBlue
import com.example.relaxapp.views.signup.Nunito
import com.example.relaxapp.views.signup.Oxygen


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
    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) { innerPadding ->

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
                    text =  stringResource(id = R.string.app_name),
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
                        .clickable { navController.navigate(Routes.ProfileView) }
                )
            }

            // Emociones
            Text(
                text = stringResource(id = R.string.how_are_you),
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 16.dp),
                fontSize = 25.sp
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                emojis.forEach { emoji ->
                    Button(
                        onClick = {
                            viewModel.onEmojiSelected(emoji)
                            navController.navigate(Routes.CalendarView)
                        },
                        shape = RoundedCornerShape(percent = 50),
                        modifier = Modifier.size(50.dp),
                        colors = ButtonDefaults.buttonColors(Color(0, 0, 0, 0))
                    ) {
                        Text(
                            text = emoji,
                            fontSize = 24.sp,
                            modifier = Modifier.align(Alignment.CenterVertically),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column {
                Text(
                    text = stringResource(id = R.string.recommendedExercises),
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 16.dp),
                    fontSize = 25.sp
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    imagenes.forEach { imageResId ->
                        Button(
                            onClick = {
                                viewModel.onImageSelected(imageResId)
                                navController.navigate(Routes.ExerciseView)
                            },
                            shape = RoundedCornerShape(percent = 45),
                            modifier = Modifier.size(250.dp),
                            colors = ButtonDefaults.buttonColors(Color(0, 0, 0, 0))
                        ) {
                            Image(
                                painter = painterResource(id = imageResId),
                                contentDescription = "Imagen recomendada",
                                modifier = Modifier.fillMaxSize()  // Llenar el espacio dentro del botón
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

