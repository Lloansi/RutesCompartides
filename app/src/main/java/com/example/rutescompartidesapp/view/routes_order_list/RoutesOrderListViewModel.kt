package com.example.rutescompartidesapp.view.routes_order_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rutescompartidesapp.data.domain.ListQuery
import com.example.rutescompartidesapp.data.domain.OrderForList
import com.example.rutescompartidesapp.data.domain.RouteForList
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class RoutesOrderListViewModel: ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _queryList = MutableStateFlow(ListQuery("", "", "", "", null, false, false, false, false))

    private val _routes = MutableStateFlow(ListConstants.routeList)

    var routes2 = combine(_routes, _queryList){ routes , queryList ->
        routes.filter { route ->
            route.puntSortida.contains(queryList.puntSortida, ignoreCase = true) ||
                    route.puntArribada.contains(queryList.puntArribada, ignoreCase = true) ||
                    route.dataSortida.contains(queryList.dataSortida, ignoreCase = true) ||
                    route.horaSortida.contains(queryList.horaSortida, ignoreCase = true) ||
                    route.isIsoterm == queryList.isIsoterm ||
                    route.isRefrigerat == queryList.isRefrigerat ||
                    route.isCongelat == queryList.isCongelat ||
                    route.isSenseHumitat == queryList.isSenseHumitat ||
                    (route.etiquetes?.any { it in queryList.etiquetes.orEmpty() } ?: true)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _routes.value)

    @OptIn(FlowPreview::class)
    var routes = searchText.debounce(500L)
        .onEach { _isSearching.update{ true } }
        .combine(_routes) { text, routes ->
            if (text.isBlank()) {
                routes
            } else {
                routes.filter { route ->
                    route.puntSortida.contains(text, ignoreCase = true) ||
                            route.puntArribada.contains(text, ignoreCase = true)
                }
            }
        } .onEach { _isSearching.update{ false } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _routes.value)


    private val _orders = MutableStateFlow(ListConstants.orderList)
    @OptIn(FlowPreview::class)
    val orders = searchText.debounce(500L)
        .onEach { _isSearching.update{ true } }
        .combine(_orders) { text, orders ->
            if (text.isBlank()) {
                orders
            } else {
                orders.filter { order ->
                    order.puntSortida.contains(text, ignoreCase = true) ||
                            order.puntArribada.contains(text, ignoreCase = true)
                }
            }
        } .onEach { _isSearching.update{ false } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _orders.value)

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }
    fun onToogleSearch() {
        _isSearching.value = !_isSearching.value
    }
    fun onFilterSearch(listQuery: ListQuery){
        _queryList.value = listQuery




        /*
        routes.combine(_routes) { text, routes ->
            routes.filter { route ->
                route.puntSortida.contains(listQuery.puntSortida, ignoreCase = true) ||
                        route.puntArribada.contains(listQuery.puntArribada, ignoreCase = true) ||
                        route.dataSortida.contains(listQuery.dataSortida, ignoreCase = true) ||
                        route.horaSortida.contains(listQuery.horaSortida, ignoreCase = true) ||

                        route.isIsoterm == listQuery.isIsoterm ||
                        route.isRefrigerat == listQuery.isRefrigerat ||
                        route.isCongelat == listQuery.isCongelat ||
                        route.isSenseHumitat == listQuery.isSenseHumitat ||
                        (route.etiquetes?.any { it in listQuery.etiquetes.orEmpty() } ?: true)
            }
        }
         */



    }
}


