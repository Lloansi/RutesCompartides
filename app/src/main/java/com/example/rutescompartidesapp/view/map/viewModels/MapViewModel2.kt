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
import com.example.rutescompartidesapp.data.network.rutes_compartides.repository.RutesCompartidesRepository
import com.example.rutescompartidesapp.R
import com.example.rutescompartidesapp.data.domain.Order
import com.example.rutescompartidesapp.data.domain.Route
import com.example.rutescompartidesapp.data.domain.Route2
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

class MapViewModel2 () :ViewModel() {

    private val _isBoxMapClicked = MutableStateFlow<Boolean>(false)
    var isBoxMapClicked = _isBoxMapClicked.asStateFlow()

    fun updateClickState(clicked: Boolean) {
        _isBoxMapClicked.value = clicked
    }

    fun createPathAndRoutePoints(viewModel: MapViewModel,startPoint: GeoPoint, endPoint: GeoPoint, mapView: MapView, roadManager: RoadManager, routeIconMarker: Drawable?){
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