package com.example.rutescompartidesapp.data.domain.rutes

import com.example.rutescompartidesapp.data.domain.LogisticNode
import com.example.rutescompartidesapp.data.domain.map.Itinerary
import com.example.rutescompartidesapp.data.domain.map.Point
import com.example.rutescompartidesapp.data.domain.user.User
import java.util.*

data class TripOffer(
    val id: Int?,
    val createdAt: Date,
    val updatedAt: Date,
    val driver: User?,
    val internalName: String?,
    val originLocation: Point?,
    val originName: String?,
    val stepLocation1: Point?,
    val stepName1: String?,
    val stepLocation2: Point?,
    val stepName2: String?,
    val stepLocation3: Point?,
    val stepName3: String?,
    val stepLocation4: Point?,
    val stepName4: String?,
    val stepLocation5: Point?,
    val stepName5: String?,
    val stepLocation6: Point?,
    val stepName6: String?,
    val destinationLocation: Point?,
    val destinationName: String?,
    val route: Itinerary?,
    val timeDepart: Date,
    val timeArrival: Date,
    val comment: String?,
    val frequency: String?,
    val frequencyGroupId: Int?,
    val tags: Set<String>?,
    val vehicle: String?,
    val costKm: Float?,
    val maxDetourKm: Float?,
    val availableSeats: Int?,
    val availableSpace: String?,
    val active: Boolean?,
    val transportIsothermic: Boolean?,
    val transportCold: Boolean?,
    val transportFreezer: Boolean?,
    val transportDry: Boolean?,
    val telegramPublished: Date?,
    val originLogisticNode: LogisticNode?,
    val destinationLogisticNode: LogisticNode?,
    val originLastMileLocation: Point?,
    val originLastMileName: String?,
    val destinationLastMileLocation: Point?,
    val destinationLastMileName: String?
)
