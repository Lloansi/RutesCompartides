package com.example.rutescompartidesapp.domain.model

data class Route(
    //val routeName: String,
    val startPoint: String,
    val inBetweenPoints: List<String>?,
    val endPoint: String
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

private val allRoute = listOf(
    Route(
        "Mataró",
        listOf("Premia de Mar"),
        "Masnou"
    ),
    Route(
        "Mataró",
        listOf("Premia de Mar", "Masnou","Badalona"),
        "Barcelona"
    ),
    Route(
        "Terrasa",
        null,
        "Sabadell"
    )
)
