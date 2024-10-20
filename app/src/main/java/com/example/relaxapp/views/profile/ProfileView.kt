package com.example.relaxapp.views.profile

import android.widget.Button
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.relaxapp.R
import com.example.relaxapp.bottomnavigationbar.BottomNavigationBar
import com.example.relaxapp.bottomnavigationbar.Routes

val MintGreen = Color(26, 204, 181, 255) // #B9DAD4
val CarolinaBlue = Color(139, 172, 205) // #8BACCD
val CambridgeBlue = Color(144, 181, 179) // #90B5B3
val AshGray = Color(177, 188, 177) // #B1BCB1
val BlueGray = Color(113, 156, 195) // #719CC3

val Nunito = FontFamily(
    Font(R.font.nunito_regular, FontWeight.Normal),
    Font(R.font.nunito_bold, FontWeight.Bold)
)

val Oxygen = FontFamily(
    Font(R.font.oxygen_regular, FontWeight.Normal)
)

val CustomTypography = Typography(
    // Título
    headlineLarge = TextStyle(
        fontFamily = com.example.relaxapp.views.login.Nunito,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
        color = com.example.relaxapp.views.login.CarolinaBlue
    ),
    // Botón
    headlineMedium = TextStyle(
        fontFamily = com.example.relaxapp.views.login.Nunito,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        color = Color.White
    ),
    // Cuerpo
    headlineSmall = TextStyle(
        fontFamily = com.example.relaxapp.views.login.Oxygen,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        color = com.example.relaxapp.views.login.CambridgeBlue
    ),
    // Caption
    labelSmall = TextStyle(
        fontFamily = com.example.relaxapp.views.login.Oxygen,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        color = com.example.relaxapp.views.login.AshGray
    )
)

@Composable
fun ProfileView(navController: NavController) {
    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) { innerPadding ->

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White)
        ) {
            // Top section
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
                    modifier = Modifier.size(50.dp)
                        .clickable {
                            navController.popBackStack()
                        }
                )
                Text(
                    text = stringResource(id = R.string.profile),
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color(26, 204, 181, 255),
                    fontSize = 50.sp,
                    modifier = Modifier.weight(1f).padding(horizontal = 16.dp),
                    textAlign = TextAlign.Center
                )
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Profile Icon",
                    tint = Color.Black,
                    modifier = Modifier.size(50.dp)
                )
            }

            Card(
                shape = RoundedCornerShape(32.dp),
                colors = CardDefaults.cardColors(containerColor = Color(26, 204, 181, 255)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Card(
                        shape = RoundedCornerShape(32.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(232, 246, 246, 255)
                        ),
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .padding(8.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = stringResource(id = R.string.greeting),
                                style = MaterialTheme.typography.headlineSmall,
                                color = Color.Black
                            )
                            Text(
                                text = "Venustiano Carranza",
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        }
                    }
                }
            }

            // Buttons section
            Column {
                Button(
                    onClick = { navController.navigate(Routes.PersonalDataView) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .height(50.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(Color(145, 255, 218, 255))
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = "Profile Icon",
                            tint = Color.Black
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = stringResource(id = R.string.personaldata),
                            color = Color.Black,
                            fontSize = 18.sp
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = { navController.navigate(Routes.FavoriteView) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .height(50.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(Color(145, 255, 218, 255))
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = "Favorite Icon",
                            tint = Color.Black
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = stringResource(id = R.string.favoritedr),
                            color = Color.Black,
                            fontSize = 18.sp
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))


            }

            Spacer(modifier = Modifier.weight(1f))

            // Ayuda
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Button(
                    onClick = { navController.navigate(Routes.HelpView) },
                    modifier = Modifier
                        //.fillMaxWidth()
                        .padding(start = 8.dp)
                        .height(50.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(Color.Transparent)
                ) {
                    Text(
                        text = stringResource(id = R.string.need_help),
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.Black,
                        textDecoration = TextDecoration.Underline,
                        fontWeight = FontWeight.Bold,
                        //modifier = Modifier.align(Alignment.Start),
                        fontSize = 16.sp
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = { navController.navigate(Routes.FAQView) },
                    modifier = Modifier
                        //.fillMaxWidth()
                        .padding(start = 8.dp)
                        .height(50.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(Color.Transparent)
                ) {
                    Text(
                        text = stringResource(id = R.string.faqs),
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.Black,
                        textDecoration = TextDecoration.Underline,
                        fontWeight = FontWeight.Bold,
                        //modifier = Modifier.align(Alignment.Start),
                        fontSize = 16.sp
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = { navController.navigate(Routes.LoginView) },
                    modifier = Modifier
                        //.fillMaxWidth()
                        .padding(start = 8.dp)
                        .height(50.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(Color.Transparent)
                ) {
                    Text(
                        text = stringResource(id = R.string.logout),
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color(255, 116, 104, 255),
                        fontWeight = FontWeight.ExtraBold,
                        //modifier = Modifier.align(Alignment.Start),
                        fontSize = 16.sp
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}



