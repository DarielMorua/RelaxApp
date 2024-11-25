package com.example.relaxapp.views.profesionales

import android.util.Log
import com.example.relaxapp.views.RetrofitClientInstance

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
            emptyList() // Devuelve una lista vacía si hay algún error
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







}
