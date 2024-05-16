package com.example.rutescompartidesapp.data.domain.external.user

import java.util.Date

data class User(
    val username: String?,
    val firstName: String?,
    val lastName: String?,
    val email: String?,
    val isStaff: Boolean,
    val isActive: Boolean,
    val dateJoined: Date
)
