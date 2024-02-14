package com.example.rutescompartidesapp.view.map.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rutescompartidesapp.R
import com.example.rutescompartidesapp.domain.model.Order
import com.example.rutescompartidesapp.view.map.viewModels.MapViewModel
import org.osmdroid.util.GeoPoint

val openSansFamily = FontFamily(
    Font(R.font.opensans, FontWeight.Normal),
)
val fredokaOneFamily = FontFamily(
    Font(R.font.fredokaone, FontWeight.Normal),
)

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