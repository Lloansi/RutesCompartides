package com.example.rutescompartidesapp.data.domain.Location

import kotlinx.serialization.Serializable

@Serializable
data class LocationsResponse(
    val locationsResponse: List<Location>
)
