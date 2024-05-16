package com.example.rutescompartidesapp.data.domain.Location.idescat.Coordinates

import kotlinx.serialization.Serializable


@Serializable
data class Indicator(
    val id: String,
    val c: String,
    val v: String,
    val u: String,
    val r: String,
    val s: String,
    val t: T,
    val l: String,
    val updated: String
)
