package com.example.rutescompartidesapp.view.splash

import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.rutescompartidesapp.R
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

        setContent {
            RutesCompartidesAppTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LogoAnimation()

                    LaunchedEffect(key1 = true){
                        delay(2000)
                        val intent = Intent(this@SplashActivity, MainActivity::class.java)
                        val bundle = ActivityOptions.makeCustomAnimation(this@SplashActivity, anim.fade_in, anim.fade_out).toBundle()
                        startActivity(intent, bundle)
                        finish()
                    }
                }
            }
        }
    }

}