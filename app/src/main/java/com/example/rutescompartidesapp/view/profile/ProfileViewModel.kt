package com.example.rutescompartidesapp.view.profile

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileViewModel: ViewModel() {

    // Background opacity

    private val _backgroundOpacity = MutableStateFlow(1f)
    val backgroundOpacity = _backgroundOpacity.asStateFlow()

    fun changeBgOpacity(opacity: Float){
        _backgroundOpacity.value = opacity
    }

    // LogOut Popup

    private val _isLogOutPopUpShowing = MutableStateFlow(false)
    val isLogOutPopUpShowing = _isLogOutPopUpShowing.asStateFlow()

    fun onClickLogOut(isPopUpShowing: Boolean){
        _isLogOutPopUpShowing.value = isPopUpShowing
    }

    // User info
    /*
    PD: Estos tres datos deberian ser temorales ya que yo haria un ViewModel para los
    datos del usuario y des de la pantalla de Perfil poder acceder a esos datos del Viewmodel

    private val _fullName = mutableStateOf("")
    val fullName: State<String> = _fullName

    private val _email = mutableStateOf("")
    val email: State<String> = _email

    private val _phoneNumber = mutableStateOf("")
    val phoneNumber: State<String> = _phoneNumber

    */

    // Profile options

    fun onClickMyRoutes(){
        TODO("Not implemented yet")
    }

    fun onClickMyOrders(){
        TODO("Not implemented yet")
    }

    fun onClickCommonPoints(){
        TODO("Not implemented yet")
    }

    fun onClickNotifications(){
        TODO("Not implemented yet")
    }

    fun onClickWorkInfo(){
        TODO("Not implemented yet")
    }

    fun onClickFAQs(){
        TODO("Not implemented yet")
    }



}