package com.example.relaxapp.views.mainmenu

import com.example.relaxapp.views.RetrofitClientInstance

object ExcerciseRepository {

    val apiService = RetrofitClientInstance.apiService

    suspend fun  getRecommendedExercises(): ExcerciseResponse {
        return apiService.getRecommendedExercises()
    }
}