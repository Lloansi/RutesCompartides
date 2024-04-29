package com.example.rutescompartidesapp.view.confirm_delivery.viewmodel

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.os.Environment
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rutescompartidesapp.data.domain.Line
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

/**
 * ViewModel for handling drawing functionality.
 */
class DrawViewModel: ViewModel() {

    private val _drawBitmap = MutableStateFlow<Bitmap?>(null)
    val drawBitmap = _drawBitmap.asStateFlow()

    private val _isSignatureActive = MutableStateFlow(false)
    val isSignatureActive = _isSignatureActive.asStateFlow()
    fun onSignatureActive(isActive: Boolean){
        _isSignatureActive.value = isActive
    }

    private val _showSuccessToastChannel = Channel<Boolean>()
    val showSuccessToastChannel = _showSuccessToastChannel.receiveAsFlow()


    /**
     * Saves the provided bitmap to the device's gallery.
     * @param bitmap The bitmap to be saved.
     */
    fun saveBitmapToGallery(bitmap: Bitmap) {
        viewModelScope.launch(Dispatchers.IO) {
            val fileName = "drawing_${System.currentTimeMillis()}.png"
            val directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val file = File(directory, fileName)
            var fos: FileOutputStream? = null
            try {
                fos = FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
                fos.flush()
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                fos?.close()
            }
        }
    }

    /**
     * Draws the provided lines on a bitmap.
     * @param lines List of lines to be drawn.
     * @param strokeWidthInPx Stroke width for drawing lines.
     * @param width Width of the bitmap.
     * @param height Height of the bitmap.
     */
    fun drawToBitmap(lines: List<Line>, strokeWidthInPx: Float, width: Int, height: Int) {
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val paint = Paint().apply {
            style = Paint.Style.STROKE
            strokeWidth = strokeWidthInPx
        }

        lines.forEach { line ->
            paint.color = line.color.toArgb()
            val path = Path()
            path.moveTo(line.start.x, line.start.y)
            path.lineTo(line.end.x, line.end.y)
            canvas.drawPath(path, paint)
        }

        _drawBitmap.value =  bitmap
        viewModelScope.launch {
            _showSuccessToastChannel.send(true)
        }
        _isSignatureActive.value = false
    }


}