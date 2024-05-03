package com.example.rutescompartidesapp.data.network.idescat.repository

import com.example.rutescompartidesapp.data.domain.Location.idescat.Coordinates.MunicipiCoordinates
import com.example.rutescompartidesapp.data.domain.Location.idescat.Coordinates.MunicipiCoordinatesResponse
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

    override suspend fun getLatLngMunicipi(id: String): MunicipiCoordinates? {
        try {
            val response = idescatApi.municipiCoordinates(
                id = id,
                indicators = "f328,f329"
            )
            /*
            val municipiInfo = response.fitxes

            val municipi = municipiInfo.cols.col.find { it.scheme == "mun" }
            val id = municipi?.id ?: ""
            val name = municipi?.content ?: ""

            val longitud = municipiInfo.indicadors.i.find { it.id == "f328" }?.v?.split(",")?.get(0) ?: ""
            val latitud = municipiInfo.indicadors.i.find { it.id == "f329" }?.v?.split(",")?.get(0) ?: ""

             */

            val municipiInfo = response.fitxes

            // Verifica si la estructura de la respuesta es la esperada
            if (municipiInfo == null || municipiInfo.cols == null || municipiInfo.indicadors == null) {
                println("La estructura de la respuesta no es la esperada")
                return null
            }

            /*
            Si volem treure la id i nom del municipi
            val municipi = municipiInfo.cols.col.find { it.scheme == "mun" }
            if (municipi == null) {
                println("No se pudo encontrar el municipio en la respuesta")
                return null
            }

             */

            // Extrae los datos de longitud y latitud
            val longitud = municipiInfo.indicadors.i.find { it.id == "f328" }?.v?.split(",")?.get(0)?.toDoubleOrNull()
            val latitud = municipiInfo.indicadors.i.find { it.id == "f329" }?.v?.split(",")?.get(0)?.toDoubleOrNull()

            // Verifica si se encontraron datos de longitud y latitud
            if (longitud == null || latitud == null) {
                println("No se encontraron datos de longitud o latitud")
                return null
            }

            // Retorna la respuesta completa
            return MunicipiCoordinates(longitud,latitud)
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: HttpException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}