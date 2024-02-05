package com.example.rutescompartidesapp.view.map

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.rutescompartidesapp.view.map.components.CardBottomMap
import com.example.rutescompartidesapp.view.map.components.ExpandableFloatingButton
import com.example.rutescompartidesapp.view.map.components.SearchViewContainer
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay

object MapScreen: Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        MapScreen()
    }
}

@Composable
fun MapScreen() {
    val mapViewModel: MapViewModel = hiltViewModel()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            val percentagePaddingDesviament = 100
            val padding = (LocalDensity.current.density * percentagePaddingDesviament).dp

            // Mapa
            MapViewContainer(viewModel = mapViewModel)

            // Search
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.TopStart)
            ) {
                SearchViewContainer()
            }

            // InfoCards Bottom Screen
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.BottomCenter)
            ) {
                CardBottomMap()
            }

            // Floatting Button Expandable
            Box(
                modifier = Modifier
                .padding(16.dp)
                .padding(bottom = padding)
                .fillMaxSize(),
                contentAlignment = Alignment.BottomEnd
            ){
                ExpandableFloatingButton()
            }
        }
    }
}

@Composable
fun MapViewContainer(viewModel: MapViewModel ){

    val ctx = LocalContext.current

    val initialZoom = 13.0

    val lifecycleOwner = LocalLifecycleOwner.current

    // Load/initialize the osmdroid configuration
    Configuration.getInstance().load(
        ctx,
        androidx.preference.PreferenceManager.getDefaultSharedPreferences(ctx)
    )
    Configuration.getInstance().userAgentValue = "rutescompartides"

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->

            //Creamos el MapView
            val mapView = org.osmdroid.views.MapView(context)

            // Set tile source for the map
            mapView.setTileSource(TileSourceFactory.MAPNIK)

            // Add default zoom buttons and enable multi-touch controls
            mapView.setBuiltInZoomControls(true)
            mapView.setMultiTouchControls(true)

            //Instanciamos el controlador del mapa, para poder establecer configuraciones de en que lugar del mundo aparecer
            val controller = mapView.controller
            controller.setCenter(viewModel.markerPosition.value)
            controller.setZoom(initialZoom)

            // Create and add the location overlay
            val locationOverlay = MyLocationNewOverlay(GpsMyLocationProvider(ctx), mapView)
            locationOverlay.enableMyLocation()
            mapView.overlays.add(locationOverlay)

            mapView
        },
        update = { mapView ->
            // Code to update or recompose the view goes here
            // Since geoPoint is read here, the view will recompose whenever it is updated
            mapView.controller.setCenter(viewModel.markerPosition.value)
        }
    )

    /*
            AL TOCAR LA PANTALLA SE CREE UN MARKER

            mapView.setOnTouchListener { _, event ->
                when (event.action) {
                    MotionEvent.ACTION_UP -> {
                        // Convert screen coordinates to GeoPoint
                        val x = event.x.toInt()
                        val y = event.y.toInt()
                        val projection = mapView.projection
                        val geoPoint = projection.fromPixels(x, y)

                        // Handle the click event
                        viewModel.createMarker(geoPoint as GeoPoint, mapView)

                        true  // Consume the touch event
                    }
                    else -> false
                }
            }

            */
}