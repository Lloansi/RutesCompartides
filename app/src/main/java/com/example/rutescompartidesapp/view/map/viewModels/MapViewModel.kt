package com.example.rutescompartidesapp.view.map.viewModels

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.MotionEvent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rutescompartidesapp.domain.model.Order
import com.example.rutescompartidesapp.view.map.MapScreen.maxKmFog
import com.example.rutescompartidesapp.view.map.components.allOrders
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.Polyline
import org.osmdroid.views.overlay.infowindow.BasicInfoWindow
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin

class MapViewModel:ViewModel() {
    //private val applicationContext: Context = context.applicationContext
    private val _orders = MutableStateFlow<MutableList<Order>>(mutableListOf())
    var orders = _orders.asStateFlow()

    private val _userClickedPointer = MutableStateFlow<MutableList<Marker>>(mutableListOf())
    private var userClickedPointer = _userClickedPointer.asStateFlow()

    private val _ordersMarkers = MutableStateFlow<MutableSet<Marker>>(mutableSetOf())
    private var ordersMarkers = _ordersMarkers.asStateFlow()

    private val _visibleOrders = MutableStateFlow<MutableList<GeoPoint>>(mutableListOf())
    var visibleOrders = _visibleOrders.asStateFlow()

    var markerPosition = MutableLiveData<GeoPoint>()

    init {
        //Barcelona GeoPoint
        markerPosition.value = GeoPoint(41.4534265,2.1837151)

        //NY
        //markerPosition.value = GeoPoint(40.796788, -73.949232)
    }

    private fun createMarker(point: GeoPoint, mapView: MapView, iconMarker: Drawable? = null){
        val marker = Marker(mapView)
        marker.position =  point
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
        marker.setInfoWindow(null) // If user click the marker, don't appear any popup window above
        if (iconMarker != null){
            marker.icon = iconMarker
        }
        ordersMarkers.value.add(marker)

        // Add the marker instantiated a few lines back in the map view
        mapView.overlays.add(marker)
    }

    private fun createClickPointerMarker(point: GeoPoint, mapView: MapView, iconMarker: Drawable? = null){
        val marker = Marker(mapView)
        marker.position =  point
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
        marker.setInfoWindow(null) // If user click the marker, don't appear any popup window above
        if (iconMarker != null){
            marker.icon = iconMarker
        }
        userClickedPointer.value.add(marker)

        // Add the marker instantiated a few lines back in the map view
        mapView.overlays.add(marker)
    }

    private fun deleteOrderMarkers(markers: MutableSet<Marker>, mapView: MapView){
        for (marker in markers){
            mapView.overlays.remove(marker)
        }
        markers.clear()
    }

    private fun deleteUserClickPointer(marker: Marker, mapView: MapView){
        userClickedPointer.value.removeAt(0)
        mapView.overlays.remove(marker)
    }

    private fun updateMarkerPosition(point: GeoPoint){
        markerPosition.value = point
    }

