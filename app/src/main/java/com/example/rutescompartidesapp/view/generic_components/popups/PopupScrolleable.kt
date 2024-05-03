package com.example.rutescompartidesapp.view.generic_components.popups

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.example.rutescompartidesapp.ui.theme.MateBlackRC

@Composable
fun PopupScrolleable(offset: IntOffset, title: String, onDismisRequest: () -> Unit,
                     content: @Composable () -> Unit){
    Popup(
        onDismissRequest = {
            onDismisRequest()
        },
        offset = offset,
        properties = PopupProperties(
            focusable = true,
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth(0.65f),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Row(Modifier.fillMaxWidth()) {
                Column(
                    Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .background(Color.LightGray),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            title,
                            style = MaterialTheme.typography.titleLarge,
                        )
                    }
                    Row(
                        Modifier
                            .height(10.dp)
                            .background(Color.LightGray)
                    ) {
                        Divider(color = MateBlackRC, thickness = 4.dp)
                    }
                    Row(Modifier.padding(12.dp)) {
                        content()
                    }

                }
            }
        }
    }
}

@Composable
fun BasicPopup(offset: IntOffset, onDismisRequest: () -> Unit,
               content: @Composable () -> Unit){
    Popup(
        onDismissRequest = {
            onDismisRequest()
        },
        offset = offset,
        properties = PopupProperties(
            focusable = true,
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth(0.65f),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Row(Modifier.fillMaxWidth()) {
                Column(
                    Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(Modifier.padding(12.dp)) {
                        content()
                    }

                }
            }
        }
    }
}