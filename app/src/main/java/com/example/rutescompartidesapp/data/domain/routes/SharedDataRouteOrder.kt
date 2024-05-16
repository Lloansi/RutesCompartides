package com.example.rutescompartidesapp.data.domain.routes

data class SharedDataRouteOrder(
    val orderID: Int? = null,
    val routeID: Int? = null,
    val puntSortida: String,
    val puntArribada: String,
    val dataSortida: String,
    val dataArribada: String,
    val isRefrigerat: Boolean,
    val isCongelat: Boolean,
    val isIsoterm: Boolean,
    val isSenseHumitat: Boolean
)

