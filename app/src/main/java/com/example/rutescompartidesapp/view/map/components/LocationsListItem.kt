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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.rutescompartidesapp.data.domain.Location.idescat.Municipi
import com.example.rutescompartidesapp.view.map.viewModels.MapViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.osmdroid.util.GeoPoint

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun LocationListItem(municipi: Municipi, ctx: Context, mapViewModel: MapViewModel, onCloseSearchBar: () -> Unit) {

    val mapView by mapViewModel.mapViewState.collectAsState()
    val roadManager by mapViewModel.roadManagerState.collectAsState()

    var municipiGeoPoint by remember {
        mutableStateOf<GeoPoint?>(null)
    }

    LaunchedEffect(municipi.content) {
        val fetchedGeoPoint = mapViewModel.getMunicipiGeoPoint(municipi.content)
        municipiGeoPoint = fetchedGeoPoint
    }

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .clickable {
                mapView?.let { mapview ->
                    roadManager?.let { roadmanager ->
                        municipiGeoPoint?.let { geopoint ->

                            /*
                            Encapsulamos las dos funciones en una corrutina para asegurarnos que el onCloseSearchBar()
                            se ejecutará cuando se recuperen los datos de la llamada a la API ordersAndRoutesFromLocation() del MapViewModel
                             */
                            GlobalScope.launch {
                                mapViewModel.ordersAndRoutesFromLocation(
                                    mapView = mapview,
                                    roadManager = roadmanager,
                                    ctx = ctx,
                                    geoPoint = geopoint,
                                    maxKmDistance = 30)

                                onCloseSearchBar()
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