package com.example.rutescompartidesapp.data.network.GeoNames

import com.example.rutescompartidesapp.data.domain.GeoName.GeoName
import com.example.rutescompartidesapp.data.domain.GeoName.GeoNamesResponse

interface GeoNamesService {

    // Cities
    suspend fun getAllCataloniaCities(): List<GeoName>
    suspend fun getCityInfo(cityname: String): GeoName?
}