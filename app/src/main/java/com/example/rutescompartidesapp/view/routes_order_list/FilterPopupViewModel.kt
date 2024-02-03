package com.example.rutescompartidesapp.view.routes_order_list

import androidx.lifecycle.ViewModel
import com.example.rutescompartidesapp.data.domain.ListQuery
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

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

    fun onEtiquetaDelete(etiqueta: String){
        // Reasigna el valor de la llista de etiquetas sense l'etiqueta que volem eliminar
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

    fun onPopupShow(isShowing: Boolean){
        _popupIsShowing.value = isShowing
        if (!isShowing){
            _puntSortidaText.value = ""
            _puntArribadaText.value = ""
            //_extraFiltersAreShowing.value = false
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
    fun onFilterSearch(listQuery: ListQuery){
        RoutesOrderListViewModel().onFilterSearch(listQuery)
        onPopupShow(false)
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


}