package com.example.rutescompartidesapp.view.map.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ExpandableFloatingButton() {
    var isExpanded by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .wrapContentSize(),
    ) {
        if (isExpanded) {
            MiniFloatingButton(
                icon = Icons.Default.ThumbUp,
                onClick = { /* Handle ThumbUp click */ }
            )
            MiniFloatingButton(
                icon = Icons.Default.Favorite,
                onClick = { /* Handle Favorite click */ }
            )
            MiniFloatingButton(
                icon = Icons.Default.Edit,
                onClick = { /* Handle Visibility click */ }
            )
            // Add more MiniFloatingButton as needed
        }

        FloatingActionButton(
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
        onClick = {
            onClick()
        },
        modifier = Modifier
            .padding(8.dp)
    ) {
        Icon(imageVector = icon, contentDescription = null)
    }
}

