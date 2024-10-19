package com.example.relaxapp.views.chat

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.relaxapp.R
import com.example.relaxapp.bottomnavigationbar.BottomNavigationBar


@Composable
fun ChatView(navController: NavController) {
    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White)
        ) {
            // encabezado
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_logo),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .size(80.dp)
                        .align(Alignment.CenterStart)
                )
                Text(
                    text = "CHAT Profesional",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Center).padding(30.dp),
                    fontSize = 20.sp
                )

            }

            // donde van los mnensajes
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                //"burbuja" de mensaje, se le pasan los datos de mensaje, nombre del que manda el mensaje y su foto
                ChatBubble(
                    message = "¡Hola Amigazo! ¿Cómo sigues?",
                    senderName = "Dr. Simi",
                    avatar = R.drawable.dummy
                )
                ChatBubble(
                    message = "¡Hola Amigazo! ¿Cómo sigues?",
                    senderName = "Dr. Simi",
                    avatar = R.drawable.dummy
                )
            }

            // textfield donde se escribe el mensaje
            OutlinedTextField(
                value = "",
                onValueChange = { /* Handle text input */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text("Escribe un mensaje...") },
                shape = RoundedCornerShape(24.dp),
                trailingIcon = {
                    IconButton(onClick = { /* Send message */ }) {
                        Icon(
                            imageVector = Icons.Filled.Send,
                            contentDescription = "Enviar",
                            tint = Color(0xFF0288D1)
                        )
                    }
                }
            )
        }
    }
}

@Composable
fun ChatBubble(message: String, senderName: String, avatar: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(8.dp)
    ) {
        Image(
            painter = painterResource(avatar),
            contentDescription = "Avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .border(2.dp, Color.LightGray, CircleShape)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Box(
            modifier = Modifier
                .background(Color(0xFFE3F2FD), shape = RoundedCornerShape(12.dp))
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Column {
                Text(text = senderName, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = message, fontSize = 14.sp)
            }
        }
    }
}

