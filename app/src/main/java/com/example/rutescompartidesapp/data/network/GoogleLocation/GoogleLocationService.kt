package com.example.rutescompartidesapp.data.network.GoogleLocation

import com.example.rutescompartidesapp.data.domain.Location.Location

interface GoogleLocationService {

    // Cities
    suspend fun getAllCities(autonomousCommunity: String): List<Location>
    suspend fun getCityInfo(cityname: String): Location?
}