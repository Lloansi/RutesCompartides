package com.example.rutescompartidesapp.data.network.rutes_compartides.repository

import com.example.rutescompartidesapp.data.domain.external.auth.AuthRequest
import com.example.rutescompartidesapp.data.domain.external.auth.AuthToken
import com.example.rutescompartidesapp.data.domain.external.comandes.TripRequest
import com.example.rutescompartidesapp.data.domain.external.rutes.TripOffer
import com.example.rutescompartidesapp.data.domain.map.MapOrder
import com.example.rutescompartidesapp.data.domain.map.MapRoute
import com.example.rutescompartidesapp.data.network.rutes_compartides.ApiRutesCompartides
import com.example.rutescompartidesapp.data.network.rutes_compartides.RutesCompartidesService
import com.example.rutescompartidesapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RutesCompartidesRepository @Inject constructor(
    private val apiRutesCompartides: ApiRutesCompartides
): RutesCompartidesService {
    override suspend fun getToken(authRequest: AuthRequest): Resource<AuthToken> {
        val response = try {
            apiRutesCompartides.getToken(authRequest)
        } catch (e: IOException) {
            e.printStackTrace()
            return Resource.Error("Error getting token")
        } catch (e: HttpException) {
            e.printStackTrace()
            return Resource.Error("Error getting token")
        } catch (e: Exception) {
            e.printStackTrace()
            return Resource.Error("Error getting token")
        }
        return Resource.Success(response)
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