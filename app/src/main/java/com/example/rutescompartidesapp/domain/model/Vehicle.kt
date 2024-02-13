package com.example.rutescompartidesapp.domain.model

import com.example.rutescompartidesapp.utils.roundTo1Decimal

data class Vehicle(
    val name: String,
    val seatsAvailable: Int,
    var vehicleHeight: Float,
    var vehicleWidth: Float,
    var vehicleLongitude: Float,
    var vehicleMesures: String = "$vehicleHeight x $vehicleWidth x $vehicleLongitude"
){
    init {
        vehicleHeight = vehicleHeight.roundTo1Decimal()
        vehicleWidth = vehicleWidth.roundTo1Decimal()
        vehicleLongitude = vehicleLongitude.roundTo1Decimal()
    }
}

