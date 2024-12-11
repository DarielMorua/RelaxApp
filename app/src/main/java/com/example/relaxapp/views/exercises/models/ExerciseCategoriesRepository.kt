package com.example.relaxapp.views.exercises.models

import com.example.relaxapp.views.RetrofitClientInstance

object ExerciseCategoriesRepository {
    private val apiService = RetrofitClientInstance.apiService

    suspend fun getExercisesCategories(authHeader: String): CategoriesResponse {
        return apiService.getExercisesCategories(authHeader)
    }
}