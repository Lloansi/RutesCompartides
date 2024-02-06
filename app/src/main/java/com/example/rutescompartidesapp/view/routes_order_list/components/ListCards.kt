package com.example.rutescompartidesapp.view.routes_order_list.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.rutescompartidesapp.data.domain.OrderForList
import com.example.rutescompartidesapp.data.domain.RouteForList

@Composable
fun RouteCard(route: RouteForList) {
    Column {
        Row {
            Spacer(modifier = Modifier.padding(4.dp))
            Text(text = route.routeName)
        }
        Row {
            Spacer(modifier = Modifier.padding(4.dp))
            Text(text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold
                    ) ){
                    append("Sortida: ")
                }
                append(route.puntSortida)
            })
            Spacer(modifier = Modifier.padding(4.dp))
            Text(text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold
                    ) ){
                    append("Arribada: ")
                }
                append(route.puntArribada)
            })
        }
    }


}

@Composable
fun OrderCard(order: OrderForList) {
    Column {
        Row {
            Spacer(modifier = Modifier.padding(4.dp))
            Text(text = order.orderName)
        }
        Row {
            Spacer(modifier = Modifier.padding(4.dp))
            Text(text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold
                    ) ){
                    append("Sortida: ")
                }
                append(order.puntSortida)
            }
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold
                    ) ){
                    append("Arribada: ")
                }
                append(order.puntArribada)
            }
            )
        }
    }

}