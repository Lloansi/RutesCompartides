package com.example.rutescompartidesapp.view.signup

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.rutescompartidesapp.data.domain.UserLocal
import com.example.rutescompartidesapp.utils.LocalConstants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel class for the SignUpFragment. Handles user sign-up logic and validation.
 */
class SignUpViewModel: ViewModel(){
    //User name text
    private val _userName = MutableStateFlow("")
    val userName = _userName.asStateFlow()

    /**
     * Handles changes in the user name input field.
     * @param text The text entered by the user.
     */
    fun onUserNameTextChange (text: String){
        _userName.value = text
        _userNameError.value = _userName.value.length > 12
    }

    //User name text error
    private val _userNameError = MutableStateFlow(false)
    val userNameError = _userNameError.asStateFlow()

    /**
     * Handles user name errors.
     * @param isError Indicates if there is an error with the user name.
     * @return The error status.
     */
    fun onUserNameError (isError:Boolean): Boolean{
       _userNameError.value = isError
        return isError
    }

    //User email text
    private val _userEmail = MutableStateFlow("")
    val userEmail = _userEmail.asStateFlow()

    /**
     * Handles changes in the user email input field.
     * @param text The text entered by the user.
     */
    fun onUserEmailTextChange (text: String){
        _userEmail.value = text

        //Check if the email is correct and update error status
        val userEmail = _userEmail.value
        val isError = userEmail !=text

        onUserEmailError(isError)
    }

    //User email error
    private val _userEmailError = MutableStateFlow(false)
    val userEmailError = _userEmailError.asStateFlow()

    /**
     * Handles user email errors.
     * @param isError Indicates if there is an error with the user email.
     * @return The error status.
     */
    fun onUserEmailError (isError:Boolean) : Boolean{
        _userEmailError.value = isError

        return isError
    }

    // User phone text
    private val _userPhone = MutableStateFlow("")
    val userPhone = _userPhone.asStateFlow()

    // User phone error
    private val _userPhoneError = MutableStateFlow(false)
    val userPhoneError = _userPhoneError.asStateFlow()

    /**
     * Handles changes in the user phone input field.
     * @param text The text entered by the user.
     */
    fun onUserPhoneTextChange(text: String) {
        _userPhone.value = text
    }

    /**
     * Handles user phone errors.
     * @param isError Indicates if there is an error with the user phone.
     * @return The error status.
     */
    fun onUserPhoneError(isError: Boolean): Boolean {
        _userPhoneError.value = isError
        return isError
    }

    //User password text
    private val _userPassword = MutableStateFlow("")
    val userPassword = _userPassword.asStateFlow()

    /**
     * Handles changes in the user password input field.
     * @param text The text entered by the user.
     */
    fun onUserPasswordTextChange (text: String){
        _userPassword.value = text
    }

    //Password visibility
    private val _isPasswordVisible = MutableStateFlow(false)
    val isPasswordVisible = _isPasswordVisible.asStateFlow()

    fun togglePasswordVisibility() {
        _isPasswordVisible.value = !_isPasswordVisible.value
    }

    //Repeat Password visibility
    private val _isRepeatPasswordVisible = MutableStateFlow(false)
    val isRepeatPasswordVisible = _isPasswordVisible.asStateFlow()

    fun toggleRepeatPasswordVisibility() {
        _isRepeatPasswordVisible.value = !_isRepeatPasswordVisible.value
    }

    //User password error
    private val _userPasswordError = MutableStateFlow(false)
    val userPasswordError = _userPasswordError.asStateFlow()

    /**
     * Handles user password errors.
     * @param isError Indicates if there is an error with the user password.
     * @return The error status.
     */
    fun onUserPasswordError (isError:Boolean): Boolean{
        _userPasswordError.value = isError
        return isError
    }

    //User password hide and show

    //User repeat password text
    private val _userRepeatPassword = MutableStateFlow("")
    val userRepeatPassword = _userRepeatPassword.asStateFlow()

    /**
     * Handles changes in the user repeat password input field.
     * @param text The text entered by the user.
     */
    fun onUserRepeatPasswordTextChange (text: String) {
        _userRepeatPassword.value = text

        // Check if passwords match and update error status
        val userPassword = _userPassword.value
        val isError = userPassword != text

        onUserRepeatPasswordError(isError)
    }

    //User repeat password error
    private val _userRepeatPasswordError = MutableStateFlow(false)
    val userRepeatPasswordError = _userRepeatPasswordError.asStateFlow()

    /**
     * Handles user repeat password errors.
     * @param isError Indicates if there is an error with the user repeat password.
     * @return The error status.
     */
    fun onUserRepeatPasswordError (isError:Boolean): Boolean{
        _userRepeatPasswordError.value = isError
        return isError
    }

    /**
     * Handles sign-up button click event.
     * @param navController The NavController for navigating to other destinations.
     */
    //Sing Up Button
    fun onSignUpButtonClick(navController: NavController) {
    //Aqui va la lògica per fer el registre

        // Variables to obtain the values of the input
        val userName = _userName.value
        val userEmail = _userEmail.value
        val userPhone = _userPhone.value
        val userPassword = _userPassword.value
        val userRepeatPassword = _userRepeatPassword.value

        // Verify if any field is empty
        //val isAnyFieldEmpty = userName.isEmpty() || userEmail.isEmpty() || userPhone.isEmpty() || userPassword.isEmpty() || userRepeatPassword.isEmpty()

        //Check the Email format
        if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
            onUserEmailError(isError = true)
        }

        //Check the Phone format
        if (!userPhone.matches(Regex("^\\s?\\(?\\d{3}\\)?\\d{3}\\d{3}$"))) {
            onUserPhoneError(isError = true)
        }

        if (!onUserNameError(userName.isEmpty()) ||
            !onUserEmailError(userEmail.isEmpty())||
            !onUserPhoneError(userPhone.isEmpty()) ||
            !onUserPasswordError(userPassword.isEmpty()) ||
            !onUserRepeatPasswordError(userRepeatPassword.isEmpty())
            ) {
           if (createUser(userName,userPhone.toInt(), userEmail, userPassword)) {
               navController.navigate("LoginScreen") {
                   popUpTo("LoginScreen") { inclusive = true }
               }
           }
        }
    }

    //User exists
    private val _userExists = MutableStateFlow(false)
    val userExists = _userExists.asStateFlow()

    /**
     * Creates a new user if it doesn't already exist.
     * @param name The name of the user.
     * @param phone The phone number of the user.
     * @param userEmail The email address of the user.
     * @param password The password of the user.
     * @return True if the user is created successfully, false otherwise.
     */
    private fun createUser(name:String, phone: Int, userEmail: String, password: String): Boolean{
        val nextID = LocalConstants.userList.maxOfOrNull { it.userId }!! +1
       val newUser = UserLocal(nextID, name, userEmail, phone, password)

        return if (LocalConstants.userList.none { user -> user.email == userEmail }) {
            LocalConstants.userList.add(newUser)
            true
        } else {
            false
        }
    }

}

