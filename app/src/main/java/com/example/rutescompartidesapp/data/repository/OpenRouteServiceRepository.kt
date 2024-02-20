package com.example.rutescompartidesapp.data.repository

import com.example.rutescompartidesapp.data.network.OpenRouteServiceApi
import com.example.rutescompartidesapp.data.network.RouteService
import javax.inject.Inject

class OpenRouteServiceRepository @Inject constructor(
    private val api: OpenRouteServiceApi
): RouteService {

}