package com.example.rutescompartidesapp.view.map.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
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
import com.example.rutescompartidesapp.view.map.viewModels.SearchViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView(searchViewModel : SearchViewModel) {

    val searchText by searchViewModel.searchText.collectAsState()
    val isSearching by searchViewModel.isSearching.collectAsState()
    val search by searchViewModel.search.collectAsState()

    SearchBar(
        modifier= Modifier
            .fillMaxWidth(0.80f)
            .wrapContentHeight()
            //.height(45.dp)
            .padding(end = 12.dp),
        query = searchText ,
        placeholder = {
            Text(
                text = "Buscar ...")
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                tint = MaterialTheme.colorScheme.onSurface,
                contentDescription = null
            )
        },
        onQueryChange = searchViewModel::onSearchTextChange,
        onSearch = searchViewModel::onSearchTextChange,
        active = isSearching,
        colors = SearchBarDefaults.colors(
            containerColor = Color.White,
            inputFieldColors = TextFieldDefaults.colors(MaterialTheme.colorScheme.primary)
        ),
        /*
        content = {
            Modifier.size(4.dp)
        },*/
        onActiveChange = { searchViewModel::onToogleSearch }) {

    }

}