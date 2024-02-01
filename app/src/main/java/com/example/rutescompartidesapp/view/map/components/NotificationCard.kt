package com.example.rutescompartidesapp.view.map.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun NotificationButtonCard(){
    Box(modifier = Modifier
        .wrapContentWidth()
        .wrapContentHeight()
        .wrapContentHeight()
        //.padding(top = 3.dp)
        .clip(RoundedCornerShape(12.dp))
        .background(color = MaterialTheme.colorScheme.primary)
        .clickable { }
    ){
        Icon(
            imageVector = Icons.Default.Notifications,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .padding(8.dp)
            //.size(24.dp) // icono de dentro
        )
    }
}