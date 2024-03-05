package com.example.rutescompartidesapp.view.edit_profile

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.rutescompartidesapp.utils.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class EditProfileViewModel : ViewModel() {

    private val _userNameText = MutableStateFlow(Constants.userList[0].name)
    val userNameText = _userNameText.asStateFlow()

    private val _firstNameText = MutableStateFlow(Constants.userList[0].name)
    val firstNameText = _firstNameText.asStateFlow()

    private val _lastNameText = MutableStateFlow(Constants.userList[0].name)
    val lastNameText = _lastNameText.asStateFlow()

    private val _emailText = MutableStateFlow(Constants.userList[0].email)
    val emailText = _emailText.asStateFlow()

    private val _phoneText = MutableStateFlow(Constants.userList[0].phone.toString())
    val phoneText = _phoneText.asStateFlow()

    fun userNameOnTextChange(value: String) {
        _userNameText.value = value
    }

    fun firstNameOnTextChange(value: String) {
        _firstNameText.value = value
    }

    fun lastNameOnTextChange(value: String) {
        _lastNameText.value = value
    }

    fun emailOnTextChange(value: String) {
        _emailText.value = value
    }

    fun phoneNameOnTextChange(value: String) {
        _phoneText.value = value
    }


    private val _userNameError = MutableStateFlow(false)
    val userNameError = _userNameError.asStateFlow()

    private val _userEmailError = MutableStateFlow(false)
    val userEmailError = _userEmailError.asStateFlow()

    private val _userPhoneError = MutableStateFlow(false)
    val userPhoneError = _userPhoneError.asStateFlow()

    fun onUserNameError(isError: Boolean): Boolean {
        _userNameError.value = isError
        return isError
    }

    fun onUserEmailError(isError: Boolean): Boolean {
        _userEmailError.value = isError

        return isError
    }

    fun onUserPhoneError(isError: Boolean): Boolean {
        _userPhoneError.value = isError
        return isError
    }


    // SaveButton onClick
    fun onSaveButtonClick(navController: NavController) {

        val emptyFields =
            _emailText.value.isEmpty() ||
            _userNameText.value.isEmpty() ||
            _phoneText.value.isEmpty()

        if (!Constants.userList.none { user -> user.name == _userNameText.value } ){
            onUserNameError(true)
        } else if (!Patterns.EMAIL_ADDRESS.matcher(_emailText.value).matches()){
            onUserEmailError(true)
        } else if (!_phoneText.value.matches(Regex("^\\s?\\(?\\d{3}\\)?\\d{3}\\d{3}$"))) {
            onUserPhoneError(true)
        } else {
            navController.navigate("ProfileScreen") {
                popUpTo("ProfileScreen") { inclusive = true }
            }
        }
    }
}