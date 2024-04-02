package com.example.rutescompartidesapp.data.domain

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.osmdroid.util.GeoPoint

@Serializable
data class Route2(
    @Contextual val startPoint: GeoPoint,
    @Contextual val endPoint: GeoPoint
)
