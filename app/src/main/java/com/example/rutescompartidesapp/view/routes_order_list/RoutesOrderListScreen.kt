package com.example.rutescompartidesapp.view.routes_order_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.FilterAltOff
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.rutescompartidesapp.view.routes_order_list.components.FilterPopup
import com.example.rutescompartidesapp.view.routes_order_list.components.TabRows
import com.example.rutescompartidesapp.view.routes_order_list.viewmodels.FilterPopupViewModel
import com.example.rutescompartidesapp.view.routes_order_list.viewmodels.RoutesOrderListViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoutesOrderListScreen(navController: NavHostController){
    val routeOrderListViewModel: RoutesOrderListViewModel = hiltViewModel()
    val filterPopupViewModel: FilterPopupViewModel = hiltViewModel()
    val searchText by routeOrderListViewModel.searchText.collectAsStateWithLifecycle()
    val isSearching by routeOrderListViewModel.isSearching.collectAsStateWithLifecycle()
    val areFilterActive by routeOrderListViewModel.activeFilters.collectAsStateWithLifecycle()
    Column (Modifier.fillMaxSize()) {
        Row (Modifier.padding(12.dp),
            horizontalArrangement = Arrangement.SpaceAround) {
            // Search Bar
                SearchBar(
                    modifier= Modifier
                        .weight(3f)
                        .padding(0.dp, 0.dp, 6.dp, 6.dp),
                    query = searchText ,
                    onQueryChange = routeOrderListViewModel::onSearchTextChange,
                    onSearch = routeOrderListViewModel::onSearchTextChange,
                    active = isSearching,
                    onActiveChange = { routeOrderListViewModel::onToogleSearch
                    // This will expand the searchBar when active with
                    // routeOrderListViewModel.onToogleSearch(true)
                                     },
                    colors = SearchBarDefaults.colors(
                       containerColor = Color.White,
                    ),
                    placeholder = {
                        Text(
                            text = "Cercar")
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            tint = MaterialTheme.colorScheme.onSurface,
                            contentDescription = "Icona de cerca"
                        )
                    },
                ) {}
            // Filter Button
            FloatingActionButton(
                modifier= Modifier
                    .weight(0.5f)
                    .align(Alignment.CenterVertically)
                    .height(52.dp),
                onClick = {
                    if (areFilterActive.contains(true)) {
                        routeOrderListViewModel.onResetFilters()
                    } else {
                        filterPopupViewModel.onPopupShow(true)
                    }
                     },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.secondary
            ) {
                if (areFilterActive.contains(true)) {
                    Icon(Icons.Filled.FilterAltOff, contentDescription = "Filter off icon",
                        Modifier
                            .height(32.dp)
                            .width(32.dp))
                } else {
                    Icon(Icons.Filled.FilterAlt, contentDescription = "Filter icon",
                        Modifier
                            .height(32.dp)
                            .width(32.dp))
                }
            }
            // Filter Popup
            FilterPopup(routeOrderListViewModel)
        }
        // Tabs and lists
        Row (Modifier.fillMaxWidth()) {
            TabRows(routeOrderListViewModel, navController)
        }
    }
}

