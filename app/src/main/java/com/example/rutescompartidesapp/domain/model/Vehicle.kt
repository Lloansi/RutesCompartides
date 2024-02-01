package com.example.rutescompartidesapp.domain.model

data class Vehicle(
    val name: String,
    val seatsAvailable: Int,
    val vehicleHeight: Float,
    val vehicleWidth: Float,
    val vehicleLongitude: Float,
    var vehicleMesures: String = ""
){
    init {
        vehicleMesures = VehicleMeasures(vehicleHeight,vehicleWidth,vehicleLongitude)
    }
    private fun VehicleMeasures(height: Float, width: Float, long: Float): String{
        return "$height x $width x $long"
    }
}
