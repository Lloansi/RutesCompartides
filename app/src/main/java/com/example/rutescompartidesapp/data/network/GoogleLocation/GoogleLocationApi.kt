package com.example.rutescompartidesapp.data.network.GoogleLocation

import com.example.rutescompartidesapp.data.domain.Location.LocationsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleLocationApi {
    @GET("maps/api/place/textsearch/json")
    suspend fun searchPlaces(
        @Query("query") query: String,
        @Query("key") apiKey: String
    ): LocationsResponse
}