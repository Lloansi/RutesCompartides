package com.example.rutescompartidesapp.view.publish_order

import androidx.lifecycle.ViewModel
import com.example.rutescompartidesapp.data.domain.OrderForList
import com.example.rutescompartidesapp.view.routes_order_list.ListConstants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.Instant
import java.time.ZoneId

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

    // Destination location coordinates
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

    // Data Min Popup
    private val _isDataMinPopupShowing = MutableStateFlow(false)
    val isDataMinPopupShowing = _isDataMinPopupShowing
    fun onDataMinPopupShow(isShowing: Boolean){
        _isDataMinPopupShowing.value = isShowing
    }
    // Data Max Popup
    private val _isDataMaxPopupShowing = MutableStateFlow(false)
    val isDataMaxPopupShowing = _isDataMaxPopupShowing
    fun onDataMaxPopupShow(isShowing: Boolean){
        _isDataMaxPopupShowing.value = isShowing
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
    private val _isSecondFormCompleted = MutableStateFlow(false)
    val isSecondFormCompleted = _isSecondFormCompleted
    fun checkAllValues(){
        when (step.value) {
            1 -> {
                _isFirstFormCompleted.value = _internalOrderName.value.isNotEmpty() &&
                        _originName.value.isNotEmpty() &&
                        _destinationName.value.isNotEmpty() &&
                        _minTimeArrivalText.value.isNotEmpty()
                if (_isFirstFormCompleted.value){
                    nextStep()
                }
            }
            2 -> {
            _isSecondFormCompleted.value = _packagesNum.value.isNotEmpty()
                if (_isSecondFormCompleted.value){
                    nextStep()
                }
            }
        }

    }
    // Screen 2

    // Number of packages

    private val _packagesNum = MutableStateFlow("")
    val packagesNum = _packagesNum
    fun setPackagesNum(num: String){
        _packagesNum.value = num
    }
    // Packages are fragile
    private val _arePackagesFragile = MutableStateFlow(false)
    val arePackagesFragile = _arePackagesFragile
    fun onPackagesFragileChange(){
        _arePackagesFragile.value = !_arePackagesFragile.value
    }

    // Packages measurements

    private val _packagesLength = MutableStateFlow("")
    val packagesLength = _packagesLength.asStateFlow()
    fun setPackagesLength(length: String){
        _packagesLength.value = length
    }

    private val _packagesWidth = MutableStateFlow("")
    val packagesWidth = _packagesWidth.asStateFlow()
    fun setPackagesWidth(width: String){
        _packagesWidth.value = width
    }

    private val _packagesHeight = MutableStateFlow("")
    val packagesHeight = _packagesHeight.asStateFlow()
    fun setPackagesHeight(height: String){
        _packagesHeight.value = height
    }

    private val _packagesWeight = MutableStateFlow("")
    val packagesWeight = _packagesWeight.asStateFlow()
    fun setPackagesWeight(weight: String){
        _packagesWeight.value = weight
    }

    // Notification
    private val _wantsDeliveryNotification = MutableStateFlow(false)
    val wantsDeliveryNotification = _wantsDeliveryNotification
    fun onWantsDeliveryNotificationChange(){
        _wantsDeliveryNotification.value = !_wantsDeliveryNotification.value
    }

    // Screen 3

    // Delivery contact
    // TODO Aquí hauria de ser un usuari, no un string
    val deliveryContact = MutableStateFlow("Ivan Martínez")
    val deliveryTelephoneNumber = MutableStateFlow("61234567")
    // TODO Aquí hauria de ser les dades reals del contacte del punt habitual
    val puntHabitualData = MutableStateFlow("\nCarrer de la Llibertat, 1, 08001 Barcelona\nTelèfon: 61234567")

    // Delivery note
    private val _deliveryNote = MutableStateFlow("")
    val deliveryNote = _deliveryNote


    private val _isDeliveryContactDataChecked = MutableStateFlow(false)
    val isDeliveryContactDataChecked = _isDeliveryContactDataChecked

    fun onDeliveryContactDataCheck() {

        _isDeliveryContactDataChecked.value = !_isDeliveryContactDataChecked.value

    }
    fun appendInfoToDeliveryNote(type: String){
        if (type == "puntHabitual"){
            if (_isPuntHabitualDataChecked.value){
                _deliveryNote.value += "\n${puntHabitualData.value}"
            } else {
                _deliveryNote.value = _deliveryNote.value.replace(puntHabitualData.value,"")
            }
        } else if (type == "userInfo") {
            val userInfo =
                "\nRecollida: ${deliveryContact.value}\nTelèfon: ${deliveryTelephoneNumber.value}"
            if (_isDeliveryContactDataChecked.value) {
                _deliveryNote.value += userInfo
            } else {
                _deliveryNote.value = _deliveryNote.value.replace(userInfo,"")
            }
        }

    }
    private val _isPuntHabitualDataChecked = MutableStateFlow(false)
    val isPuntHabitualDataChecked = _isPuntHabitualDataChecked
    fun onPuntHabitualDataCheck(){
        _isPuntHabitualDataChecked.value = !_isPuntHabitualDataChecked.value
    }

    fun setDeliveryNote(text: String){
        _deliveryNote.value = text
    }

    // Comment
    private val _comment = MutableStateFlow("")
    val comment = _comment

    fun setComment(text: String){
        _comment.value = text
    }

    private val _orderAdded = MutableStateFlow(false)
    val orderAdded = _orderAdded.asStateFlow()
    fun addOrder(){
        // TODO Cambiar la classe de la order i fer servir la oficial
        val newOrder = OrderForList(user = "Admin",
            orderID = 0,
            orderName = _internalOrderName.value,
            puntSortida = _originName.value,
            puntArribada = _destinationName.value,
            dataSortida = _minTimeArrivalText.value,
            horaSortida = _maxTimeArrivalText.value,
            isIsoterm = _isIsoterm.value,
            isRefrigerat = _isRefrigerat.value,
            isCongelat = _isCongelat.value,
            isSenseHumitat = _isSenseHumitat.value,
            etiquetes = tagsList.value
        )
        // TODO Fer un POST a la API per duplicar la ruta

        if (ListConstants.orderList.add(newOrder)){
            onOrderAdded(true)
        }
    }

    fun onOrderAdded(isOrderAdded: Boolean){
        _orderAdded.value = isOrderAdded
    }



}