package com.example.rutescompartidesapp.view.map.viewModels

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.MotionEvent
import android.graphics.Color
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rutescompartidesapp.R
import com.example.rutescompartidesapp.data.domain.Order
import com.example.rutescompartidesapp.data.domain.Route
import com.example.rutescompartidesapp.data.domain.Route2
import com.example.rutescompartidesapp.data.domain.orders.Orders
import com.example.rutescompartidesapp.data.domain.routes.Routes
import com.example.rutescompartidesapp.data.network.GoogleLocation.repository.GoogleLocationsRepository
import com.example.rutescompartidesapp.data.network.idescat.repository.idescatRepository
import com.example.rutescompartidesapp.utils.LocalConstants.orderList
import com.example.rutescompartidesapp.utils.LocalConstants.routeList
import com.example.rutescompartidesapp.view.map.MapScreen.maxKmFog
import com.example.rutescompartidesapp.view.map.components.allOrders
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.rutescompartidesapp.view.map.components.allRoute
import com.example.rutescompartidesapp.view.map.components.allRoute2
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.osmdroid.bonuspack.routing.Road
import org.osmdroid.bonuspack.routing.RoadManager
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.Polyline
import org.osmdroid.views.overlay.infowindow.BasicInfoWindow
import javax.inject.Inject
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin

