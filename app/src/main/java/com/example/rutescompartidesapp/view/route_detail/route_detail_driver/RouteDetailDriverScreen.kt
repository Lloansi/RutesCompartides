package com.example.rutescompartidesapp.view.route_detail.route_detail_driver

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.rutescompartidesapp.R
import com.example.rutescompartidesapp.data.domain.RouteForList
import com.example.rutescompartidesapp.ui.theme.BlueRC
import com.example.rutescompartidesapp.ui.theme.MateBlackRC
import com.example.rutescompartidesapp.ui.theme.OrangeRC
import com.example.rutescompartidesapp.ui.theme.RedRC
import com.example.rutescompartidesapp.view.confirm_delivery.ConfirmScreen
import com.example.rutescompartidesapp.view.confirm_delivery.viewmodel.CameraViewModel
import com.example.rutescompartidesapp.view.confirm_delivery.viewmodel.DrawViewModel
import com.example.rutescompartidesapp.view.generic_components.TopAppBarWithBackNav
import com.example.rutescompartidesapp.view.route_detail.route_detail_driver.components.RouteInteractionCard
import com.example.rutescompartidesapp.view.routes_order_list.components.RouteCardHeader

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RouteDetailDriverScreen(routeID: Int, navHost: NavHostController, routeDetailDriverViewModel : RouteDetailDriverViewModel) {
    // TODO Amb el ID hauría de fer una trucada a la API per obtenir la ruta
    // TODO i una altra per obtenir les interaccions de la ruta
    // val interactions = DetailUtils.interactionList.filter { it.routeID == routeID }
    routeDetailDriverViewModel.getRoute(routeID)
    val route by routeDetailDriverViewModel.route.collectAsStateWithLifecycle()
    val order by routeDetailDriverViewModel.order.collectAsStateWithLifecycle()
    val interactions = routeDetailDriverViewModel.interactions
    val isCompleteScreenShowing by routeDetailDriverViewModel.isCompleteScreenShowing.collectAsStateWithLifecycle()
    val routeInteractionToConfirm by routeDetailDriverViewModel.routeInteractionToConfirm.collectAsStateWithLifecycle()


    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val verticalScroll = rememberScrollState()
    val lazyListState = rememberLazyListState()


    TopAppBarWithBackNav(title = if (!isCompleteScreenShowing) {
        "Ruta #$routeID"
    } else {
        "Confirmar entrega de la comanda ${routeInteractionToConfirm!!.orderID}"
    },
        onBack = {
            println(isCompleteScreenShowing)
            if (isCompleteScreenShowing) {
                routeDetailDriverViewModel.showCompleteScreen(false)
            } else {
                navHost.popBackStack()
            }
        },
        content = {
            if (route != null){


            Column (modifier = Modifier.verticalScroll(verticalScroll))  {
                if (isCompleteScreenShowing) {
                ConfirmScreen(
                    routeDetailDriverViewModel = routeDetailDriverViewModel,
                    navController = navHost,
                    cameraViewModel = CameraViewModel(),
                    drawViewModel = DrawViewModel()
                )
            } else {
                ElevatedCard(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.elevatedCardColors(
                        containerColor = MaterialTheme.colorScheme.background
                    )
                ) {
                    Row {
                        RouteCardHeader(route = route!!)
                    }
                    Row {
                        LazyRow(modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 12.dp),
                            content = {
                                route!!.etiquetes?.forEach { etiqueta ->
                                    item {
                                        ElevatedAssistChip(
                                            onClick = { /*TODO*/ },
                                            label = {
                                                Text(etiqueta, color = Color.White)
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
                                append(route!!.dataSortida)
                            },
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                    Spacer(modifier = Modifier.padding(2.dp))
                    // Data Arribada
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 12.dp)
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
                                append(route!!.dataArribada)
                            },
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp),
                        color = OrangeRC,
                        thickness = 2.dp
                    )
                    // Transport Options
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TransportOptions(route = route!!)
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
                            data = "2"
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
                            data = "5km"
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
                            data = "Volum similair a 1 palet i mig"
                        )
                    }
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp),
                        color = OrangeRC,
                        thickness = 2.dp
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
                            data = "Wolkswagen"
                        )
                    }
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp),
                        color = OrangeRC,
                        thickness = 2.dp
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp),
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(2f)
                                .padding(bottom = 4.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                ElevatedButton(
                                    shape = RoundedCornerShape(16.dp),
                                    onClick = { navHost.navigate(
                                        "EditRouteScreen/{routeId}".replace(
                                            oldValue = "{routeId}",
                                            newValue = "$routeID"
                                        )) },
                                    colors = ButtonDefaults.elevatedButtonColors(
                                        containerColor = MateBlackRC
                                    )
                                ) {
                                    Icon(
                                        modifier = Modifier.size(28.dp),
                                        painter = painterResource(id = R.drawable.edit_route_svg),
                                        contentDescription = "Edit route icon",
                                        tint = Color.White
                                    )
                                }
                                Spacer(modifier = Modifier.padding(8.dp))
                                ElevatedButton(
                                    shape = RoundedCornerShape(16.dp),
                                    onClick = { routeDetailDriverViewModel.duplicateRoute(route!!) },
                                    colors = ButtonDefaults.elevatedButtonColors(
                                        containerColor = MaterialTheme.colorScheme.primary
                                    )
                                ) {
                                    Text(text = "Duplicar Ruta", color = Color.White)
                                }
                            }
                        }
                        Column(
                            modifier = Modifier
                                .weight(0.75f)
                                .fillMaxWidth()
                                .padding(end = 8.dp, bottom = 4.dp),
                            verticalArrangement = Arrangement.Bottom,
                            horizontalAlignment = Alignment.End
                        ) {

                            ElevatedButton(
                                shape = RoundedCornerShape(16.dp),
                                onClick = { routeDetailDriverViewModel.deleteRoute(route!!) },
                                colors = ButtonDefaults.elevatedButtonColors(
                                    containerColor = RedRC
                                )
                            ) {
                                Icon(
                                    modifier = Modifier.size(28.dp),
                                    imageVector = Icons.Filled.Delete,
                                    contentDescription = "Edit route icon",
                                    tint = Color.White
                                )
                            }

                        }

                    }
                }
                // Interaccions
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "Interaccions", color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.titleLarge,
                    )
                }
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp, bottom = 4.dp), color = OrangeRC, thickness = 2.dp
                )
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(LocalConfiguration.current.screenHeightDp.dp * 0.6f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(interactions.size) { index ->
                        Spacer(modifier = Modifier.padding(8.dp))
                        RouteInteractionCard(
                            interaction = interactions[index],
                            index = index,
                            routeDetailDriverViewModel = routeDetailDriverViewModel
                        )
                    }

                }
            }
            }
            } else {
                CircularProgressIndicator()
            }
        })
}
@Composable
fun RouteData(icon: Int, dataHeader: String, data: String) {
    Icon(modifier= Modifier.padding(start = 12.dp, 8.dp, 8.dp, 8.dp), painter = painterResource(id = icon), contentDescription = "$dataHeader Icon" ,
        tint = MaterialTheme.colorScheme.onBackground)
    Text(text = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                fontWeight = FontWeight.Bold
            )
        ) {
            append("$dataHeader: ")
        }
        append(data)
    }, color = MaterialTheme.colorScheme.onBackground)
}

@Composable
fun TransportOptions(route: RouteForList) {
    var conditions = ""
    if (route.isIsoterm) {
        conditions+=" Isoterm | "
    } else if (route.isRefrigerat){
        conditions+=" Refrigerat |"
    } else if (route.isCongelat){
        conditions+=" Congelat |"
    } else if (route.isSenseHumitat){
        conditions+=" Sense Humitat"
    }
    RouteData(icon = R.drawable.humidity_icon_svg, dataHeader = "Condicions", data = conditions)
}