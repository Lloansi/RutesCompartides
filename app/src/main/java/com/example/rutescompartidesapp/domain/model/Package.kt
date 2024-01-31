package com.example.rutescompartidesapp.domain.model

data class Package (
    val package_id: Int,
    val package_quantity: Int,
    val is_fragile: Boolean,
    val package_height: Float,
    val package_width: Float,
    val package_longitude: Float,
    val package_weight: Float,
    val package_starting_date: String,
    val package_end_date: String,
    val package_start_point: String,
    val package_end_point: String
)
