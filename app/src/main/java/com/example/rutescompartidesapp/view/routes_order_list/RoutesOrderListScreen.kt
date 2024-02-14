package com.example.rutescompartidesapp.view.routes_order_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen

object RoutesOrderListScreen: Screen {
    @Composable
    override fun Content() {
        RoutesOrderListScreen()
    }
}

@Composable
fun RoutesOrderListScreen(){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center ) {
        Text(text = "RouteOrderListScreen", fontSize = 30.sp)
    }
}