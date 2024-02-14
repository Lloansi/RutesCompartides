package com.example.rutescompartidesapp.view.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ProfileEditButton(modifier: Modifier, onClick: () -> Unit){
    Card(modifier = modifier
        .width(50.dp)
        .height(50.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        IconButton(
            modifier = Modifier
                .fillMaxSize(),
            onClick = onClick
        ){
            Image(
                imageVector = Icons.Default.Check,
                contentDescription = null,
            )
        }

    }
}