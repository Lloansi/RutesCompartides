package com.example.rutescompartidesapp.view.edit_profile

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.rutescompartidesapp.data.domain.UserLocal
import com.example.rutescompartidesapp.data.domain.session.SessionRepository
import com.example.rutescompartidesapp.utils.Constants
import com.example.rutescompartidesapp.utils.LocalConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for editing user profile information.
 *
 * @constructor Creates an [EditProfileViewModel] with the provided [sessionRepository].
 * @property sessionRepository The repository for handling session data.
 */
@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val sessionRepository: SessionRepository) : ViewModel() {

    private val _user = MutableStateFlow<UserLocal?>(null)
    val user = _user.asStateFlow()

    /**
     * Sets the user information to be edited.
     *
     * @param user The user information to be edited.
     */
    fun setUser(user: UserLocal){
        _user.value = user
    }

    private val _userNameText = MutableStateFlow("")
    val userNameText = _userNameText.asStateFlow()

    private val _firstNameText = MutableStateFlow("")
    val firstNameText = _firstNameText.asStateFlow()

    private val _lastNameText = MutableStateFlow("")
    val lastNameText = _lastNameText.asStateFlow()

    private val _emailText = MutableStateFlow("")
    val emailText = _emailText.asStateFlow()

    private val _phoneText = MutableStateFlow("")
    val phoneText = _phoneText.asStateFlow()

    // Methods for handling text changes in input fields

    /**
     * Handles text changes in the username input field.
     *
     * @param value The new value of the username input field.
     */
    fun userNameOnTextChange(value: String) {
        _userNameText.value = value
    }

    /**
     * Update the first name text field.
     * @param value The new value for first name.
     */
    fun firstNameOnTextChange(value: String) {
        _firstNameText.value = value
    }

    /**
     * Update the last name text field.
     * @param value The new value for last name.
     */
    fun lastNameOnTextChange(value: String) {
        _lastNameText.value = value
    }

    /**
     * Update the email text field.
     * @param value The new value for email.
     */
    fun emailOnTextChange(value: String) {
        _emailText.value = value
    }

    /**
     * Update the phone text field.
     * @param value The new value for phone number.
     */
    fun phoneNameOnTextChange(value: String) {
        _phoneText.value = value
    }


    private val _userNameError = MutableStateFlow(false)
    val userNameError = _userNameError.asStateFlow()

    private val _userEmailError = MutableStateFlow(false)
    val userEmailError = _userEmailError.asStateFlow()

    private val _userPhoneError = MutableStateFlow(false)
    val userPhoneError = _userPhoneError.asStateFlow()

    /**
     * Handle user name input error.
     * @param isError True if there is an error, false otherwise.
     * @return The error status.
     */
    fun onUserNameError(isError: Boolean): Boolean {
        _userNameError.value = isError
        return isError
    }

    /**
     * Handle user email input error.
     * @param isError True if there is an error, false otherwise.
     * @return The error status.
     */
    fun onUserEmailError(isError: Boolean): Boolean {
        _userEmailError.value = isError

        return isError
    }

    /**
     * Handle user phone number input error.
     * @param isError True if there is an error, false otherwise.
     * @return The error status.
     */
    fun onUserPhoneError(isError: Boolean): Boolean {
        _userPhoneError.value = isError
        return isError
    }


    // SaveButton onClick handler
    /**
     * Handle click on the Save button.
     * @param navController Navigation controller for navigation operations.
     */
    fun onSaveButtonClick(navController: NavController) {

        val emptyFields =
            _emailText.value.isEmpty() ||
            _userNameText.value.isEmpty() ||
            _phoneText.value.isEmpty()


        if (!LocalConstants.userList.none { user -> user.name == _userNameText.value } ){
            onUserNameError(true)
        } else if (!Patterns.EMAIL_ADDRESS.matcher(_emailText.value).matches()){
            onUserEmailError(true)
        } else if (!_phoneText.value.matches(Regex("^\\s?\\(?\\d{3}\\)?\\d{3}\\d{3}$"))) {
            onUserPhoneError(true)
        } else {
            updateUser()
            navController.navigate("ProfileScreen") {
                popUpTo("ProfileScreen") { inclusive = true }
            }
        }
    }

    // Methods for updating text fields with user information

    /**
     * Updates the username text field with user information.
     */
    fun updateTextFieldsWithUserInfo(){
        _userNameText.value = _user.value!!.name
        _emailText.value = _user.value!!.email
        _phoneText.value = _user.value!!.phone.toString()
    }

    /**
     * Update the user information.
     */
    private fun updateUser(){
        LocalConstants.userList.first { it.userId == user.value!!.userId }.email = _emailText.value
        LocalConstants.userList.first {  it.userId == user.value!!.userId }.name = _userNameText.value
        LocalConstants.userList.first {  it.userId == user.value!!.userId }.phone = _phoneText.value.toInt()

        /* Updatearía el email pero como lo hacemos en LOCAL
         si hacemos esto no podremos hacer login porque
         el email no será el mismo

         viewModelScope.launch {
            sessionRepository.updateEmail(_emailText.value)
        }
         */

    }

}