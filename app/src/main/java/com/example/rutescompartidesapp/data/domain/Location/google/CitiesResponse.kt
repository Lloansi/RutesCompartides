package com.example.rutescompartidesapp.data.domain.Location.google

import kotlinx.serialization.Serializable

@Serializable
data class CitiesResponse(
    val results: List<City>
)
