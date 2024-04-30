package com.example.rutescompartidesapp.data.network.idescat

import com.example.rutescompartidesapp.data.domain.Location.idescat.Municipi

interface idescatService {

    suspend fun getAllMunicipis(): List<Municipi>
}