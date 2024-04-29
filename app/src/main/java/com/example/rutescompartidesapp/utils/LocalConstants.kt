package com.example.rutescompartidesapp.utils

import com.example.rutescompartidesapp.data.domain.OrderForList
import com.example.rutescompartidesapp.data.domain.RouteForList
import com.example.rutescompartidesapp.data.domain.UserLocal
import com.example.rutescompartidesapp.data.domain.interactions.RouteInteraction
import com.example.rutescompartidesapp.data.domain.review.Review

object LocalConstants {


    val userList = mutableListOf(
        UserLocal(0, "Admin", "admin@admin.com", 666666666, "Admin"),
        UserLocal(1, "IvanM", "ivan.martinez.7e6@itb.cat", 666666666, "RutesCompartides"),
        UserLocal(2, "frodobaggins", "ahobbit@middleearth.com", 666666666, "SamGamgee"),
        UserLocal(3, "gandalfthegray", "gandalf@middleearth.com", 666666666, "Fireworks"),
        UserLocal(4, "eduard_24", "edu_24@gmail.com", 666666666, "Eduard24"),
        UserLocal(5, "sOnia", "sonia@gmail.com", 666666666, "SoniaRutes"),
        UserLocal(6, "Alejandro", "alejandroarcasleon@gmail.com", 666666666, "hola123")
    )

    val routeList = mutableListOf(
        RouteForList(1,1, "Ruta 1", "Barcelona", "Tarragona",
            listOf("Viladecans", "Castelldefels", "Vilanova i la Geltrú", "Torredembarra"),"2024-12-12", "12:00",
            "2024-12-13", "14:00", listOf("diaria"),
            false, false, false, true, "Wolkswagen", 1.2f, 5.0f, 2, "Volum similar a 2 palets", "Tinc aire acondicionat"),
        RouteForList(1, 2, "Ruta 2", "Girona", "Lleida",
            null, "2024-05-08", "14:00",
            "2024-05-09", "12:00", listOf("setmanal"),
            true, true, false, true, "Seat Ibiza", 1.0f, 4.5f, 2, "Volum similar a 2 palets", "No tinc aire acondicionat"),
        RouteForList(2,3, "A Mordor", "Hobitton", "Mordor",
            listOf("Bree", "Rivendell", "Moria", "Lothlorien", "Cirith Ungol"),"2024-10-10", "14:00",
            "2024-12-12", "12:12", listOf(),
            false, false, false, false, "A cama", 0.0f, 598.6f, 0, "4 Hobbits, 1 elf, 1 nan, 2 humans", "Acompanya al portador de l'anell a Mordor"),
        RouteForList(4,4, "Ruta 4", "Manresa", "Terrasa",
            null, "2024-04-17", "14:00", "2024-04-17", "21:00", listOf("bisetmanal"),
            true, true, false, true, "Renault Clio", 0.2f, 3.5f, 1, "Volum similar a 2 palets", "Tinc aire acondicionat"),
        RouteForList(5,5, "Ruta 5", "Manresa", "Barcelona",
            null, "2024-03-26", "20:00","2024-03-26", "23:15", listOf("setmanal"),
            true, true, false, true, "Renault Clio", 0.2f, 3.5f, 1, "Volum similar a 2 palets", "Tinc aire acondicionat"),
    )

    val orderList = mutableListOf(
        OrderForList(1,1, "Colinabos", "Barcelona", "Tarragona",
            "2024-12-12", "12:00", listOf("hortalizes"),
            true, false, false, true, 3, 1.2f, 0.5f, 0.5f, 2.5f, false, 100.0f, 10.0f, "Ben grossos i sucosos"),
        OrderForList(4,2, "Entrega de patates", "Girona", "Lleida",
            "2024-05-08", "14:00", listOf("hortalizes"),
            true, true, false, true, 2, 1.0f, 0.5f, 0.5f, 2.0f, false, 50.0f, 5.0f, ""),
        OrderForList(3,3, "El anillo único", "Hobitton", "Mordor",
            "2024-10-10", "14:00", listOf("peligrosa"),
            false, false, false, false, 1, 0.1f, 0.1f, 0.1f, 0.1f, true, 1000.0f, 100.0f, "No dejar que caiga en manos de Sauron"),
        OrderForList(5,4, "Pomes", "Manresa", "Terrasa",
            "2024-04-17", "14:00", listOf("fruita", "pomes", "verdes", "vermelles"),
            true, true, false, true, 1, 0.2f, 0.2f, 0.2f, 0.2f, false, 50.0f, 5.0f, ""),
        OrderForList(5,5, "Taronjes", "Manresa", "Barcelona",
            "2024-03-26", "20:00", listOf("fruita"),
            true, true, false, true, 1, 0.2f, 0.2f, 0.2f, 0.2f, false, 50.0f, 5.0f, "Fresques recollides ahir"),
    )

    val reviewList = mutableListOf<Review>(
        Review(1, 5, 1, 7, "Entrega a temps!"),
        Review(2, 4, 1, 7,"Molt bon viatge"),
        Review(3, 3, 2, 10,"Molt valent"),
        Review(4, 1, 5, 8,"M'ha entregat les taronjes en perfecte estat"),
    )

    val interactionList = listOf<RouteInteraction>(
        RouteInteraction(2, 1, "7/3/2024-10:14", "Acceptada"),
        RouteInteraction(3, 3, "7/3/2024-10:14", "Acceptada"),
        RouteInteraction(4, 2, "3/3/2024-20:45", "Acceptada"),
        RouteInteraction(2, 1, "4/3/2024-20:45", "Valorada"),
        RouteInteraction(4, 2, "4/3/2024-20:45", "Entregada"),
        RouteInteraction(5, 2, "4/3/2024-20:45", "Pendent"),
        RouteInteraction(3, 1, "7/3/2024-9:50", "Declinada"),
        RouteInteraction(1, 1, "4/3/2024-20:45", "Entregada"),
        )


}