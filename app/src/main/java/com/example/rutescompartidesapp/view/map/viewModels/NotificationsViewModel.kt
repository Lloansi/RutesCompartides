package com.example.rutescompartidesapp.view.map.viewModels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.rutescompartidesapp.data.domain.interactions.RouteInteraction
import com.example.rutescompartidesapp.utils.LocalConstants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NotificationsViewModel: ViewModel() {
    // Interaccions
    private val _userIteractions = mutableStateListOf<RouteInteraction>()
    val userIteractions = _userIteractions


    /**
     * Gets the user interactions filtering the orders and routes lists by the user ID
     * and then filters the interaction list by the order or route ID and updates [_userIteractions] if
     * there are interactions
     * @param userID User ID to filter the orders and routes lists
     */
    fun getUserInteractions (userID: Int) {
        val userOrders =  LocalConstants.orderList!!.filter { it.userID == userID }
        val userRoutes = LocalConstants.routeList!!.filter { it.userID == userID }
        val userInteractions = LocalConstants.interactionList.filter {
            userOrders.any { order -> order.orderID == it.orderID } ||
                    userRoutes.any { route -> route.routeID == it.routeID }
        }
        if (userInteractions.isNotEmpty()) {
            _userIteractions.addAll(userInteractions.reversed())
        }
    }

    private val _notificationPopup = MutableStateFlow(false)
    val notificationPopup = _notificationPopup.asStateFlow()

    fun onPopupToggle(){
        _notificationPopup.value = !_notificationPopup.value
    }
}