package com.example.rutescompartidesapp.data.domain

data class RouteForList(
    val routeID: Int,
    val routeName: String,
    val puntArribada: String,
    val puntSortida: String,
    val dataSortida: String,
    val horaSortida: String,
    val etiquetes: List<String>?,
    val isIsoterm: Boolean,
    val isRefrigerat: Boolean,
    val isCongelat: Boolean,
    val isSenseHumitat: Boolean
)