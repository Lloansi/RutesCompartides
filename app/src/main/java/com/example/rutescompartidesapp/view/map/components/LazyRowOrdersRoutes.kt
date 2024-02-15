package com.example.rutescompartidesapp.view.map.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.rutescompartidesapp.domain.model.Order


@Composable
fun CardBottomMap(ordersFiltered: List<Order>){
    LazyRow(
        modifier = Modifier.fillMaxWidth()
    ){

        items(ordersFiltered.size){ item ->
           val orderItem = ordersFiltered[item]
           ComandaCard(comanda = orderItem)
        }


        items(allRoute.size){item ->
            val rutaItem = allRoute[item]
            RouteCard(ruta = rutaItem, newVehicle)
        }
    }
}