package com.example.rutescompartidesapp.view.routes_order_list.viewmodels

import androidx.lifecycle.ViewModel
import com.example.rutescompartidesapp.data.domain.ListQuery
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.Instant
import java.time.ZoneId
import java.util.Calendar

class FilterPopupViewModel: ViewModel() {

    private val _isCondicionsPopupShowing = MutableStateFlow(false)
    val isCondicionsPopupShowing = _isCondicionsPopupShowing

    private val _puntSortidaText = MutableStateFlow("")
    val puntSortidaText = _puntSortidaText.asStateFlow()

    private val _puntArribadaText = MutableStateFlow("")
    val puntArribadaText = _puntArribadaText.asStateFlow()

    private val _dataSortidaText = MutableStateFlow("")
    val dataSortidaText = _dataSortidaText.asStateFlow()

    private val _horaSortidaText = MutableStateFlow("")
    val horaSortidaText = _horaSortidaText.asStateFlow()

    // Etiquetes
    private val _etiquetesText = MutableStateFlow("")
    val etiquetesText = _etiquetesText.asStateFlow()

    private val _etiquetesError = MutableStateFlow(false)
    val etiquetesError = _etiquetesError.asStateFlow()

    private val _etiquetesList = MutableStateFlow(mutableListOf<String>())
    val etiquetesList = _etiquetesList.asStateFlow()

    fun onEtiquetesAddToListChange(etiqueta: String){
        _etiquetesList.value.add(etiqueta)
        _etiquetesText.value = ""
    }

    // Reasigna el valor de la llista de etiquetas sense l'etiqueta que volem eliminar
    fun onEtiquetaDelete(etiqueta: String){
        _etiquetesList.value = _etiquetesList.value.filter { etiquetaFromList ->
            etiquetaFromList != etiqueta
        }.toMutableList()
    }

    fun onEtiquetesErrorChange(isError: Boolean){
        _etiquetesError.value = isError
    }

    private val _popupIsShowing = MutableStateFlow(false)
    val popupIsShowing = _popupIsShowing

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
    // Resets the values of the popup
    fun onPopupShow(isShowing: Boolean){
        _popupIsShowing.value = isShowing
        if (!isShowing){
            _puntSortidaText.value = ""
            _puntArribadaText.value = ""
            _horaSortidaText.value = ""
            _dataSortidaText.value = ""
            _isIsoterm.value = false
            _isRefrigerat.value = false
            _isCongelat.value = false
            _isSenseHumitat.value = false
            _isCondicionsPopupShowing.value = false
            _etiquetesError.value = false
            _etiquetesText.value = ""
            _etiquetesList.value = mutableListOf()
        }
    }
    fun onCondicionsPopupShow(isShowing: Boolean){
        _isCondicionsPopupShowing.value = isShowing
    }

    fun onEtiquetesChange(text: String){
        _etiquetesText.value = text
    }
    fun onPuntSortidaChange(text: String){
        _puntSortidaText.value = text
    }
    fun onPuntArribadaChange(text: String){
        _puntArribadaText.value = text
    }

    fun onDataSortidaChange(text: String){
        _dataSortidaText.value = text
    }
    fun onHoraArribadaChange(text: String){
        _horaSortidaText.value = text
    }

    // Expand extra filters
    private val _extraFiltersAreShowing = MutableStateFlow(false)
    val extraFiltersAreShowing = _extraFiltersAreShowing

    fun onExtraFiltersToggle(){
        _extraFiltersAreShowing.value = !_extraFiltersAreShowing.value
    }

    // Date Picker Dialog
    private val _datePickerDialogIsShowing = MutableStateFlow(false)
    val datePickerDialogIsShowing = _datePickerDialogIsShowing

    fun onDatePickerDialogShow(isShowing: Boolean){
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
             _dataSortidaText.value = "${date.dayOfMonth}/${date.monthValue}/${date.year}"
        }
        _datePickerDialogIsShowing.value = false
    }

    // Time Picker Dialog
    private val _timePickerDialogIsShowing = MutableStateFlow(false)
    val timePickerDialogIsShowing = _timePickerDialogIsShowing

    fun onTimePickerDialogShow(isShowing: Boolean){
        _timePickerDialogIsShowing.value = isShowing
    }

    /**
     * Converts the time picked from the TimePickerDialog to a string and updates the value of
     * [_horaSortidaText] to the new value and dismisses the dialog
     * @param timePicked: The time picked from the TimePickerDialog
     */
    fun onTimePickerDialogConfirm(timePicked: Calendar ){
        timePicked.timeInMillis.let {
        val time = Instant.ofEpochMilli(it).atZone(ZoneId.systemDefault()).toLocalTime()
        _horaSortidaText.value = "${time.hour}:${time.minute}"
    }
    _timePickerDialogIsShowing.value = false
    }
}