package com.example.rutescompartidesapp.view.order_detail

import androidx.lifecycle.ViewModel
import com.example.rutescompartidesapp.data.domain.OrderForList
import com.example.rutescompartidesapp.data.domain.RouteForList
import com.example.rutescompartidesapp.view.routes_order_list.ListConstants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class OrderDetailViewModel: ViewModel() {

    private val _isVisible = MutableStateFlow(true)
    var isVisible = _isVisible.asStateFlow()

    fun toggleVisibility() {
        _isVisible.value = !_isVisible.value
    }

    private val _order = MutableStateFlow<OrderForList?>(null)
    val order = _order.asStateFlow()
    fun getOrder(orderID: Int){
        val order = ListConstants.orderList.first { it.orderID == orderID }
        _order.value = order
    }


    fun deleteOrder(orderID: Int){
        ListConstants.orderList.removeIf { order -> order.orderID == orderID }

        // TODO Fer un DELETE a la API per eliminar la comanda
    }
}