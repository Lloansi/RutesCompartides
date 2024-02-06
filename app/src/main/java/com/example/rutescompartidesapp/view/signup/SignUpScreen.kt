package com.example.rutescompartidesapp.view.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.LockOpen
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.rutescompartidesapp.ui.theme.GrayRC

@Composable
fun SignUpScreen () {
    //Variables
    val signUpViewModel: SignUpViewModel = hiltViewModel()
    //Name
    val userNameText by signUpViewModel.userName.collectAsStateWithLifecycle()
    val userNameError by signUpViewModel.userNameError.collectAsStateWithLifecycle()
    //Email
    val userEmailError by signUpViewModel.userEmailError.collectAsStateWithLifecycle()
    val userEmailText by signUpViewModel.userEmail.collectAsStateWithLifecycle()
    //Phone
    val userPhoneError by signUpViewModel.userPhoneError.collectAsStateWithLifecycle()
    val userPhoneText by signUpViewModel.userPhone.collectAsStateWithLifecycle()
    //Password
    val userPasswordError by signUpViewModel.userPasswordError.collectAsStateWithLifecycle()
    val userPasswordText by signUpViewModel.userPassword.collectAsStateWithLifecycle()
    //Repeat Password
    val userRepeatPasswordError by signUpViewModel.userRepeatPasswordError.collectAsStateWithLifecycle()
    val userRepeatPasswordText by signUpViewModel.userRepeatPassword.collectAsStateWithLifecycle()

    //Column
    Column(modifier= Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        //Tittle
        Row(horizontalArrangement=Arrangement.Absolute.Left) {
            Text(text = "Crear un compte",
                color = MaterialTheme.colorScheme.primary,
                )
        }
        Spacer(modifier = Modifier.padding(4.dp))

        //User Name
        Row(){
            OutlinedTextField(value = userNameText, onValueChange = signUpViewModel::onUserNameTextChange,
                leadingIcon = {
                    Icon(imageVector = Icons.Outlined.AccountCircle, contentDescription = "user name icon", tint= Color.LightGray)
                },
                placeholder = {
                    Text(text = "Nom d'Usuari", color = Color.Gray)
                },
                shape = RoundedCornerShape(16.dp),
                //User Name Error
                isError = userNameError,
                trailingIcon = {
                    if (userNameError){
                        Icon(imageVector = Icons.Filled.Cancel,
                            contentDescription = "Error Icon",
                            tint = MaterialTheme.colorScheme.primary)
                    }
                },
                //User Name Text
                supportingText = {
                    if (userNameError){
                        Text(text = "Escriu un nom vàlid",
                            color = MaterialTheme.colorScheme.primary)
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                singleLine = true
            )
        }
        Spacer(modifier = Modifier.padding(4.dp))

        //User Email
        Row () {
            OutlinedTextField(value = userEmailText, onValueChange = signUpViewModel::onUserEmailTextChange,
                leadingIcon = {
                    Icon(imageVector = Icons.Outlined.Mail, contentDescription = "user email icon", tint= Color.LightGray)
                },
                placeholder = {
                    Text(text = "Correu Electrònic", color = Color.Gray)
                },
                shape = RoundedCornerShape(16.dp),
                //User Email Error
                isError = userEmailError,
                trailingIcon = {
                    if (userEmailError){
                        Icon(imageVector = Icons.Filled.Cancel,
                            contentDescription = "Error Icon",
                            tint = MaterialTheme.colorScheme.primary)
                    }
                },
                //User Email Text
                supportingText = {
                    if (userEmailError){
                        Text(text = "Escriu un email vàlid",
                            color = MaterialTheme.colorScheme.primary)
                    }
                },
                //User Email Keyboard
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                singleLine = true
            )

        }
        Spacer(modifier = Modifier.padding(4.dp))

        //User Phone
        Row () {
            OutlinedTextField(value = userPhoneText, onValueChange = signUpViewModel::onUserPhoneTextChange,
                leadingIcon = {
                    Icon(imageVector = Icons.Outlined.Phone, contentDescription = "user phone icon", tint= Color.LightGray)
                },
                placeholder = {
                    Text(text = "Telèfon Mòbil", color = Color.Gray)
                },
                shape = RoundedCornerShape(16.dp),
                //User Phone Error
                isError = userPhoneError,
                trailingIcon = {
                    if (userPhoneError){
                        Icon(imageVector = Icons.Filled.Cancel,
                            contentDescription = "Error Icon",
                            tint = MaterialTheme.colorScheme.primary)
                    }
                },
                //User Phone Text
                supportingText = {
                    if (userPhoneError){
                        Text(text = "Escriu un telèfon vàlid",
                            color = MaterialTheme.colorScheme.primary)
                    }
                },
                //User Phone Keyboard
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Next
                ),
                singleLine = true
            )

        }
        Spacer(modifier = Modifier.padding(4.dp))

        //User Password
        Row () {
            OutlinedTextField(value = userPasswordText, onValueChange = signUpViewModel::onUserPasswordTextChange,
                leadingIcon = {
                    Icon(imageVector = Icons.Outlined.LockOpen, contentDescription = "user password icon", tint= Color.LightGray)
                },
                placeholder = {
                    Text(text = "Contrassenya", color = Color.Gray)
                },
                shape = RoundedCornerShape(16.dp),
                //User Password Error
                isError = userPasswordError,
                trailingIcon = {
                    if (userPasswordError){
                        Icon(imageVector = Icons.Filled.Cancel,
                            contentDescription = "Error Icon",
                            tint = MaterialTheme.colorScheme.primary)
                    }
                },
                //User Password Text
                supportingText = {
                    if (userPasswordError){
                        Text(text = "Escriu una contrassenya vàlida",
                            color = MaterialTheme.colorScheme.primary)
                    }
                },
                //User Password Keyboard
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
                singleLine = true
            )

        }
        Spacer(modifier = Modifier.padding(4.dp))

        //User Repeat Password
        Row () {
            OutlinedTextField(value = userRepeatPasswordText, onValueChange = signUpViewModel::onUserRepeatPasswordTextChange,
                leadingIcon = {
                    Icon(imageVector = Icons.Outlined.LockOpen, contentDescription = "user password icon", tint= Color.LightGray)
                },
                placeholder = {
                    Text(text = "Pepeteix Contrassenya", color = Color.Gray)
                },
                shape = RoundedCornerShape(16.dp),
                //User Repeat Password Error
                isError = userRepeatPasswordError,
                trailingIcon = {
                    if (userRepeatPasswordError){
                        Icon(imageVector = Icons.Filled.Cancel,
                            contentDescription = "Error Icon",
                            tint = MaterialTheme.colorScheme.primary)
                    }
                },
                //User Repeat Password Text
                supportingText = {
                    if (userRepeatPasswordError){
                        Text(text = "Les contrassenyes no coincideixen",
                            color = MaterialTheme.colorScheme.primary)
                    }
                },
                //User Repeat Password Keyboard
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
                singleLine = true
            )

        }
        Spacer(modifier = Modifier.padding(4.dp))

        //Sing Up Button
        Row (){
            ElevatedButton(
                onClick = signUpViewModel::onSignUpButtonClick,
                modifier = Modifier.fillMaxWidth(0.7f),
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.secondary
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(text = "Envia")
            }
        }
        //Bottom Text
        Row(horizontalArrangement=Arrangement.Absolute.Left) {
            Text(text = "Tens un usuari registrat?",
                color = MaterialTheme.colorScheme.primary,
            )
        }
        Spacer(modifier = Modifier.padding(4.dp))

        //FALTA EL TEXT ENLLAÇ AL LOGIN
    }
}

@Preview(showBackground = true)
@Composable

fun SignUpScreenPreview(){
    SignUpScreen()
}