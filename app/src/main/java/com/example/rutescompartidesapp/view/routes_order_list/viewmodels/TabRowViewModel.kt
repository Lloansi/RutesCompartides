package com.example.rutescompartidesapp.view.routes_order_list.viewmodels

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CardTravel
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.CardTravel
import androidx.compose.material.icons.outlined.LocationOn
import androidx.lifecycle.ViewModel
import com.example.rutescompartidesapp.view.routes_order_list.components.TabItems
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TabRowViewModel: ViewModel() {
    private val _selectedTabIndex = MutableStateFlow(0)
    val selectedTabIndex = _selectedTabIndex.asStateFlow()

    val tabItems = listOf(
        TabItems(
            title = "Rutes",
            unselectedIcon = Icons.Outlined.LocationOn,
            selectedIcon = Icons.Filled.LocationOn
        ),
        TabItems(
            title = "Comandes",
            unselectedIcon = Icons.Outlined.CardTravel,
            selectedIcon = Icons.Filled.CardTravel
        )
    )

    fun onSelectTab(index: Int) {
        _selectedTabIndex.value = index
    }
}