package com.example.rutescompartidesapp.view.complete

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CompleteViewModel: ViewModel() {

    private val _isVisible = MutableStateFlow(true)
    var isVisible = _isVisible.asStateFlow()

    fun toggleVisibility() {
        _isVisible.value = !_isVisible.value
    }
}