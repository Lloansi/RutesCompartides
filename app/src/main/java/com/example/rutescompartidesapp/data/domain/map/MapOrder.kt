package com.example.rutescompartidesapp.data.domain.map

data class MapOrder(
    val id: Int,
    val origin: Point,
    val destination: Point,
    val route: Itinerary
    )

