package com.example.rutescompartidesapp.data.domain.external.user

data class UserProfile(
    val user: User,
    val language: String,
    val notes: String?,
    val picture: String?,
    val points: Int?,
    val avgRating: Double?,
    val organization: String?,
    val phone: String?,
    val extra: Any?,
    val newsletter: Boolean,
    val stopEmailNotifications: Boolean,
)
