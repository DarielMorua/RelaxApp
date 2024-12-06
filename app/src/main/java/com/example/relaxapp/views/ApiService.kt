package com.example.relaxapp.views

import com.example.relaxapp.views.aboutus.AboutUsInfo
import com.example.relaxapp.views.exercises.excerciseDetails.CategoriesResponse
import com.example.relaxapp.views.mainmenu.ExcerciseResponse
import com.example.relaxapp.views.login.LoginResponse
import com.example.relaxapp.views.login.User

import com.example.relaxapp.views.notifications.NotificationResponse


import com.example.relaxapp.views.mainmenu.EmotionRequest
import com.example.relaxapp.views.notifications.deleteNotification
import com.example.relaxapp.views.profesionales.Professional
import com.example.relaxapp.views.profesionales.Review
import com.example.relaxapp.views.signup.SignUpUser
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

private const val END_URL_PROFFESIONALS_GET = "professionals/mostrar-profesionales"

private const val END_URL_NOTIFICATION_DELETE = "notifications/borrar-notificacion"



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

    @POST(END_URL_PROFFESIONALS_GET)
    suspend fun getProfessionals(
        @Header("Authorization") authHeader: String
    ): List<Professional>

    @POST("professionals/mostrar-profesional")
    suspend fun getProfessionalDetails(
        @Header("Authorization") authHeader: String,
        @Body request: Map<String, String>
    ): Professional



    @POST("professionals/mostrar-reviews")
    suspend fun getReviews(
        @Header("Authorization") authHeader: String,
        @Body request: Map<String, String>
    ): List<Review>

    @POST("users/mostrar-favoritos")
    suspend fun getFavorites(
        @Header("Authorization") authHeader: String,
        @Body request: Map<String, String>
    ): List<Professional>
    @POST("favorite/crear-favorito")
    suspend fun createFavorite(
        @Header("Authorization") authHeader: String,
        @Body body: Map<String, String>
    )

    @POST(END_URL_NOTIFICATION_DELETE)
    suspend fun deleteNotification(
        @Header("Authorization") authHeader: String,
        @Body notificationRequest: deleteNotification
    ): Response<Unit>

    @POST("users/crear")
    suspend fun SignUp(
        @Body signUpUser: SignUpUser
    ): Response<Unit>

    @POST("aboutus/obtener-info")
    suspend fun getAboutUsInfo(): Response<List<AboutUsInfo>>

}