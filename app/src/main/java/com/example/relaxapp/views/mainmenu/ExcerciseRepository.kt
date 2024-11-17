package com.example.relaxapp.views.mainmenu

import com.example.relaxapp.views.RetrofitClientInstance

object ExerciseRepository {
    private val apiService = RetrofitClientInstance.apiService

    suspend fun getRecommendedExercises(authHeader: String): List<ExcerciseResponse> {
        return apiService.getRecommendedExercises(authHeader)
    }
}