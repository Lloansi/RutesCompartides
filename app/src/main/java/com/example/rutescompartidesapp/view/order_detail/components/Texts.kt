package com.example.rutescompartidesapp.view.order_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TwoTexts(value1: String, value2: String) {
    FlowRow (
        modifier = Modifier
            .padding(top = 8.dp, bottom = 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center){
        Text( value1,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Normal,
            ),
            color = Color.White
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text( value2,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            color = Color.White

        )
    }
}

@Composable
fun DetailsConfirm(icon : Int, heading: String, value : String, padding : Dp = 0.dp, color : Color = MaterialTheme.colorScheme.primary) {
    Row (
        verticalAlignment = Alignment.CenterVertically
    ){
        Icon(
            painter = painterResource(id = icon),
            tint = color,
            contentDescription = null,
            modifier = Modifier
                .padding()
                .padding(end = padding)
        )

        Text(text = "$heading: ",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold
            )
        )

        Text(text = value)
    }
}

@Composable
fun IconWithText(icon: Int, value: String) {
    Row (
        modifier = Modifier
            .padding(top = 8.dp, bottom = 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        Icon(painter = painterResource(icon),
            tint = MaterialTheme.colorScheme.onBackground,
            contentDescription = null, modifier = Modifier
            .height(LocalConfiguration.current.screenHeightDp.dp / 25)
            .width(LocalConfiguration.current.screenWidthDp.dp / 10)
        )
        Spacer(modifier = Modifier.width(LocalConfiguration.current.screenWidthDp.dp/75))
        Text(text = value, fontSize = 14.sp,
            color = Color.White)
    }
}