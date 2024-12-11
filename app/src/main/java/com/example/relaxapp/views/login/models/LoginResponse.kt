package com.example.relaxapp.views.login.models

data class LoginResponse(
    var message: String,
    val token: String,
    val user: UserResponse,
    var isSuccess: Boolean
)
