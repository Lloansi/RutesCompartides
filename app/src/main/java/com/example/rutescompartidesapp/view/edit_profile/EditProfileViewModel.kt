package com.example.rutescompartidesapp.view.edit_profile

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class EditProfileViewModel: ViewModel() {

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

    fun userNameOnTextChange(value: String){
        _userNameText.value = value
    }
    fun firstNameOnTextChange(value: String){
        _firstNameText.value = value
    }
    fun lastNameOnTextChange(value: String){
        _lastNameText.value = value
    }
    fun emailOnTextChange(value: String){
        _emailText.value = value
    }
    fun phoneNameOnTextChange(value: String){
        _phoneText.value = value
    }
}