package com.example.rutescompartidesapp.data.network.GoogleLocation.repository

import com.example.rutescompartidesapp.data.domain.Location.google.City
import com.example.rutescompartidesapp.data.network.GoogleLocation.GoogleLocationApi
import com.example.rutescompartidesapp.data.network.GoogleLocation.GoogleLocationService
import com.example.rutescompartidesapp.utils.Constants
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GoogleLocationsRepository@Inject constructor(
    private val googleLocationsApi: GoogleLocationApi
): GoogleLocationService {
    override suspend fun getAllCities(
        autonomousCommunityLat: Double,
        autonomousCommunityLng: Double,
        radius: Int
    ): List<City> {
        try {
            val response = googleLocationsApi.citiesFromLocation(
                location = "${autonomousCommunityLat},${autonomousCommunityLng}",
                radius = radius,
                type = "locality",
                apiKey = Constants.API_KEY_GOOGLE_PLACES
            )
            return response.results

        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: HttpException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return emptyList()
    }

    override suspend fun getCityInfo(cityname: String): City? {
        try {
            val response = googleLocationsApi.cityInfo(
                query = cityname,
                apiKey = Constants.API_KEY_GOOGLE_PLACES
            )
            return response.results.firstOrNull()
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