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
import com.example.rutescompartidesapp.data.domain.Order
import com.example.rutescompartidesapp.ui.theme.MateBlackRC


@Composable
fun TopCardInfo(order : Order) {
    ElevatedCard (
        modifier = Modifier.fillMaxWidth(),colors = CardDefaults.elevatedCardColors(
        containerColor = MateBlackRC)
    )
    {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center
        ){
            TopCardDetails(Icons.Outlined.Flag, heading = "Origen", value = order.packageStartPoint, color = Color.White, padding = 8.dp)

            TopCardDetails(Icons.Filled.Flag, heading = "Destinació", value = order.packageEndPoint, color = Color.White, padding = 8.dp)

            TopCardDetails(Icons.Outlined.Route, heading = "Distància", value = "17km", color = Color.White, padding = 8.dp)

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
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold
            ),
            fontSize = 22.sp,
            color = Color.White
        )

        Text(text = value,fontSize = 18.sp, color = Color.White)
    }
}