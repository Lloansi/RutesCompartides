package com.example.rutescompartidesapp.data.repository

import com.example.rutescompartidesapp.data.domain.DirectionResponse
import com.example.rutescompartidesapp.data.domain.Route
import com.example.rutescompartidesapp.data.network.openRouteService.OpenRouteServiceApi
import com.example.rutescompartidesapp.data.network.openRouteService.RouteService
import com.example.rutescompartidesapp.utils.Constants
import com.example.rutescompartidesapp.view.map.components.allRoute
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OpenRouteServiceRepository @Inject constructor(
    private val api: OpenRouteServiceApi
): RouteService {


    val asd = allRoute.forEach { route: Route ->
        route.startPoint
    }

    suspend fun getRoutes(startPoint: String, endPoint : String) {
        return withContext(Dispatchers.IO) {
            val route = api.getDirections(Constants.ORS_API_KEY, startPoint,endPoint)
        }
    }
}