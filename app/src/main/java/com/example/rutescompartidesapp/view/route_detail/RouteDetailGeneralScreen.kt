package com.example.rutescompartidesapp.view.route_detail

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.rutescompartidesapp.R
import com.example.rutescompartidesapp.data.domain.ListQuery
import com.example.rutescompartidesapp.data.domain.orders.Orders
import com.example.rutescompartidesapp.ui.theme.BlueRC
import com.example.rutescompartidesapp.ui.theme.MateBlackRC
import com.example.rutescompartidesapp.ui.theme.OrangeRC
import com.example.rutescompartidesapp.view.generic_components.TopAppBarWithBackNav
import com.example.rutescompartidesapp.view.map.viewModels.MapViewModel
import com.example.rutescompartidesapp.view.map.viewModels.MapViewModel2
import com.example.rutescompartidesapp.view.route_detail.route_detail_driver.RouteData
import com.example.rutescompartidesapp.view.route_detail.route_detail_driver.TransportOptions
import com.example.rutescompartidesapp.view.route_detail.components.RouteMapViewContainer
import com.example.rutescompartidesapp.view.generic_components.OrderPoints
import com.example.rutescompartidesapp.view.login.LoginViewModel
import com.example.rutescompartidesapp.view.publish_order.ManageOrderViewModel
import com.example.rutescompartidesapp.view.route_detail.viewModels.RouteDetailViewModel
import com.example.rutescompartidesapp.view.routes_order_list.components.RouteCardHeader
import com.example.rutescompartidesapp.view.routes_order_list.viewmodels.RoutesOrderListViewModel

@OptIn(ExperimentalLayoutApi::class)
@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun RouteDetailGeneralScreen(
    navHost: NavHostController,
    routeID: Int, mapViewModel: MapViewModel,
    mapViewModel2: MapViewModel2,
    manageOrderViewModel: ManageOrderViewModel,
    routesOrderListViewModel: RoutesOrderListViewModel,
    loginViewModel: LoginViewModel
) {

    val routeDetailViewModel = RouteDetailViewModel()
    routeDetailViewModel.getRoute(routeID)
    val matchingOrders by routeDetailViewModel.userMatchingOrders.collectAsStateWithLifecycle()
    val requestPopup by routeDetailViewModel.requestPopup.collectAsState()
    val orderFromRoute by routeDetailViewModel.orderFromRoute.collectAsStateWithLifecycle()
    val routeInfo by routeDetailViewModel.route.collectAsStateWithLifecycle()
    val isMapExpanded by mapViewModel2.isBoxMapClicked.collectAsState()
    val isOrderPendent by routeDetailViewModel.isOrderPendent.collectAsState()
    val verticalScroll = rememberScrollState()
    val user by loginViewModel.user.collectAsStateWithLifecycle()


    if (orderFromRoute != null){
        manageOrderViewModel.loadRouteInfo(orderFromRoute!!)
        navHost.navigate(
            "PublishOrderScreen/{command}/{orderID}".replace(
                oldValue = "{command}",
                newValue = "createFrom"
            ).replace(
                oldValue = "{orderID}",
                newValue = "0"
            )){
            popUpTo("RouteDetailGeneralScreen/{routeId}")
        }
    }


    // Screen
    TopAppBarWithBackNav(
        title = "Ruta #$routeID",
        onBack = { navHost.popBackStack() },
        content = {

            if (routeInfo != null) {
            val ctx = LocalContext.current
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(if (isMapExpanded) 1f else 0.25f)
                    .clip(RoundedCornerShape(16.dp))
                    .clickable {
                        mapViewModel2.updateClickState(!isMapExpanded)
                    }
            ) {
                RouteMapViewContainer(
                    viewModel = mapViewModel,
                    viewModel2 = mapViewModel2,
                    ctx,
                    routeInfo!!.startPoint,
                    routeInfo!!.endPoint,
                    13.5
                )
            }
                if (requestPopup){
                    RequestRoutePopup(matchingOrders, routeDetailViewModel, routeID) // End Popup
                } // End if acceptPopup

                Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ElevatedButton(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 4.dp, end = 4.dp),
                    shape = RoundedCornerShape(16.dp),
                    onClick = {
                        routesOrderListViewModel.onFilterSearch(
                            ListQuery(
                                puntSortida = routeInfo!!.puntSortida,
                                puntArribada = routeInfo!!.puntArribada,
                                dataSortida = routeInfo!!.dataSortida,
                                horaSortida = routeInfo!!.horaSortida,
                                etiquetes = routeInfo!!.etiquetes ?: listOf(),
                                isIsoterm = routeInfo!!.isIsoterm,
                                isRefrigerat = routeInfo!!.isRefrigerat,
                                isCongelat = routeInfo!!.isCongelat,
                                isSenseHumitat = routeInfo!!.isSenseHumitat
                            )
                        )
                        navHost.navigate("RoutesOrderListScreen")
                    },
                    colors = ButtonDefaults.elevatedButtonColors(
                        containerColor = BlueRC
                    )
                ) {
                    Icon(
                        modifier = Modifier.size(32.dp),
                        imageVector = Icons.Filled.FilterList,
                        contentDescription = "Rutes semblants icon",
                        tint = Color.White
                    )
                    Text(
                        text = "Rutes semblants", color = Color.White,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
                // Demanar Ruta
                if (!isOrderPendent){
                    ElevatedButton(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 4.dp, end = 4.dp),
                        shape = RoundedCornerShape(16.dp),
                        onClick = {
                            routeDetailViewModel.filterMatchingOrders(userID = user!!.userId )
                            routeDetailViewModel.onTogglePopup()
                        },
                        colors = ButtonDefaults.elevatedButtonColors(
                            containerColor = OrangeRC
                        )
                    ) {
                        Icon(
                            modifier = Modifier
                                .padding(end = 4.dp)
                                .size(24.dp),
                            painter = painterResource(id = R.drawable.transport_icon_svg),
                            contentDescription = "Demanar ruta icon",
                            tint = Color.White
                        )
                        Text(
                            text = "Demana aquesta ruta", color = Color.White,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.headlineMedium
                        )
                    }
                }

            }
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(verticalScroll),
                colors = CardDefaults.elevatedCardColors(
                    containerColor = MaterialTheme.colorScheme.onTertiaryContainer
                )
            ) {
                Row {
                    RouteCardHeader(route = routeInfo!!)
                }
                Row {
                    LazyRow(modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 12.dp),
                        content = {
                            routeInfo!!.etiquetes?.forEach { etiqueta ->
                                item {
                                    ElevatedAssistChip(
                                        onClick = { /*TODO*/ },
                                        label = {
                                            Text(text = etiqueta, color = Color.White)
                                        },
                                        shape = RoundedCornerShape(16.dp),
                                        colors = AssistChipDefaults.assistChipColors(
                                            containerColor = BlueRC
                                        )
                                    )
                                }
                            }
                        })
                }
                Spacer(modifier = Modifier.padding(2.dp))
                // Data sortida
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 12.dp)
                ) {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold,

                                    )
                            ) {
                                append("Data sortida: ")
                            }
                            append("${routeInfo!!.dataSortida} ${routeInfo!!.horaSortida}")
                        },
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                Spacer(modifier = Modifier.padding(2.dp))
                // Data Arribada
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 12.dp, bottom = 2.dp)
                ) {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append("Data arribada: ")
                            }
                            append("${routeInfo!!.dataArribada} ${routeInfo!!.horaArribada}")
                        },
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    color = OrangeRC,
                    thickness = 2.dp
                )
                // Transport Options
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TransportOptions(route = routeInfo!!)
                }
                // Available seats
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RouteData(
                        icon = R.drawable.seat_icon_svg,
                        dataHeader = "Seients lliures",
                        data = routeInfo!!.availableSeats.toString()
                    )
                }
                // Max Deviation
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RouteData(
                        icon = R.drawable.max_deviation_svg,
                        dataHeader = "Màxim desviament",
                        data = routeInfo!!.maxDetourKm.toString()
                    )
                }
                // Available Space
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RouteData(
                        icon = R.drawable.carrier_icon_svg,
                        dataHeader = "Espai disponible",
                        data = routeInfo!!.availableSpace.toString()
                    )
                }
                // Cost de la ruta
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RouteData(
                        icon = R.drawable.money_svg_icon,
                        dataHeader = "Cost de la ruta",
                        data = routeInfo!!.costKm.toString()+"€/km"
                    )
                }
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    color = OrangeRC, thickness = 2.dp
                )
                // Vehicle Type
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RouteData(
                        icon = R.drawable.transport_icon_svg,
                        dataHeader = "Vehicle",
                        data = routeInfo!!.vehicle.toString()
                    )
                }
                // Comentari (si existeix)
                if (!routeInfo!!.comment.isNullOrEmpty()){
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        color = OrangeRC, thickness = 2.dp
                    )
                    FlowRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp, 8.dp, 8.dp, 8.dp),
                    ) {
                        Text(text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append("Comentari: ")
                            }
                            append(routeInfo!!.comment ?: "")
                        }, color = MaterialTheme.colorScheme.onBackground)
                    }
                }

                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    color = OrangeRC,
                    thickness = 2.dp
                )
            }


        }
        }
    )

}

