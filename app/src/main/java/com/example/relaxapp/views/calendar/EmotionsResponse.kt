package com.example.relaxapp.views.calendar


data class EmotionResponse(
    val _id: String,
    val emotion: String,
    val date: String, // El formato de fecha será una cadena ISO 8601 (ej. "2024-11-22T19:02:56.000Z")
    val userDetails: UserDetails
)