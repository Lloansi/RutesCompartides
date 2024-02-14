package com.example.rutescompartidesapp.view.splash.components

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.example.rutescompartidesapp.R

@Composable
fun LogoAnimation(){
    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }.build()

    Image(
        painter = rememberAsyncImagePainter(R.drawable.rutescomp, imageLoader),
        contentDescription = null,
        modifier = Modifier.fillMaxWidth(0.5f)
    )
}