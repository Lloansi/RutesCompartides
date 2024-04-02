package com.example.rutescompartidesapp.view.prueba

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import com.example.rutescompartidesapp.data.domain.user.User
import com.example.rutescompartidesapp.view.generic_components.TopAppBarWithBackNav
import com.example.rutescompartidesapp.view.prueba.components.Chat

@Composable
fun ChatScreen(navHost: NavHostController, chatViewModel: ChatViewModel, user: User) {

    LaunchedEffect(key1 = Unit) {
        chatViewModel.connect()
    }

    DisposableEffect(key1 = Unit) {
        onDispose {
            chatViewModel.shutdown()
        }
    }

    val messages by chatViewModel.messages.collectAsState()

    TopAppBarWithBackNav(title= user.firstName!!, onBack = { navHost.popBackStack() }, content = {
        Chat(
            messages = messages,
            chatViewModel = chatViewModel,
            user = user
        )
    })


    //Chat(messages = messages, chatViewModel = chatViewModel, user = user )
}
