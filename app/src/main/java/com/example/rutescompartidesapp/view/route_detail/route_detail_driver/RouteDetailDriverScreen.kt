package com.example.rutescompartidesapp.view.route_detail.route_detail_driver

import android.annotation.SuppressLint
import android.widget.Toast
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.rutescompartidesapp.R
import com.example.rutescompartidesapp.data.domain.routes.Routes
import com.example.rutescompartidesapp.ui.theme.BlueRC
import com.example.rutescompartidesapp.ui.theme.MateBlackRC
import com.example.rutescompartidesapp.ui.theme.OrangeRC
import com.example.rutescompartidesapp.ui.theme.RedRC
import com.example.rutescompartidesapp.view.confirm_delivery.ConfirmScreen
import com.example.rutescompartidesapp.view.confirm_delivery.components.camera.CameraScreen
import com.example.rutescompartidesapp.view.confirm_delivery.components.draw.DrawScreen
import com.example.rutescompartidesapp.view.confirm_delivery.viewmodel.CameraViewModel
import com.example.rutescompartidesapp.view.confirm_delivery.viewmodel.DrawViewModel
import com.example.rutescompartidesapp.view.generic_components.TopAppBarWithBackNav
import com.example.rutescompartidesapp.view.route_detail.route_detail_driver.components.RouteInteractionCard
import com.example.rutescompartidesapp.view.routes_order_list.components.RouteCardHeader
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "RestrictedApi")
@Composable
fun RouteDetailDriverScreen(
    routeID: Int, navHost: NavHostController,
    routeDetailDriverViewModel : RouteDetailDriverViewModel,
    cameraViewModel: CameraViewModel, drawViewModel: DrawViewModel) {

    // TODO Amb el ID hauría de fer una trucada a la API per obtenir la ruta
    // TODO i una altra per obtenir les interaccions de la ruta

    routeDetailDriverViewModel.getRoute(routeID)
    val route by routeDetailDriverViewModel.route.collectAsStateWithLifecycle()
    val order by routeDetailDriverViewModel.order.collectAsStateWithLifecycle()
    val interactions = routeDetailDriverViewModel.interactions
    val isCompleteScreenShowing by routeDetailDriverViewModel.isCompleteScreenShowing.collectAsStateWithLifecycle()
    val routeInteractionToConfirm by routeDetailDriverViewModel.routeInteractionToConfirm.collectAsStateWithLifecycle()

    val isCameraActive by cameraViewModel.isCameraActive.collectAsStateWithLifecycle()
    val isSignatureActive by drawViewModel.isSignatureActive.collectAsStateWithLifecycle()

    val interactionToastText by routeDetailDriverViewModel.interactionToastText.collectAsStateWithLifecycle()
    val isInteractionPopupShowing by routeDetailDriverViewModel.isInteractionPopupShowing.collectAsStateWithLifecycle()
    val context = LocalContext.current

    // Toast per notificar a l'usuari l'event de la interacció
    LaunchedEffect(key1 = routeDetailDriverViewModel.showInteractionToastChannel){
        routeDetailDriverViewModel.showInteractionToastChannel.collectLatest { interactionToast ->
            if (interactionToast){
                Toast.makeText(context, interactionToastText, Toast.LENGTH_SHORT).show()
            }
        }
    }

    if (isCameraActive) {
        CameraScreen(cameraViewModel = cameraViewModel)
    } else if (isSignatureActive) {
        DrawScreen(drawViewModel = drawViewModel)
    } else {

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
                    println("Current back stack:\n${navHost.currentBackStack.value}")
                    navHost.popBackStack()
                }
            },
            content = {
                if (route != null) {

                    Column(modifier = Modifier
                        //.verticalScroll(verticalScroll)
                    ) {
                        if (isCompleteScreenShowing) {
                            ConfirmScreen(
                                routeDetailDriverViewModel = routeDetailDriverViewModel,
                                cameraViewModel = cameraViewModel,
                                drawViewModel = drawViewModel
                            )
                        } else {
                            ElevatedCard(
                                modifier = Modifier.fillMaxWidth(),
                                colors = CardDefaults.elevatedCardColors(
                                    containerColor = MaterialTheme.colorScheme.onTertiaryContainer
                                )
                            ) {
                                Row {
                                    RouteCardHeader(route = route!!)
                                }
                                // Etiquetes
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
                                        .padding(
                                            start = 8.dp,
                                            end = 8.dp,
                                            top = 4.dp,
                                            bottom = 4.dp
                                        ),
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
                                        data = route!!.availableSeats.toString()
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
                                        data = route!!.maxDetourKm.toString() + " km"
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
                                        data = route!!.availableSpace.toString()
                                    )
                                }
                                Divider(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(
                                            start = 8.dp,
                                            end = 8.dp,
                                            top = 4.dp,
                                            bottom = 4.dp
                                        ),
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
                                        data = route!!.vehicle.toString()
                                    )
                                }
                                Divider(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(
                                            start = 8.dp,
                                            end = 8.dp,
                                            top = 4.dp,
                                            bottom = 4.dp
                                        ),
                                    color = OrangeRC,
                                    thickness = 2.dp
                                )
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    ElevatedButton(
                                        shape = RoundedCornerShape(16.dp),
                                        onClick = {
                                            routeDetailDriverViewModel.showInteractionPopup(true)
                                        },
                                        colors = ButtonDefaults.elevatedButtonColors(
                                            containerColor = OrangeRC
                                        )
                                    ) {
                                        Icon(
                                            modifier = Modifier.size(28.dp),
                                            painter = painterResource(id = R.drawable.handshake_icon),
                                            contentDescription = "Interaction route icon",
                                            tint = Color.White
                                        )
                                        Text(text = "Interaccions", color = Color.White)

                                    }
                                }

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
                                                onClick = {
                                                    navHost.navigate(
                                                        "PublishRouteScreen/{command}/{routeID}".replace(
                                                            oldValue = "{command}",
                                                            newValue = "edit"
                                                        ).replace(
                                                            oldValue = "{routeID}",
                                                            newValue = "$routeID"
                                                        )
                                                    )
                                                },
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
                                                onClick = {
                                                    routeDetailDriverViewModel.duplicateRoute(
                                                        route!!
                                                    )
                                                },
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
                                            onClick = {
                                                routeDetailDriverViewModel.deleteRoute(route!!)
                                                navHost.popBackStack()
                                            },
                                            colors = ButtonDefaults.elevatedButtonColors(
                                                containerColor = RedRC
                                            )
                                        ) {
                                            Icon(
                                                modifier = Modifier.size(28.dp),
                                                imageVector = Icons.Filled.Delete,
                                                contentDescription = "Delete route icon",
                                                tint = Color.White
                                            )
                                        }
                                    }
                                }
                            }
                            // Interaccions
                            if (isInteractionPopupShowing) {
                                AlertDialog(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(LocalConfiguration.current.screenHeightDp.dp*0.8f),
                                    onDismissRequest = { routeDetailDriverViewModel.showInteractionPopup(false) },
                                    properties = DialogProperties(
                                        dismissOnBackPress = true,
                                        dismissOnClickOutside=  true
                                    )
                                ) {
                                    ElevatedCard (modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp, bottom = 12.dp),
                                        colors = CardDefaults.elevatedCardColors(
                                        containerColor = MaterialTheme.colorScheme.surface)
                                    ){
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(12.dp),
                                            horizontalArrangement = Arrangement.Center
                                        ) {
                                            Text(
                                                text = "Interaccions", color = MaterialTheme.colorScheme.primary,
                                                style = MaterialTheme.typography.titleLarge,
                                            )
                                        }
                                        Divider(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(start = 8.dp, end = 8.dp),
                                            color = OrangeRC,
                                            thickness = 2.dp
                                        )
                                        LazyColumn(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(8.dp),
                                            verticalArrangement = Arrangement.Center,
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            items(interactions.size) { index ->
                                                Spacer(modifier = Modifier.padding(8.dp))
                                                RouteInteractionCard(
                                                    interaction = interactions[index],
                                                    index = index,
                                                    routeDetailDriverViewModel = routeDetailDriverViewModel,
                                                    navHost = navHost
                                                )
                                            }
                                        }
                                        Spacer(modifier = Modifier.padding(8.dp))
                                    }

                                }
                            }
                        }
                    }
                } else {
                    CircularProgressIndicator()
                }
            })
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
fun TransportOptions(route: Routes) {
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