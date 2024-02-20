package com.example.rutescompartidesapp.utils

import com.example.rutescompartidesapp.data.domain.User

object Constants {

    const val OPENROUTESERVICE_URL = "https://api.openrouteservice.org/"

    val userList = mutableListOf<User>(User(0, "Admin", "admin@admin.com", 666666666, "Admin"))
}