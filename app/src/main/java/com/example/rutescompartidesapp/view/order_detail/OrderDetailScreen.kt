package com.example.rutescompartidesapp.view.order_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.rutescompartidesapp.R
import com.example.rutescompartidesapp.data.domain.Order
import com.example.rutescompartidesapp.view.generic_components.TopAppBarWithBackNav
import com.example.rutescompartidesapp.view.order_detail.components.AcceptDenyContainer
import com.example.rutescompartidesapp.view.order_detail.components.CompletedContainer
import com.example.rutescompartidesapp.view.order_detail.components.DetailsConfirm
import com.example.rutescompartidesapp.view.order_detail.components.TopCardInfo
import com.example.rutescompartidesapp.view.map.components.MeasuresText
import com.example.rutescompartidesapp.view.map.components.allOrders

@Composable
fun OrderDetailScreen(packageId: Int, navHost: NavHostController) {
    val order: Order = allOrders.first { it.packageId == packageId }

    val completeViewModel: CompleteViewModel = hiltViewModel()
    val isVisible by completeViewModel.isVisible.collectAsState()
    val responsiveHeight = LocalConfiguration.current.screenHeightDp.dp

    TopAppBarWithBackNav(
        title = "Comanda #${order.packageId}",
        onBack = { navHost.popBackStack() },
        content = {
            CompleteCard(order, responsiveHeight, isVisible, completeViewModel)
        }
    )

}

@Composable
fun CompleteCard(order: Order, responsiveHeight: Dp, isVisible: Boolean, completeViewModel: CompleteViewModel) {
    val mateBlack = Color(0xFF282826)
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
                        append(order.packageStartingDate+" 14:55")
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
                        append(order.packageEndDate+" 15:40")
                    },
                        color = MaterialTheme.colorScheme.onBackground)
                }
            }
            Column {
                DetailsConfirm( R.drawable.carrier_icon_svg, heading = "Total Paquets", value = order.packageQuantity.toString(), padding = 8.dp)
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
                        MeasuresText(Icons.AutoMirrored.Filled.KeyboardArrowRight, typeOfMeasure = "Amplada",  value = "${order.packageWidth}")
                        MeasuresText(Icons.AutoMirrored.Filled.KeyboardArrowRight, typeOfMeasure = "Altura",  value = "${order.packageHeight}")
                    }
                    Column (
                        modifier = Modifier
                            .fillMaxWidth(1f)
                    ){
                        MeasuresText(Icons.AutoMirrored.Filled.KeyboardArrowRight, typeOfMeasure = "Llargada",  value = "${order.packageLongitude}")
                        MeasuresText(Icons.AutoMirrored.Filled.KeyboardArrowRight, typeOfMeasure = "Pes",  value = "${order.packageWeight}")
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
            DetailsConfirm( R.drawable.co2_svg_icon, heading = "CO₂ estalviat", value = "4.0 kg", padding = 8.dp )
            DetailsConfirm( R.drawable.money_svg_icon, heading = "Valor orientatiu", value = "500 €", padding = 8.dp)
        }

        Spacer(modifier = Modifier.height(responsiveHeight / 40))
        OrangeLine(responsiveHeight)
        Spacer(modifier = Modifier.height(responsiveHeight / 40))

        AcceptDenyContainer(colorDenyButton = mateBlack, isVisible, completeViewModel)

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