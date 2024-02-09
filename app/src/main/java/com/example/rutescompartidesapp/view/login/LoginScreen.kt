package com.example.rutescompartidesapp.view.login

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
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.LockOpen
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen

@Composable
fun LoginScreen(){
    //Email
    val loginViewModel: LoginViewModel = hiltViewModel()
    val userEmailError by loginViewModel.userEmailError.collectAsStateWithLifecycle()
    val userEmailText by loginViewModel.userEmail.collectAsStateWithLifecycle()
    //Password
    val userPasswordError by loginViewModel.userPasswordError.collectAsStateWithLifecycle()
    val userPasswordText by loginViewModel.userPassword.collectAsStateWithLifecycle()
    val isPasswordVisible by loginViewModel.isPasswordVisible.collectAsStateWithLifecycle()
    //Repeat Password
    val userRepeatPasswordError by loginViewModel.userRepeatPasswordError.collectAsStateWithLifecycle()
    val userRepeatPasswordText by loginViewModel.userRepeatPassword.collectAsStateWithLifecycle()
    val isRepeatPasswordVisible by loginViewModel.isRepeatPasswordVisible.collectAsStateWithLifecycle()


    Column(modifier= Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        //Tittle
        Row(horizontalArrangement = Arrangement.Absolute.Left) {
            Text(
                text = "Crear un compte",
                color = MaterialTheme.colorScheme.primary,
            )
        }
        Spacer(modifier = Modifier.padding(4.dp))

        //User Email
        Row () {
            OutlinedTextField(value = userEmailText, onValueChange = loginViewModel::onUserEmailTextChange,
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
        //User Password
        Row () {


            OutlinedTextField(
                value = userPasswordText, onValueChange = loginViewModel::onUserPasswordTextChange,


                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.LockOpen,
                        contentDescription = "user password icon",
                        tint = Color.LightGray
                    )
                },
                placeholder = {
                    Text(text = "Contrassenya", color = Color.Gray)
                },
                shape = RoundedCornerShape(16.dp),

                //User Password Visibility
                visualTransformation =
                if (isPasswordVisible) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },

                //User Password Error
                isError = userPasswordError,
                trailingIcon = {
                    val image =
                        if (isPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    IconButton(onClick = {loginViewModel.togglePasswordVisibility()}) {
                        Icon(imageVector = image, contentDescription = "toggle password icon" )
                    }
                    if (userPasswordError) {
                        Icon(
                            imageVector = Icons.Filled.Cancel,
                            contentDescription = "Error Icon",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },

                //User Password Text
                supportingText = {

                    if (userPasswordError) {
                        Text(
                            text = "Escriu una contrassenya vàlida",
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                },

                //User Password Keyboard
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
                singleLine = true,

                )

        }
        Spacer(modifier = Modifier.padding(4.dp))

        //User Repeat Password
        Row () {
            OutlinedTextField(value = userRepeatPasswordText, onValueChange = loginViewModel::onUserRepeatPasswordTextChange,
                leadingIcon = {
                    Icon(imageVector = Icons.Outlined.LockOpen, contentDescription = "user password icon", tint= Color.LightGray)
                },
                placeholder = {
                    Text(text = "Pepeteix Contrassenya", color = Color.Gray)
                },
                shape = RoundedCornerShape(16.dp),

                //User Repeat Password Visibility
                visualTransformation =
                if (isRepeatPasswordVisible) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },

                //User Repeat Password Error
                isError = userRepeatPasswordError,
                trailingIcon = {
                    Row (horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(0.dp,0.dp,8.dp,0.dp)) {
                        val image =
                            if (isRepeatPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                        IconButton(onClick = {loginViewModel.toggleRepeatPasswordVisibility()}) {
                            Icon(imageVector = image, contentDescription = "toggle password icon" )
                        }
                        if (userRepeatPasswordError){
                            Icon(imageVector = Icons.Filled.Cancel,
                                contentDescription = "Error Icon",
                                tint = MaterialTheme.colorScheme.primary)
                        }
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
                onClick = loginViewModel::onSignUpButtonClick,
                modifier = Modifier.fillMaxWidth(0.7f),
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.secondary
                ),
                //enabled = false,
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(text = "Envia")
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview(){
    LoginScreen()
}