package com.example.relaxapp.views.signup.models

import com.example.relaxapp.views.RetrofitClientInstance

object SignUpRepository {
    private val apiService = RetrofitClientInstance.apiService

    suspend fun createUser(
        name: String,
        lastname: String,
        email: String,
        password: String,
        phone: String,
        country: String,
        rol: String
    ) {
        val signUpUser = SignUpUser(name, lastname, email, password, phone, country,rol)
        apiService.SignUp(signUpUser)
    }
}