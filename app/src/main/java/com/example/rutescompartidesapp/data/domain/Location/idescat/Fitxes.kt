package com.example.rutescompartidesapp.data.domain.Location.idescat

import kotlinx.serialization.Serializable


@Serializable
data class Fitxes(
    val p: String,
    val v: List<Municipi>
)
