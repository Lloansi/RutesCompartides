package com.example.rutescompartidesapp.view.map.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.rutescompartidesapp.view.map.viewModels.SearchViewModel

@Composable
fun FilteredListsBelowSearchBar(searchViewModel: SearchViewModel) {

    val routesFilteredSearchBar by searchViewModel.routesFilteredPerSearchedText.collectAsStateWithLifecycle()
    val ordersFilteredSearchBar by searchViewModel.ordersFilteredPerSearchedText.collectAsStateWithLifecycle()
    val locationsFilteredSearchBar by searchViewModel.locationsFilteredPerSearchedText.collectAsStateWithLifecycle()

    val isSearching by searchViewModel.isSearching.collectAsStateWithLifecycle()

    Column (
        modifier = Modifier
            .fillMaxWidth(if (true)1f else 0f)
            .fillMaxHeight(if (true)1f else 0f)
            .clip(RoundedCornerShape(12.dp))
            .background(color = Color.White)

    ){

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(12.dp)
        ) {
            items(locationsFilteredSearchBar.size) { index ->
                LocationListItem(location = locationsFilteredSearchBar[index])
                Spacer(modifier = Modifier.height(12.dp))
            }
        }


        /*
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp)
        ) {
            items(routesFilteredSearchBar.size) { index ->
                RouteListItem(route = routesFilteredSearchBar[index])
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp)
        ) {
            items(ordersFilteredSearchBar.size) { index ->
                OrderListItem(order = ordersFilteredSearchBar[index])
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
         */
    }
}