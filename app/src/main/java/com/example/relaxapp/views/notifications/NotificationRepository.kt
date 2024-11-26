package com.example.relaxapp.views.notifications

import com.example.relaxapp.views.RetrofitClientInstance
import com.example.relaxapp.views.profesionales.ProfessionalRepository

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
