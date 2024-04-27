package com.example.rutescompartidesapp.view.value_experience

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.rutescompartidesapp.data.domain.OrderForList
import com.example.rutescompartidesapp.data.domain.RouteForList
import com.example.rutescompartidesapp.data.domain.review.Review
import com.example.rutescompartidesapp.utils.LocalConstants
import com.example.rutescompartidesapp.utils.LocalConstants.reviewList
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

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
        _isDropdownExpanded.value = false
    }

    fun toggleDropdown(){
        _isDropdownExpanded.value = !_isDropdownExpanded.value
    }

    fun sendReview(route: RouteForList, order: OrderForList, navHost: NavController){

        val driverID = route.userID
        val userToReviewID = order.userID
        val reviewID = reviewList.maxOf { review ->
            review.reviewId
        } + 1

        val newReview = Review(
            reviewId =  reviewID,
            userId = driverID,
            userToReviewId = userToReviewID,
            score = _experienceScore.value.toInt(),
            reviewComment = _comment.value
        )
        reviewList.add(newReview)
        // Modify the status of the interaction
        LocalConstants.interactionList.first { it.routeID == route.routeID &&
                it.orderID == order.orderID}.status = "Valorada"

        viewModelScope.launch {
            _showInteractionToastChannel.send(true)
        }

        navHost.popBackStack()
    }

    // Toast d'interacció
    private val _showInteractionToastChannel = Channel<Boolean>()
    val showInteractionToastChannel = _showInteractionToastChannel.receiveAsFlow()

}