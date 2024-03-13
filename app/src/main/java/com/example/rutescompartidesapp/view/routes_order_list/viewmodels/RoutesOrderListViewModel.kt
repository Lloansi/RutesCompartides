package com.example.rutescompartidesapp.view.routes_order_list.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rutescompartidesapp.data.domain.ListQuery
import com.example.rutescompartidesapp.data.domain.OrderForList
import com.example.rutescompartidesapp.data.domain.RouteForList
import com.example.rutescompartidesapp.view.routes_order_list.ListConstants
import kotlinx.coroutines.FlowPreview
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

    // List of filters queries
    private val _queryList = MutableStateFlow(
        ListQuery("", "", "", "",
            listOf(), isIsoterm = false, isRefrigerat = false, isCongelat = false, isSenseHumitat = false))

    // List of active filters
    private val _activeFilters = MutableStateFlow(
        List(9) { false })

    val activeFilters = _activeFilters.asStateFlow()


    // List of routes from the backend
    private val _routesOriginal = MutableStateFlow(ListConstants.routeList)

    // List of routes, it's initial value it's the list of routes from the BackEnd
    private var _routes = _routesOriginal

    // Everytime the searchText changes, the routes are filtered
    @OptIn(FlowPreview::class)
    var routes = searchText.debounce(500L)
        .onEach { _isSearching.update { true } }
        .combine(_routes) { text, routes ->
            if (text.isBlank()) {
                routes
            } else {
                routes.filter { route ->
                    route.puntSortida.contains(text, ignoreCase = true) ||
                            route.puntArribada.contains(text, ignoreCase = true)
                }
            }
        }.onEach { _isSearching.update { false } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _routes.value)

    // List of routes from the backend
    private val _ordersOriginal = MutableStateFlow(ListConstants.orderList)

    // List of orders, it's initial value it's the list of routes from the BackEnd
    private var _orders = _ordersOriginal

    // Everytime the searchText changes, the orders are filtered
    @OptIn(FlowPreview::class)
    val orders = searchText.debounce(500L)
        .onEach { _isSearching.update { true } }
        .combine(_orders) { text, orders ->
            if (text.isBlank()) {
                orders
            } else {
                orders.filter { order ->
                    order.puntSortida.contains(text, ignoreCase = true) ||
                            order.puntArribada.contains(text, ignoreCase = true)
                }
            }
        }.onEach { _isSearching.update { false } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _orders.value)

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    fun onToogleSearch(isSearching: Boolean) {
        _isSearching.value = isSearching
    }

    /**
     * Function to filter the routes and orders.
     *
     * Takes a ListQuery with the values of each filter and notifies the active filters in order to
     * filter the routes and orders based in the filters specified and their query values.
     *
     * Then reassigns the value of the routes and orders to the filtered ones.
     * @param listQuery: ListQuery
     */
    fun onFilterSearch(listQuery: ListQuery) {

        _isSearching.update { true }

        // Updates the value of the query
        _queryList.value = listQuery

        // Gets the active filters based on the query values and updates the value of the active filters
        val activeFilters = listOf(
            listQuery.puntSortida.isNotBlank(),
            listQuery.puntArribada.isNotBlank(),
            listQuery.dataSortida.isNotBlank(),
            listQuery.horaSortida.isNotBlank(),
            listQuery.isIsoterm,
            listQuery.isRefrigerat,
            listQuery.isCongelat,
            listQuery.isSenseHumitat,
            listQuery.etiquetes.isNotEmpty()
        )
        _activeFilters.value = activeFilters

        // Gets the filtered routes and orders
        val filteredRoutes = getFilteredRoutes(activeFilters, listQuery)
        val filteredOrders = getFilteredOrders(activeFilters, listQuery)

        // Reassign the value of the routes and orders to the filtered ones
        _routes.value = filteredRoutes.toMutableList()
        _orders.value = filteredOrders.toMutableList()

        _isSearching.update { false }

    }
    /**
     * Resets the filters and reassigns the value of the routes and orders to the original ones.
     */
    fun onResetFilters() {
        _queryList.value = ListQuery("", "", "", "",
            listOf(),  isIsoterm = false, isRefrigerat = false, isCongelat = false, isSenseHumitat = false)

        _activeFilters.value = List(9) { false }

        _routes.value = ListConstants.routeList

        _orders.value = ListConstants.orderList
    }

    /**
     * Function to get the filtered routes based on the active filters and the query values.
     * @param activeFilters: List<Boolean>
     * @param listQuery: ListQuery
     * @return List<RouteForList>
     */
    private fun getFilteredRoutes(activeFilters: List<Boolean>, listQuery: ListQuery): List<RouteForList> {
       return _routes.value.filter { route ->
            (activeFilters[0] && route.puntSortida.contains(
                listQuery.puntSortida,
                ignoreCase = true
            )) ||
                    (activeFilters[1] && route.puntArribada.contains(
                        listQuery.puntArribada,
                        ignoreCase = true
                    )) ||
                    (activeFilters[2] && route.dataSortida.contains(
                        listQuery.dataSortida,
                        ignoreCase = true
                    )) ||
                    (activeFilters[3] && route.horaSortida.contains(
                        listQuery.horaSortida,
                        ignoreCase = true
                    )) ||
                    (activeFilters[4] && route.isIsoterm) ||
                    (activeFilters[5] && route.isRefrigerat) ||
                    (activeFilters[6] && route.isCongelat) ||
                    (activeFilters[7] && route.isSenseHumitat) ||
                    (activeFilters[8] && (route.etiquetes?.any { it in listQuery.etiquetes } == true))
        }
    }
    /**
     * Function to get the filtered orders based on the active filters and the query values.
     * @param activeFilters: List<Boolean>
     * @param listQuery: ListQuery
     * @return List<OrderForList>
     */
    private fun getFilteredOrders(activeFilters: List<Boolean>, listQuery: ListQuery): List<OrderForList> {
        return _orders.value.filter { order ->
            (activeFilters[0] && order.puntSortida.contains(
                listQuery.puntSortida,
                ignoreCase = true
            )) ||
                    (activeFilters[1] && order.puntArribada.contains(
                        listQuery.puntArribada,
                        ignoreCase = true
                    )) ||
                    (activeFilters[2] && order.dataSortida.contains(
                        listQuery.dataSortida,
                        ignoreCase = true
                    )) ||
                    (activeFilters[3] && order.horaSortida.contains(
                        listQuery.horaSortida,
                        ignoreCase = true
                    )) ||
                    (activeFilters[4] && order.isIsoterm) ||
                    (activeFilters[5] && order.isRefrigerat) ||
                    (activeFilters[6] && order.isCongelat) ||
                    (activeFilters[7] && order.isSenseHumitat) ||
                    (activeFilters[8] && (order.etiquetes?.any { it in listQuery.etiquetes } == true))
        }
    }
}
