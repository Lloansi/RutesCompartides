package com.example.rutescompartidesapp.data.network.GoogleLocation

import com.example.rutescompartidesapp.data.domain.Location.City

interface GoogleLocationService {

    // Cities
    suspend fun getAllCities(
        autonomousCommunityLat: Double,
        autonomousCommunityLng: Double,
        radius: Int
    ): List<City>
    suspend fun getCityInfo(
        cityname: String
    ): City?
}