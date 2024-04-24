package com.example.rutescompartidesapp.utils

import android.Manifest
import androidx.compose.runtime.Stable
import com.example.rutescompartidesapp.data.domain.User2

object Constants {

    val userList = mutableListOf<User2>(User2(0, "Admin", "admin@admin.com", 666666666, "Admin"))
    const val RUTES_COMPARTIDES_URL = "https://dev.rutescompartides.cat/"
    const val GEO_NAMES_URL = "http://api.geonames.org/"

    const val GEO_NAMES_USERNAME = "daviditb"

    val ALL_PERMISSIONS = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.INTERNET,
        Manifest.permission.ACCESS_NETWORK_STATE
    )

    @Stable
    data class StableWrapper<T>(val value: T)


}