package com.example.rutescompartidesapp.view.generic_components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.outlined.Flag
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rutescompartidesapp.data.domain.orders.Orders
import com.example.rutescompartidesapp.data.domain.routes.Routes
import com.example.rutescompartidesapp.ui.theme.MateBlackRC
import com.example.rutescompartidesapp.ui.theme.OrangeRC
import com.example.rutescompartidesapp.utils.LocalConstants

/**
 * Composable function to display the header for a route and an order.
 *
 * @param route The route information.
 * @param order The order information.
 */
@Composable
fun RouteOrderHeader(route: Routes, order: Orders) {
        ElevatedCard (modifier = Modifier.fillMaxWidth(),colors = CardDefaults.elevatedCardColors(
            containerColor = MateBlackRC
        )
        ) {
            Column(modifier = Modifier.padding(8.dp)) {

                RoutePoints2(route = route)
                Divider(color = MaterialTheme.colorScheme.primary, thickness = 2.dp,
                    modifier = Modifier.padding(top = 2.dp, bottom = 2.dp))
                OrderPoints(order = order)
            }
        }
}

/**
 * Preview function for displaying a preview of the [RouteOrderHeader] composable.
 */
@Preview(showBackground = true)
@Composable
fun RouteOrderHeaderPreview(){
    RouteOrderHeader(LocalConstants.routeList.first(), LocalConstants.orderList.first())
}

/**
 * Composable function to display route points.
 *
 * @param route The route information.
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RoutePoints2(route: Routes){
    FlowRow (modifier = Modifier.padding(bottom = 3.dp, top = 2.dp),
        verticalArrangement = Arrangement.Center) {
        Text(text = buildAnnotatedString {
            append("Ruta ")
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Bold,
                    color = OrangeRC
                )
            ) {
                append("${route.routeID}#")
            }
            append(": ")
        }, color = Color.White)
        Icon(
            imageVector = Icons.Outlined.Flag,
            contentDescription = "Start Point Icon",
            tint = OrangeRC,
            modifier = Modifier.size(22.dp)
        )
        Text(text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Bold,
                    color = OrangeRC
                )
            ) {
                append(route.puntSortida)
            }
        })
        Icon(imageVector = Icons.Filled.ChevronRight,
            contentDescription = "Intermediate Point Icon",
            tint = Color.White,
            modifier = Modifier.size(22.dp))
        if (!route.puntsIntermedis.isNullOrEmpty()){
            route.puntsIntermedis.forEach { puntIntermig ->
                Text(text = puntIntermig, color = Color.White)
                Icon(imageVector = Icons.Filled.ChevronRight,
                    contentDescription = "Intermediate Point Icon",
                    tint = Color.White,
                    modifier = Modifier.size(22.dp))
            }
        }
        Icon(imageVector = Icons.Filled.Flag,
            contentDescription = "End Point Icon",
            tint = OrangeRC,
            modifier = Modifier.size(22.dp))
        Text(text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Bold,
                    color = OrangeRC

                )
            ) {
                append(route.puntArribada)
            }
        })
    }


}

/**
 * Composable function to display order points.
 *
 * @param order The order information.
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun OrderPoints(order: Orders){
    FlowRow (modifier = Modifier.padding(bottom = 2.dp, top = 3.dp),
        verticalArrangement = Arrangement.Center) {
            Text(text = buildAnnotatedString {
                append("Comanda ")
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = OrangeRC
                    )
                ) {
                    append("${order.orderID}#")
                }
                append(": ")
            }, color = Color.White)
            Icon(
                imageVector = Icons.Outlined.Flag, contentDescription = "Start Point Icon",
                tint = OrangeRC,
                modifier = Modifier.size(22.dp)
            )
            Text(text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = OrangeRC
                    )
                ) {
                    append(order.puntSortida)
                }
            })
            Icon(
                imageVector = Icons.Filled.ChevronRight,
                contentDescription = "Intermediate Point Icon",
                tint = Color.White,
                modifier = Modifier.size(22.dp)
            )
            Icon(
                imageVector = Icons.Filled.Flag, contentDescription = "End Point Icon",
                tint = OrangeRC,
                modifier = Modifier.size(22.dp)
            )
            Text(text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = OrangeRC
                    )
                ) {
                    append(order.puntArribada)
                }
            })
    }

}