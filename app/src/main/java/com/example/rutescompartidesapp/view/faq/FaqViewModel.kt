package com.example.rutescompartidesapp.view.faq

import androidx.lifecycle.ViewModel
import com.example.rutescompartidesapp.view.faq.components.ItemCategoryModel
import com.example.rutescompartidesapp.view.faq.components.faqItems
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
/**
 * ViewModel for the FAQ screen.
 */
class FaqViewModel : ViewModel() {

    private val _itemListMapped = MutableStateFlow(faqItems.map {
        ItemCategoryModel(
            name = it.key,
            itemList = it.value
        )
    })

    val itemListMapped = _itemListMapped.asStateFlow()
}