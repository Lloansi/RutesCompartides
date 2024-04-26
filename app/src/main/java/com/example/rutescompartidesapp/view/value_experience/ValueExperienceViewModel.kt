package com.example.rutescompartidesapp.view.value_experience

import androidx.lifecycle.ViewModel
import com.example.rutescompartidesapp.data.domain.review.Review
import com.example.rutescompartidesapp.utils.Constants.reviewList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ValueExperienceViewModel:ViewModel() {

    // PackageDeliveredCheckbox
    private val _isPackageDelivered = MutableStateFlow(false)
    val isPackageDelivered = _isPackageDelivered.asStateFlow()

    fun onPackageDeliveredChange(){
        _isPackageDelivered.value = !_isPackageDelivered.value
    }

    // Comment
    private val _comment = MutableStateFlow("")
    val comment = _comment

    fun setComment(text: String){
        _comment.value = text
    }

    // Score
    private val _isDropdownExpanded = MutableStateFlow(false)
    val isDropdownExpanded = _isPackageDelivered.asStateFlow()
    private val _experienceScore = MutableStateFlow("1")
    val experienceScore = _experienceScore
    fun setExperienceScore( num: String){
        _experienceScore.value = num
    }

    fun toggleDropdown(){}

    fun sendReview(){
        val newReview = Review(1,
            reviewList.maxOf { review ->
            review.reviewId
        }+1, _comment.value)
        reviewList.add(newReview)
    }

}