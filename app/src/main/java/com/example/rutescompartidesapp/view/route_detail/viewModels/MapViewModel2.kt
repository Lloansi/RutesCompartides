package com.example.rutescompartidesapp.view.map.viewModels

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.osmdroid.bonuspack.routing.RoadManager
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import kotlin.math.log2

class MapViewModel2 () :ViewModel() {

    private val _isBoxMapClicked = MutableStateFlow<Boolean>(false)
    var isBoxMapClicked = _isBoxMapClicked.asStateFlow()

    /**
     * Updates the click state LiveData with the provided boolean value.

     **/
    fun updateClickState() {
        _isBoxMapClicked.value = !_isBoxMapClicked.value
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
        val handler = Handler(Looper.getMainLooper())

        mapView.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_UP -> {
                    isDragging= false
                    //true  // if MotionEvent don't have anything inside, use it to consume the touch event
                }
                MotionEvent.ACTION_MOVE -> {
                    // User dragging
                    if (isBoxMapClicked.value == false){
                        updateClickState()
                    }else{
                        isDragging = true
                    }
                }
                MotionEvent.ACTION_DOWN -> {
                    // User tap scree
                    // We add handler because if not , dragging state will take effect before the touch event MotionEvent.ACTION_DOWN
                    handler.postDelayed({
                        if (!isDragging) {

                            if (isBoxMapClicked.value == false){
                                updateClickState()
                            }else{

                                /*
                                // Controller of the mapView for set center, animations ...
                                val controller = mapView.controller
                                 */
                            }

                        }
                    }, 100)
                    //isDragging = false // Reset the drag flag
                    //true
                }
            }
            // Let the map handle the touch event for dragging
            false
        }
    }

    /**
     *Calculates the zoom level based on the distance in kilometers and the screen coverage.
     *This function computes the zoom level using the formula: Zoom Level = log2(Screen Coverage / Distance).

     *@param distanceKm The distance between two points in kilometers.

     *@param screenCoverage The desired screen coverage as a fraction of the total screen area.

     *@return The calculated zoom level.
     */
    fun calculateZoomLevel(distanceKm: Double, screenCoverage: Double): Double {
        return log2(screenCoverage / distanceKm)
    }
}