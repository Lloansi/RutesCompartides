package com.example.rutescompartidesapp.view.edit_profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rutescompartidesapp.R
import com.example.rutescompartidesapp.ui.theme.GrayRC
import com.example.rutescompartidesapp.ui.theme.MateBlackRC
import com.example.rutescompartidesapp.ui.theme.freDokaOne
import com.example.rutescompartidesapp.ui.theme.openSans
import com.example.rutescompartidesapp.view.edit_profile.components.OutlinedFieldBgColor
import com.example.rutescompartidesapp.view.generic_components.HeaderCurveImage

@Composable
fun EditProfileScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(GrayRC)
    ) {
        EditProfile()
    }
}

@Composable
fun EditProfile() {
    Column {
        HeaderCurveImage(200.dp)
        Column(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ) {
            OutlinedFields()
            Spacer(modifier = Modifier.padding(14.dp))
            SaveChangesButton()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedFields() {
    val values = listOf("Xalex284", "Alejandro", "Arcas Leon", "alejandroarcasleon@gmail.com", "647 385 328")

    for (i in 0..4) {
        Spacer(modifier = Modifier.padding(10.dp))
        OutlinedFieldBgColor(color = Color.White) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .height(60.dp),
                value = values[i],
                onValueChange = {},
                shape = RoundedCornerShape(15.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFFA5A5A5),
                    unfocusedBorderColor = Color(0xFFA5A5A5)
                ),
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Start),
                singleLine = true,
                leadingIcon = {
                    when(i){
                        0 -> { Icon(imageVector = Icons.Outlined.Person, contentDescription = null, tint = Color(0xFFA5A5A5)) }
                        1 -> { Icon(imageVector = Icons.Outlined.Person, contentDescription = null, tint = Color(0xFFA5A5A5)) }
                        2 -> { Icon(imageVector = Icons.Outlined.Person, contentDescription = null, tint = Color(0xFFA5A5A5)) }
                        3 -> { Icon(imageVector = Icons.Outlined.Email, contentDescription = null, tint = Color(0xFFA5A5A5)) }
                        4 -> { Icon(imageVector = Icons.Outlined.Phone, contentDescription = null, tint = Color(0xFFA5A5A5)) }
                        else -> { println("No existe un campo para este icono") }
                    }
                },
            )
        }
    }
}

@Composable
fun SaveChangesButton() {
    TextButton(
        modifier = Modifier
            .fillMaxWidth(0.85f)
            .height(60.dp)
            .background(
                color = if (isSystemInDarkTheme()) MateBlackRC else Color(0xFFEE976A),
                shape = RoundedCornerShape(15.dp)
            ),
        onClick = {},
    ) {
        Text(
            text = "Desa els canvis",
            color = Color.White,
            fontFamily = openSans,
            fontSize = 16.sp

        )
    }
}


