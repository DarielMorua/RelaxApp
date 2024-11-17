package com.example.relaxapp.views

import com.example.relaxapp.views.mainmenu.ExcerciseResponse
import com.example.relaxapp.views.login.LoginResponse
import com.example.relaxapp.views.login.User
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

private const val END_URL_LOGIN_WITH_EMAIL = "users/login"

// variable para mostrar la ruta de ejercicios recomendados
private const val END_URL_RECOMMENDED_EXERCISES = "exercises/mostrar-ejercicios-recomendados"



interface ApiService {
    @POST(END_URL_LOGIN_WITH_EMAIL)
    suspend fun login(@Body user: User): LoginResponse

    @POST(END_URL_RECOMMENDED_EXERCISES)
    suspend fun getRecommendedExercises(
        @Header("Authorization") authHeader: String
    ): List<ExcerciseResponse>
}