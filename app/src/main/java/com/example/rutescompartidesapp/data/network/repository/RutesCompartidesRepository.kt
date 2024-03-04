package com.example.rutescompartidesapp.data.network.repository

import com.example.rutescompartidesapp.data.domain.rutes.MapOrder
import com.example.rutescompartidesapp.data.domain.rutes.MapRoute
import com.example.rutescompartidesapp.data.network.rutes_compartides.ApiRutesCompartides
import com.example.rutescompartidesapp.data.network.rutes_compartides.RutesCompartidesService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RutesCompartidesRepository @Inject constructor(
    private val apiRutesCompartides: ApiRutesCompartides
): RutesCompartidesService {

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

}