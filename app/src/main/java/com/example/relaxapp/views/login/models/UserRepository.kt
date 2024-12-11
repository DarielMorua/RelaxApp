package com.example.relaxapp.views.login.models

import com.example.relaxapp.views.RetrofitClientInstance
import com.example.relaxapp.views.profile.models.UserProfile
import com.example.relaxapp.views.profile.models.UserRequest
import com.example.relaxapp.views.profile.images.ImagesData
import retrofit2.Response

object UserRepository {

    val apiService = RetrofitClientInstance.apiService

    suspend fun doLogin(user: User): LoginResponse {
        return apiService.login(user)
    }

    suspend fun getUserDetails(authHeader: String, userId: String): UserResponse {
        val request = UserRequest(userId)
        return apiService.getUserDetails(authHeader, request)
    }

    suspend fun updateUser(authHeader: String, user: UserProfile): Response<Unit> {
        return apiService.updateUser(authHeader, user)
    }
    suspend fun getImages(authHeader: String): List<ImagesData> {
        return apiService.getImages(authHeader)
    }
}

