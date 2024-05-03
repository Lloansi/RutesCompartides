package com.example.rutescompartidesapp.data.domain.Location.idescat.Coordinates

import kotlinx.serialization.Serializable

@Serializable
data class FitxesCoordinates(
    val cols: ColsCoordinates,
    val indicadors: Indicators,
    val p: String
)
