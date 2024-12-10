package com.example.relaxapp.views.profesionales

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.relaxapp.TokenManager
import com.example.relaxapp.views.chat.ChatDetailsMessages
import com.example.relaxapp.views.profesionales.conexionChat.ChatProfesional
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfessionalViewModel(
    private val professionalRepository: ProfessionalRepository,
    private val context: Context
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _professionals = MutableStateFlow<List<Professional>>(emptyList())
    val professionals: StateFlow<List<Professional>> = _professionals

    private val _chats = MutableStateFlow<List<ChatDetailsMessages>>(emptyList())
    val chats: StateFlow<List<ChatDetailsMessages>> = _chats

    private val _rol = MutableStateFlow<String>("")
    val rol: StateFlow<String> = _rol

    private val tokenManager = TokenManager(context)

    init {
        loadProfessionals()
        getRole()
    }

    fun getRole() {
        _rol.value = tokenManager.getRole() ?: ""
    }

    fun loadProfessionals() {
        viewModelScope.launch {
            try {
                val token = tokenManager.getToken()
                if (!token.isNullOrEmpty()) {
                    val response = withContext(Dispatchers.IO) {
                        professionalRepository.getProfessionals("Bearer $token")
                    }
                    _professionals.value = response
                } else {
                    Log.e("ProfessionalViewModel", "Token no disponible")
                }
            } catch (e: Exception) {
                Log.e("ProfessionalViewModel", "Error fetching professionals: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadProfessionalChats() {
        val userId = tokenManager.getUserId() ?: ""
        Log.d("ProfessionalViewModel", "Cargando chats para el usuario con ID: $userId")
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val token = tokenManager.getToken()!!
                val chats = professionalRepository.getChatsForProfessional(token, userId)
                if (chats.isNotEmpty()) {
                    Log.d("ProfessionalViewModel", "Chats obtenidos del backend: ${chats.size}")
                    _chats.value = chats // Actualiza solo si hay datos válidos
                    Log.d("ProfessionalViewModel", "Estado de _chats actualizado: ${_chats.value}")
                } else {
                    Log.d("ProfessionalViewModel", "No hay chats disponibles.")
                }
            } catch (exception: Exception) {
                Log.e("ProfessionalViewModel", "Error al cargar los chats: ${exception.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadFavorites(userId: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val token = tokenManager.getToken()
                if (!token.isNullOrEmpty()) {
                    val favoritesResponse = withContext(Dispatchers.IO) {
                        professionalRepository.getFavorites("Bearer $token", userId)
                    }
                    _professionals.value = favoritesResponse
                } else {
                    Log.e("ProfessionalViewModel", "Token no disponible")
                }
            } catch (exception: Exception) {
                Log.e("ProfessionalViewModel", "Error al cargar favoritos: ${exception.localizedMessage}")
                _professionals.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun addFavorite(professionalId: String) {
        viewModelScope.launch {
            try {
                val token = tokenManager.getToken()
                val userId = tokenManager.getUserId()

                if (!token.isNullOrEmpty() && !userId.isNullOrEmpty()) {
                    professionalRepository.createFavorite("Bearer $token", userId, professionalId)
                    Log.d("ProfessionalViewModel", "Profesional agregado a favoritos.")
                } else {
                    Log.e("ProfessionalViewModel", "Token o userId no disponible.")
                }
            } catch (exception: Exception) {
                Log.e("ProfessionalViewModel", "Error al agregar favorito: ${exception.localizedMessage}")
            }
        }
    }



    class ProfessionalViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ProfessionalViewModel::class.java)) {
                return ProfessionalViewModel(ProfessionalRepository, context) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
