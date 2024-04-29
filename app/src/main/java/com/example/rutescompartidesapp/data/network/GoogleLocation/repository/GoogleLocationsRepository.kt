package com.example.rutescompartidesapp.data.network.GoogleLocation.repository

import com.example.rutescompartidesapp.data.domain.Location.Location
import com.example.rutescompartidesapp.data.network.GoogleLocation.GoogleLocationApi
import com.example.rutescompartidesapp.data.network.GoogleLocation.GoogleLocationService
import com.example.rutescompartidesapp.utils.Constants
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GoogleLocationsRepository@Inject constructor(
    private val googleLocationsApi: GoogleLocationApi
): GoogleLocationService {
    override suspend fun getAllCities(autonomousCommunity: String): List<Location> {
        try {
            val response = googleLocationsApi.searchPlaces(
                query = autonomousCommunity,
                apiKey = Constants.API_KEY_GOOGLE_PLACES
            )
            return response.locationsResponse
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: HttpException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return emptyList()
    }

    override suspend fun getCityInfo(cityname: String): Location? {
        TODO("Not yet implemented")
    }
}