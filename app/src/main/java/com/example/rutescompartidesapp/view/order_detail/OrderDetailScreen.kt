package com.example.rutescompartidesapp.view.order_detail

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.rutescompartidesapp.R
import com.example.rutescompartidesapp.data.domain.orders.Orders
import com.example.rutescompartidesapp.data.domain.routes.Routes
import com.example.rutescompartidesapp.ui.theme.BlueRC
import com.example.rutescompartidesapp.ui.theme.MateBlackRC
import com.example.rutescompartidesapp.ui.theme.RedRC
import com.example.rutescompartidesapp.utils.LocalConstants
import com.example.rutescompartidesapp.view.generic_components.TopAppBarWithBackNav
import com.example.rutescompartidesapp.view.login.LoginViewModel
import com.example.rutescompartidesapp.view.order_detail.components.CompletedContainer
import com.example.rutescompartidesapp.view.order_detail.components.DetailsConfirm
import com.example.rutescompartidesapp.view.order_detail.components.TopCardInfo
import com.example.rutescompartidesapp.view.map.components.MeasuresText
import com.example.rutescompartidesapp.view.order_detail.components.TwoTexts
import com.example.rutescompartidesapp.view.publish_route.ManageRouteViewModel
import com.example.rutescompartidesapp.view.routes_order_list.components.RoutePoints

@SuppressLint("RestrictedApi")
@Composable
fun OrderDetailScreen(orderID: Int, navHost: NavHostController, loginViewModel: LoginViewModel, manageRouteViewModel: ManageRouteViewModel) {
    val orderDetailViewModel =  OrderDetailViewModel()
    orderDetailViewModel.getOrder(orderID)
    val user by loginViewModel.user.collectAsStateWithLifecycle()
    val order by orderDetailViewModel.order.collectAsStateWithLifecycle()
    val responsiveHeight = LocalConfiguration.current.screenHeightDp.dp
    val verticalScroll = rememberScrollState()

    TopAppBarWithBackNav(
        title = "Comanda #${order!!.orderID}",
        onBack = {
            println("Current back stack:\n${navHost.currentBackStack.value}")
            navHost.popBackStack() },
        content = {
            if (order != null){
                Column (modifier = Modifier.verticalScroll(verticalScroll)) {
                    CompleteCard(order!!, responsiveHeight, orderDetailViewModel,
                        manageRouteViewModel, navHost, user!!.userId)
                }
            } else {
                CircularProgressIndicator()
            }
        }
    )

}

