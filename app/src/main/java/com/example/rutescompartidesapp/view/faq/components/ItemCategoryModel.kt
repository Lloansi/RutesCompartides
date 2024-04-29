package com.example.rutescompartidesapp.view.faq.components

/**
 * Model class representing a category of FAQ items.
 *
 * @property name The name of the category.
 * @property itemList The list of FAQ items belonging to this category.
 */
data class ItemCategoryModel(
    val name: String,
    val itemList: List<FaqItemModel>
)
