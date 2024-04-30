package com.example.rutescompartidesapp.data.network.idescat.repository

import com.example.rutescompartidesapp.data.domain.Location.idescat.Municipi
import com.example.rutescompartidesapp.data.network.idescat.idescatApi
import com.example.rutescompartidesapp.data.network.idescat.idescatService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class idescatRepository@Inject constructor(
    private val idescatApi: idescatApi
): idescatService {
    override suspend fun getAllMunicipis(): List<Municipi> {
        try {
            val response = idescatApi.municipisFromCatalonia(
                tipus = "mun"
            )
            val municipis = response.fitxes.v

            return municipis
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: HttpException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return emptyList()
    }
}