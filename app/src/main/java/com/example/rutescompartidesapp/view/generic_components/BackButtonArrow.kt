package com.example.rutescompartidesapp.view.generic_components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

/**
 * Composable function to display a back button arrow.
 *
 * @param navController The NavController to handle navigation.
 * @param alignment The alignment of the button.
 * @param screenRoute The route to navigate to when the button is clicked.
 */
@Composable
fun BackButtonArrow(navController: NavController, alignment: Alignment, screenRoute: String) {
    Box(
        modifier = Modifier
            .padding(top = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .align(alignment)
        ) {
            IconButton(
                onClick = {
                    navController.navigate(screenRoute) {
                        popUpTo(screenRoute) { inclusive = true }
                    }
                }) {
                Icon(
                    modifier = Modifier
                        .size(18.dp),
                    imageVector = Icons.Rounded.ArrowBackIosNew,
                    contentDescription = null,
                    tint = Color(0xFFF2F2F2),
                )
            }
        }
    }
}