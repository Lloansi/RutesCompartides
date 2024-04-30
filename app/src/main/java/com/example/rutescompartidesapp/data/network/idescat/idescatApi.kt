package com.example.rutescompartidesapp.data.network.idescat

import com.example.rutescompartidesapp.data.domain.Location.idescat.MunicipisResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface idescatApi {
    @GET("v1/nodes.json?")
    suspend fun municipisFromCatalonia(
        @Query("tipus") tipus: String
    ): MunicipisResponse

}