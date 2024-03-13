package com.example.rutescompartidesapp.view.routedetailgeneral.components

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import com.example.rutescompartidesapp.view.map.viewModels.MapViewModel
import org.osmdroid.bonuspack.routing.OSRMRoadManager
import org.osmdroid.bonuspack.routing.RoadManager
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay


@Composable
fun RouteMapViewContainer(viewModel: MapViewModel, ctx : Context, startPoint: GeoPoint, endPoint: GeoPoint){

    val agentNameMap = "rutescompartides"

    val initialZoom = 20.0

    val lifecycleOwner = LocalLifecycleOwner.current

    // Load/initialize the osmdroid configuration
    Configuration.getInstance().load(
        ctx,
        androidx.preference.PreferenceManager.getDefaultSharedPreferences(ctx)
    )
    Configuration.getInstance().userAgentValue = agentNameMap

    AndroidView(
        modifier = Modifier
            .fillMaxHeight(0.25f)
            .fillMaxWidth(0.7f),
        factory = { context ->

            // We instance the MapView
            val mapView = MapView(context)

            // Set tile source for the map
            mapView.setTileSource(TileSourceFactory.MAPNIK)

            // Get center of route
            val centerRoute = viewModel.getCenterRoute(startPoint.latitude,startPoint.longitude,endPoint.latitude,endPoint.longitude)

            // We instantiate the map controller, so that we can set configurations of where in the world to appear
            val controller = mapView.controller
            controller.setCenter(centerRoute)
            controller.setZoom(initialZoom)

            // Create and add the location overlay
            val locationOverlay = MyLocationNewOverlay(GpsMyLocationProvider(ctx), mapView)
            locationOverlay.enableMyLocation()
            mapView.overlays.add(locationOverlay)

            // We create the static route to show
            val roadManager: RoadManager = OSRMRoadManager(ctx, agentNameMap)
            viewModel.showPathBetweenPoints(startPoint, endPoint, mapView, roadManager)

            mapView
        }
    )
}