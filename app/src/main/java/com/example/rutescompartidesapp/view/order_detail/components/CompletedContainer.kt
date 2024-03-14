package com.example.rutescompartidesapp.view.order_detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable

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