package com.example.rutescompartidesapp.utils

import com.example.rutescompartidesapp.data.domain.UserLocal
import com.example.rutescompartidesapp.data.domain.interactions.RouteInteraction
import com.example.rutescompartidesapp.data.domain.orders.Orders
import com.example.rutescompartidesapp.data.domain.review.Review
import com.example.rutescompartidesapp.data.domain.routes.Routes
import org.osmdroid.util.GeoPoint

object LocalConstants {


    val userList = mutableListOf(
        UserLocal(0, "Admin", "admin@admin.com", 666666666, "Admin"),
        UserLocal(1, "IvanM", "ivan.martinez.7e6@itb.cat", 666666666, "RutesCompartides"),
        UserLocal(2, "frodobaggins", "ahobbit@middleearth.com", 666666666, "SamGamgee"),
        UserLocal(3, "gandalfthegray", "gandalf@middleearth.com", 666666666, "Fireworks"),
        UserLocal(4, "eduard_24", "edu_24@gmail.com", 666666666, "Eduard24"),
        UserLocal(5, "sOniasOnia12", "sonia@gmail.com", 666666666, "SoniaRutes"),
        UserLocal(6, "Alejandro", "alejandroarcasleon@gmail.com", 666666666, "hola123"),
        UserLocal(7, "David", "david@gmail.com", 684395785, "david"),
        UserLocal(8, "IvanITB", "ivan.itb@gmail.com", 645323145, "IvanITB1"),

        )


    val routeList: MutableList<Routes>? = mutableListOf(
        Routes(1,1, "Ruta 1", "Barcelona", "Tarragona",
            listOf("Viladecans", "Castelldefels", "Vilanova i la Geltrú", "Torredembarra"),"2024-12-12", "12:00",
            "2024-12-13", "14:00", listOf("diaria"),
            false, false, false, true,
            "Wolkswagen", 1.2f, 5.0f, 2, "Volum similar a 2 palets", "Tinc aire acondicionat",
            GeoPoint(41.3632939, 2.2464323),
            GeoPoint(41.11888,1.2546057)),

        Routes(1, 2, "Ruta 2", "Girona", "Lleida",
            null, "2024-05-08", "14:00",
            "2024-05-09", "12:00", listOf("setmanal"),
            true, true, false, true,
            "Seat Ibiza", 1.0f, 4.5f, 2, "Volum similar a 2 palets", "No tinc aire acondicionat",
            GeoPoint(41.9495006,2.8799239),
            GeoPoint(41.6387605,0.6467242)),

        Routes(4,4, "Ruta 4", "Manresa", "Terrasa",
            null, "2024-04-17", "14:00", "2024-04-17", "21:00", listOf("bisetmanal"),
            true, true, false, true,
            "Renault Clio", 0.2f, 3.5f, 1, "Volum similar a 2 palets", "Tinc aire acondicionat",
            GeoPoint(41.7288939,1.8286765),
            GeoPoint(41.5429623,2.3110492)),

        Routes(5,5, "Ruta 5", "Manresa", "Barcelona",
            null, "2024-03-26", "20:00","2024-03-26", "23:15", listOf("setmanal"),true, true, false, true,
            "Renault Clio", 0.2f, 3.5f, 1, "Volum similar a 2 palets", "Tinc aire acondicionat",
            GeoPoint(41.7288939,1.8286765),
            GeoPoint(41.3828939, 2.4216323)),

        Routes(6,6, "Ruta 6", "Barcelona", "Barcelona",
            null, "2024-03-23", "12:30","2024-03-23", "15:15", listOf("setmanal"),true, false, false, true,
            "Renault Clio", 0.8f, 1.5f, 1, "Volum similar a 1 palet", "Tinc un seient disponible",
            GeoPoint(41.366577,2.151493),
            GeoPoint(41.385577, 2.069545)),

        Routes(7,7, "Ruta 7", "Barcelona", "Sant Cugat del Vallès",
            null, "2024-03-16", "13:15","2024-03-17", "16:15", listOf("setmanal"),true, true, false, true,
            "Renault Clio", 0.5f, 4.0f, 1, "Volum similar a 4 palets", "Tinc aire acondicionat",
            GeoPoint(41.413504,2.156450),
            GeoPoint(41.469895, 2.089806),

            ),
        Routes(4,8, "Ruta 8", "Alella", "Arenys de Mar",
            listOf("Premià de Mar", "Mataró"), "2024-03-25", "12:30","2024-03-25", "15:15", listOf("setmanal"),false, false, false, false,
            "Renault Clio", 0.8f, 1.5f, 1, "Volum similar a 1 palet", "Tinc un seient disponible",
            GeoPoint(41.4952867,2.2942758),
            GeoPoint(41.5797031,2.5491562))
    )

