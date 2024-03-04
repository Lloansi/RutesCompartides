package com.example.rutescompartidesapp.data.domain.rutes

data class MapRoute(
    val id: Int,
    val origin: Point,
    val destination: Point,
    val route: Itinerary
)



