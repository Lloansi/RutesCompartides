package com.example.rutescompartidesapp.data.domain.GeoName

import kotlinx.serialization.Serializable

@Serializable
class GeoName(
    val name: String,
    val lat: Double,
    val lng: Double
){
    fun doesMatchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            name
        )
        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}