    val orderList: MutableList<Orders>? = mutableListOf(
        Orders(1,1, "Colinabos", "Barcelona", "Tarragona",
            "2024-12-12", "12:00",
            "2024-12-13", "15:00", listOf("hortalizes"),
            true, false, false, true,
            3, 1.2f, 0.5f, 0.5f,
            2.5f, false, 100.0f, 10.0f,
            "Ben grossos i sucosos",
            GeoPoint(41.5618439, 2.3424323),
            GeoPoint(41.11888,1.2546057)),

        Orders(1,6, "Enciams", "Manresa", "Barcelona",
            "2024-03-26", "20:00",
            "2024-03-26", "23:15", listOf(),
            true, true, false, true,
            3, 1.2f, 0.5f, 0.5f,
            2.5f, false, 100.0f, 10.0f,
            "Verds i frescos",
            GeoPoint(41.1473939, 2.3592323),
            GeoPoint(41.12888,1.2846057)),

        Orders(4,2, "Entrega de patates", "Girona", "Lleida",
            "2024-05-08", "14:00",
            "2024-05-09", "18:00",  listOf("hortalizes"),
            true, true, false, true,
            2, 1.0f, 0.5f, 0.5f,
            2.0f, false, 50.0f, 5.0f,
            "",
            GeoPoint(41.9833006,2.8199739),
            GeoPoint(41.6187605,0.6327842)),

        Orders(5,4, "Pomes", "Manresa", "Terrasa",
            "2024-04-17", "14:00", "2024-04-18", "15:00",
            listOf("fruita", "pomes", "verdes", "vermelles"),
            true, true, false, true,
            1, 0.2f, 0.2f, 0.2f,
            0.2f, false, 50.0f, 5.0f,
            "",
            GeoPoint(41.7188939,1.8686765),
            GeoPoint(41.5639623,2.0150492)),

        Orders(5,5, "Taronjes", "Manresa", "Barcelona",
            "2024-03-26", "20:00", "2024-03-27", "20:00", listOf("fruita"),
            true, true, false, true,
            1, 0.2f, 0.2f, 0.2f,
            0.2f, false, 50.0f, 5.0f,
            "Fresques recollides ahir",
            GeoPoint(41.7338939,1.8286765),
            GeoPoint(41.3828939, 2.1774323))
    )


    val reviewList = mutableListOf<Review>(
        Review(1, 5, 1, 7, "Entrega a temps!"),
        Review(2, 4, 1, 7,"Molt bon viatge"),
        Review(4, 1, 5, 8,"M'ha entregat les taronjes en perfecte estat"),
        Review(5, 6, 4, 8,"La naranja estaba pocha"),
        Review(6, 6, 5, 10,"El conductor estaba buenisimo"),
    )

    val interactionList = mutableListOf<RouteInteraction>(
        //RouteInteraction(2, 1, "7/3/2024-10:14", "Acceptada"),
        RouteInteraction(4, 2, "3/3/2024-20:45", "Acceptada"),
        //RouteInteraction(2, 1, "4/3/2024-20:45", "Valorada"),
        RouteInteraction(4, 2, "4/3/2024-20:45", "Entregada"),
        RouteInteraction(5, 2, "4/3/2024-20:45", "Pendent"),
        RouteInteraction(1, 1, "4/3/2024-20:45", "Entregada")
    )


    /*
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
   */

}