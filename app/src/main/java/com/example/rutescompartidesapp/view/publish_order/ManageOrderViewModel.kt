package com.example.rutescompartidesapp.view.publish_order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rutescompartidesapp.data.domain.Location.idescat.Municipi
import com.example.rutescompartidesapp.data.domain.orders.Orders
import com.example.rutescompartidesapp.data.domain.routes.SharedDataRouteOrder
import com.example.rutescompartidesapp.data.network.GoogleLocation.repository.GoogleLocationsRepository
import com.example.rutescompartidesapp.data.network.idescat.repository.idescatRepository
import com.example.rutescompartidesapp.utils.LocalConstants
import com.example.rutescompartidesapp.utils.distanceBetweenPoints
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.osmdroid.util.GeoPoint
import java.time.Instant
import java.time.ZoneId
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

/**
 * ViewModel for managing the order creation process.
 *
 * @property googleLocationsRepository Repository for fetching Google locations.
 * @property idescatRepository Repository for fetching Idescat data.
 */
@HiltViewModel
    class ManageOrderViewModel @Inject constructor(
    private val googleLocationsRepository: GoogleLocationsRepository,
    private val idescatRepository: idescatRepository
): ViewModel(){


    private val _locations = MutableStateFlow(listOf<Municipi>())

    init {
        viewModelScope.launch {
            _locations.value = idescatRepository.getAllMunicipis()
            //_locations.value = googleLocationsRepository.getAllCities(autonomousCommunityLat = CATALONIA_LAT, autonomousCommunityLng = CATALONIA_LNG, radius = 3200)
        }
    }

    private val _userID = MutableStateFlow(0)
    val userID = _userID.asStateFlow()

    fun setUserID(id: Int){
        _userID.value = id
        deliveryContact.value = LocalConstants.userList.first{ user -> user.userId == id }.name
        deliveryTelephoneNumber.value = LocalConstants.userList.first{ user -> user.userId == id }.phone.toString()
    }

    private val _step = MutableStateFlow(1)
    val step = _step

    private fun nextStep(){
        _step.value = _step.value + 1
    }
    fun resetStep(){
        _step.value = 1
    }
    fun previousStep(){
        _step.value = _step.value - 1
    }

    // Internal order name
    private val _internalOrderName = MutableStateFlow("")
    val internalOrderName = _internalOrderName
    fun setInternalOrderName(name: String){
        _internalOrderName.value = name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    }

    // Start location name
    private val _originName = MutableStateFlow("")
    val originName = _originName

    fun setOriginName(name: String){
        _originName.value = name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    }

    // Start location coordinates
    private val _originLocation = MutableStateFlow(GeoPoint(0.0, 0.0))

    // End location name
    private val _destinationName = MutableStateFlow("")
    val destinationName = _destinationName

    fun setDestinationName(name: String){
        _destinationName.value = name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    }

    // Destination location coordinates
    private val _destinationLocation = MutableStateFlow(GeoPoint(0.0, 0.0))

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
    private val _dataSortida = MutableStateFlow("")
    val dataSortida = _dataSortida.asStateFlow()

    fun onDataSortidaChange(text: String){
        _dataSortida.value = text
    }

    // MaxTimeArrival
    private val _dataArribada = MutableStateFlow("")
    val dataArribada = _dataArribada.asStateFlow()

    fun onDataArribadaChange(text: String){
        _dataArribada.value = text
    }

    // Date Picker Dialog
    private val _datePickerDialogIsShowing = MutableStateFlow(false)
    val datePickerDialogIsShowing = _datePickerDialogIsShowing

    // Is Depart date time or arrival date time
    private val _isDateDepart = MutableStateFlow(true)
    private val _isTimeDepart = MutableStateFlow(true)

    fun onDatePickerDialogShow(isMin: Boolean, isShowing: Boolean){
        _isDateDepart.value = isMin
        _datePickerDialogIsShowing.value = isShowing
    }
    /**
     * Converts the time picked from the DatePickerDialog to a string and updates the value of
     * [_dataSortida] or [_dataArribada] to the new value and dismisses the dialog
     * @param datePicked Long: The time picked from the TimePickerDialog
     */
    fun onDatePickerDialogConfirm(datePicked: Long ){
        datePicked.let {
            val date = Instant.ofEpochMilli(it).atZone(ZoneId.systemDefault()).toLocalDate()
            if (_isDateDepart.value){
                _dataSortida.value = "${date.dayOfMonth}/${date.monthValue}/${date.year}"
            } else {
                _dataArribada.value = "${date.dayOfMonth}/${date.monthValue}/${date.year}"
            }
        }
        _datePickerDialogIsShowing.value = false
    }

    // Time

    private val _horaSortidaText = MutableStateFlow("")
    val horaSortidaText = _horaSortidaText.asStateFlow()
    fun onTimeDepartChange(text: String){
        _horaSortidaText.value = text
    }
    private val _horaArribadaText = MutableStateFlow("")
    val horaArribadaText = _horaArribadaText.asStateFlow()
    fun onTimeArrivalChange(text: String){
        _horaArribadaText.value = text
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
     * [_horaSortidaText] or [_horaArribadaText] to the new value and dismisses the dialog
     * @param timePicked: The time picked from the TimePickerDialog
     */
    fun onTimePickerDialogConfirm(timePicked: Calendar){

        timePicked.timeInMillis.let {
            val time = Instant.ofEpochMilli(it).atZone(ZoneId.systemDefault()).toLocalTime()
            if (_isTimeDepart.value) {
                _horaSortidaText.value = "${time.hour}:${String.format("%02d", time.minute)}"
            } else {
                _horaArribadaText.value = "${time.hour}:${String.format("%02d", time.minute)}"
            }
        }
        _timePickerDialogIsShowing.value = false
    }


    // FlexDateCheckbox
    private val _isFlexDate = MutableStateFlow(false)
    val isFlexDate = _isFlexDate.asStateFlow()

    fun onFlexDateChange(){
        _isFlexDate.value = !_isFlexDate.value
        if (_isFlexDate.value){
            _dataArribada.value = ""
            _horaArribadaText.value = ""
        }
    }

    // WantsCarpoolCheckbox
    private val _wantsCarpool = MutableStateFlow(false)
    val wantsCarpool = _wantsCarpool.asStateFlow()

    fun onWantsCarpoolChange(){
        _wantsCarpool.value = !_wantsCarpool.value
    }

    // Next button
    private val _isFirstFormCompleted = MutableStateFlow(false)
    private val _isSecondFormCompleted = MutableStateFlow(false)
    /**
     * Checks if all the required values are filled based on the current step of the form.
     * If all values are filled, proceeds to the next step; otherwise, shows a popup indicating missing values.
     */
    fun checkAllValues(){
        when (step.value) {
            1 -> {
                _isFirstFormCompleted.value = _internalOrderName.value.isNotEmpty() &&
                        _originName.value.isNotEmpty() &&
                        _destinationName.value.isNotEmpty() &&
                        _dataSortida.value.isNotEmpty() &&
                         _horaSortidaText.value.isNotEmpty() &&
                        if (_isFlexDate.value) true else _dataArribada.value.isNotEmpty() &&
                                if (_isFlexDate.value) true else _horaArribadaText.value.isNotEmpty()

                viewModelScope.launch {
                    val originLoc = _locations.value.filter { municipi -> municipi.content == _originName.value }
                    val destLoc = _locations.value.filter { municipi -> municipi.content == _destinationName.value }
                    if (originLoc.isNotEmpty()){
                        _originLocation.value = getMunicipiGeoPointIdescatAPI(originLoc.first().id)!!
                    }
                    if (destLoc.isNotEmpty()){
                        _destinationLocation.value = getMunicipiGeoPointIdescatAPI(destLoc.first().id)!!
                    }
                }

                checkIfEmpty(1)
                if (_isFirstFormCompleted.value){
                    nextStep()
                }
            }
            2 -> {
            _isSecondFormCompleted.value = _packagesNum.value.isNotEmpty() &&
                    _packagesLength.value.isNotEmpty() &&
                    _packagesWidth.value.isNotEmpty() &&
                    _packagesHeight.value.isNotEmpty() &&
                    _packagesWeight.value.isNotEmpty()
                checkIfEmpty(2)
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
    private val deliveryContact = MutableStateFlow(LocalConstants.userList.first{ user -> user.userId == userID.value }.name)
    private val deliveryTelephoneNumber = MutableStateFlow(LocalConstants.userList.first{ user -> user.userId == userID.value }.phone.toString())
    // TODO Aquí hauria de ser les dades reals del contacte del punt habitual
    private val puntHabitualData = MutableStateFlow("\nCarrer de la Llibertat, 1, 08001 Barcelona\nTelèfon: 61234567")

    // Delivery note
    private val _deliveryNote = MutableStateFlow("")
    val deliveryNote = _deliveryNote


    private val _isDeliveryContactDataChecked = MutableStateFlow(false)
    val isDeliveryContactDataChecked = _isDeliveryContactDataChecked

    fun onDeliveryContactDataCheck() {

        _isDeliveryContactDataChecked.value = !_isDeliveryContactDataChecked.value

    }

    /**
     * Appends the information of the delivery contact to the delivery note.
     * @param type: The type of information to append.
     */
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
        _comment.value = text.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    }

    private val _orderAdded = MutableStateFlow(false)
    val orderAdded = _orderAdded.asStateFlow()

    /**
     * Adds a new order to the list of orders with the values of the form fields.
     * @param userID: The ID of the user who is creating the order.
     */
    fun addOrder(userID: Int){
        // TODO Cambiar la classe de la order i fer servir la oficial
        val lastOrderID = LocalConstants.orderList!!.maxByOrNull { order -> order.orderID }!!.orderID
        val distance = distanceBetweenPoints(_originLocation.value.latitude, _originLocation.value.longitude, _destinationLocation.value.latitude, _destinationLocation.value.longitude)

        val newOrder = Orders(userID = userID,
            orderID = lastOrderID+1,
            orderName = _internalOrderName.value,
            puntSortida = _originName.value,
            puntArribada = _destinationName.value,
            dataSortida = _dataSortida.value,
            horaSortida = _horaSortidaText.value,
            dataArribada = _dataArribada.value,
            horaArribada = _horaArribadaText.value,
            isIsoterm = _isIsoterm.value,
            isRefrigerat = _isRefrigerat.value,
            isCongelat = _isCongelat.value,
            isSenseHumitat = _isSenseHumitat.value,
            etiquetes = tagsList.value,
            packagesNum = _packagesNum.value.toInt(),
            packagesLength = _packagesLength.value.toFloat(),
            packagesWidth = _packagesWidth.value.toFloat(),
            packagesHeight = packagesHeight.value.toFloat(),
            packagesWeight = _packagesWeight.value.toFloat(),
            packagesFragile = _arePackagesFragile.value,
            co2Saved = 0.0f,
            distance = distance.toFloat(),
            comment = _comment.value,
            startPoint = _originLocation.value,
            endPoint = _destinationLocation.value
        )

        if (LocalConstants.orderList.add(newOrder)){
            onOrderAdded(true)
        }
    }

    fun onOrderAdded(isOrderAdded: Boolean){
        _orderAdded.value = isOrderAdded
        clearValues()
    }

    // Edit Order
    // Get the order
    private val _orderToEdit = MutableStateFlow<Orders?>(null)
    val orderToEdit = _orderToEdit.asStateFlow()

    /**
     * Retrieves the order with the specified ID from the list of orders and updates the form fields
     * with the order's values.
     * @param orderID: The ID of the order to retrieve.
     */
    fun getOrder(orderID: Int) {
        _orderToEdit.value =  LocalConstants.orderList!!.find { order -> order.orderID == orderID }!!
        updateOrderInfo()
    }

    /**
     * Updates the form fields with the values of the order to edit.
     */
    private fun updateOrderInfo(){
        _internalOrderName.value = _orderToEdit.value!!.orderName
        _originName.value = _orderToEdit.value!!.puntSortida
        _destinationName.value = _orderToEdit.value!!.puntArribada
        _dataSortida.value = _orderToEdit.value!!.dataSortida
        _dataArribada.value = _orderToEdit.value!!.horaSortida
        _horaArribadaText.value = _orderToEdit.value!!.horaArribada
        _horaSortidaText.value = _orderToEdit.value!!.horaSortida
        _isIsoterm.value = _orderToEdit.value!!.isIsoterm
        _isRefrigerat.value = _orderToEdit.value!!.isRefrigerat
        _isCongelat.value = _orderToEdit.value!!.isCongelat
        _isSenseHumitat.value = _orderToEdit.value!!.isSenseHumitat
        _packagesNum.value = _orderToEdit.value!!.packagesNum.toString()
        _packagesLength.value = _orderToEdit.value!!.packagesLength.toString()
        _packagesWidth.value = _orderToEdit.value!!.packagesWidth.toString()
        _packagesHeight.value = _orderToEdit.value!!.packagesHeight.toString()
        _packagesWeight.value = _orderToEdit.value!!.packagesWeight.toString()
        _arePackagesFragile.value = _orderToEdit.value!!.packagesFragile
        _comment.value = _orderToEdit.value!!.comment ?: ""

        // Etiquetes i freqüència
        _orderToEdit.value?.etiquetes.let { etiquetes ->
            if (etiquetes != null) {
                _tagsList.value = etiquetes
            }
        }
    }

    /**
     * Updates the order with the values of the form fields.
     * @param userID: The ID of the user who is editing the order.
     */
    fun updateOrder(userID: Int){
        val distance = distanceBetweenPoints(_originLocation.value.latitude, _originLocation.value.longitude, _destinationLocation.value.latitude, _destinationLocation.value.longitude)
        val updatedOrder = Orders(userID = userID,
            orderID = _orderToEdit.value!!.orderID,
            orderName = _internalOrderName.value,
            puntSortida = _originName.value,
            puntArribada = _destinationName.value,
            dataSortida = _dataSortida.value,
            horaSortida = _horaSortidaText.value,
            dataArribada = _dataArribada.value,
            horaArribada = _horaArribadaText.value,
            isIsoterm = _isIsoterm.value,
            isRefrigerat = _isRefrigerat.value,
            isCongelat = _isCongelat.value,
            isSenseHumitat = _isSenseHumitat.value,
            etiquetes = tagsList.value,
            packagesNum = _packagesNum.value.toInt(),
            packagesLength = _packagesLength.value.toFloat(),
            packagesWidth = _packagesWidth.value.toFloat(),
            packagesHeight = packagesHeight.value.toFloat(),
            packagesWeight = _packagesWeight.value.toFloat(),
            packagesFragile = _arePackagesFragile.value,
            co2Saved = 0.0f,
            distance = distance.toFloat(),
            comment = _comment.value,
            startPoint = _originLocation.value,
            endPoint = _destinationLocation.value
        )
        // TODO Fer un PUT a la API per actualitzar la ruta
        if (LocalConstants.orderList!!.removeIf { order -> order.orderID == updatedOrder.orderID }){
            LocalConstants.orderList.add(updatedOrder)
            onOrderAdded(true)
        }
    }

    // Control de errors
    private val _screen1Errors = MutableStateFlow(List(7) { false })
    val screen1Errors = _screen1Errors.asStateFlow()

    private val _screen2Errors = MutableStateFlow(List(5) { false })
    val screen2Errors = _screen2Errors.asStateFlow()

    /**
     * Checks if any of the form fields are empty and updates the corresponding error list.
     * @param step: The step of the form to check.
     */
    private fun checkIfEmpty(step: Int){

        when(step){
            1 -> {
                val maxTimeisEmpty = !_isFlexDate.value

                val screen1Errors = listOf(
                    _internalOrderName.value.isEmpty(),
                    _originName.value.isEmpty(),
                    _destinationName.value.isEmpty(),
                    _dataSortida.value.isEmpty(),
                    _horaSortidaText.value.isEmpty(),
                    maxTimeisEmpty,
                    maxTimeisEmpty
                )

                _screen1Errors.value = screen1Errors
            }
            2 -> {
                val screen2Errors = listOf(
                    _packagesNum.value.isEmpty(),
                    _packagesLength.value.isEmpty(),
                    _packagesWidth.value.isEmpty(),
                    _packagesHeight.value.isEmpty(),
                    _packagesWeight.value.isEmpty(),
                    )
                _screen2Errors.value = screen2Errors
            }
        }
    }

    /**
     * Loads the information of a route into the order form fields.
     * @param sharedDataRouteOrder: The route to load.
     */
    fun loadRouteInfo(sharedDataRouteOrder: SharedDataRouteOrder){
        _originName.value = sharedDataRouteOrder.puntSortida
        _destinationName.value = sharedDataRouteOrder.puntArribada
        _dataSortida.value = sharedDataRouteOrder.dataSortida
        _dataArribada.value = sharedDataRouteOrder.dataArribada
        _isRefrigerat.value = sharedDataRouteOrder.isRefrigerat
        _isCongelat.value = sharedDataRouteOrder.isCongelat
        _isIsoterm.value = sharedDataRouteOrder.isIsoterm
        _isSenseHumitat.value = sharedDataRouteOrder.isSenseHumitat
    }

    /**
     * Clears the values of the form fields.
     */
    private fun clearValues(){
        _internalOrderName.value = ""
        _originName.value = ""
        _destinationName.value = ""
        _dataSortida.value = ""
        _dataArribada.value = ""
        _horaSortidaText.value = ""
        _horaArribadaText.value = ""
        _isIsoterm.value = false
        _isRefrigerat.value = false
        _isCongelat.value = false
        _isSenseHumitat.value = false
        _tagsList.value = listOf()
        _packagesNum.value = ""
        _packagesLength.value = ""
        _packagesWidth.value = ""
        _packagesHeight.value = ""
        _packagesWeight.value = ""
        _arePackagesFragile.value = false
        _comment.value = ""
        _isFirstFormCompleted.value = false
        _isSecondFormCompleted.value = false
        _screen1Errors.value = List(7) { false }
        _screen2Errors.value = List(5) { false }
        _destinationLocation.value = GeoPoint(0.0, 0.0)
        _originLocation.value = GeoPoint(0.0, 0.0)
        _isDeliveryContactDataChecked.value = false
        _isPuntHabitualDataChecked.value = false
        _deliveryNote.value = ""
        _wantsDeliveryNotification.value = false
        _wantsCarpool.value = false
        _isFlexDate.value = false
    }


    /**
     * Asynchronously retrieves the GeoPoint of a municipality from the Idescat API using its name.

     * @param cityName Name of the municipality.

     * @return GeoPoint representing the location of the municipality.
     **/
    private suspend fun getMunicipiGeoPointIdescatAPI(cityName: String): GeoPoint? {
        return withContext(Dispatchers.IO) {
            try {
                val cityInfo = idescatRepository.getLatLngMunicipi(cityName)
                GeoPoint(cityInfo?.lat ?: 0.0, cityInfo?.lng ?: 0.0)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
}