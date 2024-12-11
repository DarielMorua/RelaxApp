package com.example.relaxapp.views.login.models

data class UserResponse(
    val id: String,
    val name: String,
    val lastname: String,
    val email: String,
    val phone: String,
    val country: String,
    val rol: String,
    val photo: String
)