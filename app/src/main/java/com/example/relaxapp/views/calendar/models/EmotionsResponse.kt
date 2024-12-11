package com.example.relaxapp.views.calendar.models


data class EmotionResponse(
    val _id: String,
    val emotion: String,
    val date: String,
    val userDetails: UserDetails
)