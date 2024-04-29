package com.example.rutescompartidesapp.view.confirm_delivery.components.draw

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.example.rutescompartidesapp.data.domain.Line
import com.example.rutescompartidesapp.view.confirm_delivery.viewmodel.DrawViewModel

/**
 * Composable function for the screen where the user can draw.
 * @param drawViewModel ViewModel for drawing operations.
 */
@SuppressLint("RestrictedApi")
@Composable
fun DrawScreen(drawViewModel: DrawViewModel) {

    val context = LocalContext.current

    val lines = remember {
        mutableStateListOf<Line>()
    }

    val strokeWidthInPx = with(LocalDensity.current) { 3.dp.toPx() }
    val screenWidth = context.resources.displayMetrics.widthPixels
    val screenHeight = context.resources.displayMetrics.heightPixels

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
                drawViewModel.drawToBitmap(lines, strokeWidthInPx, screenWidth, screenHeight)
            },
            modifier = Modifier
                .padding(4.dp)
        ){
            Text("Save image")
        }
    }
}