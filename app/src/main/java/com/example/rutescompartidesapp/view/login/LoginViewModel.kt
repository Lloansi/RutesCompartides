package com.example.rutescompartidesapp.view.login

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rutescompartidesapp.data.domain.auth.AuthRequest
import com.example.rutescompartidesapp.data.domain.auth.AuthToken
import com.example.rutescompartidesapp.data.network.repository.RutesCompartidesRepository
import com.example.rutescompartidesapp.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor (
    val rutesCompartidesRepository: RutesCompartidesRepository
): ViewModel(){

    val authToken = MutableStateFlow(AuthToken(""))

    private suspend fun getToken(authRequest: AuthRequest){
        viewModelScope.launch{
            val result = rutesCompartidesRepository.getToken(authRequest)
            if (result.token != ""){
                authToken.value = result
                _userIsLogged.value = true
                _isLoading.value = false
            } else {
                println("Error getting token")
                _userIsLogged.value = false
                _isLoading.value = false
            }
        }
    }

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun onLoading(isLoading: Boolean){
        _isLoading.value = isLoading
    }

    //User email text
    private val _userEmail = MutableStateFlow("")
    val userEmail = _userEmail.asStateFlow()

    fun onUserEmailTextChange(text: String) {
        _userEmail.value = text

        //Check if the email is correct and update error status
        val userEmail = _userEmail.value
        val isError = userEmail != text

        onUserEmailError(isError)
    }

    //User email error
    private val _userEmailError = MutableStateFlow(false)
    val userEmailError = _userEmailError.asStateFlow()

    fun onUserEmailError(isError: Boolean): Boolean {
        _userEmailError.value = isError

        return isError
    }

    //User password text
    private val _userPassword = MutableStateFlow("")
    val userPassword = _userPassword.asStateFlow()

    fun onUserPasswordTextChange(text: String) {
        _userPassword.value = text
    }

    //Password visibility
    private val _isPasswordVisible = MutableStateFlow(false)
    val isPasswordVisible = _isPasswordVisible.asStateFlow()

    fun togglePasswordVisibility() {
        _isPasswordVisible.value = !_isPasswordVisible.value
    }

    //User password error
    private val _userPasswordError = MutableStateFlow(false)
    val userPasswordError = _userPasswordError.asStateFlow()

    fun onUserPasswordError(isError: Boolean): Boolean {
        _userPasswordError.value = isError
        return isError
    }

    //User password hide and show

    private val _userWantsToLogin = MutableStateFlow(false)
    val userWantsToLogin = _userWantsToLogin.asStateFlow()

    //Sing Up Button
    fun onLoginButtonClick() {

        // Variables to obtain the values of the input
        val userEmail = _userEmail.value
        val userPassword = _userPassword.value

        //Check the Email format
        if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
            onUserEmailError(isError = true)
        }

        if (!onUserEmailError(userEmail.isEmpty()) ||
            !onUserPasswordError(userPassword.isEmpty())){
            _userWantsToLogin.value = true
        }


    }

    //User exists
    private val _userIsLogged = MutableStateFlow(false)
    val userIsLogged = _userIsLogged.asStateFlow()
     fun login2(userEmail: String): Boolean {
         return if (!Constants.userList.filter { user -> user.email == userEmail }.isEmpty()){
             _userIsLogged.value = true
             println("Login okey")
             true
         } else {
             println("Login not okey")
             false
         }
     }


   suspend fun login() {
       getToken(AuthRequest(_userEmail.value, _userPassword.value))
   }
}