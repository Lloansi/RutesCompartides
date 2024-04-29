package com.example.rutescompartidesapp.view.route_detail.viewModels

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.MotionEvent
import androidx.lifecycle.ViewModel
import com.example.rutescompartidesapp.view.map.viewModels.MapViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.osmdroid.bonuspack.routing.RoadManager
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView

class MapViewModel2 () :ViewModel() {

    private val _isBoxMapClicked = MutableStateFlow<Boolean>(false)
    var isBoxMapClicked = _isBoxMapClicked.asStateFlow()

    fun updateClickState(clicked: Boolean) {
        _isBoxMapClicked.value = clicked
    }

    fun createPathAndRoutePoints(viewModel: MapViewModel, startPoint: GeoPoint, endPoint: GeoPoint, mapView: MapView, roadManager: RoadManager, routeIconMarker: Drawable?){
        viewModel.showPathBetweenPoints(startPoint, endPoint, mapView, roadManager)
        viewModel.createMarker("route", startPoint, mapView, routeIconMarker)
        viewModel.createMarker("route", endPoint, mapView, routeIconMarker)
    }

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