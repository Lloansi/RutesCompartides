package com.example.rutescompartidesapp.view.profile.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.rutescompartidesapp.ui.theme.OrangeRC

@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun ProfileEditButton(modifier: Modifier, onClick: () -> Unit, animateSize: Float, iconVisible: Boolean){
    Card(modifier = modifier
        .scale(scale = animateSize)
        .width(50.dp)
        .height(50.dp)
        .zIndex(100f)
        .clickable {

        },
        shape = RoundedCornerShape(15.dp),
        colors = if (isSystemInDarkTheme()) CardDefaults.cardColors(containerColor = Color(0xFF858585))
            else CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        IconButton(
            modifier = Modifier
                .fillMaxSize(),
            onClick = {

            },
        ){
            if (iconVisible){
                Image(
                    modifier = modifier
                        .fillMaxSize(0.5f),
                    imageVector = Icons.Default.Edit,
                    contentDescription = null,
                    colorFilter = if (isSystemInDarkTheme())  ColorFilter.tint(Color.White)
                    else ColorFilter.tint(OrangeRC.copy(0.7f))
                )
            }
        }

    }
}