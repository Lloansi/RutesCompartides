package com.example.rutescompartidesapp.view.map.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rutescompartidesapp.R
import com.example.rutescompartidesapp.view.map.viewModels.MapViewModel
import com.example.rutescompartidesapp.view.map.viewModels.SearchViewModel

val openSansFamily = FontFamily(
    Font(R.font.opensans, FontWeight.Normal),
)
val fredokaOneFamily = FontFamily(
    Font(R.font.fredokaone, FontWeight.Normal),
)

@Composable
fun CardBottomMap(){
    val mapViewModel: MapViewModel = hiltViewModel()
    val visibleOrders by mapViewModel.visibleOrders.collectAsState()
    LazyRow(
        modifier = Modifier.fillMaxWidth()
    ){
        items(allOrders.size){ item ->
           val packageItem = allOrders[item]
           ComandaCard(comanda = packageItem)
        }

        /*
        items(allOrders.size){ item ->

            val ordersFiltered = mapViewModel.filterPerVisibilityOrders(visibleOrders)

            for (order in ordersFiltered){
                ComandaCard(comanda = order)
            }
             /*
            val packageItem = allOrders[item]
            ComandaCard(comanda = packageItem)
            */
        }

         */

        items(allRoute.size){item ->
            val rutaItem = allRoute[item]
            RouteCard(ruta = rutaItem, newVehicle)
        }

    }
}

@Composable
fun SearchViewContainer() {
    val searchViewModel: SearchViewModel = hiltViewModel()
    Row (modifier = Modifier
        .offset(y = -(4).dp)
        .fillMaxWidth()
        .wrapContentHeight() ,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SearchView(searchViewModel)
        NotificationButtonCard()
    }
}