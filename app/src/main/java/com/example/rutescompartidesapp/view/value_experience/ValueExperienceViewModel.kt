package com.example.rutescompartidesapp.view.value_experience

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.rutescompartidesapp.data.domain.orders.Orders
import com.example.rutescompartidesapp.data.domain.review.Review
import com.example.rutescompartidesapp.data.domain.routes.Routes
import com.example.rutescompartidesapp.utils.LocalConstants
import com.example.rutescompartidesapp.utils.LocalConstants.reviewList
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
/**
 * ViewModel for managing the value experience screen.
 */
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
    /**
     * Sets the comment text.
     * @param text The comment text.
     */
    fun setComment(text: String){
        _comment.value = text
    }

    // Score
    private val _isDropdownExpanded = MutableStateFlow(false)
    val isDropdownExpanded = _isDropdownExpanded
    private val _experienceScore = MutableStateFlow("1")
    val experienceScore = _experienceScore
    /**
     * Sets the experience score.
     * @param num The experience score.
     */
    fun setExperienceScore( num: String){
        _experienceScore.value = num
        _isDropdownExpanded.value = false
    }

    fun toggleDropdown(){
        println("HA ENTRADOOOOOOOOOOO")
        _isDropdownExpanded.value = !_isDropdownExpanded.value
        println(_isDropdownExpanded.value)
    }
    /**
     * Sends a review for a route and order.
     * @param route The route for which the review is sent.
     * @param order The order for which the review is sent.
     * @param navHost The NavController for navigation.
     */
    fun sendReview(route: Routes, order: Orders, navHost: NavController){

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