package com.example.rutescompartidesapp.data.domain.orders

import org.osmdroid.util.GeoPoint

data class Orders(
    val userID: Int,
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
    val comment: String?,
    val startPoint: GeoPoint,
    val endPoint: GeoPoint
)
