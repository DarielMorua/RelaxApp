package com.example.relaxapp.views.chat.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import com.example.relaxapp.views.chat.viewmodels.ChatViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun ChatView(navController: NavController, chatId: String, userRole: String) {
    val context = LocalContext.current
    var messageText by remember { mutableStateOf("") }
    val viewModel: ChatViewModel = viewModel(factory = ChatViewModel.ChatViewModelFactory(context))
    val messages by viewModel.messages.collectAsState()
    val refreshing = remember { mutableStateOf(false) }

    val tokenManager = TokenManager(context)

    LaunchedEffect(chatId) {
        viewModel.loadMessages(chatId) {}
    }

    fun sendMessage() {
        if (messageText.isNotBlank()) {
            val authToken = tokenManager.getToken()!!
            val senderId = tokenManager.getUserId()!!
            val senderModel = if (userRole == "Professional") "Professional" else "User"
            viewModel.sendMessage(authToken, chatId, senderId, senderModel, messageText)
            messageText = ""
        }
    }

    fun refreshMessages() {
        refreshing.value = true
        viewModel.loadMessages(chatId) { refreshing.value = false }
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
                    messages.forEach { message ->
                        ChatBubble(
                            message = message.content,
                            avatar = R.drawable.dummy ,
                            senderModel = message.senderModel
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
fun ChatBubble(message: String, avatar: Int, senderModel: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
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
                Text(text = senderModel, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = message, fontSize = 14.sp)
            }
        }
    }
}
