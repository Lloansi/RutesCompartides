package com.example.rutescompartidesapp.view.complete

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Backpack
import androidx.compose.material.icons.filled.EnergySavingsLeaf
import androidx.compose.material.icons.filled.PanoramaWideAngle
import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.rutescompartidesapp.data.domain.Order
import com.example.rutescompartidesapp.view.complete.components.TopCardInfo
import com.example.rutescompartidesapp.view.map.components.MeasuresText


@Composable
fun ConfirmScreen(order: Order) {
    val responsiveHeight = LocalConfiguration.current.screenHeightDp.dp
    val mateBlack = Color(0xFF282826)

    Card {
        TopCardInfo(mateBlack = mateBlack, order = order)
        Spacer(modifier = Modifier.height(responsiveHeight/50))
        Column (
            modifier = Modifier
                .padding()
                .padding(
                    start = LocalConfiguration.current.screenWidthDp.dp / 15,
                    end = LocalConfiguration.current.screenWidthDp.dp / 15
                )
        ){
            Column {
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
                DetailsConfirm(Icons.Filled.PanoramaWideAngle, heading = "Dimensions total", value = "", padding = 8.dp)

            }
            /*
            Spacer(modifier = Modifier.height(responsiveHeight/60))
            DetailsConfirm(Icons.Filled.Backpack, heading = "Total Paquets", value = order.packageQuantity.toString(), padding = 8.dp)

            Spacer(modifier = Modifier.height(responsiveHeight/60))
            DetailsConfirm(Icons.Filled.PanoramaWideAngle, heading = "Dimensions total", value = "", padding = 8.dp)


             */
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ){
                    Column (
                        modifier = Modifier
                            .fillMaxWidth(0.45f)
                    ){
                        MeasuresText(Icons.AutoMirrored.Filled.KeyboardArrowRight, typeOfMeasure = "Amplada",  value = "${order.packageWidth}")
                        MeasuresText(Icons.AutoMirrored.Filled.KeyboardArrowRight, typeOfMeasure = "Altura",  value = "${order.packageHeight}")
                    }
                    Column (
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
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

        Card (
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            )
        ){
            TwoTexts("Transportar aquesta comanda amb la teva", "ruta")
        }

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding()
                .padding(top = (LocalConfiguration.current.screenHeightDp.dp) / 90, bottom = 20.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            ExtendedFloatingActionButton(
                containerColor = MaterialTheme.colorScheme.tertiary,
                onClick = { /*TODO*/ }
            ) {
                Icon(imageVector = Icons.Filled.ThumbUp, contentDescription = "Accept order")
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = "Acceptar")
            }

            ExtendedFloatingActionButton(
                containerColor = mateBlack,
                contentColor = Color.White,
                onClick = { /*TODO*/ }
            ) {
                Icon(imageVector = Icons.Filled.ThumbDown, contentDescription = "Accept order")
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = "Rebutjar")
            }
        }

    }
}

@Composable
fun DetailsConfirm(icon : ImageVector, heading: String, value : String, padding : Dp? = 0.dp, color : Color = MaterialTheme.colorScheme.primary) {
    Row (
        verticalAlignment = Alignment.CenterVertically
    ){
        var pad = 0.dp
        padding?.let{pad = it}
        Icon(
            imageVector = icon,
            tint = color,
            contentDescription = null,
            modifier = Modifier
                .padding()
                .padding(end = pad)
        )

        Text(text = "$heading: ",
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )

        Text(text = value)
    }
}

@Composable
fun TwoTexts(value1: String, value2: String) {
    Row (
        modifier = Modifier
            .padding(top = 8.dp, bottom = 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ){
        Text( value1,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Normal
            )
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text( value2,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            color = Color.Black
        )
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