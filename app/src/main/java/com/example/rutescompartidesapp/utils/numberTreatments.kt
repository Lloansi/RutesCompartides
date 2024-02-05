package com.example.rutescompartidesapp.utils

fun Float.roundTo1Decimal(): Float {
    return "%.1f".format(this).toFloat()
}