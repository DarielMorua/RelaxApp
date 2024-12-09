package com.example.relaxapp.views.chat

data class ChatDetails(
    val _id: String,
    val user: String?,
    val professional: String?,
    val messages: List<Message>,
    val __v: Int
)