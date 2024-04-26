package com.example.rutescompartidesapp.view.route_detail.route_detail_driver

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.rutescompartidesapp.data.domain.OrderForList
import com.example.rutescompartidesapp.data.domain.RouteForList
import com.example.rutescompartidesapp.view.route_detail.route_detail_driver.DetailUtils.RouteInteraction
import com.example.rutescompartidesapp.view.routes_order_list.ListConstants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class RouteDetailDriverViewModel (routeID: Int): ViewModel(){



    private val _interactions = mutableStateListOf<RouteInteraction>().apply {
        addAll(DetailUtils.interactionList.filter { it.routeID == routeID })
    }
    val interactions = _interactions

    private val _isCompleteScreenShowing = MutableStateFlow(false)
    val isCompleteScreenShowing = _isCompleteScreenShowing.asStateFlow()

    fun modifyInteractionStatus(routeInteraction: RouteInteraction, index: Int, status: String){
        _interactions[index] = routeInteraction.copy(status = status)
        // TODO POST a la API per modificar l'estat de la interacció
        //val newList = interactions.value
        //newList[interactions.value.indexOf(routeInteraction)].status = status
        //_interactions.value = newList
    }


    fun showCompleteScreen(isShowing: Boolean){
        _isCompleteScreenShowing.value = isShowing
    }

    private val _routeInteractionToConfirm = MutableStateFlow<RouteInteraction?>(null)
    val routeInteractionToConfirm = _routeInteractionToConfirm.asStateFlow()

    private val _route = MutableStateFlow<RouteForList?>(null)
    val route = _route.asStateFlow()
    private val _order = MutableStateFlow<OrderForList?>(null)
    val order = _order.asStateFlow()
    fun setRouteToConfirm(interaction: RouteInteraction){
        _routeInteractionToConfirm.value = interaction
        getRoute(interaction.routeID)
        getOrder(interaction.orderID)
        println(interaction)
        showCompleteScreen(true)
    }

    private val _userComment = MutableStateFlow("")
    val userComment = _userComment.asStateFlow()

    fun setUserComment(comment: String){
        _userComment.value = comment
    }


    // This function is called when the user confirms the delivery of the order
    fun completeOrder(){
        if (_routeInteractionToConfirm.value != null){
            println("HOLA")
            _interactions[_interactions
                .indexOf(_routeInteractionToConfirm.value)] = _routeInteractionToConfirm.value!!
                .copy(status = "Entregada")

            _userComment.value
            showCompleteScreen(false)
        }
        // TODO POST a la API per modificar l'estat de la interacció
        // TODO fer POST a la API per crear una confirmació d'entrega
    }


    fun getRoute(routeID: Int){
        val route = ListConstants.routeList.first { it.routeID == routeID }
        _route.value = route
    }


    private fun getOrder(orderID: Int){
        val order = ListConstants.orderList.first { it.orderID == orderID }
        _order.value = order
    }

    fun duplicateRoute(route: RouteForList){
        ListConstants.routeList.add(route)
       // TODO Fer un POST a la API per duplicar la ruta
    }

    fun editRoute(route: RouteForList){
        // TODO Obre un questionari amb les dades de la ruta i permet editar-les,
        // TODO després fa un PUT a la API per editar la ruta
    }


    fun deleteRoute(route: RouteForList){
        ListConstants.routeList.remove(route)
        // TODO Fer un DELETE a la API per eliminar la ruta
    }

    // CAMERA AND DRAW

    private val _isUploaded = MutableStateFlow(false)
    val isUploaded = _isUploaded.asStateFlow()

    fun onUpload(isUploaded: Boolean){
        _isUploaded.value = isUploaded
    }



    //private val _interactions: SnapshotStateList<RouteInteraction> = mutableStateListOf<RouteInteraction>()
    //private val _interactions = MutableStateFlow(DetailUtils.interactionList.filter { it.routeID == routeID })
    //val interactions = _interactions.asStateFlow()

}