package com.example.rutescompartidesapp.view.generic_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.rutescompartidesapp.R

@Composable
fun HeaderSphere(height: Dp) {
    Image(
        painter = painterResource(id = R.drawable.esfera_rc),
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .fillMaxWidth()
            .height(height),
        colorFilter = ColorFilter.tint(
            color = if (isSystemInDarkTheme()) Color(0xFF434343)
            else MaterialTheme.colorScheme.primary
        )
    )
}