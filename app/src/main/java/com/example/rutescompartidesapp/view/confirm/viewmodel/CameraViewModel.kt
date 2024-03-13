package com.example.rutescompartidesapp.view.confirm.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class CameraViewModel: ViewModel() {

    private val _bitmaps = MutableStateFlow<List<Bitmap>>(emptyList())
    val bitmaps = _bitmaps.asStateFlow()

    private val _bitmapPhoto = MutableStateFlow<Bitmap?>(null)
    val bitmapPhoto = _bitmapPhoto.asStateFlow()

    private val _bitmapDraw = MutableStateFlow<Bitmap?>(null)
    val bitmapDraw = _bitmapDraw.asStateFlow()

    fun onTakePhoto(bitmap : Bitmap){
        _bitmaps.value += bitmap
    }

    fun updatePhotoBitmap(bitmap: Bitmap){
        _bitmapPhoto.value = bitmap
    }
}