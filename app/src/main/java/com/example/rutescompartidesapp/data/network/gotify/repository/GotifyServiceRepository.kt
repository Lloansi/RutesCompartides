package com.example.rutescompartidesapp.data.network.gotify.repository

import com.example.rutescompartidesapp.data.domain.Notification
import com.example.rutescompartidesapp.data.network.gotify.GotifyService
import com.example.rutescompartidesapp.data.network.gotify.GotifyApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class GotifyServiceRepository @Inject constructor(
    private val gotifyApi: GotifyApi
): GotifyService {

    override suspend fun authenticateUser(username: String, password: String): String?{

        return withContext(Dispatchers.IO){

            val tokenResponse = gotifyApi.authenticateUser(username, password)

            try {
                println("Token del usuari correctament rebut")
                tokenResponse.value
            } catch (e: Exception) {
                println("Error al recuperar el token del usuari")
                e.printStackTrace()
                null
            }
        }
    }

    override suspend fun sendMessageToUser(notification: Notification, userToken: String): Boolean?{
        return withContext(Dispatchers.IO){

            val callNotification = gotifyApi.sendNotification(userToken, notification)

            try {
                val response = callNotification.execute()
                if (response.isSuccessful) {
                    println("Notificación enviada")
                    true
                } else {
                    println("Error al enviar la notificación: ${response.code()}")
                    false
                }
            } catch (e: IOException) {
                e.printStackTrace()
                println("Error de conexión: ${e.message}")
                null
            }
        }
    }
}