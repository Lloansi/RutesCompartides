package com.example.rutescompartidesapp.view.confirm.components.draw

import android.graphics.Bitmap
import android.os.Environment
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rutescompartidesapp.view.confirm.DrawViewModel
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

@Composable
fun DrawingScreen() {

    val drawViewModel: DrawViewModel = hiltViewModel()
    val drawBitmap by drawViewModel.drawBitmap.collectAsState()

    val lines = remember {
        mutableStateListOf<Line>()
    }

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(true) {
                detectDragGestures { change, dragAmount ->
                    change.consume()

                    val line = Line(
                        start = change.position - dragAmount,
                        end = change.position
                    )

                    lines.add(line)
                }
            }
    ) {
        lines.forEach { line ->
            drawLine(
                color = line.color,
                start = line.start,
                end = line.end,
                strokeWidth = line.strokeWidth.toPx(),
                cap = StrokeCap.Round
            )
        }
    }
    Row (
        modifier = Modifier,
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center
    ){

        Button(
            onClick = {
                drawBitmap?.let { saveBitmap(it) }
                      },
            modifier = Modifier
                .padding(4.dp)
        ){
            Text("Save image")
        }

    }
}

data class Line(
    val start: Offset,
    val end: Offset,
    val color: Color = Color.Black,
    val strokeWidth: Dp = 1.dp
)

fun saveBitmap(bitmap: Bitmap){

    val filename = "sign_image.png"
    val stream: OutputStream = ByteArrayOutputStream()

    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)

    val file = File(Environment.getExternalStorageDirectory().path + "/" + filename)

    try {
        file.createNewFile()
        val fos = FileOutputStream(file)
        fos.write((stream as ByteArrayOutputStream).toByteArray())
        fos.close()
    }catch (e: Exception){
        e.printStackTrace()
    }
}