package com.example.rutescompartidesapp.view.generic_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import com.example.rutescompartidesapp.R

@Composable
fun HeaderCurveImage(height: Dp) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)

    ) {
        Image(
            painter = painterResource(id = R.drawable.esfera_rc),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
                .align(Alignment.TopCenter),
            colorFilter = ColorFilter.tint(
                color = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.secondary
                else MaterialTheme.colorScheme.primary
            )
        )
    }
}