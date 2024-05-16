package com.example.rutescompartidesapp.data.domain.Location.idescat

import kotlinx.serialization.Serializable


@Serializable
data class Municipi(
    val scheme: String,
    val id: String,
    val content: String
){
    fun doesMatchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            content
        )
        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}
