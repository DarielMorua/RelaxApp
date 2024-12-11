package com.example.relaxapp.views.login.viewmodels


import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.relaxapp.TokenManager
import com.example.relaxapp.views.login.models.LoginResponse
import com.example.relaxapp.views.login.models.User
import com.example.relaxapp.views.login.models.UserRepository
import com.example.relaxapp.views.login.models.UserResponse
import kotlinx.coroutines.launch

class LogInViewModel(val userRepository: UserRepository, private val context: Context,) : ViewModel() {

    var loginResponse: LoginResponse by mutableStateOf(LoginResponse("", "", UserResponse("","","","","", "", "", ""), false)  )
    var isLoading: Boolean by mutableStateOf(false)
    var state: Int by mutableStateOf(0)
    private val tokenManager = TokenManager(context)


    fun doLogin(email: String, password: String) {
        isLoading = true
        viewModelScope.launch {
            try {
                loginResponse = userRepository.doLogin(User(email, password))
                tokenManager.saveToken(loginResponse.token)
                Log.d("RolLogIn", loginResponse.user.rol)
                tokenManager.saveRole(loginResponse.user.rol)
                val userId = loginResponse.user.id
                tokenManager.saveUserId(userId)

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


