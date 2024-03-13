package com.example.rutescompartidesapp.view.routedetailgeneral

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.rutescompartidesapp.R
import com.example.rutescompartidesapp.view.map.components.CardBottomMap
import com.example.rutescompartidesapp.view.map.components.ExpandableFloatingButton
import com.example.rutescompartidesapp.view.map.components.MapViewContainer
import com.example.rutescompartidesapp.view.map.components.NotificationButtonCard
import com.example.rutescompartidesapp.view.map.components.SearchView
import com.example.rutescompartidesapp.view.map.components.SearchViewContainer
import com.example.rutescompartidesapp.view.map.viewModels.MapViewModel
import com.example.rutescompartidesapp.view.map.viewModels.SearchViewModel
import com.example.rutescompartidesapp.view.routedetailgeneral.components.RouteMapViewContainer
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay

val openSansFamily = FontFamily(
    Font(R.font.opensans, FontWeight.Normal),
)
val fredokaOneFamily = FontFamily(
    Font(R.font.fredokaone, FontWeight.Normal),
)


object RouteDetailGeneralScreen: Screen {
    const val maxKmFog = 5

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
    }
}

@Composable
fun RouteDetailGeneralScreen(navController: NavHostController, mapViewModel: MapViewModel, startPoint: GeoPoint, endPoint: GeoPoint) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        // We get the context
        val ctx = LocalContext.current

        RouteMapViewContainer(viewModel = mapViewModel,ctx, startPoint,endPoint)
    }
}