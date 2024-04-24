package com.example.rutescompartidesapp.view.map.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rutescompartidesapp.data.domain.Order
import com.example.rutescompartidesapp.data.domain.OrderForList
import com.example.rutescompartidesapp.data.domain.Route
import com.example.rutescompartidesapp.data.domain.RouteForList
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SearchViewModel2 : ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _routes = MutableStateFlow(listOf<Route>())
    val routes = _routes.asStateFlow()

    private val _orders = MutableStateFlow(listOf<Order>())
    val orders = _orders.asStateFlow()

    private val _ordersAndRoutes = MutableStateFlow(listOf<Pair<List<Route>, List<Order>>>())
    val ordersAndRoutes = _ordersAndRoutes.asStateFlow()
    init {
        /*
        Hasta que no se implemente los metodos para
        conseguir la info de la api, no descomentar

        viewModelScope.launch {
            _routes.value = getAllRoutes()
            _orders.value = getAllOrders()
        }

         */

        viewModelScope.launch {
            searchText
                .debounce(500L)
                .distinctUntilChanged()
                .onEach { text ->
                    _isSearching.value = true
                    val filteredRoutes = filterRoutesBySearchText(text)
                    val filteredOrders = filterOrdersBySearchText(text)
                    _routes.value = filteredRoutes
                    _orders.value = filteredOrders
                    _ordersAndRoutes.value = listOf(filteredRoutes to filteredOrders)
                    _isSearching.value = false
                }
                .launchIn(viewModelScope)
        }
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    private fun filterRoutesBySearchText(searchText: String): List<Route> {
        val allRoutes = listOf<Route>() // TODO Remplazarlo por una llamada a una api con todas las rutas

        return if (searchText.isNotBlank()){
            allRoutes.filter { route ->
                route.startPoint.contains(searchText, ignoreCase = true) || route.endPoint.contains(searchText, ignoreCase = true)
            }
        } else{
            allRoutes
        }
    }

    private fun filterOrdersBySearchText(searchText: String): List<Order> {
        val allOrders = listOf<Order>() // TODO Remplazarlo por una llamada a una api con todas las rutas

        return if (searchText.isNotBlank()){
            allOrders.filter { order ->
                order.packageStartPoint.contains(searchText, ignoreCase = true) || order.packageEndPoint.contains(searchText, ignoreCase = true)
            }
        } else{
            allOrders
        }
    }

    private suspend fun getAllRoutes(): List<Route> {
        //TODO llamada a la api
        return emptyList()
    }

    private suspend fun getAllOrders(): List<Order> {
        //TODO llamada a la api
        return emptyList()
    }
}
