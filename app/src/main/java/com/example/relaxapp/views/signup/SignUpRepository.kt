package com.example.relaxapp.views.signup

import com.example.relaxapp.views.RetrofitClientInstance

object SignUpRepository {
    private val apiService = RetrofitClientInstance.apiService

    suspend fun createUser(
        name: String,
        lastname: String,
        email: String,
        password: String,
        phone: String,
        country: String
    ) {
        val signUpUser = SignUpUser(name, lastname, email, password, phone, country)
        apiService.SignUp(signUpUser)
    }
}