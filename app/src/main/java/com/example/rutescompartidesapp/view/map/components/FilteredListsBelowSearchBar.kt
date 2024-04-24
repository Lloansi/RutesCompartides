package com.example.rutescompartidesapp.view.map.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.rutescompartidesapp.data.domain.GeoName.GeoName
import com.example.rutescompartidesapp.data.domain.Order
import com.example.rutescompartidesapp.data.domain.Route

@Composable
fun FilteredListsBelowSearchBar(routesFilteredSearchBar: List<Route>, ordersFilteredSearchBar: List<Order>, locationsFilteredSearchBar: List<GeoName>, isSearching: Boolean) {
    Column {

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(if (isSearching)1f else 0f)
                .fillMaxHeight(if (isSearching)1f else 0f)
                .padding(16.dp)
        ) {
            items(locationsFilteredSearchBar.size) { index ->
                LocationListItem(location = locationsFilteredSearchBar[index])
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        /*
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(if (isSearching)1f else 0f)
                .fillMaxHeight(if (isSearching)1f else 0f)
                .padding(16.dp)
        ) {
            items(routesFilteredSearchBar.size) { index ->
                RouteListItem(route = routesFilteredSearchBar[index])
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(if (isSearching)1f else 0f)
                .fillMaxHeight(if (isSearching)1f else 0f)
                .padding(16.dp)
        ) {
            items(ordersFilteredSearchBar.size) { index ->
                OrderListItem(order = ordersFilteredSearchBar[index])
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

         */
    }
}