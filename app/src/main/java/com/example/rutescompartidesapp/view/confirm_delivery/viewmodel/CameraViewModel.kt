package com.example.rutescompartidesapp.view.confirm_delivery.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


class CameraViewModel: ViewModel() {

    private val _bitmaps = MutableStateFlow<List<Bitmap>>(emptyList())
    val bitmaps = _bitmaps.asStateFlow()

    private val _bitmapPhoto = MutableStateFlow<Bitmap?>(null)
    val bitmapPhoto = _bitmapPhoto.asStateFlow()

    private val _bitmapDraw = MutableStateFlow<Bitmap?>(null)
    val bitmapDraw = _bitmapDraw.asStateFlow()

    private val _isCameraActive = MutableStateFlow(false)
    val isCameraActive = _isCameraActive.asStateFlow()

    private val _showSuccessToastChannel = Channel<Boolean>()
    val showSuccessToastChannel = _showSuccessToastChannel.receiveAsFlow()


    fun onCameraActive(isActive: Boolean){
        _isCameraActive.value = isActive
    }

    fun onTakePhoto(bitmap : Bitmap){
        _bitmaps.value += bitmap
    }

    fun updatePhotoBitmap(bitmap: Bitmap){
        _bitmapPhoto.value = bitmap
        viewModelScope.launch {
            _showSuccessToastChannel.send(true)
        }
        _isCameraActive.value = false
    }
}