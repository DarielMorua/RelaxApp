package com.example.relaxapp.views.notifications.models

import com.example.relaxapp.views.RetrofitClientInstance

object NotificationRepository {
    private val apiService = RetrofitClientInstance.apiService

    suspend fun getNotifications(authHeader: String): List<NotificationResponse> {
        return apiService.getNotifications(authHeader)
    }

    suspend fun deleteNotification(authHeader: String, notificationId: String) {
        val request = deleteNotification(notificationId)
        apiService.deleteNotification(authHeader, request)
    }
}
