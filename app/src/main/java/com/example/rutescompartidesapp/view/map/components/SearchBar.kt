package com.example.rutescompartidesapp.view.map.components

import android.content.Context
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.rutescompartidesapp.view.map.viewModels.MapViewModel
import com.example.rutescompartidesapp.view.map.viewModels.SearchViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView(searchViewModel : SearchViewModel, ctx: Context, mapViewModel: MapViewModel) {

    val searchText by searchViewModel.searchText.collectAsState()
    val isSearching by searchViewModel.isSearching.collectAsState()

    val locationsFilteredSearchBar by searchViewModel.locationsFilteredPerSearchedText.collectAsStateWithLifecycle()

    val isLocationsNotNull = !locationsFilteredSearchBar.isNullOrEmpty()

    SearchBar(
        modifier= Modifier
            .fillMaxWidth(0.80f)
            .fillMaxHeight(if(!isSearching) 0.075f else 0.65f)
            //.height(45.dp)
            .padding(end = 12.dp),
        query = searchText ,
        placeholder = {
            Text(text = "Cercar", color = Color.Black)
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                tint = Color.Black,
                contentDescription = "Search Icon"
            )
        },
        onQueryChange = searchViewModel::onSearchTextChange,
        onSearch = searchViewModel::onSearchTextChange,
        active = isSearching,
        colors = SearchBarDefaults.colors(
            containerColor = Color.White,
            inputFieldColors = TextFieldDefaults.colors(MaterialTheme.colorScheme.primary)
        ),
        shape = RoundedCornerShape(16.dp),
        /*
        content = {
            Modifier.size(4.dp)
        },*/
        onActiveChange = { _ ->
            searchViewModel.onToogleSearch()
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
                                searchViewModel.onToogleSearch()
                        })
                    Spacer(modifier = Modifier.height(12.dp))
                }
            } else{
                println("ERROR | Empty list of cities")
            }
        }
    }
}