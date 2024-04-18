package com.example.rutescompartidesapp.data.network.rutes_compartides

import com.example.rutescompartidesapp.data.domain.auth.AuthRequest
import com.example.rutescompartidesapp.data.domain.auth.AuthToken
import com.example.rutescompartidesapp.data.domain.comandes.TripRequest
import com.example.rutescompartidesapp.data.domain.rutes.TripOffer
import com.example.rutescompartidesapp.data.domain.user.User
import com.example.rutescompartidesapp.data.domain.map.MapOrder
import com.example.rutescompartidesapp.data.domain.map.MapRoute
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiRutesCompartides {

    // Auth
    @POST("api/token/")
    suspend fun getToken(@Body authRequest: AuthRequest): AuthToken

    // Users
    @GET("accounts/login")
    suspend fun login(): User
    @GET("accounts/register")
    suspend fun register(): User

    // Rutes
    @GET("offer/view/all/geojson")
    suspend fun getAllMapRoutes(): List<MapRoute>
    @GET("offer/view/{id}/geojson")
    suspend fun getMapRouteById(@Path("id") id: Int): MapRoute

    @GET("offer/view/{id}")
    suspend fun getRouteById(@Path("id") id: Int): TripOffer

    // Comandes
    @GET("request/view/all/geojson")
    suspend fun getAllMapOrders(): List<MapOrder>
    @GET("request/view/{id}/geojson")
    suspend fun getMapOrderById(@Path("id") id: Int): MapOrder
    @GET("request/view/{id}")
    suspend fun getOrderById(@Path("id") id: Int): TripRequest

    // Nodes Logístics

}