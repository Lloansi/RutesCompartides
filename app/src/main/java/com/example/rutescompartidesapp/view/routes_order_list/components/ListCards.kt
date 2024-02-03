package com.example.rutescompartidesapp.view.routes_order_list.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.rutescompartidesapp.data.domain.OrderForList
import com.example.rutescompartidesapp.data.domain.RouteForList

@Composable
fun RouteCard(route: RouteForList) {
    Row {
        Text(text = route.routeName, modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            )
    }

}

@Composable
fun OrderCard(order: OrderForList) {
    Row {
        Text(text = order.orderName, modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            )
    }
}