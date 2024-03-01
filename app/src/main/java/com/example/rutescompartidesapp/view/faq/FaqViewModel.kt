package com.example.rutescompartidesapp.view.faq

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FaqViewModel: ViewModel() {

    private val _isExpanded = MutableStateFlow(false)
    val isExpanded = _isExpanded.asStateFlow()

    fun onCardIconClicked() {
        _isExpanded.value = !_isExpanded.value
    }
}