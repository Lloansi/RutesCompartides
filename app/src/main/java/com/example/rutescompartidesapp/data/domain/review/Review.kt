package com.example.rutescompartidesapp.data.domain.review

data class Review(
    val reviewId: Int,
    val userId: Int,
    val userToReviewId: Int,
    val score: Int,
    val reviewComment: String
)
