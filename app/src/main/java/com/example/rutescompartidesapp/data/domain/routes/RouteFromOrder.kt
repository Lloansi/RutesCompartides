package com.example.rutescompartidesapp.data.domain.routes

data class RouteFromOrder(
    val puntSortida: String,
    val puntArribada: String,
    val dataSortida: String,
    val dataArribada: String,
    val isRefrigerat: Boolean,
    val isCongelat: Boolean,
    val isIsoterm: Boolean,
    val isSenseHumitat: Boolean
)

