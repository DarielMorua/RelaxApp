package com.example.relaxapp.views.signup


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {

    // variables para hacer el registro
    private val _email = MutableLiveData("")
    val email: LiveData<String> = _email

    private val _username = MutableLiveData("")
    val username: LiveData<String> = _username

    private val _password = MutableLiveData("")
    val password: LiveData<String> = _password

    private val _passwordConf = MutableLiveData("")
    val passwordConf: LiveData<String> = _passwordConf

    private val _termsAccepted = MutableLiveData(false)
    val termsAccepted: LiveData<Boolean> = _termsAccepted

    private val _telephone = MutableLiveData("")
    val telephone: LiveData<String> = _telephone

    // funciones para actualizar
    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
    }

    fun onUsernameChange(newUsername: String) {
        _username.value = newUsername
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

    fun onTermsAcceptedChange(accepted: Boolean) {
        _termsAccepted.value = accepted
    }

    fun onRegisterClicked() {
        TODO("Not yet implemented")
    }

    fun onGoogleSignUp() {
        TODO("Not yet implemented")
    }

    fun onFacebookSignUp() {
        TODO("Not yet implemented")
    }



}
