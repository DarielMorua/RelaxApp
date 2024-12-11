package com.example.relaxapp.views.notifications.models

import com.google.gson.annotations.SerializedName

data class NotificationResponse(@SerializedName("_id") val id: String,
    val title: String,
    val message: String,
    val date: String,
    val seen: Boolean
)
