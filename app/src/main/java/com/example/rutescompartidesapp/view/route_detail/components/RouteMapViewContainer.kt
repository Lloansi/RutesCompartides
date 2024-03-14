package com.example.rutescompartidesapp.view.route_detail.components

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
fun RouteMapViewContainer(viewModel: MapViewModel, ctx : Context, startPoint: GeoPoint, endPoint: GeoPoint, zoomMap : Double){

    val agentNameMap = "rutescompartides"

    // Load/initialize the osmdroid configuration
    Configuration.getInstance().load(
        ctx,
        androidx.preference.PreferenceManager.getDefaultSharedPreferences(ctx)
    )
    Configuration.getInstance().userAgentValue = agentNameMap

    AndroidView(
        modifier = Modifier
            //.height(150.dp)
            //.width(150.dp)
                ,
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
            controller.setZoom(zoomMap)

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