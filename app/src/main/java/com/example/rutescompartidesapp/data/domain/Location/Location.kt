package com.example.rutescompartidesapp.data.domain.Location

import kotlinx.serialization.Serializable

@Serializable
class Location(
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
