package com.example.rutescompartidesapp.view.map.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rutescompartidesapp.data.domain.GeoName.GeoName
import com.example.rutescompartidesapp.data.domain.Order
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import com.example.rutescompartidesapp.data.domain.Route
import com.example.rutescompartidesapp.data.network.GeoNames.repository.GeoNamesRepository
import com.example.rutescompartidesapp.view.map.components.allOrders
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val geoNamesRepository: GeoNamesRepository
) : ViewModel() {

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

    private val _locations = MutableStateFlow(listOf<GeoName>())
    val locations = _locations.asStateFlow()

    init {
        viewModelScope.launch {
            _orders.value = allOrders
            _locations.value = geoNamesRepository.getAllCataloniaCities()
        }
        /*
        Hasta que no se implemente los metodos para
        conseguir la info de la api, no descomentar

        viewModelScope.launch {
            _routes.value = getAllRoutes()
            _orders.value = getAllOrders()
            _locations
        }

         */
    }

    fun onSearchTextChange(text:String){
        _searchText.value = text

    }

    @OptIn(FlowPreview::class)
    val routesFilteredPerSearchedText = searchText
        .debounce(500L)
        .onEach{ _isSearching.update{ true } }
        .combine(_routes ) { text, routes ->
            if (text.isBlank()) {
                routes
            } else {
                routes.filter {
                    it.doesMatchSearchQuery(text)
                }
            }
        }
        .onEach{ _isSearching.update{ false } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = _routes.value
        )

    @OptIn(FlowPreview::class)
    val ordersFilteredPerSearchedText = searchText
        .debounce(500L)
        .onEach{ _isSearching.update{ true } }
        .combine(_orders ) { text, orders ->
            if (text.isBlank()) {
                orders
            } else {
                orders.filter {
                    it.doesMatchSearchQuery(text)
                }
            }
        }
        .onEach{ _isSearching.update{ false } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = _orders.value
        )

    @OptIn(FlowPreview::class)
    val locationsFilteredPerSearchedText = searchText
        .debounce(500L)
        .onEach{ _isSearching.update{ true } }
        .combine(_locations ) { text, locations ->
            if (text.isBlank()) {
                locations
            } else {
                locations.filter {
                    it.doesMatchSearchQuery(text)
                }
            }
        }
        .onEach{ _isSearching.update{ false } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = _locations.value
        )


    // Function to change the content of Text (jetpack compose element) while user tap on keyboard
    fun onToogleSearch() {
        _isSearching.value = !_isSearching.value
        if (!_isSearching.value) {
            onSearchTextChange("")
        }
    }

    fun updateSearchedOrdersAndRoutes(text: String) {
        val filteredRoutes = filterRoutesBySearchText(text)
        val filteredOrders = filterOrdersBySearchText(text)

        _routes.value = filteredRoutes
        _orders.value = filteredOrders

        _ordersAndRoutes.value = listOf(Pair(_routes.value, _orders.value))
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
}

