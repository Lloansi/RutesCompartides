package com.example.rutescompartidesapp.view.edit_profile.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType

data class EditProfileTextFieldModel(
    val icon: ImageVector,
    val placeholder: String,
    val keyBoardType: KeyboardType,
)
