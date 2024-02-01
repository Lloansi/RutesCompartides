package com.example.rutescompartidesapp.view.routes_order_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Map
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rutescompartidesapp.view.routes_order_list.RoutesOrderListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun FilterPopup(){
    val routeOrderListViewModel: RoutesOrderListViewModel = hiltViewModel()
    val isPopupShowing by routeOrderListViewModel.popupIsShowing.collectAsState()
    val puntSortidaText by routeOrderListViewModel.puntSortidaText.collectAsState()
    val puntArribadaText by routeOrderListViewModel.puntArribadaText.collectAsState()
    val dataSortidaText by routeOrderListViewModel.dataSortidaText.collectAsState()
    val horaSortidaText by routeOrderListViewModel.horaSortidaText.collectAsState()
    val areExtraFiltersShowing by routeOrderListViewModel.extraFiltersAreShowing.collectAsState()

    if (isPopupShowing){
        Popup(alignment = Alignment.Center,
            offset = IntOffset(0, 700),
            onDismissRequest = { routeOrderListViewModel.onPopupShow(false) },
            properties = PopupProperties(
                focusable = true,
                dismissOnBackPress = true,
                dismissOnClickOutside=  true)
        ){
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
                        Spacer(modifier = Modifier.padding(4.dp))
                        Text(text = "Entra el punt de sortida o d'arribada")
                        Spacer(modifier = Modifier.padding(8.dp))
                        OutlinedFilterTextField(
                            value = puntSortidaText,
                            onValueChange = routeOrderListViewModel::onPuntSortidaChange,
                            placeholder = "Punt de sortida",
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Filled.Home,
                                    contentDescription = "Punt de sortida",
                                    tint = Color.Gray
                                )
                            }
                        )
                        OutlinedFilterTextField(value = puntArribadaText,
                            onValueChange = routeOrderListViewModel::onPuntArribadaChange,
                            placeholder = "Punt de aribada",
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Filled.Map,
                                    contentDescription = "Punt de aribada",
                                    tint = Color.Gray
                                )
                            }
                        )
                        IconButton(onClick = { routeOrderListViewModel.onExtraFiltersToggle() }) {
                            if (areExtraFiltersShowing) {
                                Icon(
                                    imageVector = Icons.Filled.KeyboardArrowUp,
                                    contentDescription = "Mostrar menys filtres"
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.Filled.KeyboardArrowDown,
                                    contentDescription = "Mostrar més filtres"
                                )
                            }
                        }
                        if (areExtraFiltersShowing){
                            OutlinedFilterTextField(
                                value = dataSortidaText,
                                onValueChange = routeOrderListViewModel::onDataSortidaChange,
                                placeholder = "Data de sortida",
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Filled.CalendarMonth,
                                        contentDescription = "Data de sortida",
                                        tint = Color.Gray
                                    )
                                }
                            )
                            OutlinedFilterTextField(value = horaSortidaText,
                                onValueChange = routeOrderListViewModel::onHoraArribadaChange,
                                placeholder = "Hora de aribada",
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Filled.AccessTime,
                                        contentDescription = "Hora de sortida",
                                        tint = Color.Gray
                                    )
                                }
                            )
                        }
                        Spacer(modifier = Modifier.padding(8.dp))
                        ElevatedButton(onClick = {
                            routeOrderListViewModel.onFilterSearch(
                                puntSortidaText,
                                puntArribadaText
                            )
                        }) {
                            Text(text = "Cerca")
                        }
                        Spacer(modifier = Modifier.padding(8.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun OutlinedFilterTextField(value: String, onValueChange: (String) -> Unit, placeholder: String, leadingIcon: @Composable () -> Unit){
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        leadingIcon = {
            leadingIcon()
        },
        placeholder = {
            Text(text = placeholder, color = Color.Gray)
        },
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
            unfocusedIndicatorColor = Color.Gray,
            disabledIndicatorColor = Color.Transparent,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next),
        singleLine = true,
    )

    Spacer(modifier = Modifier.padding(8.dp))

}