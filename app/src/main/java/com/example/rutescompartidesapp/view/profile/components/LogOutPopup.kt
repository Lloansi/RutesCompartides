package com.example.rutescompartidesapp.view.profile.components


import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.rutescompartidesapp.ui.theme.MateBlackRC
import com.example.rutescompartidesapp.view.login.LoginViewModel
import com.example.rutescompartidesapp.view.profile.ProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogOutPopup(viewModelProfile: ProfileViewModel, navigator: NavController, loginViewModel: LoginViewModel) {

    val isLogOutPopUpShowing by viewModelProfile.isLogOutPopUpShowing.collectAsStateWithLifecycle()
    val wantsToLogOut by viewModelProfile.wantsToLogOut.collectAsStateWithLifecycle()
    val isLoggedOut by viewModelProfile.isLoggedOut.collectAsStateWithLifecycle()

    if (wantsToLogOut){
    LaunchedEffect(true){
        viewModelProfile.updateSession()
        viewModelProfile.onClickLogOut(false)
        }
    }
    if (isLoggedOut){
        viewModelProfile.onLogOutChange(false)
        navigator.navigate("LoginScreen") {
            popUpTo("LoginScreen") { inclusive = true }
        }
    }

    if (isLogOutPopUpShowing) {
        AlertDialog(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            onDismissRequest = { viewModelProfile.onClickLogOutPopUpShow(false) },
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            )
        ) {
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .fillMaxHeight(0.2f),
                shape = RoundedCornerShape(15.dp),
                colors = CardDefaults.cardColors(containerColor = if (isSystemInDarkTheme()) Color(0xFF434343) else Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Estàs segur que desitges tancar sessió?", color = if (isSystemInDarkTheme()) Color.White else MateBlackRC )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        LogOutButton(buttonText = "No") { viewModelProfile.onClickLogOutPopUpShow(false) }
                        LogOutButton(buttonText = "Si") {
                            loginViewModel.updateCurrentIndex(0)
                            viewModelProfile.onClickLogOutPopUpShow(false)
                            viewModelProfile.onClickLogOut(true)
                        }
                    }
                }
            }

        }

    }

}