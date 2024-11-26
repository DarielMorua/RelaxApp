package com.example.relaxapp.views.notifications

import android.app.Notification
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.relaxapp.TokenManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.osmdroid.views.MapViewRepository


class NotificationViewModel (val notificationRepository: NotificationRepository,private val context: Context): ViewModel() {
    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _notifications =
        MutableStateFlow<List<com.example.relaxapp.views.notifications.Notification>>(emptyList())
    val notifications: StateFlow<List<com.example.relaxapp.views.notifications.Notification>> =
        _notifications
    private val tokenManager = TokenManager(context)

    init {
        loadNotifications()
    }

    private fun loadNotifications() {
        viewModelScope.launch {
            try {
                val token = tokenManager.getToken()
                val response = withContext(Dispatchers.IO) {
                    notificationRepository.getNotifications("Bearer $token")
                }
                _notifications.value = response.map {
                    Notification(
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
                val token = tokenManager.getToken() // Recupera el token
                if (token != null) {
                    notificationRepository.deleteNotification("Bearer $token", notificationId)
                    _notifications.value = _notifications.value.filterNot { it.id == notificationId }
                }
            } catch (e: Exception) {
                Log.e("NotificationViewModel", "Error al eliminar notificación: ${e.message}")
            }
        }
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