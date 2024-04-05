package com.example.rutescompartidesapp.data.domain.chat

import kotlinx.serialization.Serializable

@Serializable
data class Message(
    val name: String,
    var text: String
)
