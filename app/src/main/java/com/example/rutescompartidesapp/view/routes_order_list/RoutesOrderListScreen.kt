package com.example.rutescompartidesapp.view.routes_order_list

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.provider.CalendarContract
import android.widget.ToggleButton
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rutescompartidesapp.data.domain.Order
import com.example.rutescompartidesapp.data.domain.Route


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoutesOrderListScreen(){
    val routeOrderListViewModel = RoutesOrderListViewModel()
    val searchText by routeOrderListViewModel.searchText.collectAsState()
    val routes by routeOrderListViewModel.routes.collectAsState()
    val orders by routeOrderListViewModel.orders.collectAsState()
    val isSearching by routeOrderListViewModel.isSearching.collectAsState()

    Column (Modifier.fillMaxSize()) {
        Row (Modifier.padding(6.dp),
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
                },) {
            }
            FloatingActionButton(
                modifier= Modifier
                    .weight(0.5f)
                    .align(Alignment.CenterVertically)
                    .height(52.dp),
                onClick = { /*TODO*/ },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.secondary
            ) {
                Icon(Icons.Filled.List, contentDescription = "Filter",
                    Modifier
                        .height(32.dp)
                        .width(32.dp))
            }
        }
        Row (Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center) {
            Button(onClick = { /*TODO*/ }
            ) {
                    Text(text = "Rutes")
            }
            Spacer(Modifier.padding(10.dp))
            Button(onClick = { /*TODO*/ }
            ) {
                Text(text = "Comandes")
            }
        }
        Spacer(Modifier.padding(16.dp))
        if (isSearching) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        } else {
            LazyColumn(modifier= Modifier.fillMaxWidth()){
                items(routes.size) { index ->
                    Spacer(modifier = Modifier.padding(8.dp))
                    RouteCard(route = routes[index])
                }
            }
        }


    }

}

@Composable
fun RouteCard(route: Route) {
    Row {
        Text(text = route.routeName, modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .weight(3f))
    }

}

@Composable
fun OrderCar(order: Order) {
    Row {
        Text(text = order.orderName, modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .weight(3f))
    }
}

@Preview( showBackground = true)
@Composable
fun RoutesOrderListScreenPreview() {
    RoutesOrderListScreen()
}