    /*
    mapView.setOnTouchListener { _, event ->
    gestureDetector.onTouchEvent(event)
    }
     */
    @SuppressLint("ClickableViewAccessibility")
    fun handleClicksMap(mapView: MapView, iconMarkerType: Drawable? = null, iconMarkerClickPointer: Drawable? = null ){
        var isDragging = false
        mapView.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // Touch event starts, not a drag yet
                    isDragging = false
                    //true  // if MotionEvent dont have anything inside, use it to consume the touch event
                }
                MotionEvent.ACTION_MOVE -> {
                    // User is moving their finger, this is a drag
                    isDragging = true
                    //true  // if MotionEvent dont have anything inside, use it to consume the touch event
                }
                MotionEvent.ACTION_UP -> {
                    // User tap screen
                    if (!isDragging) {
                        // Convert click screen coordinates to GeoPoint
                        val x = event.x.toInt()
                        val y = event.y.toInt()
                        val projection = mapView.projection
                        val geoPoint = projection.fromPixels(x, y)
                        // We transform this click coordinates to Class GeoPoint
                        geoPoint as GeoPoint

                        // Controller of the mapView for set center, animations ...
                        val controller = mapView.controller

                        // With the position where user clicked, we create a point marker
                        // If user never clicked, we just create a Click Pointer Marker if not, we delete the old Click Pointer Marker and create the new one
                        if (userClickedPointer.value.isNotEmpty()){
                            deleteUserClickPointer(userClickedPointer.value[0], mapView)
                            createClickPointerMarker(geoPoint, mapView, iconMarkerClickPointer)
                        }else{
                            createClickPointerMarker(geoPoint, mapView, iconMarkerClickPointer)
                        }

                        // Handle the click event, we update our mutable live data, with the click coordinates
                        // The clicked point becomes the center point
                        updateMarkerPosition(geoPoint)
                        controller.setCenter(markerPosition.value)

                        // With the position where user clicked, we check if near there is any marker, if there is, markers appear
                        // If user never clicked, we just show it as mentioned before, if not, we delete the old markers and create the new ones
                        if (ordersMarkers.value.isNotEmpty()){
                            deleteOrderMarkers(ordersMarkers.value, mapView)
                            isNearClickUser(allOrders,mapView,iconMarkerType)
                        }else{
                            isNearClickUser(allOrders,mapView,iconMarkerType)
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

    private fun isNearClickUser(ordersList : List<Order>, mapView: MapView, iconMarkerType: Drawable? = null){
        // We get the pixels from the center screen
        val centerPoint = mapView.projection.fromPixels(mapView.width / 2, mapView.height / 2)

        // We iterate the list to get the lat and lon
        // Then we compare the route distance with center distance (that we instanced lines before as "centerPoint") to know if the distance between them is minor to the max permitted, if is, we show it in the map
        for (orderPos in ordersList){
            val centerLat = centerPoint.latitude
            val centerLon = centerPoint.longitude
            val centerGeoPoint = GeoPoint(centerLat,centerLon)
            val orderGeoPoint = GeoPoint(orderPos.lat.toDouble(),orderPos.lon.toDouble())

            if(isInArea(centerGeoPoint,orderGeoPoint, maxKmFog)){
                createMarker(orderGeoPoint,mapView,iconMarkerType)
                visibleOrders.value.add(orderGeoPoint)
            }
        }
    }

    private fun isInArea(point1: GeoPoint, point2: GeoPoint, maxKmDistance: Int):Boolean{
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

    fun initialPosition(orders: MutableList<Order>, centerPoint : GeoPoint, mapView: MapView){
        val controller = mapView.controller
        controller.setCenter(markerPosition.value)
        // We iterate the list to get the lat and lon
        // Then we compare the route distance with center distance (that we instanced lines before as "centerPoint") to know if the distance between them is minor to the max permitted, if is, we show it in the map
        for (order in orders){
            val orderGeoPoint = GeoPoint(order.lat.toDouble(),order.lon.toDouble())

            if(isInArea(centerPoint,orderGeoPoint, maxKmFog)){
                createMarker(orderGeoPoint,mapView)
            }
        }
    }

    fun filterPerVisibilityOrders(visibleOrders:  MutableList<GeoPoint>): List<Order>{
        val filteredOrders = allOrders.filter { order ->
            visibleOrders.any { geoPoint ->
                geoPoint.latitude.toFloat() == order.lat && geoPoint.longitude.toFloat() == order.lon
            }
        }
        return filteredOrders
    }

    fun path(mapView: MapView){

        // THIS FUNCTION CREATES A SQUARE IN NY

        //mOsmPathOverlay = new OsmPathOverlay(context);
        //mMapView.getOverlayManager().add(mOsmPathOverlay);
        //mOsmPathOverlay = new OsmPathOverlay(context);
        //mMapView.getOverlayManager().add(mOsmPathOverlay);
        val line = Polyline(mapView)
        line.title = "Central Park, NYC"
        line.subDescription = Polyline::class.java.canonicalName
        line.width = 20f
        /*
        polyline.infoWindow = null;
        polyline.color = (Color.rgb(0,191,255)) // Set the color of the polyline
        polyline.width = 5f // Set the width of the polyline in pixels

        val dashArray = floatArrayOf(10F, 20F)
        val dashPhase = 0F
        polyline.paint.pathEffect = DashPathEffect(dashArray, dashPhase)
         */
        val pts: MutableList<GeoPoint> = ArrayList()
        //here, we create a polygon, note that you need 5 points in order to make a closed polygon (rectangle)

        //here, we create a polygon, note that you need 5 points in order to make a closed polygon (rectangle)
        pts.add(GeoPoint(40.796788, -73.949232))
        pts.add(GeoPoint(40.796788, -73.981762))
        pts.add(GeoPoint(40.768094, -73.981762))
        pts.add(GeoPoint(40.768094, -73.949232))
        pts.add(GeoPoint(40.796788, -73.949232))
        line.setPoints(pts)
        line.isGeodesic = true
        line.infoWindow = BasicInfoWindow(org.osmdroid.library.R.layout.bonuspack_bubble, mapView)
        //Note, the info window will not show if you set the onclick listener
        //line can also attach click listeners to the line
        /*
        line.setOnClickListener(new Polyline.OnClickListener() {
            @Override
            public boolean onClick(Polyline polyline, MapView mapView, GeoPoint eventPos) {
                Toast.makeText(context, "Hello world!", Toast.LENGTH_LONG).show();
                return false;
            }
        });*/
        //Note, the info window will not show if you set the onclick listener
        //line can also attach click listeners to the line
        /*
        line.setOnClickListener(new Polyline.OnClickListener() {
            @Override
            public boolean onClick(Polyline polyline, MapView mapView, GeoPoint eventPos) {
                Toast.makeText(context, "Hello world!", Toast.LENGTH_LONG).show();
                return false;
            }
        });*/
        mapView.overlayManager.add(line)
    }
}