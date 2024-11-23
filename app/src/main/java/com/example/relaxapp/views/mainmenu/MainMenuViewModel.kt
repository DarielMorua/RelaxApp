package com.example.relaxapp.views.mainmenu

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.relaxapp.TokenManager
import com.example.relaxapp.views.RetrofitClientInstance.apiService
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainMenuViewModel(val excerciseRepository: ExerciseRepository, private val context: Context) : ViewModel() {

    var selectedEmoji by mutableStateOf<String?>(null)
        private set

    var selectedImage by mutableStateOf<Int?>(null)
        private set

    fun onEmojiSelected(emoji: String) {
        selectedEmoji = emoji
    }

    fun onImageSelected(imageResId: Int) {
        selectedImage =  imageResId
    }

    var exercises by mutableStateOf<List<ExcerciseResponse>>(emptyList())
        private set

    private val tokenManager = TokenManager(context)
    var isLoading: Boolean by mutableStateOf(false)
    var state: Int by mutableStateOf(0)

    fun getRecommendedExercises() {
        isLoading = true
        viewModelScope.launch {
            try {
                val token = tokenManager.getToken() // Recuperar el token
                if (token != null) {
                    val response = excerciseRepository.getRecommendedExercises("Bearer $token")
                    exercises = response
                    state = if (exercises.isNotEmpty()) 1 else -1
                    Log.d("MainMenuViewModel", "Datos recibidos: $exercises")
                } else {
                    state = -1 // Sin token
                }
            }
            catch (exception: Exception){
                exercises = emptyList()
                state = -1
            } finally {
                isLoading = false
            }
        }
    }

    fun submitEmotion(emotion: String) {
        isLoading = true
        viewModelScope.launch {
            try {
                val token = tokenManager.getToken()
                if (token != null) {
                    val isoDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
                    val formattedDate = isoDateFormat.format(Date(System.currentTimeMillis()))

                    val request = EmotionRequest(
                        emotion = emotion,
                        date = formattedDate // Formato ISO 8601
                    )

                    val response = apiService.submitEmotion("Bearer $token" ,request)
                    if (response.isSuccessful) {
                        Log.d("MainMenuViewModel", "Emoción enviada con éxito: $emotion")
                    } else {
                        Log.d("MainMenuViewModel", "Error al enviar la emoción: ${response.errorBody()}")
                    }
                } else {
                    Log.d("MainMenuViewModel", "Token no disponible")
                }
            } catch (e: Exception) {
                Log.d("MainMenuViewModel", "Error al enviar la emoción: ${e.message}")
            } finally {
                isLoading = false
            }
        }
    }

    class MainMenuViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainMenuViewModel::class.java)) {
                return MainMenuViewModel(ExerciseRepository, context) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}