package com.example.rutescompartidesapp.view.confirm_delivery.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for handling camera-related functionality.
 */
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

    /**
     * Notifies the ViewModel about the activation status of the camera.
     * @param isActive true if camera is active, false otherwise.
     */
    fun onCameraActive(isActive: Boolean){
        _isCameraActive.value = isActive
    }

    /**
     * Adds a captured photo bitmap to the list of captured bitmaps.
     * @param bitmap The bitmap of the captured photo.
     */
    fun onTakePhoto(bitmap : Bitmap){
        _bitmaps.value += bitmap
    }

    /**
     * Updates the photo bitmap with the provided bitmap and notifies about the success.
     * @param bitmap The bitmap representing the photo.
     */
    fun updatePhotoBitmap(bitmap: Bitmap){
        _bitmapPhoto.value = bitmap
        viewModelScope.launch {
            _showSuccessToastChannel.send(true)
        }
        _isCameraActive.value = false
    }
}