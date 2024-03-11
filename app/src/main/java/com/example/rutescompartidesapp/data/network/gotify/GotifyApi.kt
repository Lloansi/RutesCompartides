package com.example.rutescompartidesapp.data.network.gotify


import com.example.rutescompartidesapp.data.domain.Notification
import com.example.rutescompartidesapp.data.domain.TokenResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface GotifyApi {

    @POST("auth/login")
    suspend fun authenticateUser(@Body username: String, password: String): TokenResponse

    @POST("/message")
    suspend fun sendNotification(
        @Query("token") userToken: String,
        @Body notification: Notification
    ): Call<Void>


}