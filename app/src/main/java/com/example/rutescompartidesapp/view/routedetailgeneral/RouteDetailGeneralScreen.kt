package com.example.rutescompartidesapp.view.routedetailgeneral

import android.annotation.SuppressLint
import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.rutescompartidesapp.view.map.viewModels.MapViewModel
import com.example.rutescompartidesapp.view.routedetailgeneral.components.RouteMapViewContainer
import org.osmdroid.util.GeoPoint

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun RouteDetailGeneralScreen(navController: NavHostController, mapViewModel: MapViewModel, startPoint: GeoPoint, endPoint: GeoPoint) {

    //val responsiveHeight = (LocalConfiguration.current.screenHeightDp / 2.5).dp
    val responsiveWidth = (LocalConfiguration.current.screenWidthDp / 15).dp
    val responsiveHeight = (LocalConfiguration.current.screenHeightDp / 5).dp
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        // We get the context
        val ctx = LocalContext.current
        Column (
            modifier = Modifier ,
            verticalArrangement = Arrangement.Top
        ) {
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .fillMaxHeight(0.3f)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(16.dp)) // Adjust corner radius as needed
                ) {
                    RouteMapViewContainer(viewModel = mapViewModel, ctx, startPoint, endPoint, 13.5)
                }
            }
        }
    }
}