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
import com.example.rutescompartidesapp.data.domain.Location.Location
import com.example.rutescompartidesapp.view.map.viewModels.MapViewModel
import org.osmdroid.util.GeoPoint

@Composable
fun LocationListItem(location: Location, ctx: Context, mapViewModel: MapViewModel) {
    val geoPoint = GeoPoint(location.lat,location.lng)

    val mapView by mapViewModel.mapViewState.collectAsState()
    val roadManager by mapViewModel.roadManagerState.collectAsState()

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .clickable {
                mapView?.let { roadManager?.let { it1 ->
                    mapViewModel.ordersAndRoutesFromLocation(
                        it,
                        it1,
                        ctx,
                        geoPoint,
                        30)
                    }
                }
            }
    ){
        Text(
            modifier = Modifier
                .padding(8.dp),
            text = location.name,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
    }

}