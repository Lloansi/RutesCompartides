package com.example.rutescompartidesapp.view.map.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.rutescompartidesapp.data.domain.Order
import com.example.rutescompartidesapp.data.domain.Route


@Composable
fun CardBottomMap(ordersFiltered: List<Order>, routesFiltered: List<Route>){
    LazyRow(
        modifier = Modifier.fillMaxWidth()
    ){
        items(ordersFiltered.size){ item ->
           val orderItem = ordersFiltered[item]
           ComandaCard(comanda = orderItem)
        }


        items(routesFiltered.size){item ->
            val rutaItem = routesFiltered[item]
            RouteCard(ruta = rutaItem, newVehicle)
        }
    }
}