package com.example.rutescompartidesapp.view.edit_profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.rutescompartidesapp.ui.theme.fredokaOne
import com.example.rutescompartidesapp.view.edit_profile.components.EditProfileTextFieldModel
import com.example.rutescompartidesapp.view.generic_components.BackButtonArrow
import com.example.rutescompartidesapp.view.generic_components.HeaderSphere
import com.example.rutescompartidesapp.view.login.LoginViewModel

/**
 * Composable function for the edit profile screen.
 * @param loginViewModel The ViewModel for login-related operations.
 * @param navController The NavController for navigation.
 */
@Composable
fun EditProfileScreen(loginViewModel: LoginViewModel, navController: NavController) {
    val editProfileViewModel : EditProfileViewModel = hiltViewModel()
    val user by loginViewModel.user.collectAsStateWithLifecycle()

    if (user == null){
        CircularProgressIndicator()
    } else{
        editProfileViewModel.setUser(user!!)
        editProfileViewModel.updateTextFieldsWithUserInfo()
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier
                    .height(200.dp)
            ) {
                HeaderSphere(200.dp)

                BackButtonArrow(navController = navController, alignment = Alignment.TopStart, "ProfileScreen")

                Text(
                    modifier = Modifier
                        .align(Alignment.Center),
                    text = "Editar perfil",
                    color = Color.White,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = fredokaOne
                )
            }
            Spacer(Modifier.size(height = 30.dp, width = 0.dp))

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
            ) {
                BuildTextFields(editProfileViewModel, textFieldList())

                Spacer(Modifier.size(height = 25.dp, width = 0.dp))

                SaveButton(editProfileViewModel, navController)
            }
        }
    }


}

/**
 * Composable function to build text fields for editing profile information.
 * @param viewModel The ViewModel for editing profile.
 * @param textFieldList The list of text field models.
 */
@Composable
fun BuildTextFields(
    viewModel: EditProfileViewModel,
    textFieldList: List<EditProfileTextFieldModel>
) {
    val userNameText by viewModel.userNameText.collectAsState()
    val userNameError by viewModel.userNameError.collectAsState()

    val emailText by viewModel.emailText.collectAsState()
    val emailError by viewModel.userEmailError.collectAsState()

    val phoneText by viewModel.phoneText.collectAsState()
    val phoneError by viewModel.userPhoneError.collectAsState()

    for (field in textFieldList.indices) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .padding(top = 10.dp, bottom = 10.dp),
            value = when (field) {
                0 -> userNameText
                1 -> emailText
                2 -> phoneText
                else -> ""
            },
            onValueChange = { value ->
                when (field) {
                    0 -> viewModel.userNameOnTextChange(value)
                    1 -> viewModel.emailOnTextChange(value)
                    2 -> viewModel.phoneNameOnTextChange(value)
                    else -> "Error" + value
                }
            },
            isError = when (field) {
                0 -> userNameError
                3 -> emailError
                4 -> phoneError
                else -> phoneError
            },
            supportingText = {
                when(field) {
                    0 -> if (userNameError){
                        Text(text = "Aquest nom d'usuari ja existeix",
                            color = MaterialTheme.colorScheme.primary)
                    }
                    3 -> if (emailError){
                        Text(text = "Escriu un email vàlid",
                            color = MaterialTheme.colorScheme.primary)
                    }
                    4 -> if (phoneError){
                        Text(text = "Escriu un telèfon vàlid",
                            color = MaterialTheme.colorScheme.primary)
                    }
                }
            },
            leadingIcon = {
                Icon(
                    imageVector = textFieldList[field].icon,
                    contentDescription = textFieldList[field].placeholder + "icon",
                    tint = Color.LightGray
                )
            },
            placeholder = {
                Text(text = textFieldList[field].placeholder, color = Color.Gray)
            },
            shape = RoundedCornerShape(16.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = textFieldList[field].keyBoardType,
            ),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                errorContainerColor = MaterialTheme.colorScheme.primaryContainer,
            )
        )
    }
}

/**
 * Composable function for the save button.
 * @param viewModel The ViewModel for editing profile.
 * @param navController The NavController for navigation.
 */
@Composable
fun SaveButton(viewModel: EditProfileViewModel, navController: NavController) {

    ElevatedButton(
        onClick = {
                  viewModel.onSaveButtonClick(navController)
        },
        modifier = Modifier
            .fillMaxWidth(0.85f)
            .height(LocalConfiguration.current.screenHeightDp.dp / 16),
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.secondary
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            text = "Desa",
            style = MaterialTheme.typography.titleMedium,
            color = Color.White
        )
    }
}

/**
 * Function to provide a list of edit profile text field models.
 * @return A list of [EditProfileTextFieldModel].
 */
fun textFieldList(): List<EditProfileTextFieldModel> {
    return listOf(
        EditProfileTextFieldModel(
            Icons.Rounded.Person,
            "Nom d'usuari",
            KeyboardType.Text
        ),
        EditProfileTextFieldModel(
            Icons.Rounded.Email,
            "Correu electrònic",
            KeyboardType.Email
        ),
        EditProfileTextFieldModel(
            Icons.Rounded.Phone,
            "Telèfon",
            KeyboardType.Phone
        ),
    )
}