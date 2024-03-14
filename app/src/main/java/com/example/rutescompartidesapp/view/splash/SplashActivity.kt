package com.example.rutescompartidesapp.view.splash

import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.rutescompartidesapp.R.*
import com.example.rutescompartidesapp.ui.theme.RutesCompartidesAppTheme
import com.example.rutescompartidesapp.view.MainActivity
import com.example.rutescompartidesapp.view.splash.components.LogoAnimation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity: ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()

        setContent {
            RutesCompartidesAppTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                ) {
                    Box(
                        modifier = Modifier
                            .height(250.dp)
                            .width(250.dp)
                            .align(Alignment.TopEnd)
                    ){
                        Image(
                            painter = painterResource(drawable.splash_top_image),
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .height(250.dp)
                                .width(250.dp)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .align(Alignment.Center)
                    ){
                        LogoAnimation()

                        LaunchedEffect(key1 = true){
                            delay(2000)
                            val intent = Intent(this@SplashActivity, MainActivity::class.java)
                            val bundle = ActivityOptions.makeCustomAnimation(this@SplashActivity, anim.fade_in, anim.fade_out).toBundle()
                            startActivity(intent, bundle)
                            finish()
                        }
                    }
                    Box(
                        modifier = Modifier
                            .height(250.dp)
                            .width(250.dp)
                            .align(Alignment.BottomStart)
                    ){
                        Image(
                            painter = painterResource(drawable.splash_bottom_image),
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .height(250.dp)
                                .width(250.dp)
                        )
                    }
                }

            }
        }
    }

}