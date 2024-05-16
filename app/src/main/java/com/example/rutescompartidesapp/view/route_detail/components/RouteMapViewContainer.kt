package com.example.rutescompartidesapp.view.route_detail.components

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.example.rutescompartidesapp.R
import com.example.rutescompartidesapp.utils.getResponsivePadding
import com.example.rutescompartidesapp.utils.round
import com.example.rutescompartidesapp.view.map.viewModels.MapViewModel
import com.example.rutescompartidesapp.view.map.viewModels.MapViewModel2
import org.osmdroid.bonuspack.routing.OSRMRoadManager
import org.osmdroid.bonuspack.routing.RoadManager
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import kotlin.math.absoluteValue


@Composable
fun RouteMapViewContainer(viewModel: MapViewModel, viewModel2: MapViewModel2, ctx : Context, startPoint: GeoPoint, endPoint: GeoPoint){

    val agentNameMap = "rutescompartides"
    val routeIconMarker = ContextCompat.getDrawable(ctx, R.drawable.little_map_marker_routes_svg)


    // Load/initialize the osmdroid configuration
    Configuration.getInstance().load(
        ctx,
        androidx.preference.PreferenceManager.getDefaultSharedPreferences(ctx)
    )
    Configuration.getInstance().userAgentValue = agentNameMap


    AndroidView(
        modifier = Modifier
            .fillMaxSize()
            .padding(getResponsivePadding()),
        factory = { context ->

            // We instance the MapView
            val mapView = MapView(context)

            // Set tile source for the map
            mapView.setTileSource(TileSourceFactory.MAPNIK)

            // Add default zoom buttons and enable multi-touch controls
            mapView.setBuiltInZoomControls(true)
            mapView.setMultiTouchControls(true)

            // Get center of route
            val centerRoute = viewModel.getCenterRoute(startPoint.latitude,startPoint.longitude,endPoint.latitude,endPoint.longitude)

            // We instantiate the map controller, so that we can set configurations of where in the world to appear
            val controller = mapView.controller
            controller.setCenter(centerRoute)

            val distanceRoute = viewModel.distanceBetweenPoints(startPoint.latitude,startPoint.longitude,endPoint.latitude,endPoint.longitude)
            val zoomLevel = viewModel2.calculateZoomLevel(distanceRoute,0.08)
            controller.setZoom(zoomLevel.round(2).absoluteValue)
            println("ZOOM ${zoomLevel.round(2).absoluteValue}")
            //controller.setZoom(zoomMap)


            /*
            // Create and add the location overlay
            val locationOverlay = MyLocationNewOverlay(GpsMyLocationProvider(ctx), mapView)
            locationOverlay.enableMyLocation()
            mapView.overlays.add(locationOverlay)
             */

            // We create the static route to show
            val roadManager: RoadManager = OSRMRoadManager(ctx, agentNameMap)

            // When the map initialize, the function to print the path and points take place
            viewModel2.createPathAndRoutePoints(viewModel,startPoint,endPoint,mapView,roadManager,routeIconMarker)

            mapView
        },
        update = { mapView ->
            viewModel2.clickStateMap(mapView)
        }

    )
}