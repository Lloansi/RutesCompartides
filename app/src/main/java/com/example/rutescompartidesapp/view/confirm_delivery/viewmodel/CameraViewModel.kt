package com.example.rutescompartidesapp.view.confirm_delivery.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class CameraViewModel: ViewModel() {

    private val _bitmaps = MutableStateFlow<List<Bitmap>>(emptyList())
    val bitmaps = _bitmaps.asStateFlow()

    private val _bitmapPhoto = MutableStateFlow<Bitmap?>(null)
    val bitmapPhoto = _bitmapPhoto.asStateFlow()

    private val _bitmapDraw = MutableStateFlow<Bitmap?>(null)
    val bitmapDraw = _bitmapDraw.asStateFlow()

    /**
     * Updates the list of Bitmap images with the provided bitmap.

     * @param bitmap The Bitmap image to be added to the list.
     **/
    fun onTakePhoto(bitmap : Bitmap){
        _bitmaps.value += bitmap
    }

    /**
     * Updates the photo Bitmap with the provided bitmap.

     * @param bitmap The Bitmap image to update the photo Bitmap.
     **/
    fun updatePhotoBitmap(bitmap: Bitmap){
        _bitmapPhoto.value = bitmap
    }
}