package com.example.rutescompartidesapp.data.network.idescat

import com.example.rutescompartidesapp.data.domain.Location.idescat.Coordinates.MunicipiCoordinates
import com.example.rutescompartidesapp.data.domain.Location.idescat.Coordinates.MunicipiCoordinatesResponse
import com.example.rutescompartidesapp.data.domain.Location.idescat.Municipi

interface idescatService {

    suspend fun getAllMunicipis(): List<Municipi>

    suspend fun getLatLngMunicipi(id: String): MunicipiCoordinates?
}