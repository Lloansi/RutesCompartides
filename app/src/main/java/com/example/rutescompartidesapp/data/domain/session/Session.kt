package com.example.rutescompartidesapp.data.domain.session

data class Session(
    val isLogged: Boolean,
    val email: String,
    val password: String)