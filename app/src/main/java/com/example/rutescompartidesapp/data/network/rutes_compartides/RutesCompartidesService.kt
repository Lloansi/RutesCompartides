package com.example.rutescompartidesapp.data.network.rutes_compartides

import com.example.rutescompartidesapp.data.domain.rutes.MapOrder
import com.example.rutescompartidesapp.data.domain.rutes.MapRoute

interface RutesCompartidesService {

    // Rutes
    suspend fun getAllMapRoutes(): List<MapRoute>
    suspend fun getMapRouteById(id: Int): MapRoute

    // Comandes
    suspend fun getAllMapOrders(): List<MapOrder>
    suspend fun getMapOrderById(id: Int): MapOrder
}