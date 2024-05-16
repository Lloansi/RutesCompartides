package com.example.rutescompartidesapp.utils

import android.Manifest

object Constants {

    const val GOOGLE_MAPS_URL = "https://maps.googleapis.com/"
    const val API_KEY_GOOGLE_PLACES = "AIzaSyBAWD-wtWudkCqJx3Hq3PVC9uL106HB2zg"

    const val GEO_NAMES_URL = "http://api.geonames.org/"
    const val GEO_NAMES_USERNAME = "daviditb"

    const val RUTES_COMPARTIDES_URL = "https://dev.rutescompartides.cat/"

    const val IDESCAT_URL = "https://api.idescat.cat/emex/"


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


}