package com.example.rutescompartidesapp.data.domain

import android.media.Image
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import com.example.rutescompartidesapp.R

data class ProfileItems(
    val id: Int,
    val title: String,
    val subtitle: String,
)