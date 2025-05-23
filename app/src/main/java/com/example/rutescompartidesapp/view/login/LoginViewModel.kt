package com.example.rutescompartidesapp.view.login

import android.util.Patterns
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.rutescompartidesapp.data.domain.UserLocal
import com.example.rutescompartidesapp.data.domain.external.auth.AuthRequest
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

/**
 * ViewModel for the login screen.
 *
 * @param rutesCompartidesRepository The repository for Rutes Compartides API interactions.
 * @param sessionRepository The repository for managing user session data.
 */
@HiltViewModel
class LoginViewModel @Inject constructor (
    private val rutesCompartidesRepository: RutesCompartidesRepository,
    private val sessionRepository: SessionRepository
): ViewModel(){

    private val _currentIndex = mutableIntStateOf(0)
    val currentIndex = _currentIndex

    /**
     * Updates the current index for tab navigation.
     *
     * @param index The index to set.
     */
    fun updateCurrentIndex(index: Int){
        _currentIndex.intValue = index
    }

    private val _authToken = MutableStateFlow("")
    val authToken = _authToken.asStateFlow()
    private val _showErrorToastChannel = Channel<Boolean>()
    val showErrorToastChannel = _showErrorToastChannel.receiveAsFlow()

    private val _errorText = MutableStateFlow("")
    val errorText = _errorText.asStateFlow()

    /**
     * Makes an api call to the Login endpoint with the email and password,
     * if it's correct, gets the token and save the credentials into the dataStore
     * @param authRequest the email and password of the user
     */
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

    /**
     * Updates the loading state.
     *
     * @param isLoading Whether the app is in a loading state or not.
     */
    fun onLoading(isLoading: Boolean){
        _isLoading.value = isLoading
    }

    //User email text
    private val _userEmail = MutableStateFlow("")
    val userEmail = _userEmail.asStateFlow()

    /**
     * Updates the user email text.
     *
     * @param text The new email text.
     */
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

    /**
     * Updates the user email error status.
     *
     * @param isError Whether there is an error with the user email or not.
     * @return The error status.
     */
    fun onUserEmailError(isError: Boolean): Boolean {
        _userEmailError.value = isError

        return isError
    }

    //User password text
    private val _userPassword = MutableStateFlow("")
    val userPassword = _userPassword.asStateFlow()

    /**
     * Updates the user password text.
     *
     * @param text The new password text.
     */
    fun onUserPasswordTextChange(text: String) {
        _userPassword.value = text
    }

    //Password visibility
    private val _isPasswordVisible = MutableStateFlow(false)
    val isPasswordVisible = _isPasswordVisible.asStateFlow()
    
    //User password hide and show
    /**
     * Toggles the password visibility.
     */
    fun togglePasswordVisibility() {
        _isPasswordVisible.value = !_isPasswordVisible.value
    }

    //User password error
    private val _userPasswordError = MutableStateFlow(false)
    val userPasswordError = _userPasswordError.asStateFlow()

    /**
     * Updates the user password error status.
     *
     * @param isError Whether there is an error with the user password or not.
     * @return The error status.
     */
    fun onUserPasswordError(isError: Boolean): Boolean {
        _userPasswordError.value = isError
        return isError
    }


    private val _userWantsToLogin = MutableStateFlow(false)
    val userWantsToLogin = _userWantsToLogin.asStateFlow()



    //Sing Up Button
    /**
     * Check if the email and password aren't empty and if the mail has a correct format
     */
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

    /**
     * Sets the user flow value.
     *
     * @param user The user to set.
     */
    private fun setUser(user: UserLocal){
        _user.value = user
    }

    /**
     * Get the user from the userList by the userID
     * @param userID the id of the user
     */
    fun getUser(userID: Int){
        val user = LocalConstants.userList.first { user -> user.userId == userID }
        setUser(user)
    }

    //User exists
    private val _userIsLogged = MutableStateFlow(false)
    val userIsLogged = _userIsLogged.asStateFlow()

    /**
     * Search on the userList if the user exists and checks if the password is correct,
     * if it is, updates the dataStore with the user credentials and set the user as logged
     * @param userEmail the email of the user
     * @param userPassword the password of the user
     */
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

    /**
     * Performs login.
     */
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
                    loginLocal(session.email, session.password)
                }
            }
        }
    }
}