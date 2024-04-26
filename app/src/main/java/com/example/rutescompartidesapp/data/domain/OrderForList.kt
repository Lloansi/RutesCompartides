package com.example.rutescompartidesapp.data.domain

import com.example.rutescompartidesapp.data.domain.map.Itinerary

data class OrderForList(
    val user: String,
    val orderID: Int,
    val orderName: String,
    val puntSortida: String,
    val puntArribada: String,
    val dataSortida: String,
    val horaSortida: String,
    val etiquetes: List<String>?,
    val isIsoterm: Boolean,
    val isRefrigerat: Boolean,
    val isCongelat: Boolean,
    val isSenseHumitat: Boolean,
    val packagesNum: Int?,
    val packagesLength: Float?,
    val packagesWidth: Float?,
    val packagesHeight: Float?,
    val packagesWeight: Float?,
    val packagesFragile: Boolean,
    val distance: Float,
    val co2Saved: Float,
    val comment: String?
)
