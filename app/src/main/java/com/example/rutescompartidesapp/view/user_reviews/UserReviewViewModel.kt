package com.example.rutescompartidesapp.view.user_reviews

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
/**
 * ViewModel for managing user reviews.
 */
class UserReviewViewModel: ViewModel() {

    private val _selectedTabIndex = MutableStateFlow(0)
    val selectedTabIndex = _selectedTabIndex.asStateFlow()

    fun onSelectTab(index: Int) {
        _selectedTabIndex.value = index
    }
}