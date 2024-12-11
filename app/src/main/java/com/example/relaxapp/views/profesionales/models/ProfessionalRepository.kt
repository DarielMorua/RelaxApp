package com.example.relaxapp.views.profesionales.models

import android.util.Log
import com.example.relaxapp.views.RetrofitClientInstance
import com.example.relaxapp.views.chat.models.ChatDetailsMessages
import com.example.relaxapp.views.chat.models.ChatResponse
import com.example.relaxapp.views.chat.models.CreateChatRequest
import com.example.relaxapp.views.chat.models.Message

object ProfessionalRepository {
    private val apiService = RetrofitClientInstance.apiService

    suspend fun getProfessionals(authHeader: String): List<Professional> {
        return apiService.getProfessionals(authHeader)
    }
    suspend fun getProfessionalDetails(authHeader: String, professionalId: String): Professional {
        val requestBody = mapOf("id" to professionalId)
        return apiService.getProfessionalDetails(authHeader, requestBody)
    }


    suspend fun getReviews(authHeader: String, professionalId: String): List<Review> {
        return try {
            val response = apiService.getReviews(authHeader, mapOf("professionalId" to professionalId))
            Log.d("ProfessionalRepository", "Reviews recibidas: $response")
            response
        } catch (exception: Exception) {
            Log.e("ProfessionalRepository", "Error al obtener reviews: ${exception.message}")
            emptyList()
        }
    }
    suspend fun createFavorite(authHeader: String, userId: String, professionalId: String) {
        val body = mapOf(
            "userId" to userId,
            "professionalId" to professionalId
        )
        apiService.createFavorite(authHeader, body)
    }


    suspend fun getFavorites(authHeader: String, userId: String): List<Professional> {
        return try {
            val response = apiService.getFavorites(authHeader, mapOf("userId" to userId))
            Log.d("ProfessionalRepository", "Favoritos recibidos: $response")
            response
        } catch (exception: Exception) {
            Log.e("ProfessionalRepository", "Error al obtener favoritos: ${exception.localizedMessage}")
            emptyList()
        }
    }

    suspend fun createChat(authHeader: String, request: CreateChatRequest): ChatResponse {
        val response = apiService.createChat(authHeader, request)

        if (response.isSuccessful) {
            Log.d("ChatRepository", "Respuesta del backend: ${response.body()}")
            return response.body() ?: throw Exception("Respuesta vacía del servidor")
        } else {
            Log.e("ChatRepository", "Error en la respuesta del backend: ${response.errorBody()?.string()}")
            throw Exception("Error al crear el chat: ${response.message()}")
        }
    }

    suspend fun sendMessage(authHeader: String, chatId: String, senderId: String, senderModel: String, content: String) {
        val requestBody = Message(
            chatId = chatId,
            senderId = senderId,
            senderModel = senderModel,
            content = content
        )

        val response = apiService.sendMessage(authHeader, requestBody)

        if (response.isSuccessful) {
            Log.d("ProfessionalRepository", "Mensaje enviado correctamente")
        } else {
            throw Exception("Error al enviar mensaje: ${response.message()}")
        }
    }

        suspend fun getMessages(authHeader: String, chatId: String): List<Message> {
            return try {
                val requestBody = mapOf("chatId" to chatId)

                val response = apiService.getMessages(authHeader, requestBody)

                if (response.isSuccessful) {
                    response.body()?.chat?.messages ?: emptyList()
                } else {
                    throw Exception("Error al obtener mensajes: ${response.message()}")
                }
            } catch (exception: Exception) {
                Log.e("ProfessionalRepository", "Error al obtener mensajes: ${exception.message}")
                emptyList()
            }
        }

    suspend fun getChatsForProfessional(token: String, userId: String): List<ChatDetailsMessages> {
        val response = apiService.getProfessionalChats("Bearer $token", ProfessionalRequest(userId))
        if (response.isSuccessful) {
            val chats = response.body()?.chat ?: emptyList()
            Log.d("ProfessionalRepository", "Chats obtenidos del backend: $chats")
            return chats
        } else {
            val errorBody = response.errorBody()?.string()
            Log.e("ProfessionalRepository", "Error al obtener los chats: $errorBody")
            throw Exception("Error al obtener los chats.")
        }
    }


}








