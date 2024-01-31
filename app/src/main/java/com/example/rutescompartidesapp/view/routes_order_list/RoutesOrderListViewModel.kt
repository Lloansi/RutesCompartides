package com.example.rutescompartidesapp.view.routes_order_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rutescompartidesapp.data.domain.Order
import com.example.rutescompartidesapp.data.domain.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class RoutesOrderListViewModel: ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _routes = MutableStateFlow(routeList)
    val routes = searchText.debounce(500L)
        .onEach{ _isSearching.update{ true } }
        .combine(_routes) { text, routes ->
            if (text.isBlank()) {
                routes
            } else {
                routes.filter { route ->
                    route.routeName.contains(text)
                }
            }
        } .onEach{ _isSearching.update{ false } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _routes.value)

    private val _orders = MutableStateFlow(orderList)
    val orders = searchText.debounce(500L)
        .onEach{ _isSearching.update{ true } }
        .combine(_orders) { text, orders ->
            if (text.isBlank()) {
                orders
            } else {
                orders.filter { order ->
                    order.orderName.contains(text)
                }
            }
        } .onEach{ _isSearching.update{ false } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _orders.value)

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }
    fun onToogleSearch() {
        _isSearching.value = !_isSearching.value

    }



}
private val routeList = listOf(
    Route(1, "Ruta 1"),
    Route(2, "eeeeee 2"),
    Route(3, "Ruta 3"),
    Route(4, "ooooo 4"),
    Route(5, "Ruta 5"),
)
private val orderList = listOf(
    Order(1, "Order 1"),
    Order(2, "Order 2"),
    Order(3, "eeee 3"),
    Order(4, "Order 4"),
    Order(5, "uuuu 5"),
)