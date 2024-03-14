package com.example.rutescompartidesapp.view.routes_order_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.outlined.Flag
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.rutescompartidesapp.data.domain.OrderForList
import com.example.rutescompartidesapp.data.domain.RouteForList
import com.example.rutescompartidesapp.ui.theme.BlueRC
import com.example.rutescompartidesapp.ui.theme.MateBlackRC
import com.example.rutescompartidesapp.ui.theme.OrangeRC

@Composable
fun RoutePoints(route: RouteForList){
    Row (modifier = Modifier.padding(bottom = 2.dp, top = 2.dp), horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = Icons.Outlined.Flag, contentDescription = "Start Point Icon",
            tint = OrangeRC
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
        Icon(imageVector = Icons.Filled.ChevronRight, contentDescription = "Intermediate Point Icon",
            tint = Color.White)
    }
    if (!route.puntsIntermedis.isNullOrEmpty()){
        LazyRow(content = {
            route.puntsIntermedis.forEach { puntIntermig ->
                item {
                    Text(text = puntIntermig, color = Color.White)
                    Icon(imageVector = Icons.Filled.ChevronRight, contentDescription = "Intermediate Point Icon",
                        tint = Color.White)
                }
            }
        })
    }
    Row(modifier = Modifier.padding(bottom = 2.dp, top = 2.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        Icon(imageVector = Icons.Filled.Flag, contentDescription = "End Point Icon",
            tint = OrangeRC)
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

@Composable
fun RouteCardHeader(route: RouteForList){
    ElevatedCard (modifier = Modifier.fillMaxWidth(),colors = CardDefaults.elevatedCardColors(
        containerColor = MateBlackRC)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Row (modifier = Modifier.weight(2f),horizontalArrangement = Arrangement.Start) {
                    Text(text = route.routeName, color = Color.White)
                }
                Row(modifier = Modifier.weight(2f), horizontalArrangement = Arrangement.End) {
                    Text(text = route.user, color = OrangeRC, fontWeight = FontWeight.Bold)
                }
            }
            RoutePoints(route = route)
        }
    }
}

@Composable
fun RouteCard(route: RouteForList, navController: NavHostController) {
    Column{
        ElevatedCard (modifier = Modifier.fillMaxWidth(0.95f),
            colors = CardDefaults.elevatedCardColors(
                containerColor = Color.White)) {
            Row {
                RouteCardHeader(route = route)
            }
            Spacer(modifier = Modifier.padding(4.dp))
            Row {
                Spacer(modifier = Modifier.padding(4.dp))
                Text(text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("Data sortida: ")
                    }
                    append(route.dataSortida)
                })
            }
            Spacer(modifier = Modifier.padding(4.dp))
            Row {
                Spacer(modifier = Modifier.padding(4.dp))
                LazyRow(modifier = Modifier.weight(2f),
                    content = {
                    route.etiquetes?.forEach { etiqueta ->
                        item {
                            ElevatedAssistChip(onClick = { /*TODO*/ },
                                label = {
                                Text(etiqueta, color = Color.White) },
                                shape = RoundedCornerShape(16.dp),
                                colors = AssistChipDefaults.assistChipColors(
                                    containerColor = BlueRC
                                )
                            )
                        }
                    }
                })
                Spacer(modifier = Modifier.padding(4.dp))
                ElevatedButton( shape = RoundedCornerShape(16.dp),
                    onClick = { navController.navigate("RouteDetailDriverScreen/{routeId}".replace(
                        oldValue = "{routeId}",
                        newValue = "${route.routeID}"
                    ) )
                        },
                    colors = ButtonDefaults.elevatedButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Icon(imageVector = Icons.Filled.Bookmark, contentDescription = "Detail icon",
                        tint = Color.White)
                    Text(text = "Detall", color = Color.White)
                }
                Spacer(modifier = Modifier.padding(4.dp))
            }
            Spacer(modifier = Modifier.padding(4.dp))
        }
    }
}


@Composable
fun OrderCard(order: OrderForList, navController: NavHostController) {
    Column{
        ElevatedCard (modifier = Modifier.fillMaxWidth(0.95f),
            colors = CardDefaults.elevatedCardColors(
                containerColor = Color.White)){
            Row {
                ElevatedCard (modifier = Modifier.fillMaxWidth(),colors = CardDefaults.elevatedCardColors(
                    containerColor = MateBlackRC)
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Row(horizontalArrangement = Arrangement.SpaceBetween) {
                            Row (modifier = Modifier.weight(2f),horizontalArrangement = Arrangement.Start) {
                                Text(text = order.orderName, color = Color.White)
                            }
                            Row(modifier = Modifier.weight(2f), horizontalArrangement = Arrangement.End) {
                                Text(text = order.user, color = OrangeRC, fontWeight = FontWeight.Bold)
                            }
                        }
                        Row(modifier = Modifier.padding(bottom = 2.dp, top = 2.dp), horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Outlined.Flag, contentDescription = "Start Point Icon",
                                tint = OrangeRC
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
                            Icon(imageVector = Icons.Filled.ChevronRight, contentDescription = "Intermediate Point Icon",
                                tint = Color.White)
                        }
                        Row(modifier = Modifier.padding(bottom = 2.dp, top = 2.dp), horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Filled.Flag, contentDescription = "End Point Icon",
                                tint = OrangeRC)
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
                }
            }
            Spacer(modifier = Modifier.padding(4.dp))
            Row {
                Spacer(modifier = Modifier.padding(4.dp))
                Text(text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("Data sortida: ")
                    }
                    append(order.dataSortida)
                })
            }
            Spacer(modifier = Modifier.padding(4.dp))
            Row {
                Spacer(modifier = Modifier.padding(4.dp))
                LazyRow(modifier = Modifier.weight(2f),
                    content = {
                        order.etiquetes?.forEach { etiqueta ->
                            item {
                                ElevatedAssistChip(onClick = { /*TODO*/ },
                                    label = {
                                        Text(etiqueta, color = Color.White,
                                            style = MaterialTheme.typography.labelLarge) },
                                    shape = RoundedCornerShape(16.dp),
                                    colors = AssistChipDefaults.assistChipColors(
                                        containerColor = BlueRC
                                    )
                                )
                            }
                        }
                    })
                ElevatedButton( shape = RoundedCornerShape(16.dp),
                    onClick = { navController.navigate("OrderDetailScreen/{packageId}".replace(
                        oldValue = "{packageId}",
                        newValue = "${order.orderID}"
                    ) ) },
                    colors = ButtonDefaults.elevatedButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Icon(imageVector = Icons.Filled.Bookmark, contentDescription = "Detail icon",
                        tint = Color.White)
                    Text(text = "Detall", color = Color.White, style = MaterialTheme.typography.bodyLarge)

                }
                Spacer(modifier = Modifier.padding(4.dp))
            }
            Spacer(modifier = Modifier.padding(4.dp))
        }
    }
}

@Composable
fun EmptyResults(type: String) {
    Spacer(modifier = Modifier.padding(0.dp, LocalConfiguration.current.screenHeightDp.dp / 10))
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center) {
            Text(text = "No s'ha trobat cap $type", style = MaterialTheme.typography.headlineLarge)
        }
    }
}
