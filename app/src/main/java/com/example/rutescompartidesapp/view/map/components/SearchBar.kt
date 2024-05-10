package com.example.rutescompartidesapp.view.map.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.rutescompartidesapp.view.map.viewModels.MapViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.osmdroid.util.GeoPoint


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView(ctx: Context, mapViewModel: MapViewModel) {

    val searchText by mapViewModel.searchText.collectAsState()
    val isSearching by mapViewModel.isSearching.collectAsState()

    val mapView by mapViewModel.mapViewState.collectAsState()
    val roadManager by mapViewModel.roadManagerState.collectAsState()

    val locations by mapViewModel.locations.collectAsState()

    val locationsFilteredSearchBar by mapViewModel.locationsFilteredPerSearchedText.collectAsStateWithLifecycle()

    val isLocationsNotNull = !locationsFilteredSearchBar.isNullOrEmpty()

    SearchBar(
        modifier= Modifier
            .fillMaxWidth(0.80f)
            .fillMaxHeight(if (!isSearching) 0.075f else 0.65f)
            //.height(45.dp)
            .padding(end = 12.dp),
        query = searchText ,
        placeholder = {
            Text(text = "Cercar", color = MaterialTheme.colorScheme.onBackground)
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                tint = MaterialTheme.colorScheme.onBackground,
                contentDescription = "Search Icon"
            )
        },
        onQueryChange = mapViewModel::onSearchTextChange,
        onSearch = { query ->

            /* filtrar por HasSet (Forma eficiente de busqueda en listas)
            val nameSet = HashSet<String>()
            locations.forEach { nameSet.add(it.content.lowercase()) }

            val filteredList = nameSet.filter { it.contains(query.lowercase()) }

             */

            val filteredLocation = locations.find { it.content.equals(query, ignoreCase = true) }
            if (filteredLocation != null) {
                val locationId = filteredLocation.id

                val customScope = CoroutineScope(Dispatchers.IO)
                customScope.launch {
                    val fetchedGeoPoint = mapViewModel.getMunicipiGeoPointIdescatAPI(locationId)

                    withContext(Dispatchers.Main) {
                        mapViewModel.markerPosition.value = fetchedGeoPoint
                    }

                    launch {
                        mapView?.let {
                            roadManager?.let { it1 ->
                                mapViewModel.ordersAndRoutesFromLocation(
                                    mapView = it,
                                    roadManager = it1,
                                    ctx = ctx,
                                    geoPoint = GeoPoint(
                                        fetchedGeoPoint?.latitude ?: 0.0,
                                        fetchedGeoPoint?.longitude ?: 0.0
                                    ),
                                    maxKmDistance = 30 // 30km is cuz its a city, so, aprox 30km will show every place in city that can have a order or route
                                )
                            }
                        }
                        mapViewModel.onToogleSearch()
                    }
                }
            } else {
                Toast.makeText(ctx, "No coincideix el nom amb cap municipi", Toast.LENGTH_SHORT).show()
            }
        },
        active = isSearching,
        colors = SearchBarDefaults.colors(
            containerColor = MaterialTheme.colorScheme.background,
            dividerColor = MaterialTheme.colorScheme.onBackground,
            inputFieldColors = TextFieldDefaults.colors(MaterialTheme.colorScheme.primary)
        ),
        shape = RoundedCornerShape(16.dp),
        /*
        content = {
            Modifier.size(4.dp)
        },*/
        onActiveChange = { _ ->
            mapViewModel.onToogleSearch()
        }) {

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(12.dp)
        ) {
            if (isLocationsNotNull){
                items(locationsFilteredSearchBar.size) { index ->
                    LocationListItem(
                        municipi = locationsFilteredSearchBar[index],
                        ctx = ctx,
                        mapViewModel = mapViewModel,
                        onCloseSearchBar = {
                            mapViewModel.onToogleSearch()
                        })
                    Spacer(modifier = Modifier.height(12.dp))
                }
            } else{
                println("ERROR | Empty list of cities")
            }
        }
    }
}