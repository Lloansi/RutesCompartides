package com.example.rutescompartidesapp.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun getResponsivePadding(): Dp {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val defaultPadding = 8.dp  // Default padding value

    // Calculate responsive padding based on screen width
    return (screenWidth * 0.05f).coerceAtMost(defaultPadding)  // 5% of screen width or default padding, whichever is smaller
}