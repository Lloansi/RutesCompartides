package com.example.rutescompartidesapp.data.domain

import kotlinx.serialization.Serializable

@Serializable
data class DirectionResponse(
    val routes: List<Route2>
)
