package com.example.rutescompartidesapp.view.signup

import android.provider.ContactsContract.CommonDataKinds.Phone
import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.rutescompartidesapp.data.domain.User
import com.example.rutescompartidesapp.utils.Constants
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

    fun onUserNameError (isError:Boolean): Boolean{
       _userNameError.value = isError
        return isError
    }

    //User email text
    private val _userEmail = MutableStateFlow("")
    val userEmail = _userEmail.asStateFlow()

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

    fun onUserPhoneTextChange(text: String) {
        _userPhone.value = text
    }

    fun onUserPhoneError(isError: Boolean): Boolean {
        _userPhoneError.value = isError
        return isError
    }

    //User password text
    private val _userPassword = MutableStateFlow("")
    val userPassword = _userPassword.asStateFlow()

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
        _isPasswordVisible.value = !_isPasswordVisible.value
    }

    //User password error
    private val _userPasswordError = MutableStateFlow(false)
    val userPasswordError = _userPasswordError.asStateFlow()

    fun onUserPasswordError (isError:Boolean): Boolean{
        _userPasswordError.value = isError
        return isError
    }

    //User password hide and show

    //User repeat password text
    private val _userRepeatPassword = MutableStateFlow("")
    val userRepeatPassword = _userRepeatPassword.asStateFlow()


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

    fun onUserRepeatPasswordError (isError:Boolean): Boolean{
        _userRepeatPasswordError.value = isError
        return isError
    }

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
            onUserPhoneError(isError = true);
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
    private fun createUser(name:String, phone: Int, userEmail: String, password: String): Boolean{
        val newUser = User(Constants.userList.size + 1, name, userEmail, phone, password)

        return if (Constants.userList.none { user -> user.email == userEmail }) {
            Constants.userList.add(newUser)
            true
        } else {
            false
        }
    }

}

