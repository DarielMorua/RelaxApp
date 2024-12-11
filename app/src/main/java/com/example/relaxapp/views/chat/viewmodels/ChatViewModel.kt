package com.example.relaxapp.views.chat.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.viewModelScope
import com.example.relaxapp.TokenManager
import com.example.relaxapp.views.chat.models.Message
import com.example.relaxapp.views.profesionales.models.ProfessionalRepository
import kotlinx.coroutines.launch


class ChatViewModel(private val repository: ProfessionalRepository, private val context: Context) : ViewModel() {

    private val _messages = MutableStateFlow<List<Message>>(emptyList())
    val messages: StateFlow<List<Message>> = _messages
    private val tokenManager = TokenManager(context)

    fun loadMessages(chatId: String, onComplete: () -> Unit) {
        viewModelScope.launch {
            try {
                val token = tokenManager.getToken()
                if (!token.isNullOrEmpty()) {
                    val response = repository.getMessages(
                        "Bearer $token",
                        chatId
                    )
                    _messages.value = response
                }
            } catch (exception: Exception) {
                Log.e("ChatViewModel", "Error al cargar los mensajes: ${exception.message}")
            } finally {
                onComplete()
            }
        }
    }

    fun sendMessage(authToken: String, chatId: String, senderId: String, senderModel: String, content: String) {
        viewModelScope.launch {
            try {
                val newMessage = Message(
                    chatId = chatId,
                    senderId = senderId,
                    senderModel = senderModel,
                    content = content
                )
                repository.sendMessage("Bearer $authToken", chatId, senderId, senderModel, content)
                _messages.value = _messages.value + newMessage
            } catch (e: Exception) {
                Log.e("ChatViewModel", "Error al enviar mensaje: ${e.message}", e)
            }
        }
    }

    class ChatViewModelFactory(
        private val context: Context
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ChatViewModel::class.java)) {
                return ChatViewModel(ProfessionalRepository, context) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

