package com.example.rutescompartidesapp.view.order_detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable

@Composable
fun CompletedContainer() {
    Column {
        ConfirmedCard()
        DetailsConfirmation()
        FinalizedCard()
    }

}