@Composable
fun CompleteCard(
    order: Orders,
    responsiveHeight: Dp,
    orderDetailViewModel: OrderDetailViewModel,
    manageRouteViewModel: ManageRouteViewModel,
    navHost: NavHostController,
    userId: Int
) {
    val matchingRoutes by orderDetailViewModel.userMatchingRoutes.collectAsStateWithLifecycle()
    val acceptPopup by orderDetailViewModel.acceptPopup.collectAsStateWithLifecycle()
    val routeFromOrder by orderDetailViewModel.routeFromOrder.collectAsStateWithLifecycle()

    val isOrderAccepted by orderDetailViewModel.isOrderAccepted.collectAsStateWithLifecycle()
    val isOrderDelivered by orderDetailViewModel.isOrderDelivered.collectAsStateWithLifecycle()
    val isOrderPendent by orderDetailViewModel.isOrderPendent.collectAsStateWithLifecycle()
    val isOrderDeclined by orderDetailViewModel.isOrderDeclined.collectAsStateWithLifecycle()
    val isDeliveryValuated by orderDetailViewModel.isDeliveryValuated.collectAsStateWithLifecycle()

    if (routeFromOrder != null){
        manageRouteViewModel.loadOrderInfo(routeFromOrder!!)
        navHost.navigate(
            "PublishRouteScreen/{command}/{routeID}".replace(
                oldValue = "{command}",
                newValue = "createFrom"
            ).replace(
                oldValue = "{routeID}",
                newValue = "0"
            )){
            popUpTo("OrderDetailScreen/{orderID}")
        }
    }

    if (acceptPopup){
        Popup(
            offset = IntOffset(0, LocalConfiguration.current.screenHeightDp+100),
            alignment = Alignment.Center,
            onDismissRequest = { orderDetailViewModel.onPopupDissmissed() },
            properties = PopupProperties(
                focusable = true,
                dismissOnBackPress = true,
                dismissOnClickOutside=  true
            )
        ) {
            ElevatedCard (modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .fillMaxHeight(0.35f),colors = CardDefaults.elevatedCardColors(
                containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    if (matchingRoutes.isNotEmpty()) {
                        Text(
                            text = "Rutes que coincideixen amb la comanda",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Divider(
                            thickness = 2.dp,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(8.dp)
                        )
                        LazyColumn(content = {
                            matchingRoutes.forEach { route ->
                                item {
                                    MatchingRouteCard(route = route,
                                        orderDetailViewModel = orderDetailViewModel)
                                }
                            }
                        })

                    } else {
                        Text(
                            text = "No tens rutes que coincideixin amb la comanda",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Divider(
                            thickness = 2.dp,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(8.dp)
                        )
                        Column(modifier = Modifier.fillMaxHeight(),
                            verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                            ElevatedButton(onClick = {
                                orderDetailViewModel.createRouteFromOrder()
                                                     },
                                colors = ButtonDefaults.elevatedButtonColors(
                                    contentColor = Color.White,
                                    containerColor = MaterialTheme.colorScheme.primary
                                )
                            ) {
                                Text("Crear una nova ruta",
                                    style = MaterialTheme.typography.titleMedium)
                            }
                        }

                    }
                }
            }
        }
    }


    
    ElevatedCard (modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(
            contentColor = MaterialTheme.colorScheme.onBackground,
            containerColor = MaterialTheme.colorScheme.onTertiaryContainer)) {
        TopCardInfo(order = order)
        Column(
            modifier = Modifier
                .padding(start = LocalConfiguration.current.screenWidthDp.dp / 15,
                    end = LocalConfiguration.current.screenWidthDp.dp / 15
                )
        ){
            Column (
                modifier = Modifier
                    .padding(bottom = responsiveHeight / 90, top = 8.dp)
            ){
                // Etiquetes
                Row {
                    LazyRow(modifier = Modifier
                        .fillMaxWidth(),
                        content = {
                            order.etiquetes?.forEach { etiqueta ->
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
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,

                                )
                        ) {
                            append("Sortida: ")
                        }
                        append("${order.dataSortida} ${order.horaSortida}")
                    },
                        color = MaterialTheme.colorScheme.onBackground)
                }

                Row (
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,

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
                        color = MaterialTheme.colorScheme.onBackground)
                }
            }
            Column {
                DetailsConfirm( R.drawable.carrier_icon_svg, heading = "Total Paquets", value = order.packagesNum.toString(), padding = 8.dp)
                Spacer(modifier = Modifier.padding(8.dp))
                DetailsConfirm( R.drawable.dimensions_svg_icon, heading = "Dimensions total", value = "", padding = 8.dp)

            }
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                Column (
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                ){
                    MeasuresText(Icons.AutoMirrored.Filled.KeyboardArrowRight, typeOfMeasure = "Amplada",  value = "${order.packagesWidth}")
                    MeasuresText(Icons.AutoMirrored.Filled.KeyboardArrowRight, typeOfMeasure = "Altura",  value = "${order.packagesHeight}")
                }
                Column (
                    modifier = Modifier
                        .fillMaxWidth(1f)
                ){
                    MeasuresText(Icons.AutoMirrored.Filled.KeyboardArrowRight, typeOfMeasure = "Llargada",  value = "${order.packagesLength}")
                    MeasuresText(Icons.AutoMirrored.Filled.KeyboardArrowRight, typeOfMeasure = "Pes",  value = "${order.packagesWeight} kg")
                }
            }
        }

        Spacer(modifier = Modifier.height(responsiveHeight / 40))
        OrangeLine(responsiveHeight)
        Spacer(modifier = Modifier.height(responsiveHeight / 40))

        Column (
            modifier = Modifier
                .padding()
                .padding(
                    start = LocalConfiguration.current.screenWidthDp.dp / 15,
                    end = LocalConfiguration.current.screenWidthDp.dp / 15
                ),
            verticalArrangement = Arrangement.Center
        ){
            DetailsConfirm( R.drawable.co2_svg_icon, heading = "CO₂ estalviat", value = order.co2Saved.toString()+" kg", padding = 8.dp )
            DetailsConfirm( R.drawable.money_svg_icon, heading = "Valor orientatiu", value = "500 €", padding = 8.dp)
        }

        Spacer(modifier = Modifier.height(responsiveHeight / 40))
        OrangeLine(responsiveHeight)
        Spacer(modifier = Modifier.height(responsiveHeight / 40))

        if (order.userID == userId){
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
                        // Edit order button
                        ElevatedButton(
                            shape = RoundedCornerShape(16.dp),
                            onClick = { navHost.navigate(
                                "PublishOrderScreen/{command}/{orderID}".replace(
                                    oldValue = "{command}",
                                    newValue = "edit"
                                ).replace(
                                    oldValue = "{orderID}",
                                    newValue = "${order.orderID}"
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
                    // Delete order button
                    ElevatedButton(
                        shape = RoundedCornerShape(16.dp),
                        onClick = {
                            orderDetailViewModel.deleteOrder(order.orderID, navHost)
                        },
                        colors = ButtonDefaults.elevatedButtonColors(
                            containerColor = RedRC
                        )
                    ) {
                        Icon(
                            modifier = Modifier.size(28.dp),
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "Delete order icon",
                            tint = Color.White
                        )
                    }
                }
            }
            // Si la comanda ha estat entregada, l'usuari pot fer review del conductor
            if (isOrderDelivered && !isDeliveryValuated){
                Card (modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
                    .clickable {
                        val routeInteraction =
                            LocalConstants.interactionList.firstOrNull { it.orderID == order.orderID }
                        if (routeInteraction != null)
                            navHost.navigate(
                                "ValueExperienceGeneralScreen/{routeId}/{orderId}"
                                    .replace("{routeId}", routeInteraction.routeID.toString())
                                    .replace("{orderId}", routeInteraction.orderID.toString())
                            )
                    },
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primary),
                    shape = RoundedCornerShape(8.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
                ){
                    TwoTexts("Han entregat la comanda, valora la teva", "experiència")
                }
            }
        } else {
            if (!isOrderDelivered && !isDeliveryValuated ){
                if (isOrderDeclined){
                    Card (modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primary),
                        shape = RoundedCornerShape(8.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
                    ){
                        TwoTexts("Has declinat la petició de transport d'aquesta", "comanda")
                    }
                } else {
                    if (!isOrderAccepted) {
                        Card (
                            modifier = Modifier
                                .padding(12.dp)
                                .fillMaxWidth(),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.primary),
                            shape = RoundedCornerShape(8.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
                        ) {
                            TwoTexts("Transportar aquesta comanda amb la teva", "ruta")
                        }
                        // Accept button
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                            horizontalArrangement = Arrangement.Center) {
                            ExtendedFloatingActionButton(
                                containerColor = MaterialTheme.colorScheme.tertiary,
                                onClick = {
                                    orderDetailViewModel.filterMatchingRoutes(userId)
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.ThumbUp,
                                    contentDescription = "Accept order",
                                    tint = Color.White
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(
                                    text = "Acceptar",
                                    color = Color.White
                                )
                            }
                        }
                    } else {
                        Card (
                            modifier = Modifier
                                .padding(12.dp)
                                .fillMaxWidth(),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.primary),
                            shape = RoundedCornerShape(8.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
                        ) {
                            TwoTexts("Has acceptat transportar la comanda amb la teva", "ruta")
                        }
                    }
                } // End if !isAccepted
            } // End if !isDelivered or !isDeliveryValuated
            if (isOrderPendent){
                val pendentRouteID by orderDetailViewModel.pendentRouteID.collectAsStateWithLifecycle()
                // Decline button
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                    horizontalArrangement = Arrangement.Center) {
                    ExtendedFloatingActionButton(
                        containerColor = MateBlackRC,
                        contentColor = Color.White,
                        onClick = { orderDetailViewModel.declineOrder(routeID = pendentRouteID) }
                    ) {
                        Icon(imageVector = Icons.Filled.ThumbDown, contentDescription = "Decline order")
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(text = "Rebutjar")
                    }
                }

            }
        }

    if (isOrderDelivered || isDeliveryValuated){
        CompletedContainer()
    }
    Spacer(modifier = Modifier.height(responsiveHeight / 40))
    }
}

@Composable
fun OrangeLine(responsiveHeight: Dp) {
    Divider(
        color = MaterialTheme.colorScheme.primary,
        thickness = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = responsiveHeight / 90, end = responsiveHeight / 90)
    )
}

@Composable
fun MatchingRouteCard(route: Routes, orderDetailViewModel: OrderDetailViewModel){
    val context = LocalContext.current
    ElevatedCard (modifier = Modifier
        .fillMaxWidth()
        .clickable {
            orderDetailViewModel.acceptOrder(route.routeID, route.userID)
            Toast
                .makeText(context, "Has acceptat la comanda", Toast.LENGTH_SHORT)
                .show()
        },
        colors = CardDefaults.elevatedCardColors(containerColor = MateBlackRC)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Row (modifier = Modifier.weight(2f),horizontalArrangement = Arrangement.Start) {
                    Text(text = route.routeName, color = Color.White)
                }
            }
            RoutePoints(route = route)
        }
    }
}