package com.example.rutescompartidesapp.view.chat.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.rutescompartidesapp.data.domain.chat.Message
import com.example.rutescompartidesapp.data.domain.user.User
import com.example.rutescompartidesapp.view.chat.ChatViewModel2

@Composable
fun Chat(messages: List<Message>, chatViewModel: ChatViewModel2, user: User) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .background( color = MaterialTheme.colorScheme.background),
        color = MaterialTheme.colorScheme.background
    ) {
        val listState = rememberLazyListState()
        Column(
            modifier = Modifier.fillMaxHeight(0.9f)
        ){
            // List of messages
            LazyColumn(
                modifier = Modifier.fillMaxHeight(0.9f),
                state = listState,
                reverseLayout = false
            ){
                items(messages.size){messageIndex ->
                    val isMyMessage = messageIndex % 2 == 0
                    var message = messages[messageIndex]
                    if (message.text == "Connected to chat."){
                        GrayChip("Connected")
                    } else {
                        ChatMessage(
                            text = message,
                            isMyMessage = isMyMessage,
                            modifier = Modifier.padding(vertical = 4.dp),
                            user
                        )
                    }
                }
            }
            // Writteable box and send message button
            Column (
                modifier = Modifier.fillMaxHeight(0.8f),
                verticalArrangement = Arrangement.Bottom
            ){
                MessageContainerAndSendButton(chatViewModel)
            }
            // Scroll to the last item when the list changes
            LaunchedEffect(messages.size) {
                listState.scrollToItem(messages.size)
            }
        }
    }
}