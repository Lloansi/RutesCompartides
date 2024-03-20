package com.example.rutescompartidesapp.view.prueba.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Chat(messages: List<String>) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        val listState = rememberLazyListState()
        Column(
            modifier = Modifier.fillMaxHeight(0.9f)
        ){
            LazyColumn(
                modifier = Modifier.fillMaxHeight(0.9f),
                state = listState,
                reverseLayout = true
            ){
                items(messages.size){messageIndex ->
                    val isMyMessage = messageIndex % 2 == 0
                    ChatMessage(
                        text = messages[messageIndex],
                        isMyMessage = isMyMessage,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }
            Column (
                modifier = Modifier.fillMaxHeight(0.8f),
                verticalArrangement = Arrangement.Bottom
            ){
                SendMessage()
            }
        }
    }
}