package com.example.rutescompartidesapp.data.network.rutes_compartides

import com.example.rutescompartidesapp.data.domain.User
import com.example.rutescompartidesapp.data.domain.rutes.MapOrder
import com.example.rutescompartidesapp.data.domain.rutes.MapRoute
import retrofit2.http.GET

interface ApiRutesCompartides {

    // Users
    @GET("accounts/login")
    suspend fun login(): User
    @GET("accounts/register")
    suspend fun register(): User

    // Rutes
    @GET("offer/view/all/geojson")
    suspend fun getAllMapRoutes(): List<MapRoute>
    @GET("offer/view/{id}/geojson")
    suspend fun getMapRouteById(id: Int): MapRoute

    // Comandes
    @GET("request/view/all/geojson")
    suspend fun getAllMapOrders(): List<MapOrder>
    @GET("request/view/{id}/geojson")
    suspend fun getMapOrderById(id: Int): MapOrder

    // Nodes Logístics

}