package com.example.rutescompartidesapp.view.map.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import com.example.rutescompartidesapp.data.domain.GeoName.GeoName
import com.example.rutescompartidesapp.data.domain.Order

@Composable
fun LocationListItem(location: GeoName) {
    Text(
        text = "${location.name}",
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onBackground
    )
}