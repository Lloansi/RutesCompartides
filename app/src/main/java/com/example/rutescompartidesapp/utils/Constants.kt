package com.example.rutescompartidesapp.utils

import android.Manifest
import androidx.compose.runtime.Stable
import com.example.rutescompartidesapp.data.domain.User2
import org.osmdroid.util.GeoPoint

object Constants {

    val userList = mutableListOf<User2>(User2(0, "Admin", "admin@admin.com", 666666666, "Admin"))

    const val GOOGLE_MAPS_URL = "https://maps.googleapis.com/"
    const val API_KEY_GOOGLE_PLACES = "AIzaSyBAWD-wtWudkCqJx3Hq3PVC9uL106HB2zg"

    const val GEO_NAMES_USERNAME = "daviditb"

    const val RUTES_COMPARTIDES_URL = "https://dev.rutescompartides.cat/"


    val ALL_PERMISSIONS = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.INTERNET,
        Manifest.permission.ACCESS_NETWORK_STATE
    )

    val CAMERA_AUDIO_PERMISSIONS = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.RECORD_AUDIO
    )

    val MAP_PERMISSIONS = arrayOf(
        Manifest.permission.INTERNET,
        Manifest.permission.ACCESS_NETWORK_STATE
    )

    @Stable
    data class StableWrapper<T>(val value: T)


}