@HiltViewModel
class MapViewModel @Inject constructor (
    private val googleLocationsRepository: GoogleLocationsRepository,
    private val idescatRepository: idescatRepository
) :ViewModel() {
    private val _userClickedPointer = MutableStateFlow<MutableList<Marker>>(mutableListOf())
    private var userClickedPointer = _userClickedPointer.asStateFlow()

    private val _ordersMarkers = MutableStateFlow<MutableSet<Marker>>(mutableSetOf())
    private var ordersMarkers = _ordersMarkers.asStateFlow()

    private val _routesMarkers = MutableStateFlow<MutableSet<Marker>>(mutableSetOf())
    private var routesMarkers = _routesMarkers.asStateFlow()

    private val _routesPaths = MutableStateFlow<MutableSet<Polyline>>(mutableSetOf())
    private var routesPaths = _routesPaths.asStateFlow()

    private val _visibleOrders = MutableStateFlow<MutableList<GeoPoint>>(mutableListOf())
    var visibleOrders = _visibleOrders.asStateFlow()

    private val _visibleRoutes = MutableStateFlow<MutableList<GeoPoint>>(mutableListOf())
    var visibleRoutes = _visibleRoutes.asStateFlow()

    private val _filteredOrders = MutableStateFlow<List<Orders>?>(listOf())
    var filteredOrders = _filteredOrders.asStateFlow()

    private val _filteredRoutes = MutableStateFlow<List<Routes>?>(listOf())
    var filteredRoutes = _filteredRoutes.asStateFlow()

    private val _mapViewState = MutableStateFlow<MapView?>(null)
    var mapViewState = _mapViewState.asStateFlow()

    private val _roadManagerState = MutableStateFlow<RoadManager?>(null)
    var roadManagerState = _roadManagerState.asStateFlow()

    var markerPosition = MutableLiveData<GeoPoint>()

    init {

        //Barcelona GeoPoint
        markerPosition.value = GeoPoint(41.4534265,2.1837151)

        //NY
        //markerPosition.value = GeoPoint(40.796788, -73.949232)
    }

    /**
     * Creates a marker on the map.

     * @param type Type of marker ("ORDER" or "ROUTE").

     * @param point GeoPoint representing the position of the marker.

     * @param mapView MapView instance.

     * @param iconMarker Drawable for the marker icon.
     **/
    fun createMarker(type: String, point: GeoPoint, mapView: MapView, iconMarker: Drawable? = null){
        val marker = Marker(mapView)
        marker.position =  point
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
        marker.setInfoWindow(null) // If user click the marker, don't appear any popup window above
        if (iconMarker != null){
            marker.icon = iconMarker
        }

        when(type.uppercase()){
            "ORDER" -> _ordersMarkers.value.add(marker)
            "ROUTE" -> _routesMarkers.value.add(marker)
            else -> println("ERROR | Type mistake in createMarker()")
        }

        // Add the marker instantiated a few lines back in the map view
        mapView.overlays.add(marker)
    }

    /**
     * Creates a click pointer marker on the map.

     * @param point GeoPoint representing the position of the marker.

     * @param mapView MapView instance.

     * @param iconMarker Drawable for the marker icon.
     **/
    private fun createClickPointerMarker(point: GeoPoint, mapView: MapView, iconMarker: Drawable? = null){
        val marker = Marker(mapView)
        marker.position =  point
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
        marker.setInfoWindow(null) // If user click the marker, don't appear any popup window above
        if (iconMarker != null){
            marker.icon = iconMarker
        }
        _userClickedPointer.value.add(marker)

        // Add the marker instantiated a few lines back in the map view
        mapView.overlays.add(marker)
    }

    /**
     * Deletes order markers from the map.

     * @param markers Set of order markers to be deleted.

     * @param mapView MapView instance.
     **/
    private fun deleteOrdersMarkers(markers: MutableSet<Marker>, mapView: MapView){
        for (marker in markers){
            mapView.overlays.remove(marker)
        }
        markers.clear()
    }

    /**
     * Deletes route markers from the map.

     * @param markers Set of route markers to be deleted.

     * @param mapView MapView instance.
     **/
    private fun deleteRoutesMarkers(markers: MutableSet<Marker>, mapView: MapView){
        for (marker in markers){
            mapView.overlays.remove(marker)

        }
        markers.clear()
    }

    /**
     * Deletes route paths from the map.

     * @param paths Set of route paths to be deleted.

     * @param mapView MapView instance.
     **/
    private fun deleteRoutesPaths(paths: MutableSet<Polyline>, mapView: MapView){
        for (path in paths){
            mapView.overlays.remove(path)
        }
        paths.clear()
    }

    /**
     * Deletes the user click pointer marker from the map.

     * @param marker Marker representing the user click pointer.

     * @param mapView MapView instance.
     **/
    private fun deleteUserClickPointer(marker: Marker, mapView: MapView){
        _userClickedPointer.value.removeAt(0)
        mapView.overlays.remove(marker)
    }

    /**
     * Updates the position of the marker.

     * @param point New GeoPoint position for the marker.
     **/
    private fun updateMarkerPosition(point: GeoPoint){
        markerPosition.value = point
    }

    /**
     * Handles map clicks.

     * @param mapView The MapView object.

     * @param iconMarkerClickPointer Drawable for click pointer marker.

     * @param ctx Context object.

     * @param roadManager RoadManager object.
    **/
    @SuppressLint("ClickableViewAccessibility")
    fun handleClicksMap(mapView: MapView, iconMarkerClickPointer: Drawable? = null, ctx : Context, roadManager: RoadManager){
        // We instance the markers drawable type
        val orderIconMarker = ContextCompat.getDrawable(ctx, R.drawable.little_map_marker_orders_svg)
        val routeIconMarker = ContextCompat.getDrawable(ctx, R.drawable.little_map_marker_routes_svg)

        // We save our MapView
        _mapViewState.value = mapView

        // We save our RoadManager
        _roadManagerState.value = roadManager

        var isDragging = false
        mapView.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    isDragging= false
                    //true  // if MotionEvent don't have anything inside, use it to consume the touch event
                }
                MotionEvent.ACTION_MOVE -> {
                    // User dragging
                    isDragging = true
                    //true  // if MotionEvent don't have anything inside, use it to consume the touch event
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
                        if (_userClickedPointer.value.isNotEmpty()){
                            deleteUserClickPointer(_userClickedPointer.value[0], mapView)
                            createClickPointerMarker(geoPoint, mapView, iconMarkerClickPointer)
                        }else{
                            createClickPointerMarker(geoPoint, mapView, iconMarkerClickPointer)
                        }

                        // Handle the click event, we update our mutable live data, with the click coordinates
                        // The clicked point becomes the center point
                        updateMarkerPosition(geoPoint)
                        controller.animateTo(markerPosition.value)
                        //controller.setCenter(markerPosition.value)

                        // With the position where user clicked, we check if near there is any marker, if there is, markers appear
                        // If user never clicked, we just show it as mentioned before, if not, we delete the old markers and create the new ones
                        if (_ordersMarkers.value.isNotEmpty()){
                            // We delete orders
                            deleteOrdersMarkers(_ordersMarkers.value, mapView)
                            deleteRoutesPaths(_routesPaths.value, mapView)
                            // We check if near user's click have any order
                            isNearClickUser(ordersList = orderList, routesList = null, mapView, orderIconMarker, roadManager, maxKmDistance = maxKmFog)
                        }else{
                            // We check if near user's click have any order
                            isNearClickUser(ordersList = orderList, routesList = null, mapView, orderIconMarker, roadManager, maxKmDistance = maxKmFog)
                        }

                        if (_routesMarkers.value.isNotEmpty()){
                            // We delete routes and paths of the map
                            deleteRoutesMarkers(_routesMarkers.value, mapView)
                            deleteRoutesPaths(_routesPaths.value, mapView)
                            // We check if near user's click have any route
                            isNearClickUser(ordersList = null, routesList = routeList, mapView, routeIconMarker, roadManager, maxKmDistance = maxKmFog)
                        } else{
                            // We check if near user's click have any route
                            isNearClickUser(ordersList = null, routesList = routeList, mapView, routeIconMarker, roadManager, maxKmDistance = maxKmFog)
                        }
                    }
                    //isDragging = false // Reset the drag flag
                    //true
                }
            }
            // Let the map handle the touch event for dragging
            false
        }
    }

    /**
     *Checks if orders or routes are near the click user.

     *@param ordersList List of orders.

     *@param routesList List of routes.

     *@param mapView The MapView object.

     *@param iconMarkerType Drawable for marker type.

     *@param roadManager RoadManager object.

     *@param maxKmDistance Maximum distance in kilometers.
     **/
    private fun isNearClickUser(ordersList : MutableList<Orders>? = null, routesList: MutableList<Routes>? = null, mapView: MapView, iconMarkerType: Drawable? = null, roadManager: RoadManager, maxKmDistance: Int){
        // We get the pixels from the center screen
        val centerPoint = mapView.projection.fromPixels(mapView.width / 2, mapView.height / 2) as GeoPoint

        // We iterate the list to get the lat and lon of a marker (order)
        // Then we compare the route distance with center distance (that we instanced lines before as "centerPoint") to know if the distance between them is minor to the max permitted, if is, we show it in the map
        ordersList?.let {
            // We clear the list with previous visible orders, to latter inside if (isInArea) reassign new values
            _visibleOrders.value.clear()
            for (orderPos in ordersList){
                // Check if order is in area to show
                if(isInArea(centerPoint,orderPos.startPoint, maxKmDistance)){

                    _visibleOrders.value.add(orderPos.startPoint)

                    createMarker("order",orderPos.startPoint,mapView,iconMarkerType)

                    createMarker("order",orderPos.endPoint,mapView,iconMarkerType)

                    showPathBetweenPoints(orderPos.startPoint, orderPos.endPoint, mapView, roadManager, "order")

                }else if (isInArea(centerPoint,orderPos.endPoint,maxKmDistance)){
                    _visibleOrders.value.add(orderPos.endPoint)

                    createMarker("order",orderPos.endPoint,mapView,iconMarkerType)

                    createMarker("order",orderPos.startPoint,mapView,iconMarkerType)

                    showPathBetweenPoints(orderPos.endPoint,orderPos.startPoint, mapView, roadManager, "order")
                }
            }
        }

        // We iterate the list to get the lat and lon of a marker (route)
        // Then we compare the route distance with center distance (that we instanced lines before as "centerPoint") to know if the distance between them is minor to the max permitted, if is, we show it in the map
        routesList?.let {
            // We clear the list with previous visible routes, to latter inside if (isInArea) reassign new values
            _visibleRoutes.value.clear()
            for (routePos in routesList){
                // Check if route is in area to show
                if (isInArea(centerPoint,routePos.startPoint, maxKmDistance)){
                    _visibleRoutes.value.add(routePos.startPoint)
                    // Marker route start point
                    createMarker("route",routePos.startPoint, mapView, iconMarkerType)
                    // Marker route end point
                    createMarker("route",routePos.endPoint, mapView, iconMarkerType)
                    // We draw path
                    showPathBetweenPoints(routePos.startPoint, routePos.endPoint, mapView, roadManager, "route")
                }else if (isInArea(centerPoint,routePos.endPoint, maxKmDistance)){
                    _visibleRoutes.value.add(routePos.endPoint)
                    // Marker route start point
                    createMarker("route",routePos.startPoint, mapView, iconMarkerType)
                    // Marker route end point
                    createMarker("route",routePos.endPoint, mapView, iconMarkerType)
                    // We draw path
                    showPathBetweenPoints(routePos.startPoint, routePos.endPoint, mapView, roadManager, "route")
                }
            }
        }
        // Call the filtering function to change mutableStateFlow and print later the lazy Rows
        filterPerVisibilityOrders(_visibleOrders.value)
        filterPerVisibilityRoute(_visibleRoutes.value)
    }

    /**
     * Checks if two GeoPoints are within a specified distance from each other.

     * @param point1 First GeoPoint.

     * @param point2 Second GeoPoint.

     * @param maxKmDistance Maximum distance in kilometers.

     * @return True if the distance between the points is within the maximum distance, otherwise false.
     **/
    private fun isInArea(point1: GeoPoint, point2: GeoPoint, maxKmDistance: Int):Boolean{
        val distanceBetweenPoints = distanceBetweenPoints(point1.longitude,point1.latitude,point2.longitude,point2.latitude)

        // We return true (if distance is lower than max distance permitted) or false (if distance is bigger than max distance permitted)
        return (distanceBetweenPoints <= maxKmDistance)
    }

    /**
     * Calculates the distance between two points using their latitude and longitude.

     * @param lat1 Latitude of the first point.

     * @param lon1 Longitude of the first point.

     * @param lat2 Latitude of the second point.

     * @param lon2 Longitude of the second point.

     * @return The distance between the points in kilometers.
     **/
    private fun distanceBetweenPoints(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double{
        val earthRadius = 6371 // 6371 is Earth radius in km.

        // We transform the data parameters to get the distance between points
        val distance = acos(sin(deg2rad(lat1))*sin(deg2rad(lat2))+cos(deg2rad(lat1))*cos(deg2rad(lat2))*cos(deg2rad(lon2)-deg2rad(lon1))) * earthRadius
        return distance
    }

    /**
     * Converts degrees to radians.

     * @param deg Degree value.

     * @return The equivalent value in radians.
     **/
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
                createMarker("order",orderGeoPoint,mapView)
            }
        }
    }

    /**
     * Filters orders based on their visibility on the map and updates the filtered orders LiveData.

     * @param visibleOrders List of GeoPoints representing visible orders on the map.
     **/
     private fun filterPerVisibilityOrders(visibleOrders: MutableList<GeoPoint>){
        val ordersFiltered = orderList?.filter { order ->
            visibleOrders.any { geoPoint ->
                geoPoint == order.startPoint || geoPoint == order.endPoint
            }
        }
        _filteredOrders.value = ordersFiltered
    }

    /**
     * Filters routes based on their visibility on the map and updates the filtered routes LiveData.

     * @param visibleRoutes List of GeoPoints representing visible routes on the map.
     **/
    private fun filterPerVisibilityRoute(visibleRoutes: MutableList<GeoPoint>){
        val routesFiltered = routeList?.filter { route ->
            visibleRoutes.any{ geoPoint ->
                geoPoint == route.startPoint || geoPoint == route.endPoint
            }
        }
        _filteredRoutes.value = routesFiltered
    }

    /**
     * Asynchronously retrieves a road between two GeoPoints using a RoadManager.

     * @param roadManager RoadManager instance for obtaining road data.

     * @param startPoint Starting GeoPoint.

     * @param endPoint Ending GeoPoint.

     * @return Road object representing the road between the start and end points.
     **/
    private suspend fun getRoadAsync(roadManager: RoadManager, startPoint: GeoPoint, endPoint: GeoPoint): Road {
        return withContext(Dispatchers.IO) {
            val waypoints = ArrayList<GeoPoint>()
            waypoints.add(startPoint)
            waypoints.add(endPoint)
            return@withContext roadManager.getRoad(waypoints)
        }
    }

    /**
     * Displays a path between two points on the map.

     * @param startPoint Starting GeoPoint.

     * @param endPoint Ending GeoPoint.

     * @param mapView MapView instance.

     * @param roadManager RoadManager instance for obtaining road data.
     **/
    fun showPathBetweenPoints(startPoint: GeoPoint, endPoint: GeoPoint, mapView: MapView, roadManager: RoadManager,type: String){
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val road = getRoadAsync(roadManager, startPoint, endPoint)
                val roadOverlay = RoadManager.buildRoadOverlay(road)
                val colorNaranja = Color.rgb(211, 106, 53)

                if (type.uppercase() == "ORDER"){
                    roadOverlay.setColor(colorNaranja)
                } else{
                    roadOverlay.setColor(Color.GREEN)// Path color
                }
                roadOverlay.setWidth(20f) // Pat width

                /*
                val dashArray = floatArrayOf(10F, 20F)
                val dashPhase = 0F
                roadOverlay.paint.pathEffect = DashPathEffect(dashArray, dashPhase)
                 */

                _routesPaths.value.add(roadOverlay)
                mapView.overlays.add(roadOverlay)
                mapView.invalidate()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    /**
     * Calculates the center GeoPoint of a route defined by its start and end points.

     * @param startLat Latitude of the starting point.

     * @param startLong Longitude of the starting point.

     * @param endLat Latitude of the ending point.

     * @param endLong Longitude of the ending point.

     * @return Center GeoPoint of the route.
     **/
    fun getCenterRoute(startLat: Double, startLong: Double, endLat: Double, endLong: Double): GeoPoint {
        val centerLat = (startLat + endLat) / 2
        val centerLong = (startLong + endLong) / 2
        return GeoPoint(centerLat, centerLong)
    }

    /**
     * Updates the map with orders and routes near a specific location.

     * @param mapView MapView instance.

     * @param roadManager RoadManager instance for obtaining road data.

     * @param ctx Context object.

     * @param geoPoint GeoPoint representing the location.

     * @param maxKmDistance Maximum distance in kilometers.
     **/
    fun ordersAndRoutesFromLocation(mapView: MapView, roadManager: RoadManager, ctx: Context, geoPoint: GeoPoint, maxKmDistance: Int){

        mapView.controller.setCenter(geoPoint)
        mapView.controller.setZoom(16)

        val orderIconMarker = ContextCompat.getDrawable(ctx, R.drawable.little_map_marker_orders_svg)
        val routeIconMarker = ContextCompat.getDrawable(ctx, R.drawable.little_map_marker_routes_svg)

        if (_ordersMarkers.value.isNotEmpty()){
            deleteOrdersMarkers(_ordersMarkers.value, mapView)
            deleteRoutesPaths(_routesPaths.value, mapView)
            isNearClickUser(ordersList = orderList, routesList = null, mapView, orderIconMarker, roadManager, maxKmDistance)
        }else{
            isNearClickUser(ordersList = orderList, routesList = null, mapView, orderIconMarker, roadManager, maxKmDistance)
        }

        if (_routesMarkers.value.isNotEmpty()){
            deleteRoutesMarkers(_routesMarkers.value, mapView)
            deleteRoutesPaths(_routesPaths.value, mapView)
            isNearClickUser(ordersList = null, routesList = routeList, mapView, routeIconMarker, roadManager, maxKmDistance)
        } else{
            // We check if near user's click have any route
            isNearClickUser(ordersList = null, routesList = routeList, mapView, routeIconMarker, roadManager, maxKmDistance)
        }

        // Important condition , if dont handle user click and doesn't exists, app crash
        if (_userClickedPointer.value.isNotEmpty()){
            deleteUserClickPointer(_userClickedPointer.value[0], mapView)
        }

    }

    /**
     * Asynchronously retrieves the GeoPoint of a municipality from the Idescat API using its name.

     * @param cityName Name of the municipality.

     * @return GeoPoint representing the location of the municipality.
     **/
    suspend fun getMunicipiGeoPointIdescatAPI(cityName: String): GeoPoint? {
         return withContext(Dispatchers.IO) {
              try {
                  val cityInfo = idescatRepository.getLatLngMunicipi(cityName)
                  val lat = cityInfo?.lat
                  val lng = cityInfo?.lng
                  println("Latitud de $cityName: $lat, Longitud de $cityName: $lng")

                  println(GeoPoint(cityInfo?.lat ?: 0.0, cityInfo?.lng ?: 0.0))
                  GeoPoint(cityInfo?.lat ?: 0.0, cityInfo?.lng ?: 0.0)
              } catch (e: Exception) {
                e.printStackTrace()
                  null
              }
         }
    }

    /**
     * Asynchronously retrieves the GeoPoint of a municipality from the Google Places API using its name.

     * @param cityName Name of the municipality.

     * @return GeoPoint representing the location of the municipality.
     **/
    suspend fun getMunicipiGeoPointGoogleAPI(cityName: String): GeoPoint? {
        return withContext(Dispatchers.IO) {
            try {
                val cityInfo = googleLocationsRepository.getCityInfo(cityName)
                val location = cityInfo?.geometry?.location
                val lat = location?.lat
                val lng = location?.lng
                println("Latitud de $cityName: $lat, Longitud de $cityName: $lng")

                GeoPoint(lat ?: 0.0, lng ?: 0.0)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    // Function to print square (geometric form) in map
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