package com.example.rutescompartidesapp.data.domain

data class ListQuery(
    val puntSortida: String,
    val puntArribada: String,
    val dataSortida: String,
    val horaSortida: String,
    val etiquetes: List<String>?,
    val isIsoterm: Boolean,
    val isRefrigerat: Boolean,
    val isCongelat: Boolean,
    val isSenseHumitat: Boolean
)
