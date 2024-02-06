package com.example.rutescompartidesapp.view.map

import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import com.example.rutescompartidesapp.view.map.components.allRoute
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView

private const val DEBUG_TAG = "Gestures"

class MyGestureListener(private val mapView: MapView, private val mapViewModel: MapViewModel) : GestureDetector.SimpleOnGestureListener() {

    override fun onDown(event: MotionEvent): Boolean {
        Log.d(DEBUG_TAG, "onDown: $event")
        return true
    }

    override fun onFling(
        e1: MotionEvent?,
        e2: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        return super.onFling(e1, e2, velocityX, velocityY)
    }

    override fun onLongPress(event: MotionEvent) {
        Log.d(DEBUG_TAG, "onLongPress: $event")
    }

    override fun onScroll(
        e1: MotionEvent?,
        e2: MotionEvent,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        return super.onScroll(e1, e2, distanceX, distanceY)
    }

    override fun onShowPress(event: MotionEvent) {
        Log.d(DEBUG_TAG, "onShowPress: $event")
    }

    override fun onSingleTapUp(event: MotionEvent): Boolean {
        // Convert screen coordinates to GeoPoint
        val x = event.x.toInt()
        val y = event.y.toInt()
        val projection = mapView.projection
        val geoPoint = projection.fromPixels(x, y)

        // We get the pixels from the center
        val centerPoint = mapView.projection.fromPixels(mapView.width / 2, mapView.height / 2)

        // Controller of the mapView for set center, animations ...
        val controller = mapView.controller


        // Handle the click event
        // The clicked point becomes the center point
        mapViewModel.updateMarkerPosition(geoPoint as GeoPoint)
        controller.setCenter(mapViewModel.markerPosition.value)

        // We iterate the list to get the lat and lon
        // Then we compare the route distance with center distance (that we instanced lines before as "centerPoint") to know if the distance between them is minor to the max permitted, if is, we show it in the map
        for (route in allRoute){
            val centerLat = centerPoint.latitude
            val centerLon = centerPoint.longitude
            val centerGeoPoint = GeoPoint(centerLat,centerLon)
            val routeGeoPoint = GeoPoint(route.lat.toDouble(),route.lon.toDouble())

            if(mapViewModel.isInArea(centerGeoPoint,routeGeoPoint, MapScreen.maxKmFog)){
                mapViewModel.createMarker(routeGeoPoint,mapView)
            }
        }
        return true
    }
}
