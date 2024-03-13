package com.example.rutescompartidesapp.view.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rutescompartidesapp.ui.theme.MateBlackRC
import com.example.rutescompartidesapp.ui.theme.openSans

@Composable
fun ReviewButtons(modifier: Modifier, buttonText: String){
    TextButton(
        modifier = modifier
            .wrapContentSize()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(15.dp)
            ),

        onClick = {}) {
        Text(
            text = buttonText,
            fontSize = 15.sp,
            color = MateBlackRC,
            fontFamily = openSans
        )
    }
}