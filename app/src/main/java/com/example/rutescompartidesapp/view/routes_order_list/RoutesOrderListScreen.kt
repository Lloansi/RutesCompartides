package com.example.rutescompartidesapp.view.routes_order_list

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.provider.CalendarContract
import android.widget.ToggleButton
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.compose.ui.window.SecureFlagPolicy
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rutescompartidesapp.data.domain.Order
import com.example.rutescompartidesapp.data.domain.Route
import com.example.rutescompartidesapp.view.routes_order_list.components.FilterPopup
import com.example.rutescompartidesapp.view.routes_order_list.components.TabRows
import org.osmdroid.views.overlay.simplefastpoint.SimpleFastPointOverlayOptions


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoutesOrderListScreen(){
    val routeOrderListViewModel: RoutesOrderListViewModel = hiltViewModel()
    val searchText by routeOrderListViewModel.searchText.collectAsState()
    val isSearching by routeOrderListViewModel.isSearching.collectAsState()
    val isPopupShowing by routeOrderListViewModel.popupIsShowing.collectAsState()
    val puntSortidaText by routeOrderListViewModel.puntSortidaText.collectAsState()
    val puntArribadaText by routeOrderListViewModel.puntArribadaText.collectAsState()


    Column (Modifier.fillMaxSize()) {
        Row (Modifier.padding(12.dp),
            horizontalArrangement = Arrangement.SpaceAround) {
                SearchBar(
                    modifier= Modifier
                        .weight(3f)
                        .padding(0.dp, 0.dp, 6.dp, 6.dp),
                    query = searchText ,
                    onQueryChange = routeOrderListViewModel::onSearchTextChange,
                    onSearch = routeOrderListViewModel::onSearchTextChange,
                    active = isSearching,
                    onActiveChange = { routeOrderListViewModel::onToogleSearch },
                    tonalElevation = 4.dp,

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

                ) {
                }

            FloatingActionButton(
                modifier= Modifier
                    .weight(0.5f)
                    .align(Alignment.CenterVertically)
                    .height(52.dp),
                onClick = { routeOrderListViewModel.onPopupShow(true) },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.secondary
            ) {
                Icon(Icons.Filled.FilterList, contentDescription = "Filter",
                    Modifier
                        .height(32.dp)
                        .width(32.dp))
            }
            FilterPopup()
        }
        Row (Modifier.fillMaxWidth()) {
            TabRows()
        }
    }
}

@Preview( showBackground = true)
@Composable
fun RoutesOrderListScreenPreview() {
    RoutesOrderListScreen()
}
