package com.example.relaxapp.views.profesionales.conexionChat

import com.example.relaxapp.views.chat.Message
import com.google.gson.annotations.SerializedName

data class ChatProfesional(
    val id: String?,
    val user: User,
    val professional: Professional,
    val messages: List<Message>,
    val version: Int
)