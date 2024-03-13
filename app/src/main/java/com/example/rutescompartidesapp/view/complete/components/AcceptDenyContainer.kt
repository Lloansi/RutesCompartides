package com.example.rutescompartidesapp.view.complete.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import com.example.rutescompartidesapp.R
import com.example.rutescompartidesapp.view.complete.CompleteViewModel

@Composable
fun AcceptDenyContainer(colorDenyButton: Color,isVisible: Boolean, completeViewModel: CompleteViewModel) {
    if (isVisible){
        Column {
            TransportCard()
            AcceptDenyButtons(colorDenyButton, completeViewModel)
        }
    }
}