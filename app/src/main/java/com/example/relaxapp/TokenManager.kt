package com.example.relaxapp

import android.content.Context
import android.content.SharedPreferences

class TokenManager(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        sharedPreferences.edit().putString("auth_token", token).apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString("auth_token", null)
    }

    fun clearToken() {
        sharedPreferences.edit().remove("auth_token").apply()
    }
    // Guardar el userId
    fun saveUserId(userId: String) {
        sharedPreferences.edit().putString("user_id", userId).apply()
    }

    // Recuperar el userId
    fun getUserId(): String? {
        return sharedPreferences.getString("user_id", null)
    }

    // Limpiar el userId
    fun clearUserId() {
        sharedPreferences.edit().remove("user_id").apply()
    }

    // Método para limpiar todos los datos del usuario (token y userId)
    fun clearAll() {
        sharedPreferences.edit().clear().apply()
    }
}