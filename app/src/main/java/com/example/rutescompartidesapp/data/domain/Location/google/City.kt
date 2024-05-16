package com.example.rutescompartidesapp.data.domain.Location.google

import kotlinx.serialization.Serializable

@Serializable
class City(
    val name: String,
    val geometry: Geometry
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
