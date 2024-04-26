package com.example.rutescompartidesapp.view.order_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.rutescompartidesapp.R
import com.example.rutescompartidesapp.data.domain.OrderForList
import com.example.rutescompartidesapp.ui.theme.BlueRC
import com.example.rutescompartidesapp.ui.theme.MateBlackRC
import com.example.rutescompartidesapp.ui.theme.RedRC
import com.example.rutescompartidesapp.view.generic_components.TopAppBarWithBackNav
import com.example.rutescompartidesapp.view.login.LoginViewModel
import com.example.rutescompartidesapp.view.order_detail.components.AcceptDenyContainer
import com.example.rutescompartidesapp.view.order_detail.components.CompletedContainer
import com.example.rutescompartidesapp.view.order_detail.components.DetailsConfirm
import com.example.rutescompartidesapp.view.order_detail.components.TopCardInfo
import com.example.rutescompartidesapp.view.map.components.MeasuresText

@Composable
fun OrderDetailScreen(orderID: Int, navHost: NavHostController, loginViewModel: LoginViewModel ) {
    val orderDetailViewModel =  OrderDetailViewModel()
    orderDetailViewModel.getOrder(orderID)
    val user by loginViewModel.user.collectAsStateWithLifecycle()
    val order by orderDetailViewModel.order.collectAsStateWithLifecycle()
    val isVisible by orderDetailViewModel.isVisible.collectAsStateWithLifecycle()
    val responsiveHeight = LocalConfiguration.current.screenHeightDp.dp
    val verticalScroll = rememberScrollState()

    TopAppBarWithBackNav(
        title = "Comanda #${order!!.orderID}",
        onBack = { navHost.popBackStack() },
        content = {
            if (order != null){
                Column (modifier = Modifier.verticalScroll(verticalScroll)) {
                    CompleteCard(order!!, responsiveHeight, isVisible, orderDetailViewModel, navHost, user!!.userId)
                }
            } else {
                CircularProgressIndicator()
            }
        }
    )

}

@Composable
fun CompleteCard(
    order: OrderForList,
    responsiveHeight: Dp,
    isVisible: Boolean,
    orderDetailViewModel: OrderDetailViewModel,
    navHost: NavHostController,
    userId: Int
) {
    ElevatedCard (modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(
            contentColor = MaterialTheme.colorScheme.onBackground,
            containerColor = MaterialTheme.colorScheme.background)) {
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
                            append("Min Sortida: ")
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
                            append("Min Arribada: ")
                        }
                        append(order.dataSortida+" 15:40")
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


        // TODO chequear si el userID del usuario actual es igual al del pedido,
        // si es así, mostrar los botones de editar y eliminar
        // sino, mostrar los botones de aceptar y rechazar
        // Botones de editar y eliminar
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

                    ElevatedButton(
                        shape = RoundedCornerShape(16.dp),
                        onClick = { navHost.popBackStack()
                            orderDetailViewModel.deleteOrder(order.orderID)
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
        } else {
            AcceptDenyContainer(colorDenyButton = MateBlackRC, isVisible, orderDetailViewModel)
        }

        CompletedContainer(isVisible)

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