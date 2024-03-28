package com.example.rutescompartidesapp.view.prueba.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
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
import com.example.rutescompartidesapp.data.domain.Message
import com.example.rutescompartidesapp.view.prueba.ChatViewModel


@Composable
fun SendMessage(chatViewModel: ChatViewModel) {
    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        var messageText by remember { mutableStateOf("") }

        OutlinedTextField(
            value = messageText,
            onValueChange = { messageText = it },
            label = { Text("Escribe tu mensaje ...") },
            modifier = Modifier
                .fillMaxWidth(0.70f),
            //singleLine = false,
            //maxLines = if (singleLine) 1 else Int.MAX_VALUE,
        )
        Button(
            onClick = {
                if (messageText.isNotBlank()) {
                    chatViewModel.sendMessage(Message(
                        name = "Mi Nombre", //TODO Añadr aquí la variable donde se guarde el nombre que se consiga al hacer login
                        text =  messageText
                        )
                    )

                    messageText = ""
                }
            },
            modifier = Modifier
                //.fillMaxWidth(0.75f)
                .wrapContentWidth()
                .padding()
                .padding(start = (LocalConfiguration.current.screenWidthDp.dp / 40), top = (LocalConfiguration.current.screenHeightDp.dp/75))
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Send,
                contentDescription = "Send"
            )
        }
    }
}
