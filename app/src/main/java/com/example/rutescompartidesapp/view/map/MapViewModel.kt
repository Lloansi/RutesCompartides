package com.example.rutescompartidesapp.view.map

import androidx.compose.runtime.Composable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

class MapViewModel:ViewModel() {

    var markerPosition = MutableLiveData<GeoPoint>()

    init {
        markerPosition.value = GeoPoint(41.4534265,2.1837151)
    }

    fun createMarker(point: GeoPoint, mapView: MapView){
        val marker = Marker(mapView)
        marker.position =  point

        //Añadimos el marker instanciado unas lineas atrás
        mapView.overlays.add(marker)
    }

    fun updateMarkerPosition(point: GeoPoint){
        markerPosition.value = point
    }

}