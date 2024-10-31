package com.example.relaxapp.views.login

import com.example.relaxapp.views.RetrofitClientInstance

object UserRepository {

    val apiService = RetrofitClientInstance.apiService

    suspend fun doLogin(user: User): LoginResponse {
        return apiService.login(user)
    }
}

