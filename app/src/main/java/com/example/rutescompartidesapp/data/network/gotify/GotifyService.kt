package com.example.rutescompartidesapp.data.network.gotify

import com.example.rutescompartidesapp.data.domain.Notification

interface GotifyService {

    suspend fun authenticateUser(username: String, password: String): String?
    suspend fun sendMessageToUser(notification: Notification, userToken: String): Boolean?
}