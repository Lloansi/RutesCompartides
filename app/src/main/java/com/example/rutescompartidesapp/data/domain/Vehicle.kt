package com.example.rutescompartidesapp.data.domain

import com.example.rutescompartidesapp.utils.round
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
        vehicleHeight = vehicleHeight.toDouble().round(1).toFloat()
        vehicleWidth = vehicleWidth.toDouble().round(1).toFloat()
        vehicleLongitude = vehicleLongitude.toDouble().round(1).toFloat()

    }
}

