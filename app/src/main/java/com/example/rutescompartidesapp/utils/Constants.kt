package com.example.rutescompartidesapp.utils

import com.example.rutescompartidesapp.data.domain.User

object Constants {

    const val ORS_URL = "https://api.openrouteservice.org/"
    const val ORS_API_KEY = "5b3ce3597851110001cf62486bcdcbc5b6a343c68eabe35917a18092"

    const val GOTIFY_URL = "http://45.131.64.161:6969"
    const val GOTIFY_TOKEN = "AipC35.CrE8cP2v"

    val userList = mutableListOf<User>(User(0, "Admin", "admin@admin.com", 666666666, "Admin"))
}