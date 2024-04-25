package com.example.rutescompartidesapp.view.order_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.outlined.Flag
import androidx.compose.material.icons.outlined.Route
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rutescompartidesapp.data.domain.OrderForList
import com.example.rutescompartidesapp.ui.theme.MateBlackRC
import com.example.rutescompartidesapp.ui.theme.OrangeRC
import com.example.rutescompartidesapp.utils.roundTo1Decimal


@Composable
fun TopCardInfo(order : OrderForList) {
    ElevatedCard (
        modifier = Modifier.fillMaxWidth(),colors = CardDefaults.elevatedCardColors(
        containerColor = MateBlackRC)
    )
    {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalArrangement = Arrangement.Center
        ){
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Row (modifier = Modifier.weight(2f),horizontalArrangement = Arrangement.Start) {
                    Text(text = order.orderName, color = Color.White)
                }
                Row(modifier = Modifier.weight(2f), horizontalArrangement = Arrangement.End) {
                    Text(text = order.user, color = OrangeRC, fontWeight = FontWeight.Bold)
                }
            }
            TopCardDetails(Icons.Outlined.Flag, heading = "Origen", value = order.puntSortida, color = MaterialTheme.colorScheme.primary, padding = 4.dp)

            TopCardDetails(Icons.Filled.Flag, heading = "Destinació", value = order.puntArribada, color = MaterialTheme.colorScheme.primary, padding = 4.dp)

            TopCardDetails(Icons.Outlined.Route, heading = "Distància", value = "${order.distance.roundTo1Decimal()} km", color = MaterialTheme.colorScheme.primary, padding = 4.dp)

        }

    }
}
@Composable
fun TopCardDetails(icon : ImageVector, heading: String, value : String, padding : Dp? = 0.dp, color : Color = MaterialTheme.colorScheme.primary) {
    Row (
        verticalAlignment = Alignment.CenterVertically
    ){
        var pad = 0.dp
        padding?.let{pad = it}
        Icon(
            imageVector = icon,
            tint = color,
            contentDescription = null,
            modifier = Modifier
                .padding()
                .padding(end = pad)
        )

        Text(text = "$heading: ",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Text(text = value, color = Color.White)
    }
}