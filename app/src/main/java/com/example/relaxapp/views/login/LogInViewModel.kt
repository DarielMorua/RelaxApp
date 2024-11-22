package com.example.relaxapp.views.login


import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.relaxapp.TokenManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LogInViewModel(val userRepository: UserRepository, private val context: Context,) : ViewModel() {

    private val tokenManager = TokenManager(context)
    private val preferenceHelper = PreferenceHelper(context)

    var loginResponse: LoginResponse by mutableStateOf(LoginResponse("", "", UserResponse("", "", "", ""), false))
    var isLoading by mutableStateOf(false)
    var state by mutableStateOf(0)
    var isChecked by mutableStateOf(false)
    var email by mutableStateOf("")
    var password by mutableStateOf("")

    init {
        loadSavedCredentials()
    }

    fun doLogin(email: String, password: String) {
        isLoading = true
        viewModelScope.launch {
            try {
                loginResponse = userRepository.doLogin(User(email, password))
                if (loginResponse.isSuccess) {
                    // Guarda credenciales si el switch está activado
                    if (isChecked) {
                        preferenceHelper.saveCredentials(email, password)
                    } else {
                        preferenceHelper.clearCredentials()
                    }
                    tokenManager.saveToken(loginResponse.token)
                    state = 1 // Indica que el login fue exitoso
                    isLoading = false
                } else {
                    state = -1
                    isLoading = false
                }
            } catch (exception: Exception) {
                state = -1
                isLoading = false
            }
        }
    }

    fun updateRememberMeState(checked: Boolean) {
        isChecked = checked
        if (!checked) {
            // Borra credenciales si se desactiva el switch
            preferenceHelper.clearCredentials()
            email = ""
            password = ""
        }
    }

    fun loadSavedCredentials() {
        val (savedEmail, savedPassword) = preferenceHelper.getCredentials()
        if (savedEmail != null && savedPassword != null) {
            email = savedEmail
            password = savedPassword
            isChecked = true
        }
    }

    fun getToken(): String? {
        return tokenManager.getToken()
    }
}


class LoginViewModelFactory(private val context: Context)  : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LogInViewModel::class.java)) {
            return LogInViewModel(UserRepository, context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


