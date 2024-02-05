package com.example.rutescompartidesapp.domain.model

data class Vehicle(
    val name: String,
    val seatsAvailable: Int,
    val vehicleHeight: Float,
    val vehicleWidth: Float,
    val vehicleLongitude: Float,
    var vehicleMesures: String = "$vehicleHeight x $vehicleWidth x $vehicleLongitude"
)

