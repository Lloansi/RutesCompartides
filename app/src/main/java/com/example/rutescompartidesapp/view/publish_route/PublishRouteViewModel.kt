package com.example.rutescompartidesapp.view.publish_route

import android.graphics.Point
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class PublishRouteViewModel: ViewModel(){

    private val _step = MutableStateFlow(1)
    val step = _step

    fun nextStep(){
        _step.value = _step.value + 1
    }

    fun previousStep(){
        _step.value = _step.value - 1
    }

    // Internal route name
    private val _internalRouteName = MutableStateFlow("")
    val internalRouteName = _internalRouteName
    fun setInternalRouteName(name: String){
        _internalRouteName.value = name
    }

    // Start location name
    private val _originName = MutableStateFlow("")
    val originName = _originName

    fun setOriginName(name: String){
        _originName.value = name
    }

    // Start location coordinates
    private val _originLocation = MutableStateFlow(listOf<Double>())
    val originLocation = _originLocation
    fun getOriginLocation(name: String){
        //TODO Usar algo para obtener las coordenadas de la dirección
        val coordinates = listOf<Double>()
        _originLocation.value = coordinates
    }

    // Step location 1 name
    private val _stepName1 = MutableStateFlow("")
    val stepName1 = _stepName1
    // Step location 2 name
    private val _stepName2 = MutableStateFlow("")
    val stepName2 = _stepName2
    // Step location 3 name
    private val _stepName3 = MutableStateFlow("")
    val stepName3 = _stepName3
    // Step location 4 name
    private val _stepName4 = MutableStateFlow("")
    val stepName4 = _stepName4
    // Step location 5 name
    private val _stepName5 = MutableStateFlow("")
    val stepName5 = _stepName5
    // Step location 6 name
    private val _stepName6 = MutableStateFlow("")
    val stepName6 = _stepName6


    private val _stepLocationsNumber = MutableStateFlow(1)
    val stepLocationsNumber = _stepLocationsNumber

    fun addStepLocation(){
        if (_stepLocationsNumber.value != 6) {
            _stepLocationsNumber.value = _stepLocationsNumber.value + 1
        }
    }
    fun removeStepLocation(){
        if (_stepLocationsNumber.value != 1) {
            _stepLocationsNumber.value = _stepLocationsNumber.value - 1
        }
    }
    fun setStepLocationName(number: Int, name: String){
        when(number){
            0 -> _stepName1.value = name
            1 -> _stepName2.value = name
            2 -> _stepName3.value = name
            3 -> _stepName4.value = name
            4 -> _stepName5.value = name
            5 -> _stepName6.value = name
        }
    }

    // End location name
    private val _destinationName = MutableStateFlow("")
    val destinationName = _destinationName

    fun setDestinationName(name: String){
        _destinationName.value = name
    }

    // Step location 1 coordinates
    private val _destinationLocation = MutableStateFlow(listOf<Double>())
    val destinationLocation = _destinationLocation

    // Frequency dropdown
    private val _isDropdownExpanded = MutableStateFlow(false)
    val isDropdownExpanded = _isDropdownExpanded

    fun toggleDropdown(){
        _isDropdownExpanded.value = !_isDropdownExpanded.value
    }
    private val _routeFrequency = MutableStateFlow("")
    val routeFrequency = _routeFrequency

    fun setRouteFrequency(frequency: String){
        _routeFrequency.value = frequency
        _isDropdownExpanded.value = false
    }


    // Next button
    private val _isFirstFormCompleted = MutableStateFlow(false)
    val isFirstFormCompleted = _isFirstFormCompleted
    fun checkAllValues(){
        _isFirstFormCompleted.value = _internalRouteName.value.isNotEmpty() &&
                _originName.value.isNotEmpty() &&
                _destinationName.value.isNotEmpty() &&
                _routeFrequency.value.isNotEmpty()
    }


}