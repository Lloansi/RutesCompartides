package com.example.rutescompartidesapp.data.domain

import kotlinx.serialization.Serializable

@Serializable
data class Route2(
    val summary: Summary
) {
    data class Summary(
        val distance: Double,
        val duration: Double
    )
}
