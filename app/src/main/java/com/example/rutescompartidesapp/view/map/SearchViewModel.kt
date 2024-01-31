package com.example.rutescompartidesapp.view.map

import androidx.lifecycle.MutableLiveData
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

    val search = searchText.debounce(500L)
        .onEach{ _isSearching.update{ true } }
        .combine(_routes) { text, routes ->
            if (text.isBlank()) {
                routes
            } else {
                routes.filter {
                    it.doesMatchSearchQuery(text)
                }
            }
        } .onEach{ _isSearching.update{ false } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _routes.value)

    fun onToogleSearch() {
        _isSearching.value = !_isSearching.value
        if (!_isSearching.value) {
            onSearchTextChange("")
        }
    }
}

