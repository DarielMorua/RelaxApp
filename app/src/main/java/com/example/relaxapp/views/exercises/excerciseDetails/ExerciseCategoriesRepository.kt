package com.example.relaxapp.views.exercises.excerciseDetails

import com.example.relaxapp.views.RetrofitClientInstance
import com.example.relaxapp.views.mainmenu.ExcerciseResponse

object ExerciseCategoriesRepository {
    private val apiService = RetrofitClientInstance.apiService

    suspend fun getExercisesCategories(authHeader: String): CategoriesResponse{
        return apiService.getExercisesCategories(authHeader)
    }
}