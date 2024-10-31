package com.example.relaxapp.views.login


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LogInViewModel(val userRepository: UserRepository) : ViewModel() {

    var loginResponse: LoginResponse by mutableStateOf(LoginResponse("", "", UserResponse("","","",""))  )
    var isLoading: Boolean by mutableStateOf(false)
    var state: Int by mutableStateOf(0)

    fun doLogin(email: String, password: String) {
        isLoading = true
        viewModelScope.launch {
            try {
                loginResponse = userRepository.doLogin(User(email, password))
                state = 1
                loginResponse.message = "Login exitoso"
                isLoading = false
            }
            catch (exception: Exception){
                state = 1
                loginResponse.message = "Error en el login"
                loginResponse.message = "ocurrio error"
                isLoading = false
            }
        }
    }
}

class LoginViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LogInViewModel::class.java)) {
            return LogInViewModel(UserRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


