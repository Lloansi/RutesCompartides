package com.example.rutescompartidesapp.view.map.components

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.example.rutescompartidesapp.ui.theme.MateBlackRC
import com.example.rutescompartidesapp.view.MainActivity
import com.example.rutescompartidesapp.view.map.viewModels.MapViewModel
import com.example.rutescompartidesapp.view.profile.components.LogOutButton
import kotlin.system.exitProcess

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackButtonPopUp(mapViewModel: MapViewModel){
    AlertDialog(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        onDismissRequest = {
            mapViewModel.onClickBackButton(false)
        },
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
                Text(text = "Estàs segur que desitges sortir de l'aplicació?", color = if (isSystemInDarkTheme()) Color.White else MateBlackRC )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    LogOutButton(buttonText = "No") {
                        mapViewModel.onClickBackButton(false)
                    }
                    LogOutButton(buttonText = "Si") {
                        val activity = MainActivity()
                        activity.finish()
                        exitProcess(0)
                    }
                }
            }
        }

    }

}