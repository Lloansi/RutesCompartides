package com.example.rutescompartidesapp.view.generic_components.popups

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties

/**
 * Composable function to display a scrollable popup with a title and dismissible on outside click.
 *
 * @param title The title text of the popup.
 * @param onDismisRequest The callback for the dismiss request.
 * @param content The content of the popup.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopupScrolleable(title: String, onDismisRequest: () -> Unit,
                     content: @Composable () -> Unit){
    AlertDialog(
        onDismissRequest = {
            onDismisRequest()
        },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false
        )
    ) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth(0.65f),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background)
        ) {
            Row(Modifier.fillMaxWidth()) {
                Column(
                    Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .background(MaterialTheme.colorScheme.background),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ){
                        Text(
                            title,
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                    Row (
                        Modifier
                            .height(10.dp)
                            .background(MaterialTheme.colorScheme.background)) {

                        Divider(color = MaterialTheme.colorScheme.onBackground, thickness = 4.dp)
                    }
                    Row(Modifier.padding(12.dp)) {
                        content()
                    }

                }
            }
        }
    }
}

/**
 * Composable function to display a basic popup without a title and dismissible on outside click.
 *
 * @param onDismisRequest The callback for the dismiss request.
 * @param content The content of the popup.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicPopup(onDismisRequest: () -> Unit,
               content: @Composable () -> Unit){
    AlertDialog(
        onDismissRequest = {
            onDismisRequest()
        },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false
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