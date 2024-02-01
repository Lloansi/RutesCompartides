package com.example.rutescompartidesapp.view.routes_order_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Map
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rutescompartidesapp.view.routes_order_list.RoutesOrderListViewModel

@Preview(showBackground = true)
@Composable
fun FilterPopup(){
    val routeOrderListViewModel: RoutesOrderListViewModel = hiltViewModel()
    val isPopupShowing by routeOrderListViewModel.popupIsShowing.collectAsState()
    val puntSortidaText by routeOrderListViewModel.puntSortidaText.collectAsState()
    val puntArribadaText by routeOrderListViewModel.puntArribadaText.collectAsState()

    if (isPopupShowing){
        Popup(alignment = Alignment.Center,
            offset = IntOffset(0, 700),
            onDismissRequest = { routeOrderListViewModel.onPopupShow(false) },
            properties = PopupProperties(
                focusable = true,
                dismissOnBackPress = true,
                dismissOnClickOutside=  true,
            )
        )    {
            ElevatedCard(modifier = Modifier
                .fillMaxWidth(0.9f)
                .background(Color.White)
            ){
                Row(modifier = Modifier.fillMaxWidth()) {
                    Column (
                        Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Entra el punt de sortida o d'arribada")
                        TextField(value = puntSortidaText , onValueChange = routeOrderListViewModel::onPuntSortidaChange,
                            leadingIcon = {
                                Icon(imageVector = Icons.Filled.Home, contentDescription = "Punt de sortida" )
                            },
                            placeholder = {
                                Text(text = "Punt de sortida", color = Color.Gray)
                            })
                        TextField(value = puntArribadaText , onValueChange = routeOrderListViewModel::onPuntArribadaChange,
                            leadingIcon = {
                                Icon(imageVector = Icons.Filled.Map, contentDescription = "Punt de aribada" )
                            },
                            placeholder = {
                                Text(text = "Punt de arribada", color = Color.Gray)
                            })
                        ElevatedButton(onClick = {
                            routeOrderListViewModel.onFilterSearch(
                                puntSortidaText,
                                puntArribadaText
                            )
                        }) {
                            Text(text = "Cerca")
                        }
                    }
                }
                }

        }
    }
}