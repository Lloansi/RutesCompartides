package com.example.rutescompartidesapp.view.routes_order_list.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rutescompartidesapp.data.domain.ListQuery
import com.example.rutescompartidesapp.data.domain.RouteForList
import com.example.rutescompartidesapp.view.routes_order_list.ListConstants
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.transformLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RoutesOrderListViewModel: ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _queryList = MutableStateFlow(
        ListQuery("", "", "", "",
            listOf(), false, false, false, false))
    private val _activeFilters = MutableStateFlow(
        List(9) { false })

    val activeFilters = _activeFilters.asStateFlow()
    val queryList = _queryList.asStateFlow()


    private var _routes = MutableStateFlow(ListConstants.routeList)
    var routes55 = _routes.asStateFlow()

    var routes2 = _queryList
        .onEach { _isSearching.update { true } }
        .combine(_routes) { queryList, routes ->
            if (_activeFilters.value.contains(true).not()) {
                println("Todas")
                routes
            } else {
                println("Filtrando")
                routes.filter { route ->
                    (_activeFilters.value[0] && route.puntSortida.contains(
                        queryList.puntSortida,
                        ignoreCase = true
                    )) ||
                            (_activeFilters.value[1] && route.puntArribada.contains(
                                queryList.puntArribada,
                                ignoreCase = true
                            )) ||
                            (_activeFilters.value[2] && route.dataSortida.contains(
                                queryList.dataSortida,
                                ignoreCase = true
                            )) ||
                            (_activeFilters.value[3] && route.horaSortida.contains(
                                queryList.horaSortida,
                                ignoreCase = true
                            )) ||
                            (_activeFilters.value[4] && route.isIsoterm) ||
                            (_activeFilters.value[5] && route.isRefrigerat) ||
                            (_activeFilters.value[6] && route.isCongelat) ||
                            (_activeFilters.value[7] && route.isSenseHumitat) ||
                            (_activeFilters.value[8] && (route.etiquetes?.any { it in _queryList.value.etiquetes } == true))
                }
            }.onEach { _isSearching.update { false } }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _routes.value)


    @OptIn(FlowPreview::class)
    var routes = searchText.debounce(500L)
        .onEach { _isSearching.update { true } }
        .combine(_routes) { text, routes ->
            if (text.isBlank()) {
                println("Todas")
                routes

            } else {
                println("Filtro searchbar")
                routes.filter { route ->
                    route.puntSortida.contains(text, ignoreCase = true) ||
                            route.puntArribada.contains(text, ignoreCase = true)
                }
            }
        }
            .onEach { _isSearching.update { false } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _routes.value)


    private val _orders = MutableStateFlow(ListConstants.orderList)

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

    fun onFilterSearch(listQuery: ListQuery) {
        _queryList.value = listQuery
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

        println(_activeFilters.value)

        // FUNCIONA

        val routes2 = _routes.value.filter { route ->
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
        val routes5 = getFilteredRoutes(activeFilters, listQuery)

        println(routes5)

    }

    fun getFilteredRoutes(activeFilters: List<Boolean>, listQuery: ListQuery): List<RouteForList> {
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

}
