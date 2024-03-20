package com.example.rutescompartidesapp.data.domain

import com.example.rutescompartidesapp.utils.roundTo1Decimal

data class Route(
    val routeId: Int,
    val startPoint: String,
    val inBetweenPoints: List<String>?,
    val endPoint: String,
    val maxDeviation: Int,
    var routePrice: Float,
    val startLat: Float,
    val startLon: Float,
    val endLat: Float,
    val endLon: Float,
){
    init {
        routePrice = routePrice.roundTo1Decimal()

    }

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