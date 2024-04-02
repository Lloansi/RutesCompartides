package com.example.rutescompartidesapp.data.domain

import kotlinx.serialization.Serializable

@Serializable
data class Message(
    val name: String,
    val text: String
)
