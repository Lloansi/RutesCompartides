package com.example.rutescompartidesapp.view.complete.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.example.rutescompartidesapp.view.complete.CompleteViewModel


@Composable
fun AcceptDenyButtons(colorDenyButton: Color, completeViewModel: CompleteViewModel) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding()
            .padding(top = (LocalConfiguration.current.screenHeightDp.dp) / 90, bottom = 20.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ){
        ExtendedFloatingActionButton(
            containerColor = MaterialTheme.colorScheme.tertiary,
            onClick = { completeViewModel.toggleVisibility() }
        ) {
            Icon(imageVector = Icons.Filled.ThumbUp, contentDescription = "Accept order")
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = "Acceptar")
        }

        ExtendedFloatingActionButton(
            containerColor = colorDenyButton,
            contentColor = Color.White,
            onClick = { completeViewModel.toggleVisibility() }
        ) {
            Icon(imageVector = Icons.Filled.ThumbDown, contentDescription = "Accept order")
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = "Rebutjar")
        }
    }
}