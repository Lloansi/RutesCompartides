package com.example.rutescompartidesapp.data.domain.Location.idescat.Coordinates

import kotlinx.serialization.Serializable

@Serializable
data class MunicipiCoordinatesResponse(
    val fitxes: FitxesCoordinates,
    val n: String,
    val o: String,
    val lang: String,
    val version: String
)
