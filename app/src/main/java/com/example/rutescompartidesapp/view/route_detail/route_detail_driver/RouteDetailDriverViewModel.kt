package com.example.rutescompartidesapp.view.route_detail.route_detail_driver

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.example.rutescompartidesapp.data.domain.RouteForList
import com.example.rutescompartidesapp.view.route_detail.route_detail_driver.DetailUtils.RouteInteraction
import com.example.rutescompartidesapp.view.routes_order_list.ListConstants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RouteDetailDriverViewModel (routeID: Int): ViewModel(){

    //private val _interactions: SnapshotStateList<RouteInteraction> = mutableStateListOf<RouteInteraction>()

    init {
        // TODO GET a la API per obtenir les interaccions de la ruta
        //_interactions.addAll(DetailUtils.interactionList.filter { it.routeID == routeID })
    }
    //private val _interactions = MutableStateFlow(DetailUtils.interactionList.filter { it.routeID == routeID })
    //val interactions = _interactions.asStateFlow()

    private val _interactions = mutableStateListOf<RouteInteraction>().apply {
        addAll(DetailUtils.interactionList.filter { it.routeID == routeID })
    }
    val interactions = _interactions

    private val _isCompletePopupShowing = MutableStateFlow(false)
    val isCompletePopupShowing = _isCompletePopupShowing.asStateFlow()

    fun modifyInteractionStatus(routeInteraction: RouteInteraction, index: Int, status: String){
        _interactions[index] = routeInteraction.copy(status = status)
        // TODO POST a la API per modificar l'estat de la interacció
        //val newList = interactions.value
        //newList[interactions.value.indexOf(routeInteraction)].status = status
        //_interactions.value = newList
    }


    fun showCompletePopup(isShowing: Boolean){
        _isCompletePopupShowing.value = isShowing
    }

    // This function is called when the user confirms the delivery of the order
    fun completeOrder(routeInteraction: RouteInteraction){

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