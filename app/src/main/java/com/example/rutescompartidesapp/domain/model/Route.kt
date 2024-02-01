package com.example.rutescompartidesapp.domain.model

data class Route(
    val routeId: Int,
    val startPoint: String,
    val inBetweenPoints: List<String>?,
    val endPoint: String,
    val maxDeviation: Int,
    val routePrice: Float
){
    fun doesMatchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            startPoint,
            endPoint,
            "${startPoint.first()}",
            "${endPoint.first()}"
        )
        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}