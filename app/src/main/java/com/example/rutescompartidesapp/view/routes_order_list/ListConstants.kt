package com.example.rutescompartidesapp.view.routes_order_list

import com.example.rutescompartidesapp.data.domain.OrderForList
import com.example.rutescompartidesapp.data.domain.RouteForList

object ListConstants {

    val routeList = listOf(
        RouteForList(1, "Ruta 1", "Barcelona", "Tarragona",
            "2024-12-12", "12:00", listOf("diaria"),
            false, false, false, true),
        RouteForList(2, "Ruta 2", "Girona", "Lleida",
            "2024-05-08", "14:00", listOf("setmanal"),
            true, true, false, true),
        RouteForList(3, "Ruta 3", "Manresa", "Terrasa",
            "2024-04-17", "14:00", listOf("bisetmanal"),
            true, true, false, true),
        RouteForList(4, "A Mordor", "Hobitton", "Mordor",
            "2024-10-10", "14:00", listOf(),
            false, false, false, false),
        RouteForList(5, "Ruta 5", "Manresa", "Barcelona",
            "2024-03-26", "20:00", listOf("setmanal"),
            true, true, false, true),
    )
    val orderList = listOf(
        OrderForList(1, "Colinabos", "Barcelona", "Tarragona",
            "2024-12-12", "12:00", listOf("hortalizes"),
            true, false, false, true),
        OrderForList(2, "Entrega de patatas", "Girona", "Lleida",
            "2024-05-08", "14:00", listOf("hortalizes"),
            true, true, false, true),
        OrderForList(3, "Manzanas", "Manresa", "Terrasa",
            "2024-04-17", "14:00", listOf("fruita"),
            true, true, false, true),
        OrderForList(4, "El anillo único", "Hobitton", "Mordor",
            "2024-10-10", "14:00", listOf(),
            false, false, false, false),
        OrderForList(5, "Naranjas", "Manresa", "Barcelona",
            "2024-03-26", "20:00", listOf("fruita"),
            true, true, false, true),
    )

}