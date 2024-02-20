package com.example.rutescompartidesapp.data.network

import com.example.rutescompartidesapp.data.domain.DirectionResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenRouteServiceApi {
    @GET("v2/directions/driving-car")
    suspend fun getDirections(
        @Query("api_key") apiKey: String,
        @Query("start") start: String,
        @Query("end") end: String
    ): Response<DirectionResponse>
}