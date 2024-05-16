package com.example.rutescompartidesapp.view.generic_components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Route
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

/**
 * Composable function to display a text field for measurements.
 *
 * @param value The current value of the text field.
 * @param onValueChange Callback to be invoked when the value changes.
 * @param placeholder The placeholder text to be displayed when the field is empty.
 * @param suffix The text to be displayed as a suffix.
 * @param isError Indicates if the field has an error.
 * @param keyboardType The keyboard type for the text field.
 */
@Composable
fun MeasurementsTextField(value: String, onValueChange: (String) -> Unit, placeholder: String, suffix: String,
                          isError: Boolean, keyboardType: KeyboardType){
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(0.95f),
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(text = placeholder, color = Color.Gray)
        },
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
            unfocusedIndicatorColor = Color.Gray,
            disabledContainerColor = MaterialTheme.colorScheme.background,
            disabledIndicatorColor = Color.Gray,
            focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        suffix = {
            Text(text = suffix, color = Color.Gray)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = ImeAction.Next),
        singleLine = true,
        isError = isError,
        supportingText = {
            if (isError) {
                Text(text = "Aquest camp és obligatori", color = Color.Red)
            }
        },
        trailingIcon = {
            if (isError){
                Icon(imageVector = Icons.Filled.Cancel,
                    contentDescription = "Error Icon",
                    tint = MaterialTheme.colorScheme.primary)
            }
        }
        )
    Spacer(modifier = Modifier.padding(4.dp))
}

/**
 * Composable function to display a multiline text field.
 *
 * @param value The current value of the text field.
 * @param onValueChange Callback to be invoked when the value changes.
 * @param placeholder The placeholder text to be displayed when the field is empty.
 * @param isError Indicates if the field has an error.
 */
@Composable
fun MultilineTextField(value: String, onValueChange: (String) -> Unit, placeholder: String, isError: Boolean){
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .height(LocalConfiguration.current.screenWidthDp.dp / 2),
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(text = placeholder, color = Color.Gray)
        },
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
            unfocusedIndicatorColor = Color.Gray,
            disabledContainerColor = MaterialTheme.colorScheme.background,
            disabledIndicatorColor = Color.Gray,
            focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next
        ),
        singleLine = false,
        maxLines = 7,
        isError = isError,
        supportingText = {
            if (isError) {
                Text(text = "Aquest camp és obligatori", color = Color.Red)
            }
        },
        trailingIcon = {
            if (isError){
                Icon(imageVector = Icons.Filled.Cancel,
                    contentDescription = "Error Icon",
                    tint = MaterialTheme.colorScheme.primary)
            }
        }
    )
    Spacer(modifier = Modifier.padding(4.dp))
}

/**
 * Composable function to display a basic text field.
 *
 * @param value The current value of the text field.
 * @param onValueChange Callback to be invoked when the value changes.
 * @param placeholder The placeholder text to be displayed when the field is empty.
 * @param isError Indicates if the field has an error.
 * @param keyboardType The keyboard type for the text field.
 */
@Composable
fun BasicTextField(value: String, onValueChange: (String) -> Unit, placeholder: String, isError: Boolean,
                   keyboardType: KeyboardType = KeyboardType.Text){
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(0.95f),
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(text = placeholder, color = Color.Gray)
        },
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
            unfocusedIndicatorColor = Color.Gray,
            disabledContainerColor = MaterialTheme.colorScheme.background,
            disabledIndicatorColor = Color.Gray,
            focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = ImeAction.Next),
        singleLine = true,
        isError = isError,
        supportingText = {
            if (isError) {
                Text(text = "Aquest camp és obligatori", color = Color.Red)
            }
        },
        trailingIcon = {
            if (isError){
                Icon(imageVector = Icons.Filled.Cancel,
                    contentDescription = "Error Icon",
                    tint = MaterialTheme.colorScheme.primary)
            }
        }
        )
    Spacer(modifier = Modifier.padding(4.dp))
}


/**
 * Composable function to display a text field with an icon.
 *
 * @param value The current value of the text field.
 * @param onValueChange Callback to be invoked when the value changes.
 * @param placeholder The placeholder text to be displayed when the field is empty.
 * @param leadingIcon The leading icon to be displayed in the text field.
 * @param isError Indicates if the field has an error.
 */

@Composable
fun IconTextField(value: String, onValueChange: (String) -> Unit, placeholder: String, leadingIcon: @Composable () -> Unit,
                  isError: Boolean){
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(0.95f),
        value = value,
        onValueChange = onValueChange,
        leadingIcon = {
            leadingIcon()
        },
        placeholder = {
            Text(text = placeholder, color = Color.Gray)
        },
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
            unfocusedIndicatorColor = Color.Gray,
            disabledContainerColor = MaterialTheme.colorScheme.background,
            disabledIndicatorColor = Color.Gray,
            focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next),
        singleLine = true,
        isError = isError,
        supportingText = {
            if (isError) {
                Text(text = "Aquest camp és obligatori", color = Color.Red)
            }
        },
        trailingIcon = {
            if (isError){
                Icon(imageVector = Icons.Filled.Cancel,
                    contentDescription = "Error Icon",
                    tint = MaterialTheme.colorScheme.primary)
            }
        }
        )
    Spacer(modifier = Modifier.padding(4.dp))
}

/**
 * Composable function to display a text field for steps.
 *
 * @param modifier The modifier for the text field.
 * @param value The current value of the text field.
 * @param onValueChange Callback to be invoked when the value changes.
 */
@Composable
fun StepTextField(modifier: Modifier, value: String, onValueChange: (String) -> Unit){
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Route, contentDescription = "Step Route Icon")
        },
        placeholder = {
            Text(text = "Punt intermedi", color = Color.Gray)
        },
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
            unfocusedIndicatorColor = Color.Gray,
            disabledContainerColor = MaterialTheme.colorScheme.background,
            disabledIndicatorColor = Color.Gray,
            focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next),
        singleLine = true,
        )
    Spacer(modifier = Modifier.padding(4.dp))
}

/**
 * Composable function to display a text field for date and time picker.
 *
 * @param invocation Callback to be invoked when the field is clicked.
 * @param time The current time value of the text field.
 * @param onValueChange Callback to be invoked when the value changes.
 * @param placeholder The placeholder text to be displayed when the field is empty.
 * @param icon The icon for the text field.
 * @param isError Indicates if the field has an error.
 */
@Composable
fun DateTimePickerTextField(
    invocation: () -> Unit, time: String, onValueChange: (String) -> Unit,
    placeholder: String, icon: ImageVector, isError: Boolean){
    // Time Picker TextField
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .clickable {
                invocation()
            },
        value = time,
        onValueChange = onValueChange,
        placeholder =  {
            Text(text = placeholder, color = Color.Gray)
        },
        enabled = false,
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = "$placeholder Icon",
                tint = Color.Gray
            )
        },
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
            unfocusedIndicatorColor = Color.Gray,
            disabledContainerColor = MaterialTheme.colorScheme.background,
            disabledIndicatorColor = Color.Gray,
            disabledTextColor = MaterialTheme.colorScheme.onBackground,
            focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next
        ),
        singleLine = true,
        isError = isError,
        supportingText = {
            if (isError) {
                Text(text = "Aquest camp és obligatori", color = Color.Red)
            }
        },
        trailingIcon = {
            if (isError){
                Icon(imageVector = Icons.Filled.Cancel,
                    contentDescription = "Error Icon",
                    tint = MaterialTheme.colorScheme.primary)
            }
        }
        )
    Spacer(modifier = Modifier.padding(4.dp))
}