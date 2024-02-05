package com.example.rutescompartidesapp.view.map

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin

class MapViewModel:ViewModel() {

    private val _markerList = MutableStateFlow<MutableList<GeoPoint>>(mutableListOf())
    var markerList = _markerList.asStateFlow()

    var markerPosition = MutableLiveData<GeoPoint>()

    init {
        //Barcelona GeoPoint
        markerPosition.value = GeoPoint(41.4534265,2.1837151)
    }

    private fun distanceBetweenPoints(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double{
        val earthRadius = 6371 // 6371 is Earth radius in km.

        val distance = acos(sin(deg2rad(lat1))*sin(deg2rad(lat2))+cos(deg2rad(lat1))*cos(deg2rad(lat2))*cos(deg2rad(lon2)-deg2rad(lon1))) * earthRadius
        return distance
    }

    private fun deg2rad(deg: Double): Double {
        return deg * Math.PI / 180.0
    }

    fun isInArea(point1: GeoPoint,point2: GeoPoint, maxKmDistance: Int):Boolean{
        val distanceBetweenPoints = distanceBetweenPoints(point1.longitude,point1.latitude,point2.longitude,point2.latitude)

        return (distanceBetweenPoints <= maxKmDistance)
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