package com.example.rutescompartidesapp.view.map

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.rutescompartidesapp.R
import com.example.rutescompartidesapp.view.map.components.CardBottomMap
import com.example.rutescompartidesapp.view.map.components.ExpandableFloatingButton
import com.example.rutescompartidesapp.view.map.components.NotificationButtonCard
import com.example.rutescompartidesapp.view.map.components.SearchView
import com.example.rutescompartidesapp.view.map.viewModels.MapViewModel
import com.example.rutescompartidesapp.view.map.viewModels.SearchViewModel
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay

object MapScreen: Screen {
    const val maxKmFog = 5

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        MapScreen()
    }
}

@Composable
fun MapScreen() {
    val mapViewModel: MapViewModel = hiltViewModel()
    val visibleOrders by mapViewModel.visibleOrders.collectAsState()
    val ordersFiltered by mapViewModel.filteredOrders.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            // Padding percentage formula
            val percentagePaddingDesviament = 100
            val padding = (LocalDensity.current.density * percentagePaddingDesviament).dp

            // Map
            // We get the context
            val ctx = LocalContext.current

            // We instance the markers drawable type
            val iconMarkerType = ContextCompat.getDrawable(ctx, R.drawable.little_map_marker_orders_svg)
            val iconMarkerClickPointer = ContextCompat.getDrawable(ctx, R.drawable.marker_svgrepo_com)

            MapViewContainer(viewModel = mapViewModel,ctx, iconMarkerType,iconMarkerClickPointer, visibleOrders)

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
                CardBottomMap(ordersFiltered)
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
fun MapViewContainer(viewModel: MapViewModel, ctx : Context,iconMarkerType: Drawable? = null,iconMarkerClickPointer: Drawable? = null, visibleOrders:MutableList<GeoPoint>){

    val initialZoom = 13.0

    val lifecycleOwner = LocalLifecycleOwner.current

    // Load/initialize the osmdroid configuration
    Configuration.getInstance().load(
        ctx,
        androidx.preference.PreferenceManager.getDefaultSharedPreferences(ctx)
    )
    Configuration.getInstance().userAgentValue = "rutescompartides"

    AndroidView(
        modifier = Modifier
            .fillMaxSize(),
            /*
            .pointerInput(Unit) {
                detectTapGestures (
                ) { offset ->

                    // We get the x & y where ocurred the click
                    val clickX = offset.x
                    val clickY = offset.y

                    // We update with clicked pixels data before on our remember
                    viewModel.markerPosition.value = GeoPoint(clickX.toDouble(),clickY.toDouble())

                    }
                }
            .combinedClickable (

            ){}
             */
        factory = { context ->

            // We instance the MapView
            val mapView = org.osmdroid.views.MapView(context)

            // Set tile source for the map
            mapView.setTileSource(TileSourceFactory.MAPNIK)

            // Add default zoom buttons and enable multi-touch controls
            mapView.setBuiltInZoomControls(true)
            mapView.setMultiTouchControls(true)

            // We instantiate the map controller, so that we can set configurations of where in the world to appear
            val controller = mapView.controller
            controller.setCenter(viewModel.markerPosition.value)
            controller.setZoom(initialZoom)

            // Create and add the location overlay
            val locationOverlay = MyLocationNewOverlay(GpsMyLocationProvider(ctx), mapView)
            locationOverlay.enableMyLocation()
            mapView.overlays.add(locationOverlay)

            /*
            PARA QUE CUANDO SE CREE EL MAPA SE MIRE SI HAY MARKERS CERCA DEL PUNTO INCIAL
            viewModel.markerPosition.value?.let {
                viewModel.initialPosition(allRoute, it,mapView)
            }
             */

            viewModel.path(mapView)

            mapView
        },
        update = { mapView ->

            // Code to update or recompose the view goes here
            // Since geoPoint is read here, the view will recompose whenever it is updated
            mapView.controller.setCenter(viewModel.markerPosition.value)


            viewModel.handleClicksMap(mapView, iconMarkerType, iconMarkerClickPointer)

            /*
            SI USAMOS GESTURE DETECTOR EN VEZ DE MOTION EVENTS
            mapView.setOnTouchListener { _, event ->
                gestureDetector.onTouchEvent(event)
            }
            */
        }
    )
}

@Composable
fun SearchViewContainer() {
    val searchViewModel: SearchViewModel = hiltViewModel()
    Row (modifier = Modifier
        .offset(y = -(4).dp)
        .fillMaxWidth()
        .wrapContentHeight() ,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SearchView(searchViewModel)
        NotificationButtonCard()
    }
}