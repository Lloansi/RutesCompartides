package com.example.rutescompartidesapp.view.route_detail

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.rutescompartidesapp.R
import com.example.rutescompartidesapp.data.domain.ListQuery
import com.example.rutescompartidesapp.data.domain.Route
import com.example.rutescompartidesapp.data.domain.RouteForList
import com.example.rutescompartidesapp.ui.theme.BlueRC
import com.example.rutescompartidesapp.ui.theme.OrangeRC
import com.example.rutescompartidesapp.view.generic_components.TopAppBarWithBackNav
import com.example.rutescompartidesapp.view.map.components.allRoute
import com.example.rutescompartidesapp.view.map.viewModels.MapViewModel
import com.example.rutescompartidesapp.view.map.viewModels.MapViewModel2
import com.example.rutescompartidesapp.view.route_detail.route_detail_driver.RouteData
import com.example.rutescompartidesapp.view.route_detail.route_detail_driver.TransportOptions
import com.example.rutescompartidesapp.view.route_detail.components.RouteMapViewContainer
import com.example.rutescompartidesapp.utils.LocalConstants
import com.example.rutescompartidesapp.view.routes_order_list.components.RouteCardHeader
import com.example.rutescompartidesapp.view.routes_order_list.viewmodels.RoutesOrderListViewModel
import org.osmdroid.util.GeoPoint

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun RouteDetailGeneralScreen(navHost: NavHostController, routeID: Int, mapViewModel: MapViewModel,mapViewModel2: MapViewModel2, routesOrderListViewModel: RoutesOrderListViewModel) {

    val routeGeo: Route = allRoute.first { it.routeId == routeID }
    val routeInfo: RouteForList = LocalConstants.routeList.first { it.routeID == routeID }
    val startGeoPoint = GeoPoint(routeGeo.startLat.toDouble(), routeGeo.startLon.toDouble())
    val endGeoPoint = GeoPoint(routeGeo.endLat.toDouble(), routeGeo.endLon.toDouble())
    val routeDetailViewModel = RouteDetailViewModel()

    val isMapExpanded by mapViewModel2.isBoxMapClicked.collectAsState()

    TopAppBarWithBackNav(
        title = "Ruta #$routeID",
        onBack = { navHost.popBackStack() },
        content = {
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
                    startGeoPoint,
                    endGeoPoint,
                    13.5
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ElevatedButton(
                    modifier = Modifier.weight(1f).padding(start= 4.dp, end = 4.dp),
                    shape = RoundedCornerShape(16.dp),
                    onClick = {
                        routesOrderListViewModel.onFilterSearch(
                            ListQuery(
                                puntSortida = routeInfo.puntSortida,
                                puntArribada = routeInfo.puntArribada,
                                dataSortida = routeInfo.dataSortida,
                                horaSortida = routeInfo.horaSortida,
                                etiquetes = routeInfo.etiquetes ?: listOf(),
                                isIsoterm = routeInfo.isIsoterm,
                                isRefrigerat = routeInfo.isRefrigerat,
                                isCongelat = routeInfo.isCongelat,
                                isSenseHumitat = routeInfo.isSenseHumitat
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
                ElevatedButton(
                    modifier = Modifier.weight(1f).padding(start= 4.dp, end = 4.dp),
                    shape = RoundedCornerShape(16.dp),
                    onClick = { routeDetailViewModel.requestRoute(routeID, 0) },
                    colors = ButtonDefaults.elevatedButtonColors(
                        containerColor = OrangeRC
                    )
                ) {
                    Icon(
                        modifier = Modifier.padding(end = 4.dp).size(24.dp),
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
            ElevatedCard(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.elevatedCardColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            ) {
                Row {
                    RouteCardHeader(route = routeInfo)
                }
                Row {
                    LazyRow(modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 12.dp),
                        content = {
                            routeInfo.etiquetes?.forEach { etiqueta ->
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
                            append(routeInfo.dataSortida)
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
                            append(routeInfo.dataArribada)
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
                    TransportOptions(route = routeInfo)
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
            }
        }
    )
}
