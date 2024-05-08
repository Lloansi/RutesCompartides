package com.example.rutescompartidesapp.view.map.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.rutescompartidesapp.data.domain.Order
import com.example.rutescompartidesapp.data.domain.Route
import com.example.rutescompartidesapp.data.domain.orders.Orders
import com.example.rutescompartidesapp.data.domain.routes.Routes
import com.example.rutescompartidesapp.view.login.LoginViewModel


@Composable
fun CardBottomMap(ordersFiltered: List<Orders>?, routesFiltered: List<Routes>?, navController: NavHostController,loginViewModel: LoginViewModel){
    LazyRow(
        modifier = Modifier.fillMaxWidth()
    ){
        if (!ordersFiltered.isNullOrEmpty()){
            items(ordersFiltered.size){ item ->
                val orderItem = ordersFiltered[item]
                ComandaCard(comanda = orderItem, navController)
            }
        }

        if (!routesFiltered.isNullOrEmpty()){
            items(routesFiltered.size){item ->
                val rutaItem = routesFiltered[item]
                RouteCard(ruta = rutaItem, navController, loginViewModel)
            }
        }
    }
}