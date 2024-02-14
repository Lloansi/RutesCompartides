package com.example.rutescompartidesapp.view.map.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rutescompartidesapp.R
import com.example.rutescompartidesapp.domain.model.Order
import com.example.rutescompartidesapp.view.map.viewModels.MapViewModel
import org.osmdroid.util.GeoPoint



@Composable
fun CardBottomMap2(){
    val mapViewModel: MapViewModel = hiltViewModel()
    val visibleOrders by mapViewModel.visibleOrders.collectAsState()
    val ordersFiltered = mapViewModel.filterPerVisibilityOrders(visibleOrders)



    LazyRow(
        modifier = Modifier.fillMaxWidth()
    ){
        /*
        items(allOrders.size){ item ->
           val packageItem = allOrders[item]
           ComandaCard(comanda = packageItem)
        }

         */
        ordersFiltered.forEach { item ->
            ComandaCard(comanda = item)
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
fun CustomLazyRow(
    modifier: Modifier = Modifier.fillMaxWidth().wrapContentHeight(),
    content: @Composable () -> Unit
) {
    Row(
        modifier = modifier
            .horizontalScroll(rememberScrollState())
            .padding(vertical = 8.dp)
    ) {
        content()
    }
}

@Composable
fun CardBottom3() {
    val mapViewModel: MapViewModel = hiltViewModel()
    val visibleOrders by mapViewModel.visibleOrders.collectAsState()
    val ordersFiltered = mapViewModel.filterPerVisibilityOrders(visibleOrders)


    CustomLazyRow {
        // Add items here
        //repeat(10) {
            // Replace this with your item composable
            ordersFiltered.forEach { item ->
                ComandaCard(comanda = item)
            }
        //}
    }

    // More of your Compose UI code...
}