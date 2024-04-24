package com.example.rutescompartidesapp.data.network.GeoNames.repository

import com.example.rutescompartidesapp.data.domain.GeoName.GeoName
import com.example.rutescompartidesapp.data.domain.GeoName.GeoNamesResponse
import com.example.rutescompartidesapp.data.network.GeoNames.ApiGeoNames
import com.example.rutescompartidesapp.data.network.GeoNames.GeoNamesService
import com.example.rutescompartidesapp.utils.Constants
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GeoNamesRepository@Inject constructor(
    private val apiGeoNames: ApiGeoNames
): GeoNamesService {
    override suspend fun getAllCataloniaCities(): List<GeoName> {

        try {
            val response = apiGeoNames.searchCities(
                query = "catalonia",
                username = Constants.GEO_NAMES_USERNAME
            )
            return response.geonames
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: HttpException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return emptyList()
    }

    override suspend fun getCityInfo(cityname: String): GeoName? {
        try {
            val response = apiGeoNames.searchCities(
                query = cityname,
                maxRows = 1,
                username = Constants.GEO_NAMES_USERNAME
            )
            return response.geonames.firstOrNull()
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