@Composable
private fun RequestRoutePopup(
    matchingOrders: List<Orders>,
    routeDetailViewModel: RouteDetailViewModel,
    routeID: Int
){
    Popup(
        offset = IntOffset(0, LocalConfiguration.current.screenHeightDp + 100),
        alignment = Alignment.Center,
        onDismissRequest = { routeDetailViewModel.onTogglePopup() },
        properties = PopupProperties(
            focusable = true,
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .fillMaxHeight(0.35f), colors = CardDefaults.elevatedCardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                if (matchingOrders.isNotEmpty()) {
                    Text(
                        text = "Comandas que coincideixen amb la ruta",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Divider(
                        thickness = 2.dp,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(8.dp)
                    )
                    LazyColumn(content = {
                        matchingOrders.forEach { order ->
                            item {
                                MatchingOrderCard(
                                    order = order,
                                    routeDetailViewModel = routeDetailViewModel,
                                    routeID = routeID
                                )
                            }
                        }
                    })

                } else {
                    Text(
                        text = "No tens comandes que coincideixin amb la ruta",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Divider(
                        thickness = 2.dp,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(8.dp)
                    )
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ElevatedButton(
                            onClick = {
                                routeDetailViewModel.createRouteFromOrder()
                            },
                            colors = ButtonDefaults.elevatedButtonColors(
                                contentColor = Color.White,
                                containerColor = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Text(
                                "Crear una nova comanda",
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MatchingOrderCard(order: Orders, routeDetailViewModel: RouteDetailViewModel, routeID: Int){
    val context = LocalContext.current
    ElevatedCard (modifier = Modifier
        .fillMaxWidth()
        .clickable {
            routeDetailViewModel.requestRoute(routeID, order.userID, order.orderID)
            Toast
                .makeText(context, "Has fet una petició de transport", Toast.LENGTH_SHORT)
                .show()
            routeDetailViewModel.onTogglePopup()
        },
        colors = CardDefaults.elevatedCardColors(containerColor = MateBlackRC)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Row (modifier = Modifier.weight(2f),horizontalArrangement = Arrangement.Start) {
                    Text(text = order.orderName, color = Color.White)
                }
            }
            OrderPoints(order = order)
        }
    }
}
