package com.example.rutescompartidesapp.view.complete

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Backpack
import androidx.compose.material.icons.filled.EnergySavingsLeaf
import androidx.compose.material.icons.filled.PanoramaWideAngle
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rutescompartidesapp.data.domain.Order
import com.example.rutescompartidesapp.view.complete.components.AcceptDenyContainer
import com.example.rutescompartidesapp.view.complete.components.CompletedContainer
import com.example.rutescompartidesapp.view.complete.components.DetailsConfirm
import com.example.rutescompartidesapp.view.complete.components.TopCardInfo
import com.example.rutescompartidesapp.view.map.components.MeasuresText

@Composable
fun CompleteScreen(order: Order) {
    val completeViewModel: CompleteViewModel = hiltViewModel()
    val isVisible by completeViewModel.isVisible.collectAsState()

    val responsiveHeight = LocalConfiguration.current.screenHeightDp.dp
    val responsiveWidth = LocalConfiguration.current.screenWidthDp.dp
    Surface(
        modifier = Modifier.padding(top = responsiveHeight/40, bottom = responsiveHeight/40, start =  responsiveWidth/20, end = responsiveWidth/20),
        //color = Color.White
    ) {
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
        Spacer(modifier = Modifier.height(responsiveHeight/50))
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