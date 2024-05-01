package com.example.rutescompartidesapp.data.domain


data class Order (
    val packageId: Int,
    val packageQuantity: Int,
    val isFragile: Boolean,
    var packageHeight: Float,
    var packageWidth: Float,
    var packageLongitude: Float,
    var packageWeight: Float,
    val packageStartingDate: String,
    val packageEndDate: String,
    val packageStartPoint: String,
    val packageEndPoint: String,

    val lat: Float,
    val lon: Float
){
    fun doesMatchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            packageStartPoint,
            packageEndPoint,
            "${packageStartPoint.first()}",
            "${packageEndPoint.first()}"
        )
        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }


}
