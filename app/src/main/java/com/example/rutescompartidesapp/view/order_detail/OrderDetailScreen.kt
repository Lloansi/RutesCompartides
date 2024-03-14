package com.example.rutescompartidesapp.view.order_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Backpack
import androidx.compose.material.icons.filled.EnergySavingsLeaf
import androidx.compose.material.icons.filled.PanoramaWideAngle
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.rutescompartidesapp.data.domain.Order
import com.example.rutescompartidesapp.data.domain.RouteForList
import com.example.rutescompartidesapp.view.order_detail.components.AcceptDenyContainer
import com.example.rutescompartidesapp.view.order_detail.components.CompletedContainer
import com.example.rutescompartidesapp.view.order_detail.components.DetailsConfirm
import com.example.rutescompartidesapp.view.order_detail.components.TopCardInfo
import com.example.rutescompartidesapp.view.map.components.MeasuresText
import com.example.rutescompartidesapp.view.map.components.allOrders
import com.example.rutescompartidesapp.view.routes_order_list.ListConstants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderDetailScreen(packageId: Int, navHost: NavHostController) {
    val order: Order = allOrders.first { it.packageId == packageId }

    val completeViewModel: CompleteViewModel = hiltViewModel()
    val isVisible by completeViewModel.isVisible.collectAsState()

    val responsiveHeight = LocalConfiguration.current.screenHeightDp.dp
    val responsiveWidth = LocalConfiguration.current.screenWidthDp.dp
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    Scaffold( modifier = Modifier
        .fillMaxSize()
        .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = { Text(text = "Comanda #${order.packageId}") },
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
            .padding(
                top = paddingValues.calculateTopPadding() + 8.dp,
                bottom = paddingValues.calculateBottomPadding() + 8.dp,
                start = 8.dp,
                end = 8.dp
            ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
        }
        CompleteCard(order, responsiveHeight, isVisible, completeViewModel)

    }

}

@Composable
fun CompleteCard(order: Order, responsiveHeight: Dp, isVisible: Boolean, completeViewModel: CompleteViewModel) {
    val mateBlack = Color(0xFF282826)
    Card (
        modifier = Modifier
            .fillMaxWidth(),
    ){
        TopCardInfo(mateBlack = mateBlack, order = order)
        Column (
            modifier = Modifier
                .padding()
                .padding(
                    start = LocalConfiguration.current.screenWidthDp.dp / 15,
                    end = LocalConfiguration.current.screenWidthDp.dp / 15
                )
        ){
            Column (
                modifier = Modifier
                    .padding()
                    .padding(bottom = responsiveHeight / 90)
            ){
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(text = "Min.Sortida:",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(text = order.packageStartingDate)
                    Text(text = "14:55")
                }

                Row (
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(text = "Min.Arribada:",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(text = order.packageEndDate)
                    Text(text = "15:40")
                }
            }
            Column {
                DetailsConfirm(Icons.Filled.Backpack, heading = "Total Paquets", value = order.packageQuantity.toString(), padding = 8.dp)
                Spacer(modifier = Modifier.height(responsiveHeight/90))
                DetailsConfirm(Icons.Filled.PanoramaWideAngle, heading = "Dimensions total", value = "", padding = 8.dp)

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
            DetailsConfirm(Icons.Filled.EnergySavingsLeaf, heading = "CO₂ estalviat", value = "4.0 kg", padding = 8.dp )
            DetailsConfirm(Icons.Filled.AttachMoney, heading = "Valor orientatiu", value = "500 €", padding = 8.dp)
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