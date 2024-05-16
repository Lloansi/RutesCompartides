package com.example.rutescompartidesapp.view.map.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.rutescompartidesapp.data.domain.Order

@Composable
fun OrderListItem(order: Order) {
    Text(
        modifier = Modifier.padding(12.dp),
        text = "${order.packageId}",
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onBackground
    )
}