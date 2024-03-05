package com.example.rutescompartidesapp.data.domain.comandes
import com.example.rutescompartidesapp.data.domain.LogisticNode
import com.example.rutescompartidesapp.data.domain.map.Itinerary
import com.example.rutescompartidesapp.data.domain.map.Point
import com.example.rutescompartidesapp.data.domain.user.User
import java.util.Date
data class TripRequest(
    val createdAt: Date,
    val updatedAt: Date,
    val client: User?,
    val internalName: String?,
    val originLocation: Point?,
    val originName: String?,
    val originLastMileLocation: Point?,
    val originLastMileName: String?,
    val originLogisticNode: LogisticNode?,
    val destinationLogisticNode: LogisticNode?,
    val destinationLastMileLocation: Point?,
    val destinationLastMileName: String?,
    val destinationLocation: Point?,
    val destinationName: String?,
    val minTimeArrival: Date,
    val maxTimeArrival: Date?,
    val wantsCarpool: Boolean,
    val wantsDeliveryNotification: Boolean,
    val tags: Set<String>,
    val comment: String?,
    val deliveryContact: String?,
    val deliveryNote: String?,
    val telegramPublished: Date?,
    val active: Boolean,
    val frozen: Boolean,
    val transportIsothermic: Boolean?,
    val transportCold: Boolean?,
    val transportFreezer: Boolean?,
    val transportDry: Boolean?,
    val packagesNum: Int?,
    val packagesLength: Float?,
    val packagesWidth: Float?,
    val packagesHeight: Float?,
    val packagesWeight: Float?,
    val packagesFragile: Boolean,
    val route: Itinerary?,
    val distance: Float,
    val co2Saved: Float
)

