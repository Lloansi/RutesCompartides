package com.example.rutescompartidesapp.data.network.GoogleLocation

import com.example.rutescompartidesapp.data.domain.Location.CitiesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleLocationApi {

    @GET("maps/api/place/nearbysearch/json")
    suspend fun citiesFromLocation(
        @Query("location") location: String, // Latitude and longitude of the center of Catalonia
        @Query("radius") radius: Int, // Radius in meters, e.g., 50000 for 50km
        @Query("type") type: String, // Type of place, e.g., "locality" for cities
        @Query("key") apiKey: String
    ): CitiesResponse

    @GET("maps/api/place/textsearch/json")
    suspend fun cityInfo(
        @Query("query") query: String,
        @Query("key") apiKey: String
    ): CitiesResponse
}