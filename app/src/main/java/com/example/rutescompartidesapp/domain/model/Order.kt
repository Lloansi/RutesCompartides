package com.example.rutescompartidesapp.domain.model

import com.example.rutescompartidesapp.utils.roundTo1Decimal

data class Order (
    val packageId: Int,
    val packageQuantity: Int,
    val isFragile: Boolean,
    var packageHeight: Float,
    var packageWidth: Float,
    var packageLongitude: Float,
    var packageWeight: Float,
    val packageStartingDate: String,
    val packageEndDate: String,
    val packageStartPoint: String,
    val packageEndPoint: String,
    val lat: Float,
    val lon: Float
){
    init {
        packageHeight = packageHeight.roundTo1Decimal()
        packageWidth = packageWidth.roundTo1Decimal()
        packageLongitude = packageLongitude.roundTo1Decimal()
        packageWeight = packageWeight.roundTo1Decimal()
    }

}
