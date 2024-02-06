package com.example.rutescompartidesapp.view.map

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.MotionEvent
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.core.view.GestureDetectorCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rutescompartidesapp.R
import com.example.rutescompartidesapp.domain.model.Route
import com.example.rutescompartidesapp.view.map.MapScreen.maxKmFog
import com.example.rutescompartidesapp.view.map.components.allRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin


class MapViewModel(
    //context: Context,
):ViewModel() {


    //private val applicationContext: Context = context.applicationContext

    private val _markerList = MutableStateFlow<MutableList<GeoPoint>>(mutableListOf())
    var markerList = _markerList.asStateFlow()

    var markerPosition = MutableLiveData<GeoPoint>()

    init {
        //Barcelona GeoPoint
        markerPosition.value = GeoPoint(41.4534265,2.1837151)

    }

    fun createMarker(point: GeoPoint, mapView: MapView, icon: Drawable? = null){
        val marker = Marker(mapView)
        marker.position =  point
        if (icon != null){
            marker.icon = icon
        }

        // Add the marker instantiated a few lines back in the map view
        mapView.overlays.add(marker)
    }

    fun updateMarkerPosition(point: GeoPoint){
        markerPosition.value = point
    }

    fun addMarkersList(markerList: MutableStateFlow<MutableList<GeoPoint>>, mapView: MapView){
        val markers = markerList.value.toMutableList()

        for (geoPoint in markers) {
            //createMarker(geoPoint,mapView)
        }
    }

    /*
    mapView.setOnTouchListener { _, event ->
    gestureDetector.onTouchEvent(event)
    }
     */
    @SuppressLint("ClickableViewAccessibility")
    fun handleClickMap(mapView: MapView, icon: Drawable? = null){
        var isDragging = false
        mapView.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // Touch event starts, not a drag yet
                    isDragging = false
                    true  // Consume the touch event
                }
                MotionEvent.ACTION_MOVE -> {
                    // User is moving their finger, this is a drag
                    isDragging = true
                    true  // Consume the touch event
                }
                MotionEvent.ACTION_UP -> {
                    if (!isDragging) {
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
                        updateMarkerPosition(geoPoint as GeoPoint)
                        controller.setCenter(markerPosition.value)

                        // We iterate the list to get the lat and lon
                        // Then we compare the route distance with center distance (that we instanced lines before as "centerPoint") to know if the distance between them is minor to the max permitted, if is, we show it in the map
                        for (route in allRoute){
                            val centerLat = centerPoint.latitude
                            val centerLon = centerPoint.longitude
                            val centerGeoPoint = GeoPoint(centerLat,centerLon)
                            val routeGeoPoint = GeoPoint(route.lat.toDouble(),route.lon.toDouble())

                            if(isInArea(centerGeoPoint,routeGeoPoint, maxKmFog)){
                                createMarker(routeGeoPoint,mapView)
                            }
                        }
                    }
                    // Reset the drag flag
                    //isDragging = false
                    //true
                }
            }
            // Let the map handle the touch event for dragging
            false
        }
    }

    fun isInArea(point1: GeoPoint, point2: GeoPoint, maxKmDistance: Int):Boolean{
        val distanceBetweenPoints = distanceBetweenPoints(point1.longitude,point1.latitude,point2.longitude,point2.latitude)

        // We return true (if distance is lower than max distance permitted) or false (if distance is bigger than max distance permitted)
        return (distanceBetweenPoints <= maxKmDistance)
    }

    private fun distanceBetweenPoints(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double{
        val earthRadius = 6371 // 6371 is Earth radius in km.

        // We transform the data parameters to get the distance between points
        val distance = acos(sin(deg2rad(lat1))*sin(deg2rad(lat2))+cos(deg2rad(lat1))*cos(deg2rad(lat2))*cos(deg2rad(lon2)-deg2rad(lon1))) * earthRadius
        return distance
    }

    // Function to convert in radians
    private fun deg2rad(deg: Double): Double {
        return deg * Math.PI / 180.0
    }

    fun initialPosition(routes: List<Route>, centerPoint : GeoPoint, mapView: MapView){
        val controller = mapView.controller
        controller.setCenter(markerPosition.value)
        // We iterate the list to get the lat and lon
        // Then we compare the route distance with center distance (that we instanced lines before as "centerPoint") to know if the distance between them is minor to the max permitted, if is, we show it in the map
        for (route in routes){
            val routeGeoPoint = GeoPoint(route.lat.toDouble(),route.lon.toDouble())

            if(isInArea(centerPoint,routeGeoPoint, maxKmFog)){
                createMarker(routeGeoPoint,mapView)
            }
        }
    }
}
/*
fun getDrawableResource(id: Int): Drawable? {
    return ContextCompat.getDrawable(applicationContext, R.drawable.comanda_icon_svg)
}

 */

/*
fun getDrawableResource(): Drawable? {
    val d = ContextCompat.getDrawable(getApplication(context), R.drawable.comanda_icon_svg)
    val bitmap: Bitmap = (d as BitmapDrawable?)!!.bitmap
    val scaledBitmap = Bitmap.createScaledBitmap(
        bitmap,
        (48.0f * getApplication(context).resources.displayMetrics.density).toInt(),
        (48.0f * getApplication(context).resources.displayMetrics.density).toInt(),
        true
    )
    return BitmapDrawable(getApplication(context).resources, scaledBitmap)
}


 */

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