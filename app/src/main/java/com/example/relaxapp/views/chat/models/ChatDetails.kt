package com.example.relaxapp.views.chat.models

data class ChatDetails(
    val user: String?,
    val professional: String?,
    val _id: String,
    val messages: List<Message>,
    val __v: Int
)