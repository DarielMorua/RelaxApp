package com.example.relaxapp.views.profile

import android.content.Context
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.relaxapp.TokenManager
import com.example.relaxapp.views.login.UserRepository
import com.example.relaxapp.views.login.UserResponse
import com.example.relaxapp.views.profile.images.ImagesData
import kotlinx.coroutines.launch

class ProfileViewModel(
    val userRepository: UserRepository,
    private val context: Context
) : ViewModel() {

    private val _user = mutableStateOf<UserResponse?>(null)
    val user: State<UserResponse?> = _user

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading


    private val _images = mutableStateOf<List<ImagesData>>(emptyList())
    val images: State<List<ImagesData>> = _images
    private val tokenManager = TokenManager(context)

    fun getUserDetails(userId: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val token = tokenManager.getToken()  // Obtener el token de autorización
                if (token.isNullOrEmpty()) {
                    Log.d("ProfileViewModel", "Token vacío o nulo")
                    _user.value = null
                    _isLoading.value = false
                    return@launch
                }

                // Llamar al repositorio pasando el token y el userId
                val userDetails = userRepository.getUserDetails("Bearer $token", userId)
                _user.value = userDetails
            } catch (exception: Exception) {
                Log.d(
                    "ProfileViewModel",
                    "Error al obtener detalles del usuario: ${exception.message}"
                )
                _user.value = null
            } finally {
                _isLoading.value = false
            }
        }
    }

    class ProfileViewModelFactory(private val context: Context) :
                ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
                        return ProfileViewModel(UserRepository, context) as T
                    }
                    throw IllegalArgumentException("Clase ViewModel desconocida")
                }
            }
        }

