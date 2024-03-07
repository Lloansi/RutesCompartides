package com.example.rutescompartidesapp.view.route_detail

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.rutescompartidesapp.R
import com.example.rutescompartidesapp.data.domain.RouteForList
import com.example.rutescompartidesapp.ui.theme.BlueRC
import com.example.rutescompartidesapp.ui.theme.MateBlackRC
import com.example.rutescompartidesapp.ui.theme.OrangeRC
import com.example.rutescompartidesapp.ui.theme.RedRC
import com.example.rutescompartidesapp.view.map.components.AppBar
import com.example.rutescompartidesapp.view.route_detail.components.RouteInteractionCard
import com.example.rutescompartidesapp.view.routes_order_list.ListConstants
import com.example.rutescompartidesapp.view.routes_order_list.components.RouteCardHeader

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoutesDetailScreen(routeID: Int, navHost: NavHostController) {
    // TODO Amb el ID hauría de fer una trucada a la API per obtenir la ruta
    // TODO i una altra per obtenir les interaccions de la ruta
    val interactions = DetailUtils.interactionList.filter { it.routeID == routeID }
    val route: RouteForList = ListConstants.routeList.first { it.routeID == routeID }
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    Scaffold( modifier = Modifier.fillMaxSize()
        .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = { Text(text = "Ruta #$routeID") },
                navigationIcon = {
                    IconButton(onClick = { navHost.navigate("RoutesOrderListScreen") }) {
                        Icon(imageVector = Icons.Filled.ArrowBackIosNew, contentDescription = "Go Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground
                ),
                scrollBehavior = scrollBehavior,
            )
        }) { paddingValues ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding() + 8.dp, bottom = paddingValues.calculateBottomPadding() + 8.dp, start = 8.dp, end = 8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally){
            ElevatedCard (modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.elevatedCardColors(
                    containerColor = MaterialTheme.colorScheme.background)) {
                Row {
                    RouteCardHeader(route = route)
                }
                Row {
                    LazyRow(modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 12.dp),
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
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp)) {
                    Text(text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,

                            )
                        ) {
                            append("Data sortida: ")
                        }
                        append(route.dataSortida)
                    },
                        color = MaterialTheme.colorScheme.onBackground)
                }
                Spacer(modifier = Modifier.padding(2.dp))
                // Data Arribada
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp)) {
                    Text(text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("Data arribada: ")
                        }
                        append(route.dataArribada)
                    },
                        color = MaterialTheme.colorScheme.onBackground)
                }
                Divider(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp), color = OrangeRC, thickness = 2.dp)
                // Transport Options
                Row(modifier = Modifier
                    .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically) {
                    TransportOptions(route = route)
                }
                // Available seats
                Row(modifier = Modifier
                    .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically) {
                    RouteData(icon = R.drawable.seat_icon_svg, dataHeader = "Seients lliures", data = "2")
                }
                // Max Deviation
                Row(modifier = Modifier
                    .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically) {
                    RouteData(icon = R.drawable.max_deviation_svg, dataHeader = "Màxim desviament", data = "5km")

                }
                // Available Space
                Row(modifier = Modifier
                    .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically) {
                    RouteData(icon = R.drawable.carrier_icon_svg, dataHeader = "Espai disponible", data = "Volum similair a 1 palet i mig")
                }
                Divider(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp), color = OrangeRC, thickness = 2.dp)

                // Vehicle Type
                Row(modifier = Modifier
                    .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically) {
                    RouteData(icon = R.drawable.transport_icon_svg, dataHeader = "Vehicle", data = "Wolkswagen")
                }
                Divider(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp), color = OrangeRC, thickness = 2.dp)

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Column(modifier = Modifier
                        .weight(2f)
                        .padding(bottom = 4.dp)) {
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp),
                            verticalAlignment = Alignment.CenterVertically) {
                            ElevatedButton( shape = RoundedCornerShape(16.dp),
                                modifier = Modifier.align(Alignment.CenterVertically),
                                onClick = { /*TODO*/ },
                                colors = ButtonDefaults.elevatedButtonColors(
                                    containerColor = Color.Gray
                                )
                            ) {
                                Icon(painter = painterResource(id = R.drawable.informe_icon_svg), contentDescription = "Detail icon",
                                    tint = Color.White)
                                Text(text = "Informe de ruta", color = Color.White)
                            }
                        }
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp),
                            verticalAlignment = Alignment.CenterVertically) {
                            ElevatedButton( shape = RoundedCornerShape(16.dp),
                                onClick = { /*TODO*/ },
                                colors = ButtonDefaults.elevatedButtonColors(
                                    containerColor = MateBlackRC
                                )
                            ){
                                Icon(modifier = Modifier.size(28.dp),
                                    painter = painterResource(id = R.drawable.edit_route_svg), contentDescription = "Edit route icon",
                                    tint = Color.White)
                            }
                            Spacer(modifier = Modifier.padding(8.dp))
                            ElevatedButton( shape = RoundedCornerShape(16.dp),
                                onClick = { /*TODO*/ },
                                colors = ButtonDefaults.elevatedButtonColors(
                                    containerColor = MaterialTheme.colorScheme.primary
                                )
                            ) {
                                Text(text = "Duplicar Ruta", color = Color.White)
                            }
                        }
                    }
                    Column(modifier = Modifier
                        .weight(0.75f)
                        .fillMaxWidth()
                        .padding(end = 8.dp, bottom = 4.dp),
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.End) {

                        ElevatedButton(
                            shape = RoundedCornerShape(16.dp),
                            onClick = { /*TODO*/ },
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
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
                horizontalArrangement = Arrangement.Start) {
                Text(text = "Interaccions", color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleLarge,)
            }
            Divider(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, bottom = 4.dp), color = OrangeRC, thickness = 2.dp)
            LazyColumn(modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(interactions.size) { interaction ->
                    Spacer(modifier = Modifier.padding(8.dp))
                    RouteInteractionCard(interaction = interactions[interaction])
                }

            }

        }

    }



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