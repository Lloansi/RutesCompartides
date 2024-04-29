package com.example.rutescompartidesapp.view.confirm_delivery.components.draw

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.example.rutescompartidesapp.data.domain.Line
import com.example.rutescompartidesapp.view.confirm_delivery.viewmodel.DrawViewModel

@Composable
fun DrawScreen(navController: NavHostController, drawViewModel: DrawViewModel) {

    val ctx = LocalContext.current

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        // Handle permission result
        if (isGranted) {
            Toast.makeText(ctx, "Permiso concedido", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(ctx, "Permiso denegado", Toast.LENGTH_SHORT).show()
        }
    }

    val hasCameraPermission = remember(ctx) {
        ContextCompat.checkSelfPermission(
            ctx,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    val hasAudioPermission = remember(ctx) {
        ContextCompat.checkSelfPermission(
            ctx,
            Manifest.permission.RECORD_AUDIO
        ) == PackageManager.PERMISSION_GRANTED
    }

    if (!hasCameraPermission) {
        requestPermissionLauncher.launch(Manifest.permission.ACCESS_NETWORK_STATE)
    }

    if (!hasAudioPermission) {
        requestPermissionLauncher.launch(Manifest.permission.ACCESS_NETWORK_STATE)
    }

    val context = LocalContext.current

    val lines = remember {
        mutableStateListOf<Line>()
    }

    val strokeWidthInPx = with(LocalDensity.current) { 3.dp.toPx() }
    val screenWidth = context.resources.displayMetrics.widthPixels
    val screenHeight = context.resources.displayMetrics.heightPixels

    val bitmapWidth = screenWidth
    val bitmapHeight = screenHeight

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
                drawViewModel.drawToBitmap(lines, strokeWidthInPx, bitmapWidth, bitmapHeight)
                navController.popBackStack()
            },
            modifier = Modifier
                .padding(4.dp)
        ){
            Text("Save image")
        }
    }
}