package com.example.rutescompartidesapp.view.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen

object MapScreen: Screen {
    @Composable
    override fun Content() {
        MapScreen()
    }
}

@Composable
fun MapScreen(){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center ) {
        Text(text = "MapScreen", fontSize = 30.sp)
    }
}