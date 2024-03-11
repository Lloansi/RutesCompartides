package com.example.rutescompartidesapp.view.publish_order

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.Instant
import java.time.ZoneId
import java.util.Calendar

class PublishOrderViewModel: ViewModel(){

    private val _step = MutableStateFlow(1)
    val step = _step

    fun nextStep(){
        _step.value = _step.value + 1
    }

    fun previousStep(){
        _step.value = _step.value - 1
    }

    // Internal order name
    private val _internalOrderName = MutableStateFlow("")
    val internalOrderName = _internalOrderName
    fun setInternalOrderName(name: String){
        _internalOrderName.value = name
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

    // End location name
    private val _destinationName = MutableStateFlow("")
    val destinationName = _destinationName

    fun setDestinationName(name: String){
        _destinationName.value = name
    }

    // Step location 1 coordinates
    private val _destinationLocation = MutableStateFlow(listOf<Double>())
    val destinationLocation = _destinationLocation

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

    private val _tagsList = MutableStateFlow(mutableListOf<String>())
    val tagsList = _tagsList.asStateFlow()

    fun onTagsAddToListChange(etiqueta: String){
        _tagsList.value.add(etiqueta)
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

    // MinTimeArrival
    private val _minTimeArrivalText = MutableStateFlow("")
    val minTimeArrivalText = _minTimeArrivalText.asStateFlow()

    fun onMinTimeArrivalChange(text: String){
        _minTimeArrivalText.value = text
    }

    // MaxTimeArrival
    private val _maxTimeArrivalText = MutableStateFlow("")
    val maxTimeArrivalText = _maxTimeArrivalText.asStateFlow()

    fun onMaxTimeArrivalTextChange(text: String){
        _maxTimeArrivalText.value = text
    }

    // Date Picker Dialog
    private val _datePickerDialogIsShowing = MutableStateFlow(false)
    val datePickerDialogIsShowing = _datePickerDialogIsShowing

    // Is Min time or Max time
    private val _isMinTime = MutableStateFlow(true)
    private val isMinTime = _isMinTime.asStateFlow()
    fun onDatePickerDialogShow(isMin: Boolean, isShowing: Boolean){
        _isMinTime.value = isMin
        _datePickerDialogIsShowing.value = isShowing
    }
    /**
     * Converts the time picked from the DatePickerDialog to a string and updates the value of
     * [_dataSortidaText] to the new value and dismisses the dialog
     * @param datePicked Long: The time picked from the TimePickerDialog
     */
    fun onDatePickerDialogConfirm(datePicked: Long ){
        datePicked.let {
            val date = Instant.ofEpochMilli(it).atZone(ZoneId.systemDefault()).toLocalDate()
            if (isMinTime.value){
                _minTimeArrivalText.value = "${date.dayOfMonth}/${date.monthValue}/${date.year}"
            } else {
                _maxTimeArrivalText.value = "${date.dayOfMonth}/${date.monthValue}/${date.year}"
            }
        }
        _datePickerDialogIsShowing.value = false
    }

    // FlexDateCheckbox
    private val _isFlexDate = MutableStateFlow(false)
    val isFlexDate = _isFlexDate.asStateFlow()

    fun onFlexDateChange(){
        _isFlexDate.value = !_isFlexDate.value
    }

    // WantsCarpoolCheckbox
    private val _wantsCarpool = MutableStateFlow(false)
    val wantsCarpool = _wantsCarpool.asStateFlow()

    fun onWantsCarpoolChange(){
        _wantsCarpool.value = !_wantsCarpool.value
    }

    // Next button
    private val _isFirstFormCompleted = MutableStateFlow(false)
    val isFirstFormCompleted = _isFirstFormCompleted
    fun checkAllValues(){
        _isFirstFormCompleted.value = _internalOrderName.value.isNotEmpty() &&
                _originName.value.isNotEmpty() &&
                _destinationName.value.isNotEmpty() &&
                _minTimeArrivalText.value.isNotEmpty()
    }



}