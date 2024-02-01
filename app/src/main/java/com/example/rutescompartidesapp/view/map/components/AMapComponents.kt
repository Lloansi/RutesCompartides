package com.example.rutescompartidesapp.view.map.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rutescompartidesapp.view.map.SearchViewModel


@Composable
fun CardBottomMap(){
    val totalSizeCardAndRoutes = packagesList.size + allRoute.size
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
        .padding(top = 4.dp)
        .fillMaxWidth()
        .wrapContentHeight() ,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SearchView(searchViewModel)
        NotificationButtonCard()
    }
}





/*
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView(state: MutableState<TextFieldValue>, placeHolder: String, modifier: Modifier) {
    TextField(
        value = state.value.text,
        onValueChange = { value ->
            state.value = value
        },
        modifier = Modifier
            .fillMaxWidth(0.75f)
            .padding(top = 20.dp)
            .clip(RoundedCornerShape(10.dp))
            .border(2.dp, Color.DarkGray, RoundedCornerShape(10.dp)),
        placeHolder = placeHolder/*{
            Text(text = placeholder)
        }
        */
        ,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White
        ),
        maxLines = 1,
        singleLine = true,
        textStile = androidx.compose.ui.text.TextStyle(
            color = Color.Black, fontSize = 20.sp
        )
    )
}
 */
