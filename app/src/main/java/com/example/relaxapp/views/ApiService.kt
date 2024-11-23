package com.example.relaxapp.views

import com.example.relaxapp.views.exercises.excerciseDetails.CategoriesResponse
import com.example.relaxapp.views.mainmenu.ExcerciseResponse
import com.example.relaxapp.views.login.LoginResponse
import com.example.relaxapp.views.login.User

import com.example.relaxapp.views.notifications.NotificationResponse


import com.example.relaxapp.views.mainmenu.EmotionRequest
import retrofit2.Response

import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

private const val END_URL_LOGIN_WITH_EMAIL = "users/login"

// variable para mostrar la ruta de ejercicios recomendados
private const val END_URL_RECOMMENDED_EXERCISES = "exercises/mostrar-ejercicios-recomendados"

private const val END_URL_EXERCISES_CATEGORIES = "exercises/mostrar-ejercicios-por-categoria"


private const val END_URL_NOTIFICATION_GET="notifications/mostrar-notificaciones"

private const val END_URL_SUBMIT_EMOTION = "emotion/submit-emotion"




interface ApiService {
    @POST(END_URL_LOGIN_WITH_EMAIL)
    suspend fun login(@Body user: User): LoginResponse

    @POST(END_URL_RECOMMENDED_EXERCISES)
    suspend fun getRecommendedExercises(
        @Header("Authorization") authHeader: String
    ): List<ExcerciseResponse>

    @POST(END_URL_EXERCISES_CATEGORIES)
    suspend fun getExercisesCategories(
        @Header("Authorization") authHeader: String
    ): CategoriesResponse


  @POST(END_URL_NOTIFICATION_GET)
    suspend fun getNotifications(
        @Header("Authorization") authHeader: String
    ): List<NotificationResponse>

  @POST(END_URL_SUBMIT_EMOTION)
    suspend fun submitEmotion(
        @Header("Authorization") authHeader: String,
        @Body emotionRequest: EmotionRequest
    ): Response<Unit>

}