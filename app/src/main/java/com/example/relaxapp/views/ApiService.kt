package com.example.relaxapp.views



import com.example.relaxapp.views.chat.models.ChatResponse
import com.example.relaxapp.views.chat.models.CreateChatRequest
import com.example.relaxapp.views.chat.models.Message


import com.example.relaxapp.views.aboutus.models.AboutUsInfo


import com.example.relaxapp.views.chat.models.ChatResponseMessages



import com.example.relaxapp.views.calendar.models.EmotionApiResponse



import com.example.relaxapp.views.exercises.models.CategoriesResponse
import com.example.relaxapp.views.mainmenu.models.ExcerciseResponse
import com.example.relaxapp.views.login.models.LoginResponse
import com.example.relaxapp.views.login.models.User
import com.example.relaxapp.views.login.models.UserResponse

import com.example.relaxapp.views.notifications.models.NotificationResponse


import com.example.relaxapp.views.mainmenu.models.EmotionRequest
import com.example.relaxapp.views.notifications.models.deleteNotification
import com.example.relaxapp.views.profesionales.models.Professional
import com.example.relaxapp.views.profesionales.models.ProfessionalRequest
import com.example.relaxapp.views.profesionales.models.Review


import com.example.relaxapp.views.profesionales.conexionChat.ProfessionalChatsResponse


import com.example.relaxapp.views.profile.models.UserProfile




import com.example.relaxapp.views.signup.models.SignUpUser


import com.example.relaxapp.views.profile.models.UserRequest
import com.example.relaxapp.views.profile.images.ImagesData


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

private const val END_URL_USER_GET = "users/obtener/"

private const val END_URL_CREATE_CHAT = "chat/crear-chat"

private const val END_URL_SEND_MESSAGE = "chat/mandar-mensaje"

private const val END_URL_SHOW_MESSAGE = "chat/mostrar-mensajes"

private const val END_URL_GET_PROFESSIONAL_CHATS = "chat/obtener-chats-profesional"



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

  
  @POST("users/actualizar")
  suspend fun updateUser(
      @Header("Authorization") authHeader: String,
      @Body user: UserProfile
  ): Response<Unit>

  @POST("users/crear")
    suspend fun SignUp(
        @Body signUpUser: SignUpUser
    ): Response<Unit>

  @POST("images/get-images")
    suspend fun getImages(
        @Header("Authorization") authHeader: String
    ): List<ImagesData>

  @POST(END_URL_USER_GET)
    suspend fun getUserDetails(
        @Header("Authorization") token: String, 
        @Body request: UserRequest
    ): UserResponse

    @POST(END_URL_CREATE_CHAT)
    suspend fun createChat(
        @Header("Authorization") authHeader: String,
        @Body request: CreateChatRequest
    ): Response<ChatResponse>

    @POST(END_URL_SEND_MESSAGE)
    suspend fun sendMessage(
        @Header("Authorization") authHeader: String,
        @Body requestBody: Message
    ): Response<ChatResponse>

    @POST(END_URL_SHOW_MESSAGE)
    suspend fun getMessages(
        @Header("Authorization") authHeader: String,
        @Body requestBody: Map<String, String>
    ): Response<ChatResponseMessages>

  
  
  
    @POST("aboutus/obtener-info")
    suspend fun getAboutUsInfo(): Response<List<AboutUsInfo>>

  

  @POST(END_URL_GET_PROFESSIONAL_CHATS)
    suspend fun getProfessionalChats(
        @Header("Authorization") authHeader: String,
        @Body request: ProfessionalRequest
    ): Response<ProfessionalChatsResponse>

  
  @POST("emotion/get-emotions-by-user-id")
    suspend fun getEmotionsByUserId(
        @Header("Authorization") authToken: String,
        @Body request: com.example.relaxapp.views.calendar.models.EmotionRequest
    ): Response<EmotionApiResponse>

  
  
}