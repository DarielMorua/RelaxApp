package com.example.relaxapp.views.login

import com.example.relaxapp.views.RetrofitClientInstance
import com.example.relaxapp.views.profile.UserRequest

object UserRepository {

    val apiService = RetrofitClientInstance.apiService

    suspend fun doLogin(user: User): LoginResponse {
        return apiService.login(user)
    }

    suspend fun getUserDetails(authHeader: String, userId: String): UserResponse {
        val request = UserRequest(userId) // Construir el cuerpo de la solicitud
        return apiService.getUserDetails(authHeader, request)
    }

}

