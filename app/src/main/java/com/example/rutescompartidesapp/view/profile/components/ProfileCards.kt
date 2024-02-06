package com.example.rutescompartidesapp.view.profile.components

import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rutescompartidesapp.data.domain.ProfileItems

@Composable
fun CreateCardsWithItems(list: List<ProfileItems>, paddingBottom: Dp) {
    Card(
        modifier = Modifier
            .width(LocalConfiguration.current.screenWidthDp.dp - 50.dp)
            .wrapContentHeight()
            .padding(top = 20.dp, bottom = paddingBottom),
        shape = RoundedCornerShape(25.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        for (i in list.indices) {
            TextButton(
                shape = RoundedCornerShape(25.dp),
                onClick = {

                }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier
                            .padding(start = 5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(imageVector = list[i].icon, contentDescription = null)
                        Column(
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .padding(start = 10.dp),
                        ) {
                            var titleColor = Color(0xFF000000)
                            if (list[i].title == "Tanca la sessió") {
                                titleColor = Color(0xFFFD0000)
                            }
                            Text(
                                text = list[i].title,
                                color = titleColor,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 18.sp
                            )
                            Text(
                                text = list[i].subtitle,
                                color = Color(0xFF292929),
                                fontSize = 14.sp
                            )
                        }
                    }
                    Image(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = null,
                    )
                }
            }
        }
    }
}