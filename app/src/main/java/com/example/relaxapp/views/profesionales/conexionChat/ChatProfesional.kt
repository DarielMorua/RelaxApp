package com.example.relaxapp.views.profesionales.conexionChat

import com.example.relaxapp.views.chat.models.Message

data class ChatProfesional(
    val id: String?,
    val user: User,
    val professional: Professional,
    val messages: List<Message>,
    val version: Int
)