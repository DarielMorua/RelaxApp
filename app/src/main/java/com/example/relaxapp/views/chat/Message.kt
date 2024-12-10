package com.example.relaxapp.views.chat

data class Message(
    val _id: String? = null,   // Puedes hacer esto opcional
    val chatId: String,
    val senderId: String,
    val senderModel: String,
    val content: String,
    val timestamp: String? = null // Esto podría ser opcional dependiendo de la API
)