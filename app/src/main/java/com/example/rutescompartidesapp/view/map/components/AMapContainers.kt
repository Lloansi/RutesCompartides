package com.example.rutescompartidesapp.view.map.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rutescompartidesapp.R
import com.example.rutescompartidesapp.view.map.SearchViewModel

val openSansFamily = FontFamily(
    Font(R.font.opensans, FontWeight.Normal),
)
val fredokaOneFamily = FontFamily(
    Font(R.font.fredokaone, FontWeight.Normal),
)

@Composable
fun CardBottomMap(){
    LazyRow(
        modifier = Modifier.fillMaxWidth()
    ){
        items(packagesList.size){ item ->
            val packageItem = packagesList[item]
            ComandaCard(comanda = packageItem)
        }
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
        .offset(y = -(8).dp)
        .fillMaxWidth()
        .wrapContentHeight() ,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SearchView(searchViewModel)
        NotificationButtonCard()
    }
}