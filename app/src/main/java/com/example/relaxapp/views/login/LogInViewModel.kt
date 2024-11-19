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

    var loginResponse: LoginResponse by mutableStateOf(LoginResponse("", "", UserResponse("","","",""), false)  )
    var isLoading: Boolean by mutableStateOf(false)
    var state: Int by mutableStateOf(0)
    private val tokenManager = TokenManager(context)


    fun doLogin(email: String, password: String) {
        isLoading = true
        viewModelScope.launch {
            try {
                loginResponse = userRepository.doLogin(User(email, password))
                tokenManager.saveToken(loginResponse.token)
                state = 1
                loginResponse.message = "Login exitoso"
                loginResponse.isSuccess = true
                isLoading = false

            }
            catch (exception: Exception){
                state = -1
                loginResponse.message = "Error en el login"
                loginResponse.isSuccess = false
                isLoading = false
            }
        }
    }
    fun getToken(): String? {
        return tokenManager.getToken() // Método para recuperar el token
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


