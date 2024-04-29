package com.example.rutescompartidesapp.view.order_detail

import androidx.lifecycle.ViewModel
import com.example.rutescompartidesapp.data.domain.orders.Orders
import com.example.rutescompartidesapp.utils.LocalConstants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class OrderDetailViewModel: ViewModel() {

    private val _isVisible = MutableStateFlow(true)
    var isVisible = _isVisible.asStateFlow()

    fun toggleVisibility() {
        _isVisible.value = !_isVisible.value
    }

    private val _order = MutableStateFlow<Orders?>(null)
    val order = _order.asStateFlow()
    fun getOrder(orderID: Int){
        val order = LocalConstants.orderList.first { it.orderID == orderID }
        _order.value = order
    }


    fun deleteOrder(orderID: Int){
        LocalConstants.orderList.removeIf { order -> order.orderID == orderID }

        // TODO Fer un DELETE a la API per eliminar la comanda
    }
}