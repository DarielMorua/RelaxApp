package com.example.relaxapp.views.chat

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.viewModelScope
import com.example.relaxapp.TokenManager
import com.example.relaxapp.views.profesionales.ProfessionalRepository
import kotlinx.coroutines.launch


class ChatViewModel(private val repository: ProfessionalRepository,  private val context: Context) : ViewModel() {

    val messages = MutableStateFlow<List<Message>>(emptyList())
    private val tokenManager = TokenManager(context)

    // Función para cargar los mensajes de un chat
    fun loadMessages(chatId: String, onComplete: () -> Unit) {
        viewModelScope.launch {
            try {
                val token = tokenManager.getToken()
                if (!token.isNullOrEmpty()) {
                    val response = repository.getMessages(
                        "Bearer $token",
                        chatId
                    ) // Llamada correcta a la función
                    messages.value = response
                }
            } catch (exception: Exception) {
                Log.e("ChatViewModel", "Error al cargar los mensajes: ${exception.message}")
            } finally {
                onComplete()
            }
        }
    }

    // Función para enviar un mensaje
    fun sendMessage(authToken: String, chatId: String, senderId: String, senderModel: String, content: String) {
        viewModelScope.launch {
            try {
                // Llamada al repositorio para enviar el mensaje
                repository.sendMessage("Bearer $authToken", chatId, senderId, senderModel, content)
                // Si la respuesta fue exitosa, actualizamos los mensajes o hacemos cualquier otra acción necesaria
            } catch (e: Exception) {
                Log.e("ChatViewModel", "Error al enviar mensaje: ${e.message}")
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

