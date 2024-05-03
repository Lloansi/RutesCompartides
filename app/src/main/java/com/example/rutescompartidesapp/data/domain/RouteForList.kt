package com.example.rutescompartidesapp.data.domain

data class RouteForList(
    val userID: Int,
    val routeID: Int,
    val routeName: String,
    val puntSortida: String,
    val puntArribada: String,
    val puntsIntermedis: List<String>?,
    val dataSortida: String,
    val horaSortida: String,
    val dataArribada: String,
    val horaArribada: String,
    val etiquetes: List<String>?,
    val isIsoterm: Boolean,
    val isRefrigerat: Boolean,
    val isCongelat: Boolean,
    val isSenseHumitat: Boolean,
    val vehicle: String?,
    val costKm: Float?,
    val maxDetourKm: Float?,
    val availableSeats: Int?,
    val availableSpace: String?,
    val comment: String?
)