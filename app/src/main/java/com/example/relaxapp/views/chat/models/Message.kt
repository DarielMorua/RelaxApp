package com.example.relaxapp.views.chat.models

data class Message(
    val _id: String? = null,
    val chatId: String,
    val senderId: String,
    val senderModel: String,
    val content: String,
    val timestamp: String? = null
)