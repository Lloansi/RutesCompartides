package com.example.rutescompartidesapp.view.edit_profile.components

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType

/**
 * Model class representing a text field used in the edit profile screen.
 * @property icon The icon to be displayed in the text field.
 * @property placeholder The placeholder text to be displayed when the text field is empty.
 * @property keyBoardType The keyboard type for the text field.
 */
data class EditProfileTextFieldModel(
    val icon: ImageVector,
    val placeholder: String,
    val keyBoardType: KeyboardType,
)
