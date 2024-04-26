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

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val sessionRepository: SessionRepository) : ViewModel() {

    private val _user = MutableStateFlow<UserLocal?>(null)
    val user = _user.asStateFlow()

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

    fun updateTextFieldsWithUserInfo(){
        _userNameText.value = _user.value!!.name
        _emailText.value = _user.value!!.email
        _phoneText.value = _user.value!!.phone.toString()
    }

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