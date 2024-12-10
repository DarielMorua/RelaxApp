package com.example.relaxapp.views.calendar

import java.util.Date

data class EmotionRecord(
    val emotion: String,
    val date: Date,
    val userDetails: UserDetails
)
