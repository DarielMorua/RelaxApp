package com.example.relaxapp.views.chat

data class ChatDetails(
    val _id: String,
    val user: User?,
    val professional: Professional,
    val messages: List<Message>,
    val __v: Int
)