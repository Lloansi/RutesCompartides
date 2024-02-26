package com.example.rutescompartidesapp.utils

import android.Manifest
import androidx.core.content.ContextCompat
import com.example.rutescompartidesapp.data.domain.User

object Constants {

    val userList = mutableListOf<User>(User(0, "Admin", "admin@admin.com", 666666666, "Admin"))

    val ALL_PERMISSIONS = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.INTERNET,
        Manifest.permission.ACCESS_NETWORK_STATE
    )


}