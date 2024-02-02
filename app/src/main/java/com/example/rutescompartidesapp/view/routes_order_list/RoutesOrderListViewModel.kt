package com.example.rutescompartidesapp.view.routes_order_list

import android.os.Build
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CardTravel
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.CardTravel
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.runtime.MutableDoubleState
import androidx.compose.runtime.MutableIntState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rutescompartidesapp.data.domain.Order
import com.example.rutescompartidesapp.data.domain.Route
import com.example.rutescompartidesapp.view.routes_order_list.components.TabItems
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

    private val _isCondicionsPopupShowing = MutableStateFlow(false)
    val isCondicionsPopupShowing = _isCondicionsPopupShowing

    private val _puntSortidaText = MutableStateFlow("")
    val puntSortidaText = _puntSortidaText.asStateFlow()

    private val _puntArribadaText = MutableStateFlow("")
    val puntArribadaText = _puntArribadaText.asStateFlow()

    private val _dataSortidaText = MutableStateFlow("")
    val dataSortidaText = _dataSortidaText.asStateFlow()

    private val _horaSortidaText = MutableStateFlow("")
    val horaSortidaText = _horaSortidaText.asStateFlow()

    private val _popupIsShowing = MutableStateFlow(false)
    val popupIsShowing = _popupIsShowing

    // Condicions de transport chips
    private val _isIsoterm = MutableStateFlow(false)
    val isIsoterm = _isIsoterm.asStateFlow()

    private val _isRefrigerat = MutableStateFlow(false)
    val isRefrigerat = _isRefrigerat.asStateFlow()

    private val _isCongelat = MutableStateFlow(false)
    val isCongelat = _isCongelat.asStateFlow()

    private val _isSenseHumitat = MutableStateFlow(false)
    val isSenseHumitat = _isSenseHumitat.asStateFlow()

    fun onCheckChip(condition: String){
        when(condition) {
            "Isoterm" -> _isIsoterm.value = !_isIsoterm.value
            "Refrigerat" -> _isRefrigerat.value = !_isRefrigerat.value
            "Congelat" -> _isCongelat.value = !_isCongelat.value
            "SenseHumitat" -> _isSenseHumitat.value = !_isSenseHumitat.value
        }

    }

    fun onPopupShow(isShowing: Boolean){
        /*if (!_isCondicionsPopupShowing.value) {
            _popupIsShowing.value = isShowing
        }
        */
        _popupIsShowing.value = isShowing

        if (!isShowing){
            _puntSortidaText.value = ""
            _puntArribadaText.value = ""
            _extraFiltersAreShowing.value = false
            _horaSortidaText.value = ""
            _dataSortidaText.value = ""
            _isIsoterm.value = false
            _isRefrigerat.value = false
            _isCongelat.value = false
            _isSenseHumitat.value = false
            _isCondicionsPopupShowing.value = false
        }
    }
    fun onCondicionsPopupShow(isShowing: Boolean){
        _isCondicionsPopupShowing.value = isShowing

    }
    fun onFilterSearch(puntSortida: String?, puntArribada: String?){
        _popupIsShowing.value = false
        if (puntSortida != null) {
            _searchText.value = puntSortida
        }
        _puntSortidaText.value = ""
        _puntArribadaText.value = ""
        _isIsoterm.value = false
        _isRefrigerat.value = false
        _isCongelat.value = false
        _isSenseHumitat.value = false
    }
    fun onPuntSortidaChange(text: String){
        _puntSortidaText.value = text
    }
    fun onPuntArribadaChange(text: String){
        _puntArribadaText.value = text
    }

    fun onDataSortidaChange(text: String){
        _dataSortidaText.value = text
    }
    fun onHoraArribadaChange(text: String){
        _horaSortidaText.value = text
    }

    private val _extraFiltersAreShowing = MutableStateFlow(false)
    val extraFiltersAreShowing = _extraFiltersAreShowing

    fun onExtraFiltersToggle(){
        _extraFiltersAreShowing.value = !_extraFiltersAreShowing.value
    }



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
                    route.routeName.contains(text, ignoreCase = true)
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
                    order.orderName.contains(text, ignoreCase = true)
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