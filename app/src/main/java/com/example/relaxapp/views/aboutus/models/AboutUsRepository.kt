package com.example.relaxapp.views.aboutus.models

import com.example.relaxapp.views.RetrofitClientInstance

import retrofit2.Response

object AboutUsRepository {
    val apiService = RetrofitClientInstance.apiService

    suspend fun getAboutUsInfo(): Response<List<AboutUsInfo>> {
        return apiService.getAboutUsInfo()
    }
}