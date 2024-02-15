package com.example.rutescompartidesapp.view.map.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import com.example.rutescompartidesapp.domain.model.Route
import kotlinx.coroutines.FlowPreview

class SearchViewModel: ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _routes = MutableStateFlow(listOf<Route>())
    val routes = _routes.asStateFlow()

    fun onSearchTextChange(text:String){
        _searchText.value = text
    }

    @OptIn(FlowPreview::class)
    val search = searchText
        // Similar to delay (not the same, if something happens before, don't show it)
        .debounce(500L)
        // We update the state of a boolean to know , user is using the search bar
        .onEach{ _isSearching.update{ true } }
        // combine lets the developer to use properties from more than one element, as we can see, we can deal with text and routes at the same time
        .combine(_routes) { text, routes ->
            // if user dont writes anything, we return all the list of routes
            if (text.isBlank()) {
                routes
            // else , we apply a filter to show routes that contains the letters
            } else {
                routes.filter {
                    it.doesMatchSearchQuery(text)
                }
            }
        // We update the state of a boolean to know , user is not using the search bar
        } .onEach{ _isSearching.update{ false } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _routes.value)

    // Function to change the content of Text (jetpack compose element) while user tap on keyboard
    fun onToogleSearch() {
        _isSearching.value = !_isSearching.value
        if (!_isSearching.value) {
            onSearchTextChange("")
        }
    }
}

