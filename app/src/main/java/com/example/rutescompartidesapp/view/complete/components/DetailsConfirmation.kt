package com.example.rutescompartidesapp.view.complete.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@Composable
fun DetailsConfirmation() {
    val heading1 = "Data de confirmació d'entrega"
    val heading2 = "Comentari de l'entrega"

    val value1 = "29 de gener de 2024 a les 11:06"
    val value2 = "Rebut en perfectes condicions dins del temps acordat"

    Column (
        modifier = Modifier
            .padding()
            .padding(
                start = LocalConfiguration.current.screenWidthDp.dp / 15,
                end = LocalConfiguration.current.screenWidthDp.dp / 15,
                top = LocalConfiguration.current.screenHeightDp.dp/90,
                bottom = LocalConfiguration.current.screenHeightDp.dp/90,
            )
    ){
        HeadingAndText(heading1,value1)
        Spacer(modifier = Modifier.height(LocalConfiguration.current.screenHeightDp.dp/90))
        HeadingAndText(heading2,value2)
    }
}

@Composable
fun HeadingAndText(heading: String, value: String) {
    Column {
        Text(text = "$heading: ",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold
            )
        )
        Text(text = value)
    }
}