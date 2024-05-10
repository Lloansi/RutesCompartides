package com.example.rutescompartidesapp.view.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rutescompartidesapp.data.domain.session.SessionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the profile screen.
 *
 * @param sessionRepository The repository for managing user session data.
 */
@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val sessionRepository: SessionRepository,
): ViewModel() {

    // Background opacity

    private val _backgroundOpacity = MutableStateFlow(1f)
    val backgroundOpacity = _backgroundOpacity.asStateFlow()

    fun changeBgOpacity(opacity: Float){
        _backgroundOpacity.value = opacity
    }

    // LogOut Popup

    private val _isLogOutPopUpShowing = MutableStateFlow(false)
    val isLogOutPopUpShowing = _isLogOutPopUpShowing.asStateFlow()

    fun onClickLogOutPopUpShow(isPopUpShowing: Boolean){
        _isLogOutPopUpShowing.value = isPopUpShowing
    }


    private val _wantsToLogOut = MutableStateFlow(false)
    val wantsToLogOut = _wantsToLogOut.asStateFlow()

    fun onClickLogOut(wantsToLogOut : Boolean){
        _wantsToLogOut.value = wantsToLogOut
    }

    private val _isLoggedOut = MutableStateFlow(false)
    val isLoggedOut = _isLoggedOut.asStateFlow()
    fun onLogOutChange(isLoggedOut : Boolean){
        _isLoggedOut.value = isLoggedOut
    }

    suspend fun updateSession(){
        viewModelScope.launch {
            sessionRepository.updateIsLogged(false)
            sessionRepository.updateEmail("")
            sessionRepository.updatePassword("")
            onLogOutChange(true)
        }
    }

    // Item onClick Placeholder
    private val _onClickPlaceholder = MutableStateFlow(false)
    val onClickPlaceholder = _onClickPlaceholder.asStateFlow()

    // Profile Button onClick Animation
    private val _editProfileButtonSize = MutableStateFlow(1f)
    var editProfileButtonSize = _editProfileButtonSize.asStateFlow()

    fun onClickChangeSize(newSize: Float) {
        _editProfileButtonSize.value = newSize
    }

    // Profile Button visibility
    private val _editProfileButtonVisible = MutableStateFlow(true)
    var editProfileButtonVisible = _editProfileButtonVisible.asStateFlow()

    fun onClickChangeVisibility(newVisibility: Boolean) {
        _editProfileButtonVisible.value = newVisibility
    }


    fun onClickItemPlaceholder(isShowingAlert: Boolean) {
        _onClickPlaceholder.value = isShowingAlert
    }


}