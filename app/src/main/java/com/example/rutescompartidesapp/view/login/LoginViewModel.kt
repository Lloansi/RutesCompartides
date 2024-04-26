package com.example.rutescompartidesapp.view.login

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.rutescompartidesapp.data.domain.UserLocal
import com.example.rutescompartidesapp.data.domain.auth.AuthRequest
import com.example.rutescompartidesapp.data.domain.session.SessionRepository
import com.example.rutescompartidesapp.data.network.rutes_compartides.repository.RutesCompartidesRepository
import com.example.rutescompartidesapp.utils.LocalConstants
import com.example.rutescompartidesapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor (
    private val rutesCompartidesRepository: RutesCompartidesRepository,
    private val sessionRepository: SessionRepository
): ViewModel(){




    private val _authToken = MutableStateFlow("")
    val authToken = _authToken.asStateFlow()
    private val _showErrorToastChannel = Channel<Boolean>()
    val showErrorToastChannel = _showErrorToastChannel.receiveAsFlow()

    private val _errorText = MutableStateFlow("")
    val errorText = _errorText.asStateFlow()

    private suspend fun getToken(authRequest: AuthRequest){
        viewModelScope.launch{
            val result = rutesCompartidesRepository.getToken(authRequest)
            when (result) {
                is Resource.Success -> {
                    result.data?.let { authResult ->
                        _authToken.value = authResult.token
                        _userIsLogged.value = true
                        _isLoading.value = false
                        // Save the credentials in the session
                        sessionRepository.updateIsLogged(true)
                        sessionRepository.updateEmail(authRequest.username)
                        sessionRepository.updatePassword(authRequest.password)
                        _userWantsToLogin.value = false

                    }
                }
                is Resource.Error -> {
                    when (result.message ) {
                        "Not Found" -> {
                            _errorText.value = "Este correo electrónico no está registrado"
                        }
                        "Unauthorized" -> {
                            _errorText.value = "Credenciales erróneas"
                        }
                        else -> {
                            _errorText.value = result.message!!
                        }
                    }
                    _showErrorToastChannel.send(true)
                    _userIsLogged.value = false
                    _userWantsToLogin.value = false
                }
                else -> {}
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
    
    //User password hide and show
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

    private val _user = MutableStateFlow<UserLocal?>(null)
    val user = _user.asStateFlow()

    private fun setUser(user: UserLocal){
        _user.value = user
    }
    fun getUser(userID: Int){
        val user = LocalConstants.userList.first { user -> user.userId == userID }
        setUser(user)
    }

    //User exists
    private val _userIsLogged = MutableStateFlow(false)
    val userIsLogged = _userIsLogged.asStateFlow()

     fun loginLocal(userEmail: String, userPassword: String) {
         val userExists = !LocalConstants.userList.none { user -> user.email == userEmail }
         if (userExists){
             val user = LocalConstants.userList.first { user -> user.email == userEmail }
             if (user.password != userPassword){
                 println("Contrasenya incorrecta")
             } else {
                 // Save the credentials in the session
                 viewModelScope.launch {
                     _userIsLogged.value = true
                     setUser(LocalConstants.userList.first { user -> user.email == userEmail })
                     _isLoading.value = false
                     sessionRepository.updateIsLogged(true)
                     sessionRepository.updateEmail(userEmail)
                     sessionRepository.updatePassword(userPassword)
                     _userWantsToLogin.value = false
                 }
             }
         } else {
             println("Aquest usuari no existeix")

         }
     }


   suspend fun login() {
       getToken(AuthRequest(_userEmail.value, _userPassword.value))
   }

    val initialSetupEvent = liveData {
        emit(sessionRepository.fetchInitialPreferences())
    }

    // Keep the user preferences as a stream of changes
    private val userPreferencesFlow = sessionRepository.sessionPreferencesFlow


    init {
        viewModelScope.launch {
            userPreferencesFlow.collectLatest { session ->
                _userEmail.value = session.email
                _userPassword.value = session.password
                _userIsLogged.value = session.isLogged
                if (session.isLogged){
                    println(session.email)
                    println(session.password)
                    loginLocal(session.email, session.password)
                }
            }
        }
    }
}