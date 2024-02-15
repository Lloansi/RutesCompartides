package com.example.rutescompartidesapp.view.map.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CardTravel
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Directions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp

@Composable
fun ExpandableFloatingButton() {
    var isExpanded by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .wrapContentSize()
    ) {
        if (isExpanded) {
            MiniFloatingButton(
                icon = Icons.Filled.Directions,
                onClick = { /* Handle Favorite click */ }
            )
            MiniFloatingButton(
                icon = Icons.Default.CardTravel,
                onClick = { /* Handle Visibility click */ }
            )
        }
        FloatingActionButton(
            containerColor = MaterialTheme.colorScheme.primary,
            onClick = {
                isExpanded = !isExpanded
                if (isExpanded) {
                    keyboardController?.hide() // Hide keyboard if expanded
                }
            }
        ) {
            Icon(
                imageVector = if (isExpanded) Icons.Default.Close else Icons.Default.Add,
                contentDescription = null
            )
        }
    }
}

@Composable
fun MiniFloatingButton(
    icon: ImageVector,
    onClick: () -> Unit
) {
    FloatingActionButton(
        containerColor = Color.White,
        contentColor = MaterialTheme.colorScheme.primary,
        onClick = {
            onClick()
        },
        modifier = Modifier
            .padding(8.dp)
    ) {
        Icon(imageVector = icon, contentDescription = null)
    }
}