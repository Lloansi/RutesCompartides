package com.example.rutescompartidesapp.view.complete.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.rutescompartidesapp.R

@Composable
fun CompletedContainer(isVisible: Boolean) {
    if (!isVisible){
        Column {
            ConfirmedCard()
            DetailsConfirmation()
            FinalizedCard()
        }
    }
}