package com.example.rutescompartidesapp.view.order_detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.rutescompartidesapp.view.order_detail.OrderDetailViewModel

@Composable
fun AcceptDenyContainer(colorDenyButton: Color, isVisible: Boolean, orderDetailViewModel: OrderDetailViewModel) {
    if (isVisible){
        Column {
            TransportCard()
            AcceptDenyButtons(colorDenyButton, orderDetailViewModel)
        }
    }
}