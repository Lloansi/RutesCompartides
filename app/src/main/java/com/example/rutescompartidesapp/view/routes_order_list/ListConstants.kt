package com.example.rutescompartidesapp.view.routes_order_list

import com.example.rutescompartidesapp.data.domain.OrderForList
import com.example.rutescompartidesapp.data.domain.RouteForList

object ListConstants {

    val routeList = mutableListOf(
        RouteForList("ivan",1, "Ruta 1", "Barcelona", "Tarragona",
            listOf("Viladecans", "Castelldefels", "Vilanova i la Geltrú", "Torredembarra"),"2024-12-12", "12:00",
            "2024-12-13", "14:00", listOf("diaria"),
            false, false, false, true, "Wolkswagen", 1.2f, 5.0f, 2, "Volum similar a 2 palets", "Tinc aire acondicionat"),
        RouteForList("usuari", 2, "Ruta 2", "Girona", "Lleida",
            null, "2024-05-08", "14:00",
            "2024-05-09", "12:00", listOf("setmanal"),
            true, true, false, true, "Seat Ibiza", 1.0f, 4.5f, 2, "Volum similar a 2 palets", "No tinc aire acondicionat"),
        RouteForList("frodobaggins",3, "A Mordor", "Hobitton", "Mordor",
            listOf("Bree", "Rivendell", "Moria", "Lothlorien", "Cirith Ungol"),"2024-10-10", "14:00",
            "2024-12-12", "12:12", listOf(),
            false, false, false, false, "A cama", 0.0f, 598.6f, 0, "4 Hobbits, 1 elf, 1 nan, 2 humans", "Acompanya al portador de l'anell a Mordor"),
        RouteForList("usuari",4, "Ruta 4", "Manresa", "Terrasa",
            null, "2024-04-17", "14:00", "2024-04-17", "21:00", listOf("bisetmanal"),
            true, true, false, true, "Renault Clio", 0.2f, 3.5f, 1, "Volum similar a 2 palets", "Tinc aire acondicionat"),
        RouteForList("usuari",5, "Ruta 5", "Manresa", "Barcelona",
            null, "2024-03-26", "20:00","2024-03-26", "23:15", listOf("setmanal"),
            true, true, false, true, "Renault Clio", 0.2f, 3.5f, 1, "Volum similar a 2 palets", "Tinc aire acondicionat"),
    )
    val orderList = mutableListOf(
        OrderForList("ivan",1, "Colinabos", "Barcelona", "Tarragona",
            "2024-12-12", "12:00", listOf("hortalizes"),
            true, false, false, true, 3, 1.2f, 0.5f, 0.5f, 2.5f, false, 100.0f, 10.0f, "Ben grossos i sucosos"),
        OrderForList("usuari",2, "Entrega de patatas", "Girona", "Lleida",
            "2024-05-08", "14:00", listOf("hortalizes"),
            true, true, false, true, 2, 1.0f, 0.5f, 0.5f, 2.0f, false, 50.0f, 5.0f, ""),
        OrderForList("gandalfthegray",3, "El anillo único", "Hobitton", "Mordor",
            "2024-10-10", "14:00", listOf("peligrosa"),
            false, false, false, false, 1, 0.1f, 0.1f, 0.1f, 0.1f, true, 1000.0f, 100.0f, "No dejar que caiga en manos de Sauron"),
        OrderForList("usuari",4, "Manzanas", "Manresa", "Terrasa",
            "2024-04-17", "14:00", listOf("fruita", "pomes", "verdes", "vermelles"),
            true, true, false, true, 1, 0.2f, 0.2f, 0.2f, 0.2f, false, 50.0f, 5.0f, ""),
        OrderForList("usuari",5, "Naranjas", "Manresa", "Barcelona",
            "2024-03-26", "20:00", listOf("fruita"),
            true, true, false, true, 1, 0.2f, 0.2f, 0.2f, 0.2f, false, 50.0f, 5.0f, "Fresques recollides ahir"),
    )

}