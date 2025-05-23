package com.example.rutescompartidesapp.data.domain.external.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthRequest(
    val username: String,
    val password: String
)