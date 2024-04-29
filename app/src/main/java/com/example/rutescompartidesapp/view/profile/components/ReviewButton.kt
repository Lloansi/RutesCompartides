package com.example.rutescompartidesapp.view.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.rutescompartidesapp.ui.theme.MateBlackRC
import com.example.rutescompartidesapp.ui.theme.openSans

@Composable
fun ReviewButton(modifier: Modifier, buttonText: String, navController: NavController){
    TextButton(
        modifier = modifier
            .wrapContentSize()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(15.dp)
            ),

        onClick = {
            navController.navigate("UserReviewScreen") {
                popUpTo("UserReviewScreen") { inclusive = true }
            }
        }) {
        Text(
            text = buttonText,
            fontSize = 15.sp,
            color = MateBlackRC,
            fontFamily = openSans
        )
    }
}