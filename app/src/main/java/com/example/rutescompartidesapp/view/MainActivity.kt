package com.example.rutescompartidesapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.rutescompartidesapp.ui.theme.RutesCompartidesAppTheme
import com.example.rutescompartidesapp.view.profile.ProfileScreen
import com.example.rutescompartidesapp.view.profile.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint


class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RutesCompartidesAppTheme {
                ProfileScreen(viewModel = ProfileViewModel())
            }
        }
    }
}


