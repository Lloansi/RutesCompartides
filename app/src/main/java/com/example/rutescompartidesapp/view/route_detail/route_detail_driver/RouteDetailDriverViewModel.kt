package com.example.rutescompartidesapp.view.route_detail.route_detail_driver

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.rutescompartidesapp.data.domain.OrderForList
import com.example.rutescompartidesapp.data.domain.RouteForList
import com.example.rutescompartidesapp.data.domain.interactions.RouteInteraction
import com.example.rutescompartidesapp.utils.LocalConstants
import com.example.rutescompartidesapp.utils.LocalConstants.interactionList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class RouteDetailDriverViewModel (routeID: Int): ViewModel(){



    private val _interactions = mutableStateListOf<RouteInteraction>().apply {
        addAll(interactionList.filter { it.routeID == routeID })
    }
    val interactions = _interactions

    private val _isCompleteScreenShowing = MutableStateFlow(false)
    val isCompleteScreenShowing = _isCompleteScreenShowing.asStateFlow()

    fun modifyInteractionStatus(routeInteraction: RouteInteraction, index: Int, status: String){
        _interactions[index] = routeInteraction.copy(status = status)

        interactionList.first { it.routeID == routeInteraction.routeID &&
        it.orderID == routeInteraction.orderID}.status = status
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
            val routeID = _routeInteractionToConfirm.value!!.routeID
            val orderID = _routeInteractionToConfirm.value!!.orderID
            // Canviem l'estat de la interacció del state
            _interactions[_interactions
                .indexOf(_routeInteractionToConfirm.value)] = _routeInteractionToConfirm.value!!
                .copy(status = "Entregada")
            // Canviem l'estat de la interacció de la llista local
            interactionList.first { it.routeID == routeID &&
                    it.orderID == orderID}.status = "Entregada"
            _userComment.value
            showCompleteScreen(false)
        }
        // TODO POST a la API per modificar l'estat de la interacció
        // TODO fer POST a la API per crear una confirmació d'entrega
    }


    fun getRoute(routeID: Int){
        val route = LocalConstants.routeList.first { it.routeID == routeID }
        _route.value = route
    }


    private fun getOrder(orderID: Int){
        val order = LocalConstants.orderList.first { it.orderID == orderID }
        _order.value = order
    }

    fun duplicateRoute(route: RouteForList){
        val newRoute = route.copy(routeID = LocalConstants.routeList.first { it.routeID == route.routeID }.routeID + 1)
        LocalConstants.routeList.add(newRoute)
       // TODO Fer un POST a la API per duplicar la ruta
    }

    fun editRoute(route: RouteForList){
        /**
         * TODO Obre el questionari de PublishRouteScreen amb les dades de la ruta i permet editar-les,
         * navHost.navigate("PublishRouteScreen/{command}/{routeID}".replace(
         *   oldValue = "{command}", newValue = "edit")
         * .replace(oldValue = "{routeID}",newValue = "$routeID"))
         * TODO després fa un PUT a la API per editar la ruta
         */

    }


    fun deleteRoute(route: RouteForList){
        LocalConstants.routeList.remove(route)
        // TODO Fer un DELETE a la API per eliminar la ruta
    }


    //private val _interactions: SnapshotStateList<RouteInteraction> = mutableStateListOf<RouteInteraction>()
    //private val _interactions = MutableStateFlow(DetailUtils.interactionList.filter { it.routeID == routeID })
    //val interactions = _interactions.asStateFlow()

}