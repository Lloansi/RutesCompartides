package com.example.rutescompartidesapp.domain.model

data class Package (
    val packageId: Int,
    val packageQuantity: Int,
    val isFragile: Boolean,
    val packageHeight: Float,
    val packageWidth: Float,
    val packageLongitude: Float,
    val packageWeight: Float,
    val packageStartingDate: String,
    val packageEndDate: String,
    val packageStartPoint: String,
    val packageEndPoint: String
)
