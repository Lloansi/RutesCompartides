package com.example.rutescompartidesapp.utils

import android.Manifest
import com.example.rutescompartidesapp.data.domain.User2

object Constants {

    val userList = mutableListOf<User2>(User2(0, "Admin", "admin@admin.com", 666666666, "Admin"))
    const val RUTES_COMPARTIDES_URL = "https://rutescompartides.cat/"

    val ALL_PERMISSIONS = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.INTERNET,
        Manifest.permission.ACCESS_NETWORK_STATE
    )


}