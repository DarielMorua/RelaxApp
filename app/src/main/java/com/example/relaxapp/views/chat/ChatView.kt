package com.example.relaxapp.views.chat

import android.annotation.SuppressLint
import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.relaxapp.R
import com.example.relaxapp.TokenManager
import com.example.relaxapp.bottomnavigationbar.BottomNavigationBar
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState


@Composable
fun ChatView(navController: NavController, chatId: String, userRole: String) {
    val context = LocalContext.current // Utiliza el contexto local
    var messageText by remember { mutableStateOf("") }
    val messages = remember { mutableStateOf<List<Message>>(emptyList()) }
    val isSendingMessage = remember { mutableStateOf(false) }
    val refreshing = remember { mutableStateOf(false) }
    val tokenManager = TokenManager(context)

    // Obtén el ViewModel usando la ViewModelFactory

    val viewModel: ChatViewModel = viewModel(factory = ChatViewModel.ChatViewModelFactory(context))

    fun sendMessage() {
        if (messageText.isNotBlank()) {
            isSendingMessage.value = true
            val senderModel = if (userRole == "Professional") "Professional" else "User"
            val senderId = tokenManager.getUserId() // El ID del usuario
            val authToken = tokenManager.getToken() // Obtén el token de autenticación

            // Llamar al ViewModel para enviar el mensaje
            viewModel.sendMessage(authToken!!, chatId, senderId!!, senderModel, messageText)
        }
    }

    fun refreshMessages() {
        refreshing.value = true
        viewModel.loadMessages(chatId) {
            refreshing.value = false
        }
    }

    LaunchedEffect(viewModel.messages) {
        viewModel.messages.collect { newMessages ->
            messages.value = newMessages
            isSendingMessage.value = false
        }
    }

    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) { innerPadding ->
        val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = refreshing.value)

        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = { refreshMessages() }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(Color.White)
            ) {
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
                        text = stringResource(R.string.chatprofessional),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.Center).padding(30.dp),
                        fontSize = 20.sp
                    )
                }

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    messages.value.forEach { message ->
                        ChatBubble(
                            message = message.content,
                            avatar = R.drawable.dummy // Usar el avatar del remitente
                        )
                    }
                }

                OutlinedTextField(
                    value = messageText,
                    onValueChange = { messageText = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    label = { Text(text = stringResource(id = R.string.writemessage)) },
                    shape = RoundedCornerShape(24.dp),
                    trailingIcon = {
                        IconButton(onClick = { sendMessage() }) {
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
}

@Composable
fun ChatBubble(message: String, avatar: Int) {
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
                Text(text = "Doctor", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = message, fontSize = 14.sp)
            }
        }
    }
}
