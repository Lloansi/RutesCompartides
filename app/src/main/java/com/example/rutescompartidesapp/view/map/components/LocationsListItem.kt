package com.example.rutescompartidesapp.view.map.components

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.rutescompartidesapp.data.domain.Location.idescat.Municipi
import com.example.rutescompartidesapp.view.map.viewModels.MapViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.osmdroid.util.GeoPoint

@Composable
fun LocationListItem(municipi: Municipi, ctx: Context, mapViewModel: MapViewModel, onCloseSearchBar: () -> Unit) {

    val mapView by mapViewModel.mapViewState.collectAsState()
    val roadManager by mapViewModel.roadManagerState.collectAsState()

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .clickable {

                // Llamada a la api para recuperar el GeoPoint del punto
                val customScope = CoroutineScope(Dispatchers.IO)
                customScope.launch {

                    val fetchedGeoPoint = mapViewModel.getMunicipiGeoPointIdescatAPI(municipi.id) // Si fuera la llamada a google, seria municipi.content

                    println("GEOPOINT CLICKED: ${fetchedGeoPoint?.latitude}, ${fetchedGeoPoint?.longitude}")

                    // Update markerPosition LiveData from the main thread
                    withContext(Dispatchers.Main) {
                        mapViewModel.markerPosition.value = fetchedGeoPoint
                    }
                    // Navigate to a point of map, when user click on item's list
                    mapView?.let { mapview ->
                        roadManager?.let { roadmanager ->
                            /*
                            Encapsulamos las dos funciones en una corrutina para asegurarnos que el onCloseSearchBar()
                            se ejecutará cuando se recuperen los datos de la llamada a la API ordersAndRoutesFromLocation() del MapViewModel
                             */
                            launch {
                                mapViewModel.ordersAndRoutesFromLocation(
                                    mapView = mapview,
                                    roadManager = roadmanager,
                                    ctx = ctx,
                                    geoPoint = GeoPoint(
                                        fetchedGeoPoint?.latitude ?: 0.0,
                                        fetchedGeoPoint?.longitude ?: 0.0
                                    ),
                                    maxKmDistance = 30 // 30km is cuz its a city, so, aprox 30km will show every place in city that can have a order or route
                                )
                                onCloseSearchBar() // We update the state of searching, so will close the list below the search bar
                            }
                        }
                    }
                }
            }
    ){
        Text(
            modifier = Modifier
                .padding(8.dp),
            text = municipi.content,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}
