package com.example.relaxapp.views.mainmenu

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.relaxapp.views.login.LogInViewModel
import com.example.relaxapp.views.login.LoginResponse
import com.example.relaxapp.views.login.User
import com.example.relaxapp.views.login.UserRepository
import com.example.relaxapp.views.login.UserResponse
import kotlinx.coroutines.launch

class MainMenuViewModel(val excerciseRepository: ExcerciseRepository) : ViewModel() {

    var selectedEmoji by mutableStateOf<String?>(null)
        private set

    var selectedImage by mutableStateOf<Int?>(null)
        private set

    fun onEmojiSelected(emoji: String) {
        selectedEmoji = emoji
    }

    fun onImageSelected(imageResId: Int) {
        selectedImage =  imageResId
    }

    var excerciseResponse: ExcerciseResponse by mutableStateOf(ExcerciseResponse("", "", "", "", "", "", ""))
    var isLoading: Boolean by mutableStateOf(false)
    var state: Int by mutableStateOf(0)

    fun getRecommendedExercises() {
        isLoading = true
        viewModelScope.launch {
            try {
                excerciseResponse = excerciseRepository.getRecommendedExercises()
                state = 1
                isLoading = false

            }
            catch (exception: Exception){
                state = -1
                isLoading = false
            }
        }
    }

    class MainMenuViewModelFactory : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainMenuViewModel::class.java)) {
                return MainMenuViewModel(ExcerciseRepository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}