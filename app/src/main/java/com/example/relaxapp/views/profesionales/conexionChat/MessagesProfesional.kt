package com.example.relaxapp.views.profesionales.conexionChat

data class MessagesProfesional(
    val _id: String? = null,   // Puedes hacer esto opcional
    val chatId: String,
    val senderId: String,
    val senderModel: String,
    val content: String,
    val timestamp: String? = null
)