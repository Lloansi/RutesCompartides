package com.example.rutescompartidesapp.view.confirm

import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DrawViewModel: ViewModel() {

    private val _drawBitmap = MutableStateFlow<Bitmap?>(null)
    val drawBitmap = _drawBitmap.asStateFlow()

    fun canvasToBitmap(canvas : Canvas){

    }

}