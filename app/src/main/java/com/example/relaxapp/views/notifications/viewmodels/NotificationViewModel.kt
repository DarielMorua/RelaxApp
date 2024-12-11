package com.example.relaxapp.views.notifications.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.relaxapp.TokenManager
import com.example.relaxapp.views.notifications.models.NotificationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class NotificationViewModel(val notificationRepository: NotificationRepository, private val context: Context) : ViewModel() {
    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _notifications =
        MutableStateFlow<List<com.example.relaxapp.views.notifications.models.Notification>>(emptyList())
    val notifications: StateFlow<List<com.example.relaxapp.views.notifications.models.Notification>> =
        _notifications
    private val tokenManager = TokenManager(context)

    init {
        loadNotifications()
    }

    private fun loadNotifications() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val token = tokenManager.getToken()
                val response = withContext(Dispatchers.IO) {
                    notificationRepository.getNotifications("Bearer $token")
                }
                _notifications.value = response.map {
                    com.example.relaxapp.views.notifications.models.Notification(
                        id = it.id,
                        title = it.title,
                        message = it.message,
                        date = it.date,
                        seen = it.seen
                    )
                }
            } catch (e: Exception) {
                Log.e("NotificationViewModel", "Error fetching notifications: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun deleteNotification(notificationId: String) {
        viewModelScope.launch {
            try {
                val token = tokenManager.getToken()
                if (token != null) {
                    notificationRepository.deleteNotification("Bearer $token", notificationId)
                    _notifications.value = _notifications.value.filterNot { it.id == notificationId }
                }
            } catch (e: Exception) {
                Log.e("NotificationViewModel", "Error al eliminar notificación: ${e.message}")
            }
        }
    }

    fun refreshNotifications() {
        _isLoading.value = true
        loadNotifications()
    }
}

class NotificationViewModelFactory(
    private val notificationRepository: NotificationRepository,
    private val context: Context
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotificationViewModel::class.java)) {
            return NotificationViewModel(notificationRepository, context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}