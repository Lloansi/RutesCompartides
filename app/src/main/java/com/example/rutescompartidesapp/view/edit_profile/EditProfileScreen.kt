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
import androidx.compose.material.icons.outlined.LockOpen
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.rutescompartidesapp.ui.theme.fredokaOne
import com.example.rutescompartidesapp.view.edit_profile.components.EditProfileTextFieldModel
import com.example.rutescompartidesapp.view.generic_components.BackButtonArrow
import com.example.rutescompartidesapp.view.generic_components.HeaderSphere

@Composable
fun EditProfileScreen(viewModel: EditProfileViewModel, navController: NavController) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .height(200.dp)
        ) {
            HeaderSphere(200.dp)

            BackButtonArrow(navController = navController, alignment = Alignment.TopStart)

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
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            BuildTextFields(viewModel, textFieldList())

            Spacer(Modifier.size(height = 25.dp, width = 0.dp))

            SaveButton()
        }
    }
}

@Composable
fun BuildTextFields(
    viewModel: EditProfileViewModel,
    textFieldList: List<EditProfileTextFieldModel>
) {


    for (field in textFieldList.indices) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .padding(top = 10.dp, bottom = 10.dp),
            value = "",
            onValueChange = {},
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

@Composable
fun SaveButton() {
    ElevatedButton(
        onClick = {},
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
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White
        )
    }
}

fun textFieldList(): List<EditProfileTextFieldModel> {
    return listOf(
        EditProfileTextFieldModel(
            Icons.Rounded.Person,
            "Nom d'usuari",
            KeyboardType.Text
        ),
        EditProfileTextFieldModel(
            Icons.Rounded.Person,
            "Nom",
            KeyboardType.Text
        ),
        EditProfileTextFieldModel(
            Icons.Rounded.Person,
            "Cognoms",
            KeyboardType.Text
        ),
        EditProfileTextFieldModel(
            Icons.Rounded.Email,
            "Correo electrònic",
            KeyboardType.Email
        ),
        EditProfileTextFieldModel(
            Icons.Rounded.Phone,
            "Telèfon",
            KeyboardType.Phone
        ),
    )
}