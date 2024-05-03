package com.example.rutescompartidesapp.view.route_detail.route_detail_driver

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rutescompartidesapp.data.domain.interactions.RouteInteraction
import com.example.rutescompartidesapp.data.domain.orders.Orders
import com.example.rutescompartidesapp.data.domain.routes.Routes
import com.example.rutescompartidesapp.utils.LocalConstants
import com.example.rutescompartidesapp.utils.LocalConstants.interactionList
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class RouteDetailDriverViewModel (routeID: Int): ViewModel(){

    // Interaccions
    private val _interactions = mutableStateListOf<RouteInteraction>().apply {
        addAll(interactionList.filter { it.routeID == routeID }.reversed())
    }
    val interactions = _interactions


    /**
     * Modifica l'estat de la interacció a la llista de la ruta i a la llista local
     * @param routeInteraction Interacció a modificar
     * @param index Índex de la interacció a modificar
     * @param status Nou estat de la interacció
     *
     */
    fun modifyInteractionStatus(routeInteraction: RouteInteraction, index: Int, status: String){
        _interactions[index] = routeInteraction.copy(status = status)

        interactionList.first { it.routeID == routeInteraction.routeID &&
        it.orderID == routeInteraction.orderID}.status = status

        viewModelScope.launch {
            _interactionToastText.value = "Comanda ${status.lowercase()}"
            _showInteractionToastChannel.send(true)
        }

        // TODO POST a la API per modificar l'estat de la interacció
        //val newList = interactions.value
        //newList[interactions.value.indexOf(routeInteraction)].status = status
        //_interactions.value = newList
    }

    // Boolea que mostra si la pantalla de confirmació està mostrant-se
    private val _isCompleteScreenShowing = MutableStateFlow(false)
    val isCompleteScreenShowing = _isCompleteScreenShowing.asStateFlow()
    /**
     * Mostra o amaga la pantalla de confirmació
     * @param isShowing Booleà que indica si es mostra o amaga la pantalla
     */
    fun showCompleteScreen(isShowing: Boolean){
        _isCompleteScreenShowing.value = isShowing
    }

    // Interacció a confirmar, guarda la interacció que es vol confirmar la entrega
    private val _routeInteractionToConfirm = MutableStateFlow<RouteInteraction?>(null)
    val routeInteractionToConfirm = _routeInteractionToConfirm.asStateFlow()

    private val _route = MutableStateFlow<Routes?>(null)
    val route = _route.asStateFlow()
    private val _order = MutableStateFlow<Orders?>(null)
    val order = _order.asStateFlow()

    /**
     * Guarda la interacció que es vol confirmar la entrega i mostra la pantalla de confirmació
     * @param interaction Interacció a confirmar
     */
    fun setRouteToConfirm(interaction: RouteInteraction){
        _routeInteractionToConfirm.value = interaction
        getRoute(interaction.routeID)
        getOrder(interaction.orderID)
        showCompleteScreen(true)
    }

    // Comentari de l'usuari
    private val _userComment = MutableStateFlow("")
    val userComment = _userComment.asStateFlow()

    fun setUserComment(comment: String){
        _userComment.value = comment
    }


    // This function is called when the user confirms the delivery of the order
    /**
     * Completa la entrega de la interacció i amaga la pantalla de confirmació
     */
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

            viewModelScope.launch {
                _interactionToastText.value = "Entrega confirmada"
                _showInteractionToastChannel.send(true)
            }

            showCompleteScreen(false)
        }
        // TODO POST a la API per modificar l'estat de la interacció
        // TODO fer POST a la API per crear una confirmació d'entrega
    }

    /**
     * Obté la ruta a partir de la seva ID i la guarda al state
     * @param routeID ID de la ruta
     */
    fun getRoute(routeID: Int){
        val route = LocalConstants.routeList.first { it.routeID == routeID }
        _route.value = route
    }

    /**
     * Obté la comanda a partir de la seva ID i la guarda al state
     * @param orderID ID de la comanda
     */
    private fun getOrder(orderID: Int){
        val order = LocalConstants.orderList.first { it.orderID == orderID }
        _order.value = order
    }

    /**
     * Duplica la ruta i la guarda a la llista de rutes amb un nou ID
     * @param route Ruta a duplicar
     */
    fun duplicateRoute(route: Routes){
        val nextRouteID = LocalConstants.routeList.maxOf { lastRoute ->
            lastRoute.routeID
        } + 1
        val newRoute = route.copy(routeID = nextRouteID)
        LocalConstants.routeList.add(newRoute)
       // TODO Fer un POST a la API per duplicar la ruta
    }



    fun deleteRoute(route: Routes){
        LocalConstants.routeList.remove(route)
        // TODO Fer un DELETE a la API per eliminar la ruta
    }

    // Toast d'interacció
    private val _showInteractionToastChannel = Channel<Boolean>()
    val showInteractionToastChannel = _showInteractionToastChannel.receiveAsFlow()
    // Text del toast d'interacció
    private val _interactionToastText = MutableStateFlow("")
    val interactionToastText = _interactionToastText.asStateFlow()

    /* De moment es fa localment però a futur s'haurà de fer a la API
       fun editRoute(route: RouteForList){
    /**
     * TODO Obre el questionari de PublishRouteScreen amb les dades de la ruta i permet editar-les,
     * navHost.navigate("PublishRouteScreen/{command}/{routeID}".replace(
     *   oldValue = "{command}", newValue = "edit")
     * .replace(oldValue = "{routeID}",newValue = "$routeID"))
     * TODO després fa un PUT a la API per editar la ruta
     */
}
 */

}