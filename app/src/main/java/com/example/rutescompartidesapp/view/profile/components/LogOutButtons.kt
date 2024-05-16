package com.example.rutescompartidesapp.view.profile.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.rutescompartidesapp.ui.theme.MateBlackRC

@Composable
fun LogOutButton(buttonText: String, onClick: () -> Unit){

    var buttonBgColor = MaterialTheme.colorScheme.secondary

    if (buttonText == "Si"){
        buttonBgColor = MaterialTheme.colorScheme.primary
    }

    Button(
        colors = ButtonDefaults.buttonColors(buttonBgColor),
        modifier = Modifier
            .width(130.dp)
            .padding(start = 10.dp, end = 10.dp, top = 20.dp),
        onClick = onClick
    ) {
        Text(text = buttonText, color = if (isSystemInDarkTheme()) Color.White else MateBlackRC)
    }
}