package com.example.rutescompartidesapp.view.routes_order_list.viewmodels

import androidx.lifecycle.ViewModel
import com.example.rutescompartidesapp.R
import com.example.rutescompartidesapp.view.routes_order_list.components.TabItems
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TabRowViewModel: ViewModel() {
    private val _selectedTabIndex = MutableStateFlow(0)
    val selectedTabIndex = _selectedTabIndex.asStateFlow()
    val tabItems = listOf(
        TabItems(
            title = "Rutes",
            unselectedIcon =  R.drawable.location_outlined,
            selectedIcon = R.drawable.location_filled
        ),
        TabItems(
            title = "Comandes",
            unselectedIcon = R.drawable.outlined_box,
            selectedIcon = R.drawable.filled_box
        )
    )

    /**
     * Changes the index of the tab when selected and
     * notifies the view pager which tab is selected
     * @param index Index of the selected tab
     */
    fun onSelectTab(index: Int) {
        _selectedTabIndex.value = index
    }
}