package com.example.relaxapp.views.notifications.models

data class Notification(
    val id: String,
    val title: String,
    val message: String,
    val date: String,
    val seen: Boolean
)
