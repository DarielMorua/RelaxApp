package com.example.relaxapp.views.chat

data class ChatDetails(
    val user: String?,
    val professional: String?,
    val _id: String,
    val messages: List<Message>,
    val __v: Int
)