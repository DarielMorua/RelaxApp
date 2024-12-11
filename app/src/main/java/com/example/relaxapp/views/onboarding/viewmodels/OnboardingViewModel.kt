package com.example.relaxapp.views.onboarding.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class OnboardingViewModel(application: Application) : AndroidViewModel(application) {

    private val sharedPreferences =
        application.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    private val _isOnboardingCompleted =
        MutableStateFlow(sharedPreferences.getBoolean("onboarding_completed", false))
    val isOnboardingCompleted: StateFlow<Boolean> = _isOnboardingCompleted

    fun completeOnboarding() {
        sharedPreferences.edit().putBoolean("onboarding_completed", true).apply()
        _isOnboardingCompleted.value = true
    }
}