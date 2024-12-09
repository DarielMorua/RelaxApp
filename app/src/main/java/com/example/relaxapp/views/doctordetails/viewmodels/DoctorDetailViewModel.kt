package com.example.relaxapp.views.doctordetails.viewmodels

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.relaxapp.TokenManager
import com.example.relaxapp.views.chat.CreateChatRequest
import com.example.relaxapp.views.profesionales.Professional
import com.example.relaxapp.views.profesionales.ProfessionalRepository
import com.example.relaxapp.views.profesionales.Review
import kotlinx.coroutines.launch

class DoctorDetailViewModel(
    private val repository: ProfessionalRepository,
    private val context: Context
) : ViewModel() {

    var professional by mutableStateOf<Professional?>(null)
        private set

    var reviews by mutableStateOf<List<Review>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var state by mutableStateOf(0)
        private set

    private val tokenManager = TokenManager(context)

    fun loadProfessionalDetails(professionalId: String) {
        isLoading = true
        viewModelScope.launch {
            try {
                val token = tokenManager.getToken()
                if (!token.isNullOrEmpty()) {
                    // Cargar los detalles del profesional
                    val professionalResponse = repository.getProfessionalDetails("Bearer $token", professionalId)
                    professional = professionalResponse

                    // Cargar las reseñas del profesional
                    val reviewsResponse = repository.getReviews("Bearer $token", professionalId)
                    reviews = reviewsResponse

                    Log.d("DoctorDetailViewModel", "Professional cargado: $professional")
                    Log.d("DoctorDetailViewModel", "Reviews cargadas: $reviews")
                } else {
                    Log.e("DoctorDetailViewModel", "Token no disponible")
                }
            } catch (exception: Exception) {
                Log.e("DoctorDetailViewModel", "Error al cargar datos: ${exception.message}")
                professional = null
                reviews = emptyList()
            } finally {
                isLoading = false
            }
        }
    }

    // Función para crear el chat
    fun createChat(professionalId: String, onChatCreated: (chatId: String) -> Unit) {
        viewModelScope.launch {
            isLoading = true
            try {
                val token = tokenManager.getToken() // Obtener el token de autenticación
                if (!token.isNullOrEmpty()) {
                    // Asumiendo que tienes el userId disponible en alguna parte
                    val userId = tokenManager.getUserId()
                    val request = CreateChatRequest(userId!!, professionalId)

                    // Llamada al repositorio para crear el chat
                    val response = repository.createChat("Bearer $token", request)

                    // Aquí recibimos directamente el objeto ChatResponse desde el repositorio
                    val chatId = response.chat?._id
                    if (!chatId.isNullOrEmpty()) {
                        onChatCreated(chatId) // Notificar que el chat ha sido creado
                    } else {
                        Log.e("DoctorDetailViewModel", "Chat creado pero sin chatId")
                    }
                } else {
                    Log.e("DoctorDetailViewModel", "Token no disponible")
                }
            } catch (exception: Exception) {
                Log.e("DoctorDetailViewModel", "Error al crear el chat: ${exception.message}")
            } finally {
                isLoading = false
            }
        }
    }

    fun sendMessage(
        chatId: String,
        message: String,
        senderId: String,
        senderModel: String,
    ) {
        viewModelScope.launch {
            try {
                val token = tokenManager.getToken()
                if (!token.isNullOrEmpty()) {
                    // Llamar a la función del repositorio para enviar el mensaje
                    repository.sendMessage("Bearer $token", chatId, message, senderId, senderModel)
                } else {
                    Log.e("DoctorDetailViewModel", "Token no disponible")
                }
            } catch (exception: Exception) {
                Log.e("DoctorDetailViewModel", "Error al enviar el mensaje: ${exception.message}")
            }
        }
    }

    class DoctorDetailViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DoctorDetailViewModel::class.java)) {
                return DoctorDetailViewModel(ProfessionalRepository, context) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}