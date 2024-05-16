package com.example.rutescompartidesapp.view.map.components

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import com.example.rutescompartidesapp.view.map.viewModels.MapViewModel
import org.osmdroid.bonuspack.routing.OSRMRoadManager
import org.osmdroid.bonuspack.routing.RoadManager
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay


@Composable
fun MapViewContainer(viewModel: MapViewModel, ctx : Context, iconMarkerClickPointer: Drawable? = null){

    val agentNameMap = "rutescompartides"

    val initialZoom = 13.0

    val lifecycleOwner = LocalLifecycleOwner.current

    // Load/initialize the osmdroid configuration
    Configuration.getInstance().load(
        ctx,
        androidx.preference.PreferenceManager.getDefaultSharedPreferences(ctx)
    )
    Configuration.getInstance().userAgentValue = agentNameMap

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
            val mapView = MapView(context)

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


            mapView
        },
        update = { mapView ->
            // We instance RoadManager
            val roadManager: RoadManager = OSRMRoadManager(ctx, agentNameMap)

            // Code to update or recompose the view goes here
            // Since geoPoint is read here, the view will recompose whenever it is updated
            mapView.controller.setCenter(viewModel.markerPosition.value)

            // ViewModel function to handle click's user in map
            viewModel.handleClicksMap(mapView, iconMarkerClickPointer, ctx, roadManager)
        }
    )
}