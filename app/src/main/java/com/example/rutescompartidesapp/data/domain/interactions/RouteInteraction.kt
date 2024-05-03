package com.example.rutescompartidesapp.data.domain.interactions

data class RouteInteraction(
    val orderID: Int,
    val routeID: Int,
    val date: String,
    var status: String,
)