package com.example.rutescompartidesapp.view.publish_route

import androidx.lifecycle.ViewModel
import com.example.rutescompartidesapp.data.domain.RouteForList
import com.example.rutescompartidesapp.view.routes_order_list.ListConstants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.Instant
import java.time.ZoneId
import java.util.Calendar

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
    val stepName1 = _stepName1.asStateFlow()
    // Step location 2 name
    private val _stepName2 = MutableStateFlow("")
    val stepName2 = _stepName2.asStateFlow()
    // Step location 3 name
    private val _stepName3 = MutableStateFlow("")
    val stepName3 = _stepName3.asStateFlow()
    // Step location 4 name
    private val _stepName4 = MutableStateFlow("")
    val stepName4 = _stepName4.asStateFlow()
    // Step location 5 name
    private val _stepName5 = MutableStateFlow("")
    val stepName5 = _stepName5.asStateFlow()
    // Step location 6 name
    private val _stepName6 = MutableStateFlow("")
    val stepName6 = _stepName6.asStateFlow()

    private val _stepNameList = MutableStateFlow(listOf(stepName1.value, stepName2.value,
        stepName3.value, stepName4.value, stepName5.value, stepName6.value))
    val stepNameList = _stepNameList.asStateFlow()

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
            0 ->{
                _stepName1.value = name
                println("Step 1: ${_stepName1.value}/$name")
            }
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
        if (_routeFrequency.value != frequency){
            val updatedTagsList = _tagsList.value.toMutableList()

            if (_routeFrequency.value.lowercase() in updatedTagsList && frequency != "No es repeteix") {
                updatedTagsList.remove(_routeFrequency.value.lowercase())
                updatedTagsList.add(frequency.lowercase())
            } else if (_routeFrequency.value.lowercase() !in updatedTagsList && frequency != "No es repeteix"){
                updatedTagsList.add(frequency.lowercase())
            } else if (_routeFrequency.value.lowercase() in updatedTagsList && frequency == "No es repeteix"){
                updatedTagsList.remove(_routeFrequency.value.lowercase())
            }

            _routeFrequency.value = frequency
            _isDropdownExpanded.value = false

            _tagsList.value = updatedTagsList
        }
    }

    // Frequency Popup
    private val _isFreqPopupShowing = MutableStateFlow(false)
    val isFreqPopupShowing = _isFreqPopupShowing
    fun onFreqPopupShow(isShowing: Boolean){
        _isFreqPopupShowing.value = isShowing
    }

    // Depart
    private val _dateDepart = MutableStateFlow("")
    val dateDepart = _dateDepart.asStateFlow()

    // Arrival
    private val _dateArrival = MutableStateFlow("")
    val dateArrival = _dateArrival.asStateFlow()

    /*
    Is Depart or Arrival Date & Time ->
    Variables to check if the date or time that is being changed is the departure or arrival one
    */
    private val _isDateDepart = MutableStateFlow(true)
    private val isDateDepart = _isDateDepart.asStateFlow()
    private val _isTimeDepart = MutableStateFlow(true)
    private val isTimeDepart = _isDateDepart.asStateFlow()
    fun onDateDepartChange(text: String){
        _dateDepart.value = text
    }

    fun onDateArrivalChange(text: String){
        _dateArrival.value = text
    }

    // Date Picker Dialog
    private val _datePickerDialogIsShowing = MutableStateFlow(false)
    val datePickerDialogIsShowing = _datePickerDialogIsShowing
    fun onDatePickerDialogShow(isDepart: Boolean, isShowing: Boolean){
        _isDateDepart.value = isDepart
        _datePickerDialogIsShowing.value = isShowing
    }

    fun onDatePickerDialogConfirm(datePicked: Long ){
        datePicked.let {
            val date = Instant.ofEpochMilli(it).atZone(ZoneId.systemDefault()).toLocalDate()
            if (_isDateDepart.value){
                _dateDepart.value = "${date.dayOfMonth}/${date.monthValue}/${date.year}"

            } else {
                _dateArrival.value = "${date.dayOfMonth}/${date.monthValue}/${date.year}"
            }
        }
        _datePickerDialogIsShowing.value = false
    }

    // Time

    private val _timeDepartText = MutableStateFlow("")
    val timeDepartText = _timeDepartText.asStateFlow()
    fun onTimeDepartChange(text: String){
        _timeDepartText.value = text
    }
    private val _timeArrivalText = MutableStateFlow("")
    val timeArrivalText = _timeArrivalText.asStateFlow()
    fun onTimeArrivalChange(text: String){
        _timeArrivalText.value = text
    }

    // Time Picker Dialog
    private val _timePickerDialogIsShowing = MutableStateFlow(false)
    val timePickerDialogIsShowing = _timePickerDialogIsShowing

    fun onTimePickerDialogShow(isDepart: Boolean, isShowing: Boolean){
        _isTimeDepart.value = isDepart
        _timePickerDialogIsShowing.value = isShowing
    }

    /**
     * Converts the time picked from the TimePickerDialog to a string and updates the value of
     * [_timeDepartText] or [_timeArrivalText] to the new value and dismisses the dialog
     * @param timePicked: The time picked from the TimePickerDialog
     */
    fun onTimePickerDialogConfirm(timePicked: Calendar){
        timePicked.timeInMillis.let {
            val time = Instant.ofEpochMilli(it).atZone(ZoneId.systemDefault()).toLocalTime()
            if (_isTimeDepart.value) {
                _timeDepartText.value = "${time.hour}:${time.minute}"
            } else {
                _timeArrivalText.value = "${time.hour}:${time.minute}"
            }
        }
        _timePickerDialogIsShowing.value = false
    }

    // Next button
    private val _isFirstFormCompleted = MutableStateFlow(false)

    private val _isSecondFormCompleted = MutableStateFlow(false)

    private val _isFormCompletedPopup = MutableStateFlow(false)
    val isFormCompletedPopup = _isFormCompletedPopup

    fun checkAllValues(){
        when (step.value) {
            1 -> {
                _isFirstFormCompleted.value = _internalRouteName.value.isNotEmpty() &&
                        _originName.value.isNotEmpty() && _destinationName.value.isNotEmpty() &&
                        _dateDepart.value.isNotEmpty() && _dateArrival.value.isNotEmpty() &&
                        _timeDepartText.value.isNotEmpty() && _timeArrivalText.value.isNotEmpty()
                if (_isFirstFormCompleted.value) {
                    nextStep()
                } else {
                    missingValuesPopupToggle()
                }
            }
            2 -> {
                _isSecondFormCompleted.value = _availableSpace.value.isNotEmpty()
                        && _vehicle.value.isNotEmpty()
                if (_isSecondFormCompleted.value) {
                    nextStep()
                } else {
                    missingValuesPopupToggle()
                }
            }
        }
    }

    private fun missingValuesPopupToggle(){
        _isFormCompletedPopup.value = !_isFormCompletedPopup.value
    }

    // Screen 2

    // Max Detour KM
    private val _maxDetourKm = MutableStateFlow("")
    val maxDetourKm = _maxDetourKm.asStateFlow()

    fun setMaxDetourKm(km: String){
        _maxDetourKm.value = km
    }

    // Available Seats

    private val _availableSeats = MutableStateFlow("")
    val availableSeats = _availableSeats.asStateFlow()

    fun setSeats(seats: String){
        _availableSeats.value = seats
    }

    // Available Space
    private val _availableSpace = MutableStateFlow("")
    val availableSpace = _availableSpace.asStateFlow()

    fun setAvailableSpace(text: String){
        _availableSpace.value = text
    }
    // Cost KM
    private val _costKM = MutableStateFlow("")
    val costKM = _costKM.asStateFlow()

    fun setCostKM(cost: String){
        _costKM.value = cost
    }

    // Vehicle
    private val _vehicle = MutableStateFlow("")
    val vehicle = _vehicle.asStateFlow()

    fun setVehicle(vehicle: String){
        _vehicle.value = vehicle
    }

    // Screen 3
    // Condicions de transport chips
    private val _isIsoterm = MutableStateFlow(false)
    val isIsoterm = _isIsoterm.asStateFlow()

    private val _isRefrigerat = MutableStateFlow(false)
    val isRefrigerat = _isRefrigerat.asStateFlow()

    private val _isCongelat = MutableStateFlow(false)
    val isCongelat = _isCongelat.asStateFlow()

    private val _isSenseHumitat = MutableStateFlow(false)
    val isSenseHumitat = _isSenseHumitat.asStateFlow()

    fun onCheckChip(condition: String){
        when(condition) {
            "Isoterm" -> _isIsoterm.value = !_isIsoterm.value
            "Refrigerat" -> _isRefrigerat.value = !_isRefrigerat.value
            "Congelat" -> _isCongelat.value = !_isCongelat.value
            "SenseHumitat" -> _isSenseHumitat.value = !_isSenseHumitat.value
        }

    }

    // tags
    private val _tagsText = MutableStateFlow("")
    val tagsText = _tagsText.asStateFlow()

    private val _tagsError = MutableStateFlow(false)
    val tagsError = _tagsError.asStateFlow()

    private val _tagsList = MutableStateFlow(listOf<String>())
    val tagsList = _tagsList.asStateFlow()

    fun onTagsAddToListChange(etiqueta: String){
        val updatedTagsList = _tagsList.value.toMutableList()
        updatedTagsList.add(etiqueta.lowercase())
        _tagsList.value = updatedTagsList
        _tagsText.value = ""
    }

    // Reasigna el valor de la llista de etiquetas sense l'etiqueta que volem eliminar
    fun onTagDelete(etiqueta: String){
        _tagsList.value = _tagsList.value.filter { etiquetaFromList ->
            etiquetaFromList != etiqueta
        }.toMutableList()
    }

    fun onTagsErrorChange(isError: Boolean){
        _tagsError.value = isError
    }
    fun onTagsChange(text: String){
        _tagsText.value = text
    }

    // Popup Transport Conditions
    private val _isCondicionsPopupShowing = MutableStateFlow(false)
    val isCondicionsPopupShowing = _isCondicionsPopupShowing
    fun onCondicionsPopupShow(isShowing: Boolean){
        _isCondicionsPopupShowing.value = isShowing
    }

    // Cost KM Popup
    private val _isCostPopupShowing = MutableStateFlow(false)
    val isCostPopupShowing = _isCostPopupShowing
    fun onCostPopupShow(isShowing: Boolean){
        _isCostPopupShowing.value = isShowing
    }

    // Comment
    private val _comment = MutableStateFlow("")
    val comment = _comment

    fun setComment(comment: String){
        _comment.value = comment
    }

    private val _routeAdded = MutableStateFlow(false)
    val routeAdded = _routeAdded.asStateFlow()
    fun addRoute(){
        // TODO Cambiar la classe de la ruta i fer servir la oficial
        val newRoute = RouteForList(user = "Admin",
            routeID = 0,
            routeName = _internalRouteName.value,
            puntSortida = _originName.value,
            puntArribada = _destinationName.value,
            puntsIntermedis = stepNameList.value,
            dataSortida = _dateDepart.value,
            horaSortida = timeDepartText.value,
            dataArribada = dateArrival.value,
            horaArribada = timeArrivalText.value,
            isIsoterm = _isIsoterm.value,
            isRefrigerat = _isRefrigerat.value,
            isCongelat = _isCongelat.value,
            isSenseHumitat = _isSenseHumitat.value,
            etiquetes = tagsList.value
            )
        // TODO Fer un POST a la API per duplicar la ruta

        if (ListConstants.routeList.add(newRoute)){
            onRouteAdded(true)
        }
    }

    fun onRouteAdded(isRouteAdded: Boolean){
        _routeAdded.value = isRouteAdded
    }

}