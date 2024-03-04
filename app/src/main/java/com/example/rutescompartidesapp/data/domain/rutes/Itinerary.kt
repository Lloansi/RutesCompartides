package com.example.rutescompartidesapp.data.domain.rutes

data class Itinerary(
    val type: String,
    val coordinates: List<List<Double>>
)