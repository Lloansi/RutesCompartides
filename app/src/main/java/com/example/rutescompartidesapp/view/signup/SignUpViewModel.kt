package com.example.rutescompartidesapp.view.signup

import android.util.Patterns
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class SignUpViewModel: ViewModel(){
    //User name text
    private val _userName = MutableStateFlow("")
    val userName = _userName.asStateFlow()

    fun onUserNameTextChange (text: String){
        _userName.value = text
    }

    //User name text error
    private val _userNameError = MutableStateFlow(false)
    val userNameError = _userNameError.asStateFlow()

    fun onUserNameError (isError:Boolean){
       _userNameError.value = isError
    }

    //User email text
    private val _userEmail = MutableStateFlow("")
    val userEmail = _userEmail.asStateFlow()

    fun onUserEmailTextChange (text: String){
        _userEmail.value = text
    }

    //User email error
    private val _userEmailError = MutableStateFlow(false)
    val userEmailError = _userEmailError.asStateFlow()

    fun onUserEmailError (isError:Boolean){
        _userEmailError.value = isError
    }

    //User phone text
    private val _userPhone = MutableStateFlow("")
    val userPhone = _userPhone.asStateFlow()

    fun onUserPhoneTextChange (text: String){
        _userPhone.value = text
    }

    //User phone error
    private val _userPhoneError = MutableStateFlow(false)
    val userPhoneError = _userPhoneError.asStateFlow()

    fun onUserPhoneError (isError:Boolean){
        _userPhoneError.value = isError
    }

    //User password text
    private val _userPassword = MutableStateFlow("")
    val userPassword = _userPassword.asStateFlow()

    fun onUserPasswordTextChange (text: String){
        _userPassword.value = text
    }

    //User password error
    private val _userPasswordError = MutableStateFlow(false)
    val userPasswordError = _userPasswordError.asStateFlow()

    fun onUserPasswordError (isError:Boolean){
        _userPasswordError.value = isError
    }

    //User repeat password text
    private val _userRepeatPassword = MutableStateFlow("")
    val userRepeatPassword = _userRepeatPassword.asStateFlow()

    fun onUserRepeatPasswordTextChange (text: String){
        _userRepeatPassword.value = text

        // Check if passwords match and update error status
        val userPassword = _userPassword.value
        val repeatPassword = text
        val isError = userPassword != repeatPassword

        onUserRepeatPasswordError(isError)
    }

    //User repeat password error
    private val _userRepeatPasswordError = MutableStateFlow(false)
    val userRepeatPasswordError = _userRepeatPasswordError.asStateFlow()

    fun onUserRepeatPasswordError (isError:Boolean){
        _userRepeatPasswordError.value = isError
    }

    //Sing Up Button
    fun onSignUpButtonClick() {
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
            return
        }

        //Verify if passwords match
        if (userPassword != userRepeatPassword) {
            onUserRepeatPasswordError(isError = true)
            return
        }

        onUserNameError(userName.isEmpty())
        onUserEmailError(userEmail.isEmpty())
        onUserPhoneError(userPhone.isEmpty())
        onUserPasswordError(userPassword.isEmpty())
        onUserRepeatPasswordError(userRepeatPassword.isEmpty())
        
       /* if(isAnyFieldEmpty){
            println("Siusplau, completa tots els camps")
        }
       */
    }

}

