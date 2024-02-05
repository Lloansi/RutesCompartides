package com.example.rutescompartidesapp.view.map

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

class MapViewModel:ViewModel() {

    private val _markerList = MutableStateFlow<MutableList<GeoPoint>>(mutableListOf())
    var markerList = _markerList.asStateFlow()

    var markerPosition = MutableLiveData<GeoPoint>()

    init {
        //Barcelona GeoPoint
        markerPosition.value = GeoPoint(41.4534265,2.1837151)
    }

    private fun createMarker(point: GeoPoint, mapView: MapView){
        val marker = Marker(mapView)
        marker.position =  point

        //Añadimos el marker instanciado unas lineas atrás en la vista del mapa
        mapView.overlays.add(marker)
    }

    fun addMarkersList(markerList: MutableStateFlow<MutableList<GeoPoint>>, mapView: MapView){
        val markers = markerList.value.toMutableList()

        for (geoPoint in markers) {
            createMarker(geoPoint,mapView)
        }
    }

    fun updateMarkerPosition(point: GeoPoint){
        markerPosition.value = point
    }
}