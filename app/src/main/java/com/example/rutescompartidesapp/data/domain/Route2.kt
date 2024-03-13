package com.example.rutescompartidesapp.data.domain

import kotlinx.serialization.Serializable
import org.osmdroid.util.GeoPoint

@Serializable
data class Route2(
    val startPoint: GeoPoint,
    val endPoint: GeoPoint
)
