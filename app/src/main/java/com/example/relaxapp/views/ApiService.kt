package com.example.relaxapp.views

import com.example.relaxapp.views.login.LoginResponse
import com.example.relaxapp.views.login.User
import retrofit2.http.Body
import retrofit2.http.POST

private const val END_URL_LOGIN_WITH_EMAIL = "users/login"



interface ApiService {
    @POST(END_URL_LOGIN_WITH_EMAIL)
    suspend fun login(@Body user: User): LoginResponse

}