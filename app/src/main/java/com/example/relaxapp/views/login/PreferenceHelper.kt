package com.example.relaxapp.views.login

import android.content.Context

class PreferenceHelper(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("login_preferences", Context.MODE_PRIVATE)

    fun saveCredentials(username: String, password: String) {
        sharedPreferences.edit().apply {
            putString("email", username)
            putString("password", password)
            apply()
        }
    }

    fun getCredentials(): Pair<String?, String?> {
        val username = sharedPreferences.getString("email", null)
        val password = sharedPreferences.getString("password", null)
        return Pair(username, password)
    }

    fun clearCredentials() {
        sharedPreferences.edit().apply {
            remove("email")
            remove("password")
            apply()
        }
    }
}