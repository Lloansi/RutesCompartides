package com.example.rutescompartidesapp.view.map.viewModels

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.MotionEvent
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.osmdroid.bonuspack.routing.RoadManager
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView

class MapViewModel2 () :ViewModel() {

    private val _isBoxMapClicked = MutableStateFlow<Boolean>(false)
    var isBoxMapClicked = _isBoxMapClicked.asStateFlow()

    /**
     * Updates the click state LiveData with the provided boolean value.
     * @param clicked Boolean value representing the click state.
     **/
    fun updateClickState(clicked: Boolean) {
        _isBoxMapClicked.value = clicked
    }

    /**
     * Creates a path and route points between two GeoPoints on the map.

     * @param viewModel MapViewModel instance for accessing necessary functions.

     * @param startPoint Starting GeoPoint.

     * @param endPoint Ending GeoPoint.

     * @param mapView MapView instance.

     * @param roadManager RoadManager instance for obtaining road data.

     * @param routeIconMarker Drawable for the route markers.
     **/
    fun createPathAndRoutePoints(viewModel: MapViewModel, startPoint: GeoPoint, endPoint: GeoPoint, mapView: MapView, roadManager: RoadManager, routeIconMarker: Drawable?){
        viewModel.showPathBetweenPoints(startPoint, endPoint, mapView, roadManager, "route")
        viewModel.createMarker("route", startPoint, mapView, routeIconMarker)
        viewModel.createMarker("route", endPoint, mapView, routeIconMarker)
    }

    /**
     * Handles the click state of the map.
     * This function toggles the click state when the map is clicked.

     * @param mapView MapView instance.
     **/
    @SuppressLint("ClickableViewAccessibility")
    fun clickStateMap(mapView: MapView) {
        var isDragging = false

        mapView.setOnTouchListener { _, event ->
            when(event.action){
                MotionEvent.ACTION_DOWN -> {
                    isDragging= false
                    //true
                }
                MotionEvent.ACTION_MOVE -> {
                    isDragging = true // User dragging
                    //true
                }
                MotionEvent.ACTION_UP -> {
                    if (!isDragging) {
                        _isBoxMapClicked.value = !_isBoxMapClicked.value
                    }
                }
            }
            true
        }
    }
}