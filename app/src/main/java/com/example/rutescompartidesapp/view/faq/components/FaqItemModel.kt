package com.example.rutescompartidesapp.view.faq.components

/**
 * Model class representing an item in the FAQ list.
 *
 * @property title The title of the FAQ item.
 * @property description The description or content of the FAQ item.
 * @property category The category to which the FAQ item belongs.
 */
data class FaqItemModel(
    val title: String,
    val description: String,
    val category: String,
)
