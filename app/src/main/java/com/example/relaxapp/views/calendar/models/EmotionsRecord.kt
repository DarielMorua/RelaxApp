package com.example.relaxapp.views.calendar.models

import java.util.Date

data class EmotionRecord(
    val emotion: String,
    val date: Date,
    val userDetails: UserDetails
)
