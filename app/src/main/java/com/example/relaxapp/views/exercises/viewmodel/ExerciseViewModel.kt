package com.example.relaxapp.views.exercises.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.relaxapp.TokenManager
import com.example.relaxapp.views.exercises.models.Category
import com.example.relaxapp.views.exercises.models.ExerciseCategoriesRepository
import kotlinx.coroutines.launch

class ExerciseViewModel(private val exerciseCategory: ExerciseCategoriesRepository, private val context: Context
) : ViewModel() {

    var categories by mutableStateOf<List<Category>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var state by mutableStateOf(0)
        private set

    private val tokenManager = TokenManager(context)

    fun getExercisesByCategory() {
        isLoading = true
        viewModelScope.launch {
            try {
                val token = tokenManager.getToken()
                if (token != null) {
                    val response = exerciseCategory.getExercisesCategories("Bearer $token")
                    Log.d("ExerciseViewModel", "Datos recibidos: $response")
                    categories = response.categories

                    Log.d("ExerciseViewModel", "Datos recibidos: $categories")
                    state = if (categories.isNotEmpty()) 1 else -1
                } else {
                    Log.d("ExerciseViewModel", "Token no disponible")
                    state = -1
                }
            } catch (exception: Exception) {
                Log.d("ExerciseViewModel", "Error al recuperar datos ${exception.message}")
                categories = emptyList()
                state = -1
            } finally {
                Log.d("ExerciseViewModel", "Fin de la carga")
                isLoading = false
            }
        }
    }

    class ExerciseViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ExerciseViewModel::class.java)) {
                return ExerciseViewModel(ExerciseCategoriesRepository, context) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}