package com.example.rutescompartidesapp.view.route_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rutescompartidesapp.data.domain.RouteForList
import com.example.rutescompartidesapp.view.route_detail.DetailUtils.RouteInteraction
import com.example.rutescompartidesapp.view.routes_order_list.ListConstants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RouteDetailViewModel (interactionList: List<RouteInteraction>): ViewModel(){

    private val _interactions = MutableStateFlow(interactionList)
    val interactions = _interactions.asStateFlow()

    private val _isCompletePopupShowing = MutableStateFlow(false)
    val isCompletePopupShowing = _isCompletePopupShowing.asStateFlow()

    fun acceptOrder(routeInteraction: RouteInteraction){
        viewModelScope.launch {
            _interactions.value[interactions.value.indexOf(routeInteraction)].status = "Acceptada"
            _interactions.value = interactions.value

        }
    }
    fun declineOrder(routeInteraction: RouteInteraction){
        viewModelScope.launch {
            interactions.value[interactions.value.indexOf(routeInteraction)].status = "Declinada"
            _interactions.value = interactions.value
        }

        /*
        _interactions.update { list ->
         list[list.indexOf(routeInteraction)].apply {
                status = "Declinada"
            }
            _interactions.value = list
            list
        }
         */

    }

    fun showCompletePopup(isShowing: Boolean){
        _isCompletePopupShowing.value = isShowing
    }

    // This function is called when the user confirms the delivery of the order
    fun completeOrder(routeInteraction: RouteInteraction){
        _interactions.update { list ->
            list[list.indexOf(routeInteraction)].apply {
                status = "Entregada"
            }
            list
        }
        // Closes the popup
        showCompletePopup(false)
    }

    fun duplicateRoute(route: RouteForList){
        ListConstants.routeList.add(route)
       // TODO Fer un POST a la API per duplicar la ruta
    }

    fun editRoute(route: RouteForList){
        // TODO Obre un questionari amb les dades de la ruta i permet editar-les,
        // TODO després fa un PUT a la API per editar la ruta
    }

    fun showRouteReport(route: RouteForList){
        // TODO Mostra el report de la ruta
    }
    fun deleteRoute(route: RouteForList){
        ListConstants.routeList.remove(route)
        // TODO Fer un DELETE a la API per eliminar la ruta
    }

}