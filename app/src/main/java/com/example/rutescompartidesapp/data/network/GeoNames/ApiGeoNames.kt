package com.example.rutescompartidesapp.data.network.GeoNames

import com.example.rutescompartidesapp.data.domain.GeoName.GeoNamesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiGeoNames {
    @GET("searchJSON")
    suspend fun searchCities(
        @Query("q") query: String,
        @Query("maxRows") maxRows: Int = 100,
        @Query("country") country: String = "ES",
        @Query("username") username: String
    ): GeoNamesResponse

}