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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.rutescompartidesapp.R
import com.example.rutescompartidesapp.data.domain.routes.Routes
import com.example.rutescompartidesapp.view.login.LoginViewModel
import com.example.rutescompartidesapp.view.map.fredokaOneFamily
import com.example.rutescompartidesapp.view.map.openSansFamily

@Composable
fun RouteCard(ruta : Routes, navController: NavHostController, loginViewModel: LoginViewModel) {
    val user by loginViewModel.user.collectAsState()

    Card(
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            //.background(color = Color.White)
            .clickable {
                navController.navigate(
                    if (user?.userId == ruta.routeID){
                        "RouteDetailDriverScreen/${ruta.routeID}"
                    }else {
                        "RouteDetailGeneralScreen/${ruta.routeID}"
                    }
                )
            },
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, end = 10.dp)
        ){
            Column(
                modifier = Modifier
                    .padding(start = 15.dp, end = 7.dp, bottom = 10.dp)
                    .wrapContentWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                // Package number
                Text(
                    text = "Ruta nº${ruta.routeID}",
                    style = MaterialTheme.typography.titleMedium,
                    fontFamily = fredokaOneFamily,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                // SVG Comanda
                Image(
                    painter = painterResource(id = R.drawable.route_svg),
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .padding(start = 4.dp)
                        .padding(top = 5.dp, bottom = 5.dp)
                )
                Row {
                    // Icon van
                    Icon(
                        painter = painterResource(id = R.drawable.van_svg),
                        contentDescription = "Date icon",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .size(28.dp)
                            .padding(end = 2.dp)
                            .offset(x = (-2).dp)
                    )
                    Text(
                        text = ruta.vehicle ?: "No Assignat",
                        style = MaterialTheme.typography.bodyMedium,
                        fontFamily = fredokaOneFamily,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                        .padding(top = 4.dp)
                    )
                }
            }
            Column(
                modifier = Modifier
                    .padding(end = 10.dp)
                    .wrapContentWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                // Route start & end point
                Text(
                    text = "${ruta.puntSortida} - ${ruta.puntArribada}",
                    style = MaterialTheme.typography.titleMedium,
                    fontFamily = fredokaOneFamily,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(10.dp))



                // Route information
                DetailsCard(idImage = R.drawable.seat_svg, value = "${ruta.availableSeats}", imageSize = 28.dp, paddingPercentage = 2)
                PricePerKM(idImage1 = R.drawable.eur_svg, idImage2 = R.drawable.km_svg, value = "${ruta.costKm}€")
                DetailsCard(idImage = R.drawable.van_measures, value = ruta.availableSpace ?: "No assignat", imageSize = 35.dp, fontSize = 13.sp, paddingPercentage = 5)

                val percentagePaddingDesviament = 5
                val paddingDesviament = (LocalDensity.current.density * percentagePaddingDesviament).dp

                val annotatedString = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontSize = 14.sp, fontWeight = FontWeight.SemiBold)) {
                        append("Desviament de ")
                    }
                    withStyle(style = SpanStyle(fontSize = 17.sp, fontWeight = FontWeight.ExtraBold, color = MaterialTheme.colorScheme.primary)) {
                        append("${ruta.maxDetourKm}km")
                    }
                    withStyle(style = SpanStyle(fontSize = 14.sp, fontWeight = FontWeight.SemiBold)) {
                        append(" màx")
                    }
                }

                Text(
                    text = annotatedString,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = openSansFamily,
                    modifier = Modifier
                        .padding()
                        .padding(start = paddingDesviament)
                )

            }
        }
    }
}

@Composable
fun DetailsCard(idImage: Int,value: String, imageSize: Dp, fontSize: TextUnit = 16.sp, paddingPercentage: Int){
    val padding = (LocalDensity.current.density * paddingPercentage).dp
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding()
            .padding(start = padding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ){
        Image(
            painter = painterResource(id = idImage),
            contentDescription = null,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .size(imageSize)
                .padding(end = 6.dp)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            fontFamily = openSansFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = fontSize,
        )
    }
}

@Composable
fun PricePerKM(idImage1: Int, idImage2: Int, value : String, fontSize : TextUnit = 16.sp){
    Row (
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ){
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            fontFamily = openSansFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = fontSize,
            modifier = Modifier
                .padding()
                .padding(end = 5.dp)
        )
        Image(
            painter = painterResource(id = idImage1),
            contentDescription = null,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .size(14.dp)
                .padding(end = 1.dp)
        )
        Text(
            text = "/",
            style = MaterialTheme.typography.bodyMedium,
            fontFamily = openSansFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
        )
        Image(
            painter = painterResource(id = idImage2),
            contentDescription = null,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .size(22.dp)
                .padding(start = 1.dp)
        )

    }
}
