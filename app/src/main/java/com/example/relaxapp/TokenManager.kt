package com.example.relaxapp

import android.content.Context
import android.content.SharedPreferences

class TokenManager(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        sharedPreferences.edit().putString("auth_token", token).apply()
    }

    fun saveRole(rol: String) {
        sharedPreferences.edit().putString("role", rol).apply()
    }

    fun getRole(): String? {
        return sharedPreferences.getString("role", null)
    }

    fun getToken(): String? {
        return sharedPreferences.getString("auth_token", null)
    }

    fun clearToken() {
        sharedPreferences.edit().remove("auth_token").apply()
    }

    fun saveUserId(userId: String) {
        sharedPreferences.edit().putString("user_id", userId).apply()
    }

    fun getUserId(): String? {
        return sharedPreferences.getString("user_id", null)
    }

    fun clearUserId() {
        sharedPreferences.edit().remove("user_id").apply()
    }

    fun clearAll() {
        sharedPreferences.edit().clear().apply()
    }
}