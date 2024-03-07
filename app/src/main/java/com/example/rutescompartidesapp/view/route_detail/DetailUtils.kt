package com.example.rutescompartidesapp.view.route_detail
import java.time.LocalDateTime

object DetailUtils {

    data class RouteInteraction(
        val orderID: Int,
        val routeID: Int,
        val date: String,
        val status: String,
    )

    val interactionList = listOf<RouteInteraction>(
        RouteInteraction(1, 1, "4/3/2024-20:45", "Entregada"),
        RouteInteraction(1, 1, "6/3/2024-15:30", "Pendent"),
        RouteInteraction(2, 1, "7/3/2024-9:50", "Declinada"),
        RouteInteraction(2, 1, "7/3/2024-10:14", "Acceptada"),
    )
}