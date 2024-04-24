package com.example.rutescompartidesapp.data.domain.GeoName

import kotlinx.serialization.Serializable

@Serializable
data class GeoNamesResponse(
    val geonames: List<GeoName>
)
