package com.example.rutescompartidesapp.view.prueba.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp


@Composable
fun SendMessage() {
    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        var messageText by remember { mutableStateOf("") }

        OutlinedTextField(
            value = messageText,
            onValueChange = { messageText = it },
            label = { Text("Escribe tu mensaje") },
            modifier = Modifier
                .fillMaxWidth(0.70f),
            //singleLine = false,
            //maxLines = if (singleLine) 1 else Int.MAX_VALUE,
        )
        Button(
            onClick = {
                if (messageText.isNotBlank()) {
                    // TODO sendMessage(messageText)
                    messageText = ""
                }
            },
            modifier = Modifier
                //.fillMaxWidth(0.75f)
                .wrapContentWidth()
                .padding()
                .padding(start = (LocalConfiguration.current.screenWidthDp.dp / 40))
        ) {
            Icon(
                imageVector = Icons.Default.Send,
                contentDescription = "Send"
            )
        }
    }
}
