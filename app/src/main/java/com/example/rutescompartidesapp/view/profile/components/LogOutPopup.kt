package com.example.rutescompartidesapp.view.profile.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.example.rutescompartidesapp.view.profile.ProfileViewModel

@Composable
fun LogOutPopup(viewModelProfile: ProfileViewModel){

    val isLogOutPopUpShowing by viewModelProfile.isLogOutPopUpShowing.collectAsState()

    if (isLogOutPopUpShowing) {
        Popup(
            onDismissRequest = {  },
            properties = PopupProperties(
                focusable = true,
                dismissOnBackPress = true,
                dismissOnClickOutside=  true
            )
        ) {
            TODO("Not implemented yet")
        }
    }

}