package com.example.rutescompartidesapp.data.network.rutes_compartides

import com.example.rutescompartidesapp.data.domain.auth.AuthRequest
import com.example.rutescompartidesapp.data.domain.auth.AuthToken
import com.example.rutescompartidesapp.data.domain.comandes.TripRequest
import com.example.rutescompartidesapp.data.domain.rutes.TripOffer
import com.example.rutescompartidesapp.data.domain.map.MapOrder
import com.example.rutescompartidesapp.data.domain.map.MapRoute

interface RutesCompartidesService {

    // Auth
    suspend fun getToken(authRequest: AuthRequest): AuthToken

    // Rutes
    suspend fun getAllMapRoutes(): List<MapRoute>
    suspend fun getMapRouteById(id: Int): MapRoute
    suspend fun getRouteById(id: Int): TripOffer

    // Comandes
    suspend fun getAllMapOrders(): List<MapOrder>
    suspend fun getMapOrderById(id: Int): MapOrder
    suspend fun getOrderById(id: Int): TripRequest

}