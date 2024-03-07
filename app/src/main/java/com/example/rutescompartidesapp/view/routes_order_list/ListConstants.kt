package com.example.rutescompartidesapp.view.routes_order_list

import com.example.rutescompartidesapp.data.domain.OrderForList
import com.example.rutescompartidesapp.data.domain.RouteForList

object ListConstants {

    val routeList = listOf(
        RouteForList("usuari",1, "Ruta 1", "Barcelona", "Tarragona",
            listOf("Viladecans", "Castelldefels", "Vilanova i la Geltrú", "Torredembarra"),"2024-12-12", "12:00",
            "2024-12-13", "14:00", listOf("diaria"),
            false, false, false, true),
        RouteForList("usuari", 2, "Ruta 2", "Girona", "Lleida",
            null, "2024-05-08", "14:00",
            "2024-05-09", "12:00", listOf("setmanal"),
            true, true, false, true),
        RouteForList("frodobaggins",3, "A Mordor", "Hobitton", "Mordor",
            listOf("Bree", "Rivendell", "Moria", "Lothlorien", "Cirith Ungol"),"2024-10-10", "14:00",
            "2024-12-12", "12:12", listOf(),
            false, false, false, false),
        RouteForList("usuari",4, "Ruta 4", "Manresa", "Terrasa",
            null, "2024-04-17", "14:00", "2024-04-17", "21:00", listOf("bisetmanal"),
            true, true, false, true),
        RouteForList("usuari",5, "Ruta 5", "Manresa", "Barcelona",
            null, "2024-03-26", "20:00","2024-03-26", "23:15", listOf("setmanal"),
            true, true, false, true),
    )
    val orderList = listOf(
        OrderForList("ivan",1, "Colinabos", "Barcelona", "Tarragona",
            "2024-12-12", "12:00", listOf("hortalizes"),
            true, false, false, true),
        OrderForList("usuari",2, "Entrega de patatas", "Girona", "Lleida",
            "2024-05-08", "14:00", listOf("hortalizes"),
            true, true, false, true),
        OrderForList("gandalfthegray",3, "El anillo único", "Hobitton", "Mordor",
            "2024-10-10", "14:00", listOf("peligrosa"),
            false, false, false, false),
        OrderForList("usuari",4, "Manzanas", "Manresa", "Terrasa",
            "2024-04-17", "14:00", listOf("fruita", "pomes", "verdes", "vermelles"),
            true, true, false, true),
        OrderForList("usuari",5, "Naranjas", "Manresa", "Barcelona",
            "2024-03-26", "20:00", listOf("fruita"),
            true, true, false, true),
    )

}