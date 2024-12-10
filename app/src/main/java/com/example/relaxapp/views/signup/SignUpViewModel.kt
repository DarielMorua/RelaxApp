package com.example.relaxapp.views.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {

    private val _email = MutableLiveData("")
    val email: LiveData<String> = _email

    private val _name = MutableLiveData("")
    val name: LiveData<String> = _name

    private val _lastname = MutableLiveData("")
    val lastname: LiveData<String> = _lastname

    private val _password = MutableLiveData("")
    val password: LiveData<String> = _password

    private val _passwordConf = MutableLiveData("")
    val passwordConf: LiveData<String> = _passwordConf

    private val _country = MutableLiveData("")
    val country: LiveData<String> = _country


    private val _telephone = MutableLiveData("")
    val telephone: LiveData<String> = _telephone

    private val _rol = MutableLiveData("User")
    val rol: LiveData<String> = _rol

    private val _registerStatus = MutableLiveData<String>()
    val registerStatus: LiveData<String> = _registerStatus

    // Funciones para actualizar los campos
    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
    }

    fun onUsernameChange(newUsername: String) {
        _name.value = newUsername
    }

    fun onLastnameChange(newLastname: String) {
        _lastname.value = newLastname
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
    }

    fun onPasswordConfChange(newPasswordConf: String) {
        _passwordConf.value = newPasswordConf
    }

    fun onTelephoneChange(newTelephone: String) {
        _telephone.value = newTelephone
    }

    fun onCountryChange(newCountry: String) {
        _country.value = newCountry
    }

    fun onRolChange(newRol: String) {
        _rol.value = newRol
    }

    // Funcipon que se llama cuando el usuario hace clic en el registro
    fun onRegisterClicked() {
        if (validateInput()) {
            // Llamar al repositorio para hacer el registro
            viewModelScope.launch {
                val response = SignUpRepository.createUser(
                    _name.value.orEmpty(),
                    _lastname.value.orEmpty(),
                    _email.value.orEmpty(),
                    _password.value.orEmpty(),
                    _telephone.value.orEmpty(),
                    _country.value.orEmpty(),
                    _rol.value.orEmpty()
                )


            }
        }
    }

    // Validar que los campos no esten vacios
    private fun validateInput(): Boolean {
        return _name.value?.isNotEmpty() == true &&
                _lastname.value?.isNotEmpty() == true &&
                _email.value?.isNotEmpty() == true &&
                _password.value?.isNotEmpty() == true &&
                _passwordConf.value == _password.value &&
                _telephone.value?.isNotEmpty() == true &&
                _country.value?.isNotEmpty() == true &&
                _rol.value?.isNotEmpty() == true
    }
}
