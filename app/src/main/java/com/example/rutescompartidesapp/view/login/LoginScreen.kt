package com.example.rutescompartidesapp.view.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.LockOpen
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.rutescompartidesapp.R
import com.example.rutescompartidesapp.ui.theme.OrangeRC

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(loginViewModel: LoginViewModel, navController: NavController){
    //Email
    val userEmailError by loginViewModel.userEmailError.collectAsStateWithLifecycle()
    val userEmailText by loginViewModel.userEmail.collectAsStateWithLifecycle()
    //Password
    val userPasswordError by loginViewModel.userPasswordError.collectAsStateWithLifecycle()
    val userPasswordText by loginViewModel.userPassword.collectAsStateWithLifecycle()
    val isPasswordVisible by loginViewModel.isPasswordVisible.collectAsStateWithLifecycle()
    //User is logged
    val userIsLogged by loginViewModel.userIsLogged.collectAsStateWithLifecycle()
    val userWantsToLogin by loginViewModel.userWantsToLogin.collectAsStateWithLifecycle()
    // Loading state
    val isLoading by loginViewModel.isLoading.collectAsStateWithLifecycle()


    Column(modifier= Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Box(modifier = Modifier.fillMaxSize()) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(270.dp)
                    .align(Alignment.TopCenter)
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(270.dp),
                    painter = painterResource(id = R.drawable.authheader),
                    colorFilter = ColorFilter.tint( color =
                        if (isSystemInDarkTheme()) {
                            Color.Gray
                        } else {
                          MaterialTheme.colorScheme.primary
                        }
                    ),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = "Header image"
                )
                Image(
                    modifier = Modifier
                        .fillMaxWidth(0.58f)
                        .align(Alignment.TopCenter)
                        .padding(top = 25.dp),
                    painter = painterResource(id = if (isSystemInDarkTheme()) {
                        R.drawable.logowhite
                    } else {
                        R.drawable.logoblack
                    })
                    ,
                    contentScale = ContentScale.Fit,
                    contentDescription = "Logo image"
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .align(Alignment.Center)
                    .padding(top = 120.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    //Tittle
                    Row(modifier= Modifier
                        .fillMaxWidth(0.85f),
                        horizontalArrangement= Arrangement.Absolute.Left) {
                        Text(text = "Inicia sessió",
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.headlineLarge,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 26.sp

                            )
                    }
                    Spacer(modifier = Modifier.padding(8.dp))

                    //User Email
                    Row {
                        OutlinedTextField(modifier = Modifier.fillMaxWidth(0.85f),
                            value = userEmailText, onValueChange = loginViewModel::onUserEmailTextChange,
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
                            singleLine = true,

                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                                focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                                errorContainerColor = MaterialTheme.colorScheme.primaryContainer,
                            )
                        )

                    }
                    Spacer(modifier = Modifier.padding(4.dp))
                    //User Password
                    Row{


                        OutlinedTextField( modifier = Modifier.fillMaxWidth(0.85f),
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
                                Row (horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(0.dp,0.dp,8.dp,0.dp)) {
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

                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                                focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                                errorContainerColor = MaterialTheme.colorScheme.primaryContainer,
                            )
                            )

                    }
                    Spacer(modifier = Modifier.padding(4.dp))

                    //Sing Up Button
                    Row (){
                        ElevatedButton(
                            onClick = loginViewModel::onLoginButtonClick,
                            modifier = Modifier
                                .fillMaxWidth(0.85f)
                                .height(LocalConfiguration.current.screenHeightDp.dp / 16),
                            colors = ButtonDefaults.elevatedButtonColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = MaterialTheme.colorScheme.secondary
                            ),
                            //enabled = false,
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Text(text = "Envia",
                                style = MaterialTheme.typography.headlineLarge)
                        }
                    }

                    Spacer(modifier = Modifier.padding(12.dp))

                    //Bottom Text
                    Row(horizontalArrangement=Arrangement.Absolute.Left) {
                        Text(text = buildAnnotatedString {
                            append("No tens compte?  ")
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    color = OrangeRC
                                )
                            ) {
                                append("Registra't")
                            }
                        },
                            color =  MaterialTheme.colorScheme.onBackground,
                            //FALTA EL TEXT ENLLAÇ AL LOGIN
                            modifier = Modifier.clickable {

                                navController.navigate("SignUpScreen") {
                                    popUpTo("SignUpScreen") { inclusive = true }
                                }

                            }
                        )
                    }
                    if (isLoading) {
                        Box(contentAlignment = Alignment.Center) {
                                CircularProgressIndicator()
                        }
                    }

                    if(userWantsToLogin){
                        loginViewModel.onLoading(true)
                        LaunchedEffect(isLoading){
                            loginViewModel.login()
                        }
                    }
                    if (userIsLogged){
                        loginViewModel.onLoading(false)

                        // Navigate to home
                        navController.navigate("MapScreen") {
                            popUpTo("MapScreen") { inclusive = true }
                        }
                    }

                }
            }

                }
            }
        }
