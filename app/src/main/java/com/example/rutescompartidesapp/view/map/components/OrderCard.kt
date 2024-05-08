package com.example.rutescompartidesapp.view.map.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.rutescompartidesapp.R
import com.example.rutescompartidesapp.data.domain.Order
import com.example.rutescompartidesapp.data.domain.orders.Orders
import com.example.rutescompartidesapp.view.map.fredokaOneFamily
import com.example.rutescompartidesapp.view.map.openSansFamily

val allOrders = listOf(
    Order(1,
        3,
        false,
        40.5f,
        256.5f,
        356.5f,
        2.5f,
        "11-04-2024",
        "13-04-2024",
        "Mataró",
        "Sabadell",
        41.487125f,
        2.181916f
    ),
    Order(2,
        2,
        true,
        46.3f,
        389.2f,
        189.3f,
        2.7f,
        "11-04-2024",
        "13-04-2024",
        "Mataró",
        "Sabadell",
        41.513485f,
        2.181916f
    ),
    Order(3,
        2,
        true,
        49.3f,
        324.2f,
        157.3f,
        2.7f,
        "11-04-2024",
        "13-04-2024",
        "Mataró",
        "Sabadell",
        41.453426f,
        2.187916f
    )
)

@Composable
fun ComandaCard(comanda: Orders, navController: NavHostController) {
    Card(
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable { navController.navigate("OrderDetailScreen/${comanda.orderID}") },
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, end = 10.dp)
        ){
            Column(
                modifier = Modifier
                    .padding(start = 15.dp, end = 15.dp, bottom = 10.dp)
                    .wrapContentWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                // Package number
                Text(
                    text = "Comanda nº${comanda.orderID}",
                    style = MaterialTheme.typography.titleMedium,
                    fontFamily = fredokaOneFamily,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                // SVG Comanda
                Image(
                    painter = painterResource(id = R.drawable.comanda_svg),
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .padding(start = 4.dp)
                        .padding(top = 5.dp, bottom = 5.dp)
                )
                Row {
                    // Icon date
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Date icon",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .padding(top = 7.dp, end = 2.dp)
                            .offset(x = (-2).dp)
                    )
                    Column {
                        Text(
                            text = comanda.dataArribada,
                            style = MaterialTheme.typography.bodyMedium,
                            fontFamily = fredokaOneFamily,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = comanda.dataSortida,
                            style = MaterialTheme.typography.bodyMedium,
                            fontFamily = fredokaOneFamily,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .padding(end = 10.dp)
                    .wrapContentWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Profile details
                Text(
                    text = "${comanda.puntSortida} - ${comanda.puntArribada}",
                    style = MaterialTheme.typography.titleMedium,
                    fontFamily = fredokaOneFamily,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.height(4.dp))
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    //Important package information
                    DetailsCard(idImage = R.drawable.packages_svg, value = "${comanda.packagesNum}", imageSize = 35.dp, paddingPercentage = 4)
                    DetailsCard(idImage = R.drawable.weight_svg, value = "${comanda.packagesHeight}kg", imageSize = 27.dp, paddingPercentage = 1)
                }
                Column (horizontalAlignment = Alignment.Start){
                    // Measures information
                    MeasuresText(icon = Icons.AutoMirrored.Filled.KeyboardArrowRight, typeOfMeasure = "Amplada", value = "${comanda.packagesWidth}")
                    MeasuresText(icon = Icons.AutoMirrored.Filled.KeyboardArrowRight, typeOfMeasure = "Longitud", value = "${comanda.packagesLength}")
                    MeasuresText(icon = Icons.AutoMirrored.Filled.KeyboardArrowRight, typeOfMeasure = "Altura", value = "${comanda.packagesHeight}")
                }
            }
        }
    }
}

@Composable
fun MeasuresText(icon: ImageVector, typeOfMeasure:String, value: String) {
    Row(
        modifier = Modifier
            .wrapContentWidth()
            .padding(vertical = 2.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            modifier = Modifier
                .size(20.dp),
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onBackground

        )
        Spacer(modifier = Modifier.width(2.dp))
        Text(
            text = "${typeOfMeasure}:",
            style = MaterialTheme.typography.bodySmall,
            fontFamily = openSansFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(3.dp))
        Text(
            text = "${value}cm",
            style = MaterialTheme.typography.bodySmall,
            fontFamily = openSansFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp
        )
    }
}