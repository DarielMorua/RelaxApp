package com.example.relaxapp.views.notifications

import com.example.relaxapp.views.RetrofitClientInstance

object NotificationRepository {
    private val apiService = RetrofitClientInstance.apiService

    suspend fun getNotifications(authHeader: String): List<NotificationResponse> {
        return apiService.getNotifications(authHeader)
    }
}
