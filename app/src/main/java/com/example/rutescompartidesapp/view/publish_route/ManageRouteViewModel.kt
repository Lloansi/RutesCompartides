package com.example.rutescompartidesapp.view.publish_route

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rutescompartidesapp.data.domain.Location.idescat.Municipi
import com.example.rutescompartidesapp.data.domain.routes.SharedDataRouteOrder
import com.example.rutescompartidesapp.data.domain.routes.Routes
import com.example.rutescompartidesapp.data.network.GoogleLocation.repository.GoogleLocationsRepository
import com.example.rutescompartidesapp.data.network.idescat.repository.idescatRepository
import com.example.rutescompartidesapp.utils.LocalConstants
import com.example.rutescompartidesapp.view.map.components.allOrders
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
import javax.inject.Inject

@HiltViewModel
class ManageRouteViewModel @Inject constructor(
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
    }

    private val _step = MutableStateFlow(1)
    val step = _step

    private fun nextStep(){
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
    private val _originLocation = MutableStateFlow(GeoPoint(0.0, 0.0))

    // Step location names
    private val _stepName1 = MutableStateFlow("")
    private val _stepName2 = MutableStateFlow("")
    private val _stepName3 = MutableStateFlow("")
    private val _stepName4 = MutableStateFlow("")
    private val _stepName5 = MutableStateFlow("")
    private val _stepName6 = MutableStateFlow("")

    private val _stepNameList = MutableStateFlow(listOf(_stepName1.value, _stepName2.value,
        _stepName3.value, _stepName4.value, _stepName5.value, _stepName6.value))
    val stepNameList = _stepNameList.asStateFlow()

    private val _stepLocationsNumber = MutableStateFlow(1)
    val stepLocationsNumber = _stepLocationsNumber

    fun addStepLocation(){
        if (_stepLocationsNumber.value != 6) {
            _stepLocationsNumber.value = _stepLocationsNumber.value + 1
        }
    }
    fun removeStepLocation(index: Int){
        if (_stepLocationsNumber.value != 1) {
            _stepLocationsNumber.value = _stepLocationsNumber.value - 1

            val updatedStepNameList = _stepNameList.value.toMutableList()
            if (index != 5) {
                for (i in index until _stepNameList.value.size - 1) {
                    updatedStepNameList[i] = _stepNameList.value[i+1]
                }
                updatedStepNameList[updatedStepNameList.size - 1] = ""
            } else {
                updatedStepNameList[index] = ""
            }

            updateStepNameList(updatedStepNameList)
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
        val newList =  listOf(_stepName1.value, _stepName2.value,
            _stepName3.value, _stepName4.value, _stepName5.value, _stepName6.value)
        updateStepNameList(newList)
    }

    private fun updateStepNameList(newList: List<String>){
        println(newList)
        _stepNameList.value = newList
    }

    // End location name
    private val _destinationName = MutableStateFlow("")
    val destinationName = _destinationName

    fun setDestinationName(name: String){
        _destinationName.value = name
    }

    // Step location 1 coordinates
    private val _destinationLocation = MutableStateFlow(GeoPoint(0.0, 0.0))

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
    private val _isTimeDepart = MutableStateFlow(true)
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
                _timeDepartText.value = "${time.hour}:${String.format("%02d", time.minute)}"
            } else {
                _timeArrivalText.value = "${time.hour}:${String.format("%02d", time.minute)}"
            }
        }
        _timePickerDialogIsShowing.value = false
    }

    // Next button
    private val _isFirstFormCompleted = MutableStateFlow(false)

    private val _isSecondFormCompleted = MutableStateFlow(false)

    private val _isFormCompletedPopup = MutableStateFlow(false)

    fun checkAllValues(){
        when (step.value) {
            1 -> {
                _isFirstFormCompleted.value = _internalRouteName.value.isNotEmpty() &&
                        _originName.value.isNotEmpty() && _destinationName.value.isNotEmpty() &&
                        _dateDepart.value.isNotEmpty() && _dateArrival.value.isNotEmpty() &&
                        _timeDepartText.value.isNotEmpty() && _timeArrivalText.value.isNotEmpty()

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

                if (_isFirstFormCompleted.value) {
                    nextStep()
                } else {
                    missingValuesPopupToggle()
                }
            }
            2 -> {
                _isSecondFormCompleted.value = _availableSpace.value.isNotEmpty()
                        && _vehicle.value.isNotEmpty() && _availableSeats.value.isNotEmpty()
                        && _costKM.value.isNotEmpty() && _maxDetourKm.value.isNotEmpty()

                checkIfEmpty(2)


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
    fun addRoute(userID: Int){
        // TODO Cambiar la classe de la ruta i fer servir la oficial
        val lastRouteID = LocalConstants.routeList.maxByOrNull { route -> route.routeID }!!.routeID
        // Elimina els punts intermitjos buits
        val cleanStepNameList = stepNameList.value.filter { step -> step.isNotEmpty() }
        val newRoute = Routes(userID = userID,
            routeID = lastRouteID+1,
            routeName = _internalRouteName.value,
            puntSortida = _originName.value,
            puntArribada = _destinationName.value,
            puntsIntermedis = cleanStepNameList,
            dataSortida = _dateDepart.value,
            horaSortida = timeDepartText.value,
            dataArribada = dateArrival.value,
            horaArribada = timeArrivalText.value,
            isIsoterm = _isIsoterm.value,
            isRefrigerat = _isRefrigerat.value,
            isCongelat = _isCongelat.value,
            isSenseHumitat = _isSenseHumitat.value,
            etiquetes = tagsList.value,
            vehicle = _vehicle.value,
            costKm = _costKM.value.toFloat(),
            maxDetourKm = _maxDetourKm.value.toFloat(),
            availableSeats = _availableSeats.value.toInt(),
            availableSpace = _availableSpace.value,
            comment = _comment.value,
            startPoint = _originLocation.value,
            endPoint = _destinationLocation.value
            )
        // TODO Fer un POST a la API per duplicar la ruta

        if (LocalConstants.routeList.add(newRoute)){
            onRouteAdded(true)
        }
    }

    fun onRouteAdded(isRouteAdded: Boolean){
        _routeAdded.value = isRouteAdded
        clearValues()
    }


    // Get the route
    private val _routeToEdit = MutableStateFlow<Routes?>(null)
    val routeToEdit = _routeToEdit.asStateFlow()
    fun getRoute(routeID: Int) {
        _routeToEdit.value =  LocalConstants.routeList.find { route -> route.routeID == routeID }!!
        updateRouteInfo()
    }

    private fun updateRouteInfo(){
        _internalRouteName.value = _routeToEdit.value!!.routeName
        _originName.value = _routeToEdit.value!!.puntSortida
        _destinationName.value = _routeToEdit.value!!.puntArribada
        _dateDepart.value = _routeToEdit.value!!.dataSortida
        _timeDepartText.value = _routeToEdit.value!!.horaSortida
        _dateArrival.value = _routeToEdit.value!!.dataArribada
        _timeArrivalText.value = _routeToEdit.value!!.horaArribada
        _isIsoterm.value = _routeToEdit.value!!.isIsoterm
        _isRefrigerat.value = _routeToEdit.value!!.isRefrigerat
        _isCongelat.value = _routeToEdit.value!!.isCongelat
        _isSenseHumitat.value = _routeToEdit.value!!.isSenseHumitat
        _vehicle.value = _routeToEdit.value!!.vehicle ?: ""
        _costKM.value = _routeToEdit.value!!.costKm.toString()
        _maxDetourKm.value = _routeToEdit.value!!.maxDetourKm.toString()
        _availableSeats.value = _routeToEdit.value!!.availableSeats.toString()
        _availableSpace.value = _routeToEdit.value!!.availableSpace ?: ""
        _comment.value = _routeToEdit.value!!.comment ?: ""

        // Etiquetes i freqüència
        _routeToEdit.value?.etiquetes.let { etiquetes ->
            if (etiquetes != null) {
                _tagsList.value = etiquetes
                _tagsList.value.forEach { tag ->
                    when (tag) {
                        "diaria" -> _routeFrequency.value = "Diaria"
                        "setmanal" -> _routeFrequency.value = "Setmanal"
                        "mensual" -> _routeFrequency.value = "Mensual"
                        "bimensual" -> _routeFrequency.value = "Bimensual"
                    }
                }
            }
        }
        // Punts intermitjos
        _routeToEdit.value?.puntsIntermedis.let { puntsIntermedis ->
            if (puntsIntermedis != null) {
                _stepLocationsNumber.value = puntsIntermedis.size
                updateStepNameList(puntsIntermedis)
            }
        }
    }

    fun updateRoute(userID: Int){
        val cleanStepNameList = stepNameList.value.filter { step -> step.isNotEmpty() }

        val updatedRoute = Routes(userID = userID,
            routeID = _routeToEdit.value!!.routeID,
            routeName = _internalRouteName.value,
            puntSortida = _originName.value,
            puntArribada = _destinationName.value,
            puntsIntermedis = cleanStepNameList,
            dataSortida = _dateDepart.value,
            horaSortida = timeDepartText.value,
            dataArribada = dateArrival.value,
            horaArribada = timeArrivalText.value,
            isIsoterm = _isIsoterm.value,
            isRefrigerat = _isRefrigerat.value,
            isCongelat = _isCongelat.value,
            isSenseHumitat = _isSenseHumitat.value,
            etiquetes = _tagsList.value,
            vehicle = _vehicle.value,
            costKm = _costKM.value.toFloat(),
            maxDetourKm = _maxDetourKm.value.toFloat(),
            availableSeats = _availableSeats.value.toInt(),
            availableSpace = _availableSpace.value,
            comment = _comment.value,
            startPoint = _originLocation.value,
            endPoint = _destinationLocation.value
        )
        println(updatedRoute)
        // TODO Fer un PUT a la API per actualitzar la ruta
        if (LocalConstants.routeList.removeIf { route -> route.routeID == updatedRoute.routeID }){
            LocalConstants.routeList.add(updatedRoute)
            onRouteAdded(true)
        }
    }
    // Control de errors
    private val _screen1Errors = MutableStateFlow(List(7) { false })
    val screen1Errors = _screen1Errors.asStateFlow()

    private val _screen2Errors = MutableStateFlow(List(5) { false })
    val screen2Errors = _screen2Errors.asStateFlow()

    private fun checkIfEmpty(step: Int){

        when(step){
            1 -> {
                val screen1Errors = listOf(
                    _internalRouteName.value.isEmpty(),
                    _originName.value.isEmpty(),
                    _destinationName.value.isEmpty(),
                    _dateDepart.value.isEmpty(),
                    _timeDepartText.value.isEmpty(),
                    _dateArrival.value.isEmpty(),
                    _timeArrivalText.value.isEmpty()
                )
                _screen1Errors.value = screen1Errors
            }
            2 -> {
                val screen2Errors = listOf(
                    _maxDetourKm.value.isEmpty(),
                    _availableSeats.value.isEmpty(),
                    _availableSpace.value.isEmpty(),
                    _costKM.value.isEmpty(),
                    _vehicle.value.isEmpty(),

                )
                _screen2Errors.value = screen2Errors
            }
        }

    }


    fun loadOrderInfo(sharedDataRouteOrder: SharedDataRouteOrder){
        _originName.value = sharedDataRouteOrder.puntSortida
        _destinationName.value = sharedDataRouteOrder.puntArribada
        _dateDepart.value = sharedDataRouteOrder.dataSortida
        _dateArrival.value = sharedDataRouteOrder.dataArribada
        _isRefrigerat.value = sharedDataRouteOrder.isRefrigerat
        _isCongelat.value = sharedDataRouteOrder.isCongelat
        _isIsoterm.value = sharedDataRouteOrder.isIsoterm
        _isSenseHumitat.value = sharedDataRouteOrder.isSenseHumitat
    }

    /**
     * Clears all the values of the ViewModel
     */
    private fun clearValues(){
        _internalRouteName.value = ""
        _originName.value = ""
        _destinationName.value = ""
        _dateDepart.value = ""
        _dateArrival.value = ""
        _timeDepartText.value = ""
        _timeArrivalText.value = ""
        _isIsoterm.value = false
        _isRefrigerat.value = false
        _isCongelat.value = false
        _isSenseHumitat.value = false
        _vehicle.value = ""
        _costKM.value = ""
        _maxDetourKm.value = ""
        _availableSeats.value = ""
        _availableSpace.value = ""
        _comment.value = ""
        _tagsList.value = listOf()
        _routeFrequency.value = ""
        _stepLocationsNumber.value = 1
        _stepName1.value = ""
        _stepName2.value = ""
        _stepName3.value = ""
        _stepName4.value = ""
        _stepName5.value = ""
        _stepName6.value = ""
        _stepNameList.value = listOf(_stepName1.value, _stepName2.value,
            _stepName3.value, _stepName4.value, _stepName5.value, _stepName6.value)
        _screen1Errors.value = List(7) { false }
        _screen2Errors.value = List(5) { false }
        _originLocation.value = GeoPoint(0.0, 0.0)
        _destinationLocation.value = GeoPoint(0.0, 0.0)
        _step.value = 1
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