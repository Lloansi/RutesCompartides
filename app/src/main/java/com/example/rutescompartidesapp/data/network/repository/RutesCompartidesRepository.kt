package com.example.rutescompartidesapp.data.network.repository

import com.example.rutescompartidesapp.data.domain.auth.AuthRequest
import com.example.rutescompartidesapp.data.domain.auth.AuthToken
import com.example.rutescompartidesapp.data.domain.comandes.TripRequest
import com.example.rutescompartidesapp.data.domain.rutes.TripOffer
import com.example.rutescompartidesapp.data.domain.map.MapOrder
import com.example.rutescompartidesapp.data.domain.map.MapRoute
import com.example.rutescompartidesapp.data.network.rutes_compartides.ApiRutesCompartides
import com.example.rutescompartidesapp.data.network.rutes_compartides.RutesCompartidesService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RutesCompartidesRepository @Inject constructor(
    private val apiRutesCompartides: ApiRutesCompartides
): RutesCompartidesService {
    override suspend fun getToken(authRequest: AuthRequest): AuthToken {
        return withContext(Dispatchers.IO) {
                apiRutesCompartides.getToken(authRequest)
        }
    }

    // Routes
    override suspend fun getAllMapRoutes(): List<MapRoute> {
       return withContext(Dispatchers.IO) {
           apiRutesCompartides.getAllMapRoutes()
        }
    }
    override suspend fun getMapRouteById(id: Int): MapRoute {
        return withContext(Dispatchers.IO) {
            apiRutesCompartides.getMapRouteById(id)
        }
    }

    override suspend fun getRouteById(id: Int): TripOffer {
        return withContext(Dispatchers.IO) {
            apiRutesCompartides.getRouteById(id)
        }
    }

    // Orders
    override suspend fun getAllMapOrders(): List<MapOrder> {
        return withContext(Dispatchers.IO) {
            apiRutesCompartides.getAllMapOrders()
        }
    }

    override suspend fun getMapOrderById(id: Int): MapOrder {
        return withContext(Dispatchers.IO) {
            apiRutesCompartides.getMapOrderById(id)
        }
    }

    override suspend fun getOrderById(id: Int): TripRequest {
        return withContext(Dispatchers.IO) {
            apiRutesCompartides.getOrderById(id)
        }
    }

}