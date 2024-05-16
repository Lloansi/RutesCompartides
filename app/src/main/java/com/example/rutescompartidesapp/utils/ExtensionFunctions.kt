package com.example.rutescompartidesapp.utils

import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin

/**
 * Rounds a double number to a certain number of decimals.
 * @param decimals Number of decimals to round the number to.
 * @return The rounded number in Double format.
 */
fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) {
        multiplier *= 10
    }
    return kotlin.math.round(this * multiplier) / multiplier
}

/**
 * Calculates the distance between two points using their latitude and longitude.

 * @param lat1 Latitude of the first point.

 * @param lon1 Longitude of the first point.

 * @param lat2 Latitude of the second point.

 * @param lon2 Longitude of the second point.

 * @return The distance between the points in kilometers.
 **/
fun distanceBetweenPoints(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double{
    val earthRadius = 6371 // 6371 is Earth radius in km.
    // Transform the data parameters to get the distance between points
    val distance = acos(sin(deg2rad(lat1)) * sin(deg2rad(lat2)) + cos(deg2rad(lat1)) * cos(deg2rad(lat2)) * cos(deg2rad(lon2)-deg2rad(lon1))) * earthRadius
    return distance
}

/**
 * Converts degrees to radians.

 * @param deg Degree value.

 * @return The equivalent value in radians.
 **/
fun deg2rad(deg: Double): Double {
    return deg * Math.PI / 180.0
}