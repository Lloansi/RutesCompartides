package com.example.rutescompartidesapp.view.map.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import com.example.rutescompartidesapp.data.domain.Route

@Composable
fun RouteListItem(route: Route) {
    Text(
        text = "$route.routeId",
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onBackground
    )
}