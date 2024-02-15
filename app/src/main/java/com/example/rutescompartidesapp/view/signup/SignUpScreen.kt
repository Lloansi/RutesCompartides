package com.example.rutescompartidesapp.view.signup

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.LockOpen
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.rutescompartidesapp.R
import com.example.rutescompartidesapp.ui.theme.OrangeRC

@Composable
fun SignUpScreen(navController: NavController) {
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
    val isPasswordVisible by signUpViewModel.isPasswordVisible.collectAsStateWithLifecycle()
    //Repeat Password
    val userRepeatPasswordError by signUpViewModel.userRepeatPasswordError.collectAsStateWithLifecycle()
    val userRepeatPasswordText by signUpViewModel.userRepeatPassword.collectAsStateWithLifecycle()
    val isRepeatPasswordVisible by signUpViewModel.isRepeatPasswordVisible.collectAsStateWithLifecycle()
    //User exists
    val userExists by signUpViewModel.userExists.collectAsStateWithLifecycle()

    //Column
    Column(modifier= Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Box(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier
                .fillMaxWidth().height(270.dp)
                .align(Alignment.TopCenter)
            ){
                Image(modifier = Modifier
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
                    contentDescription = "Header image")
                Image(modifier = Modifier.fillMaxWidth(0.58f).align(Alignment.TopCenter).padding(top = 25.dp),
                    painter = painterResource(id = if (isSystemInDarkTheme()) {
                        R.drawable.logowhite
                    } else {
                        R.drawable.logoblack
                    }),
                    contentScale = ContentScale.Fit,
                    contentDescription = "Logo image")
            }
            Box(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .align(Alignment.Center).padding(top = 120.dp)
            ){
                Column(modifier= Modifier
                    .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center) {

                    //Tittle
                    Row(modifier= Modifier
                        .fillMaxWidth(0.85f),
                        horizontalArrangement= Arrangement.Absolute.Left) {
                        Text(text = "Crear un compte",
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.headlineLarge,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 26.sp)
                    }
                    Spacer(modifier = Modifier.padding(8.dp))

                    //User Name
                    Row{
                        OutlinedTextField(modifier= Modifier
                            .fillMaxWidth(0.85f),
                            value = userNameText, onValueChange = signUpViewModel::onUserNameTextChange,
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
                            singleLine = true,

                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedContainerColor = Color.White,
                                focusedContainerColor = Color.White,
                                errorContainerColor = Color.White,
                            )
                        )
                    }
                    Spacer(modifier = Modifier.padding(4.dp))

                    //User Email
                    Row {
                        OutlinedTextField(modifier= Modifier
                            .fillMaxWidth(0.85f),
                            value = userEmailText, onValueChange = signUpViewModel::onUserEmailTextChange,
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
                                unfocusedContainerColor = Color.White,
                                focusedContainerColor = Color.White,
                                errorContainerColor = Color.White
                                )
                        )

                    }
                    Spacer(modifier = Modifier.padding(4.dp))

                    //User Phone
                    Row {
                        OutlinedTextField(modifier= Modifier
                            .fillMaxWidth(0.85f),
                            value = userPhoneText, onValueChange = signUpViewModel::onUserPhoneTextChange,
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
                            singleLine = true,

                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedContainerColor = Color.White,
                                focusedContainerColor = Color.White,
                                errorContainerColor = Color.White
                                )
                        )

                    }
                    Spacer(modifier = Modifier.padding(4.dp))

                    //User Password
                    Row {
                        OutlinedTextField(
                            modifier= Modifier
                                .fillMaxWidth(0.85f),
                            value = userPasswordText, onValueChange = signUpViewModel::onUserPasswordTextChange,
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
                                IconButton(onClick = {signUpViewModel.togglePasswordVisibility()}) {
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

                            colors = OutlinedTextFieldDefaults.colors(
                               unfocusedContainerColor = Color.White,
                                focusedContainerColor = Color.White,
                                errorContainerColor = Color.White
                                )

                            )

                    }
                    Spacer(modifier = Modifier.padding(4.dp))

                    //User Repeat Password
                    Row {
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(0.85f),
                            value = userRepeatPasswordText, onValueChange = signUpViewModel::onUserRepeatPasswordTextChange,
                            leadingIcon = {
                                Icon(imageVector = Icons.Outlined.LockOpen, contentDescription = "user password icon", tint= Color.LightGray)
                            },
                            placeholder = {
                                Text(text = "Repeteix Contrassenya", color = Color.Gray)
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
                                    IconButton(onClick = {signUpViewModel.toggleRepeatPasswordVisibility()}) {
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
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedContainerColor = Color.White,
                                focusedContainerColor = Color.White,
                                errorContainerColor = Color.White,
                                )
                        )

                    }
                    Spacer(modifier = Modifier.padding(4.dp))

                    //Sing Up Button
                    Row {
                        ElevatedButton(
                            onClick = signUpViewModel::onSignUpButtonClick,
                            modifier = Modifier.fillMaxWidth(0.85f)
                                .fillMaxHeight(0.15f),
                            colors = ButtonDefaults.elevatedButtonColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = MaterialTheme.colorScheme.secondary
                            ),
                            //enabled = false,
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Text(text = "Registra't",
                                style = MaterialTheme.typography.headlineMedium,
                                modifier = Modifier.clickable {
                                    navController.navigate("LoginScreen") {
                                        popUpTo("LoginScreen") { inclusive = true }
                                    }
                                })
                        }
                    }

                    Spacer(modifier = Modifier.padding(12.dp))

                    //Bottom Text
                    Row(horizontalArrangement=Arrangement.Absolute.Left) {
                        Text(text = buildAnnotatedString {
                            append("Tens un usuari registrat?  ")
                                    withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    color = OrangeRC
                                )
                            ) {
                                append("Inicia sessió")
                            }
                                                         },
                            color = Color.Black,
                            //FALTA EL TEXT ENLLAÇ AL LOGIN
                            modifier = Modifier.clickable {
                                navController.navigate("LoginScreen") {
                                    popUpTo("LoginScreen") { inclusive = true }
                                }

                            }
                        )
                    }

                    if(userExists){
                        Toast.makeText(LocalContext.current, "Usuari ja registrat", Toast.LENGTH_SHORT).show()
                    }


                }
            }
        }
    }
}
