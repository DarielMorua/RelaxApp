package com.example.relaxapp.views.calendar.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.relaxapp.TokenManager
import com.example.relaxapp.views.RetrofitClientInstance.apiService
import com.example.relaxapp.views.calendar.models.EmotionRecord
import com.example.relaxapp.views.calendar.models.EmotionRequest
import com.example.relaxapp.views.calendar.models.EmotionResponse
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.util.Date
import java.util.Locale

class EmotionsViewModel(private val context: Context) : ViewModel() {

    private val _emotions = MutableLiveData<List<EmotionRecord>>(emptyList())
    val emotions: LiveData<List<EmotionRecord>> = _emotions

    private val _groupedEmotions = MutableLiveData<Map<DayOfWeek, List<EmotionRecord>>>(emptyMap())
    val groupedEmotions: LiveData<Map<DayOfWeek, List<EmotionRecord>>> get() = _groupedEmotions

    private val tokenManager: TokenManager = TokenManager(context)

    fun getEmotionsFromBackend() {
        viewModelScope.launch {
            try {
                val token = tokenManager.getToken()
                val userId = tokenManager.getUserId()

                if (userId != null) {
                    val request = EmotionRequest(userId)

                    val response = apiService.getEmotionsByUserId("Bearer $token", request)

                    if (response.isSuccessful) {
                        val emotionsResponse = response.body()?.emotions ?: emptyList()
                        val emotions = emotionsResponse.map { mapEmotionResponseToRecord(it) }

                        _emotions.value = emotions

                        Log.d("EmotionsViewModel", "Emociones obtenidas: $emotions")
                    } else {
                        Log.e("EmotionsViewModel", "Error al obtener emociones: ${response.code()} ${response.message()}")
                        Log.e("EmotionsViewModel", "Cuerpo de error: ${response.errorBody()?.string()}")
                    }
                } else {
                    Log.e("EmotionsViewModel", "User ID es nulo")
                }
            } catch (e: Exception) {
                Log.e("EmotionsViewModel", "Error al hacer la solicitud: ${e.localizedMessage}")
            }
        }
    }

    fun mapEmotionResponseToRecord(emotionResponse: EmotionResponse): EmotionRecord {
        val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault()).parse(emotionResponse.date) ?: Date()

        return EmotionRecord(
            emotion = emotionResponse.emotion,
            date = date,
            userDetails = emotionResponse.userDetails
        )
    }


    class EmotionsViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(EmotionsViewModel::class.java)) {
                return EmotionsViewModel(context) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}



