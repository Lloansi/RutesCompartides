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

    // Item onClick Placeholder
    private val _onClickPlaceholder = MutableStateFlow(false)
    val onClickPlaceholder = _onClickPlaceholder.asStateFlow()

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

    fun onClickItemPlaceholder(isShowingAlert: Boolean) {
        _onClickPlaceholder.value = isShowingAlert
    }


}