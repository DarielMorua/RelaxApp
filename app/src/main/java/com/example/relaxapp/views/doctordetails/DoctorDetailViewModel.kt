package com.example.relaxapp.views.doctordetails

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.relaxapp.TokenManager
import com.example.relaxapp.views.profesionales.Professional
import com.example.relaxapp.views.profesionales.ProfessionalRepository
import com.example.relaxapp.views.profesionales.Review
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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



    fun loadProfessionalReviews(professionalId: String) {
        isLoading = true
        viewModelScope.launch {
            try {
                val token = tokenManager.getToken()
                if (!token.isNullOrEmpty()) {
                    val reviewsResponse = repository.getReviews("Bearer $token", professionalId)
                    reviews = reviewsResponse

                    Log.d("DoctorDetailViewModel", "Reviews cargadas: $reviews")
                } else {
                    Log.e("DoctorDetailViewModel", "Token no disponible")
                }
            } catch (exception: Exception) {
                Log.e("DoctorDetailViewModel", "Error al cargar reviews: ${exception.message}")
            } finally {
                isLoading = false
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
