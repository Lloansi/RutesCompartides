package com.example.rutescompartidesapp.view.routes_order_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import com.example.rutescompartidesapp.data.domain.orders.Orders
import com.example.rutescompartidesapp.data.domain.routes.Routes
import com.example.rutescompartidesapp.ui.theme.BlueRC
import com.example.rutescompartidesapp.ui.theme.OrangeRC
import com.example.rutescompartidesapp.utils.LocalConstants

/**
 * Composable function for the Route Points, which displays the start, intermediate and end points of the route.
 * @param route Route
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RoutePoints(route: Routes){
    FlowRow (modifier = Modifier.padding(bottom = 2.dp, top = 2.dp),
        verticalArrangement = Arrangement.Center) {
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
        if (!route.puntsIntermedis.isNullOrEmpty()){
            route.puntsIntermedis.forEach { puntIntermig ->
                Text(text = puntIntermig, color = Color.White)
                Icon(imageVector = Icons.Filled.ChevronRight, contentDescription = "Intermediate Point Icon",
                    tint = Color.White)

            }
        }
       Row {
            Icon(
                imageVector = Icons.Filled.Flag, contentDescription = "End Point Icon",
                tint = OrangeRC
            )
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

}

/**
 * Composable function for the Route Card Header, which displays the route name and the user name
 * along with the route points.
 * @param route Route
 */

@Composable
fun RouteCardHeader(route: Routes){
    ElevatedCard (modifier = Modifier.fillMaxWidth(),colors = CardDefaults.elevatedCardColors(
        containerColor = MaterialTheme.colorScheme.onTertiary)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Row (modifier = Modifier.weight(2f),horizontalArrangement = Arrangement.Start) {
                    Text(text = route.routeName, color = Color.White)
                }
                Row(modifier = Modifier.weight(2f), horizontalArrangement = Arrangement.End) {
                    Text(text = LocalConstants.userList.first{ user -> user.userId == route.userID }.name
                        , color = OrangeRC, fontWeight = FontWeight.Bold)
                }
            }
            RoutePoints(route = route)
        }
    }
}

/**
 * Composable function for the Route Card, which displays the route information.
 * @param route Route with the information to display
 * @param navController NavController to navigate to the detail of the route
 * @param userID Int used to check if the user is the driver of the route and navigate accordingly
 */
@Composable
fun RouteCard(route: Routes, navController: NavHostController, userID: Int) {
    Column{
        ElevatedCard (modifier = Modifier
            .fillMaxWidth(0.95f)
            .clickable {
                if (route.userID == userID) {
                    navController.navigate(
                        "RouteDetailDriverScreen/{routeId}".replace(
                            oldValue = "{routeId}",
                            newValue = "${route.routeID}"
                        )
                    )
                } else {
                    navController.navigate(
                        "RouteDetailGeneralScreen/{routeId}".replace(
                            oldValue = "{routeId}",
                            newValue = "${route.routeID}"
                        )
                    )
                }

            },
            colors = CardDefaults.elevatedCardColors(
                containerColor = MaterialTheme.colorScheme.onTertiaryContainer)) {
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
                        append("Sortida: ")
                    }
                    append("${route.dataSortida} ${route.horaSortida}")
                },
                    color =  MaterialTheme.colorScheme.onBackground)
            }
            Row {
                Spacer(modifier = Modifier.padding(4.dp))
                Text(text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("Arribada: ")
                    }
                    append("${route.dataArribada} ${route.horaArribada}")
                },
                    color =  MaterialTheme.colorScheme.onBackground)
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
                    onClick = { if (route.userID == userID) {
                        navController.navigate(
                            "RouteDetailDriverScreen/{routeId}".replace(
                                oldValue = "{routeId}",
                                newValue = "${route.routeID}"
                            )
                        )
                    } else {
                        navController.navigate(
                            "RouteDetailGeneralScreen/{routeId}".replace(
                                oldValue = "{routeId}",
                                newValue = "${route.routeID}"
                            )
                        )
                    }
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

/**
 * Composable function for the Order Card, which displays the order information.
 * @param order Order to obtain the information
 * @param navController NavController to navigate to the detail of the order
 */

@Composable
fun OrderCard(order: Orders, navController: NavHostController) {
    Column{
        ElevatedCard (modifier = Modifier
            .fillMaxWidth(0.95f)
            .clickable {
                navController.navigate(
                    "OrderDetailScreen/{orderID}".replace(
                        oldValue = "{orderID}",
                        newValue = "${order.orderID}"
                    )
                )
            },
            colors = CardDefaults.elevatedCardColors(
                containerColor = MaterialTheme.colorScheme.onTertiaryContainer)){
            Row {
                ElevatedCard (modifier = Modifier.fillMaxWidth(),colors = CardDefaults.elevatedCardColors(
                    containerColor = MaterialTheme.colorScheme.onTertiary)
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Row(horizontalArrangement = Arrangement.SpaceBetween) {
                            Row (modifier = Modifier.weight(2f),horizontalArrangement = Arrangement.Start) {
                                Text(text = order.orderName, color = Color.White)
                            }
                            Row(modifier = Modifier.weight(2f), horizontalArrangement = Arrangement.End) {
                                Text(text = LocalConstants.userList.first{ user -> user.userId == order.userID }.name, color = OrangeRC, fontWeight = FontWeight.Bold)
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
                        append("Sortida: ")
                    }
                    append("${order.dataSortida} ${order.horaSortida}")
                },
                    color =  MaterialTheme.colorScheme.onBackground)
            }
            Row {
                Spacer(modifier = Modifier.padding(4.dp))
                Text(text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("Arribada: ")
                    }
                    if (order.dataArribada.isNotEmpty()){
                        append(order.dataArribada+" ${order.horaArribada}")
                    } else {
                        append("Flexible")
                    }
                },
                    color =  MaterialTheme.colorScheme.onBackground)
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

/**
 * Composable function to display a message when there are no results.
 * @param type String to display if it is a route or an order
 */
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
            Text(text = "No s'ha trobat cap $type", style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground)
        }
    }
}
