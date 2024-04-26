package com.example.rutescompartidesapp.utils

import android.Manifest

object Constants {

    const val RUTES_COMPARTIDES_URL = "https://dev.rutescompartides.cat/"
    const val GEO_NAMES_URL = "http://api.geonames.org/"

    const val GEO_NAMES_USERNAME = "daviditb"

    val ALL_PERMISSIONS = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.INTERNET,
        Manifest.permission.ACCESS_NETWORK_STATE
    )


}