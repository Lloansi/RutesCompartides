package com.example.rutescompartidesapp.view.map.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rutescompartidesapp.data.domain.Location.idescat.Municipi
import com.example.rutescompartidesapp.data.domain.Order
import com.example.rutescompartidesapp.data.domain.Route
import com.example.rutescompartidesapp.data.network.GoogleLocation.repository.GoogleLocationsRepository
import com.example.rutescompartidesapp.data.network.idescat.repository.idescatRepository
import com.example.rutescompartidesapp.view.map.components.allOrders
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.PI
import kotlin.math.sqrt

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val googleLocationsRepository: GoogleLocationsRepository,
    private val idescatRepository: idescatRepository
) : ViewModel() {

    // NO SE USA ESTE VIEWMODEL, ESTA FUSIONADO CON EL MAPVIEWMODEL

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

    private val _locations = MutableStateFlow(listOf<Municipi>())
    val locations = _locations.asStateFlow()

    private fun getCataloniaRadius(): Double {
        val areaOfCatalonia = 32000 // in square kilometers
        return sqrt(areaOfCatalonia / PI)
    }

    private val CATALONIA_LAT = 41.5912
    private val CATALONIA_LNG = 1.5209
    private val RADIUS_CATALONIA =  getCataloniaRadius()

    init {
        viewModelScope.launch {
            _orders.value = allOrders
            _locations.value = idescatRepository.getAllMunicipis()
            //_locations.value = googleLocationsRepository.getAllCities(autonomousCommunityLat = CATALONIA_LAT, autonomousCommunityLng = CATALONIA_LNG, radius = 3200)
        }

    }

    /**
     * Updates the search text LiveData with the provided text.

     * @param text The text entered by the user for searching.
     **/
    fun onSearchTextChange(text:String){
        _searchText.value = text

    }

    /**
     * LiveData representing locations filtered based on the searched text.
     * This property combines the searchText LiveData with the _locations LiveData to filter locations.
     * It uses a debounce of 500 milliseconds to delay the emission of events and avoid frequent updates.

     * @return Flow representing the filtered locations based on the searched text.
     **/
    @OptIn(FlowPreview::class)
    val locationsFilteredPerSearchedText = searchText
        .debounce(500L)
        //.onEach{ _isSearching.update{ true } }
        .combine(_locations ) { text, locations ->
            if (text.isBlank()) {
                locations
            } else {
                locations.filter {
                    it.doesMatchSearchQuery(text)
                }
            }
        }
        //.onEach{ _isSearching.update{ false } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = _locations.value
        )


    /**
     * Toggles the search state and clears the search text if the search state is turned off.
     **/
    fun onToogleSearch() {
        _isSearching.value = !_isSearching.value
        if (!_isSearching.value) {
            onSearchTextChange("")
        }
    }
}

