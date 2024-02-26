package com.example.rutescompartidesapp.view.confirm

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Camera
import android.graphics.Matrix
import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture.OnImageCapturedCallback
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cameraswitch
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rutescompartidesapp.view.confirm.components.CameraPreview
import com.example.rutescompartidesapp.view.confirm.components.PhotoBottomSheetContent
import com.example.rutescompartidesapp.view.map.viewModels.MapViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmScreen(){
    val confirmViewModel: ConfirmViewModel = hiltViewModel()
    val bitmaps by confirmViewModel.bitmaps.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
        val ctx = LocalContext.current
        val scope = rememberCoroutineScope()
        val controller = remember {
            LifecycleCameraController(ctx).apply {
                setEnabledUseCases(
                    CameraController.IMAGE_CAPTURE or CameraController.VIDEO_CAPTURE
                )
            }
        }

        BottomSheetScaffold(
            scaffoldState = bottomSheetScaffoldState,
            sheetPeekHeight = 0.dp,
            sheetContent = {
                PhotoBottomSheetContent(
                    bitmaps = bitmaps,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        ){ padding ->
            Box (modifier = Modifier
                .fillMaxSize()
                .padding(padding)
            ){
                CameraPreview(
                    controller = controller,
                    modifier = Modifier.fillMaxSize()
                )
                IconButton(onClick = {
                        if (controller.cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA){
                            CameraSelector.DEFAULT_FRONT_CAMERA
                        } else CameraSelector.DEFAULT_BACK_CAMERA
                    },
                    modifier = Modifier
                        .offset(16.dp, 16.dp)
                ){
                    Icon(
                        imageVector = Icons.Default.Cameraswitch,
                        contentDescription = "Switch camera"
                    )
                }

                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ){
                    IconButton(
                        onClick = {
                            scope.launch {
                                bottomSheetScaffoldState.bottomSheetState.expand()
                            }
                        }
                    ) {
                      Icon(
                          imageVector = Icons.Default.Photo,
                          contentDescription = "Open gallery"
                      )
                    }

                    IconButton(
                        onClick = {
                            takePhoto(
                                controller = controller,
                                ctx,
                                onPhotoTaken = confirmViewModel::onTakePhoto
                            )
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.PhotoCamera,
                            contentDescription = "Take photo"
                        )
                    }
                }
            }

        }
    }
}

private fun takePhoto(
    controller: LifecycleCameraController,
    ctx: Context,
    onPhotoTaken: (Bitmap)-> Unit
){
    controller.takePicture(
        ContextCompat.getMainExecutor(ctx),
        object: OnImageCapturedCallback(){
            override fun onCaptureSuccess(image: ImageProxy) {
                super.onCaptureSuccess(image)

                val matrix = Matrix().apply{
                    postRotate(image.imageInfo.rotationDegrees.toFloat())
                    postScale(-1f,1f)
                }
                val rotatedBitmap = Bitmap.createBitmap(
                    image.toBitmap(),
                    0,
                    0,
                    image.width,
                    image.height,
                    matrix,
                    true
                )

                onPhotoTaken(rotatedBitmap)
                // Instead of rotatedBitmap, we could simplify to image.toBitmap() if we dont want to handle , when landscape mobile rotate
            }

            override fun onError(exception: ImageCaptureException) {
                super.onError(exception)
                Log.e("Camera", "Couldn't take photo:", exception)
            }
        }
    )
}
