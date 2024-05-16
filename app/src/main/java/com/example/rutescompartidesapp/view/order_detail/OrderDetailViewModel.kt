package com.example.rutescompartidesapp.view.order_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.rutescompartidesapp.data.domain.interactions.RouteInteraction
import com.example.rutescompartidesapp.data.domain.orders.Orders
import com.example.rutescompartidesapp.data.domain.routes.Routes
import com.example.rutescompartidesapp.data.domain.routes.SharedDataRouteOrder
import com.example.rutescompartidesapp.utils.LocalConstants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

class OrderDetailViewModel: ViewModel() {

    private val _order = MutableStateFlow<Orders?>(null)
    val order = _order.asStateFlow()

    /**
     * Obté la comanda amb l'ID i actualitza [_order] amb la comanda,
     * també crida a [checkOrderStatus] per comprovar l'estat de la comanda
     * @param orderID Int -> ID de la comanda
     */
    fun getOrder(orderID: Int){
        val order = LocalConstants.orderList?.find { it.orderID == orderID }
        order?.let {
            _order.value = order
            checkOrderStatus()
        }
    }

    private val _acceptPopup = MutableStateFlow(false)
    val acceptPopup = _acceptPopup.asStateFlow()

    fun onPopupDissmissed(){
        _acceptPopup.value = false
    }


    private val _userMatchingRoutes = MutableStateFlow<List<Routes>>(emptyList())
    val userMatchingRoutes = _userMatchingRoutes.asStateFlow()

    /**
     * Obté les rutes de l'usuari i fa un filtre per comprovar quines d'aquestes rutes coincideixen amb la comanda
     * Comprova que els punts de sortida i arribada coincideixin amb els de la comanda o estiguin en els punts intermitjos de la ruta,
     * les dates de sortida i arribada coincideixin amb les de la comanda i les condicions de transport també coincideixin
     * i actualitza [_userMatchingRoutes] amb les rutes que coincideixen
     * @param userID ID de l'usuari
     */
    fun filterMatchingRoutes(userID: Int){
        _acceptPopup.value = true
        val userRoutes = LocalConstants.routeList!!.filter { it.userID == userID }

        val orderConditions = listOf(order.value!!.isRefrigerat, order.value!!.isCongelat,
            order.value!!.isIsoterm, order.value!!.isSenseHumitat)

        val matchingRoutes = userRoutes.filter { route ->

            val routeConditions = listOf(route.isRefrigerat, route.isCongelat,
                route.isIsoterm, route.isSenseHumitat)
            // Comproba que el punt de sortida i d'arribada coincidexin amb els de la comanda
            // o estiguin en els punts intermitjos de la ruta
            (route.puntSortida == order.value?.puntSortida || route.puntsIntermedis?.contains(
                order.value!!.puntSortida
            ) == true ) &&
                    (route.puntArribada == order.value?.puntArribada || route.puntsIntermedis?.contains(
                        order.value!!.puntSortida
                    ) == true ) &&
                    // Comprovar que les dates de sortida i arribada coincideixen amb les de la comanda
                    route.dataSortida == order.value?.dataSortida &&
                    route.dataArribada == order.value?.dataArribada &&
                    // Comprovar que les condicions de transport de la ruta coincideixen amb les de la comanda
                    routeConditions.zip(orderConditions).all { (routeCondition, orderCondition) ->
                        routeCondition == orderCondition
                    }

        }
        _userMatchingRoutes.value = matchingRoutes
    }

    /**
     * Checks the order status by accessing
     * [LocalConstants.interactionList]
     * and updates [_isOrderAccepted], [_isOrderDelivered] and [_isOrderPendent] with the result [_isOrderDeclined]
     * and [_isDeliveryValuated] with the result.
     *
     * If the status is "Declinada", updates [_isOrderDeclined] with true and [_pendentRouteID] with the routeID
     */
    private fun checkOrderStatus(){
        val order = order.value!!
        val routeInteraction = LocalConstants.interactionList.firstOrNull { it.orderID == order.orderID }
        _isOrderAccepted.value = routeInteraction?.status == "Acceptada"
        _isOrderDelivered.value = routeInteraction?.status == "Entregada"
        if (routeInteraction?.status == "Pendent"){
            _isOrderPendent.value = routeInteraction.status == "Pendent"
            _pendentRouteID.value = routeInteraction.routeID
        }
        if (routeInteraction?.status == "Declinada"){
            _isOrderDeclined.value = routeInteraction.status == "Declinada"
        }
        _isDeliveryValuated.value = routeInteraction?.status == "Valorada"
    }

    private val _pendentRouteID = MutableStateFlow(0)
    val pendentRouteID = _pendentRouteID.asStateFlow()

    private val _isOrderAccepted = MutableStateFlow(false)
    val isOrderAccepted = _isOrderAccepted.asStateFlow()

    private val _isOrderDelivered = MutableStateFlow(false)
    val isOrderDelivered = _isOrderDelivered.asStateFlow()

    private val _isOrderPendent = MutableStateFlow(false)
    val isOrderPendent = _isOrderPendent.asStateFlow()

    private val _isOrderDeclined = MutableStateFlow(false)
    val isOrderDeclined = _isOrderDeclined.asStateFlow()

    private val _isDeliveryValuated = MutableStateFlow(false)
    val isDeliveryValuated = _isDeliveryValuated.asStateFlow()

    /**
     * When the user accepts the order, checks if there's already a RouteInteraction with the same routeID and orderID,
     * if it is, updates the status tu "Acceptada", if not, creates a new RouteInteraction object with
     * the status "Acceptada"
     * @param routeID Int -> ID of the route
     * @param userID Int -> ID of the user
     */
    fun acceptOrder(routeID: Int, userID: Int){
        // TODO Fer un POST a la API per acceptar la comanda i crear una notificació
        val newRouteInteraction = RouteInteraction(
            routeID = routeID,
            orderID = order.value!!.orderID,
            date = LocalDate.now().toString(),
            status = "Acceptada"
        )

        if(LocalConstants.interactionList.none { it.orderID == order.value!!.orderID && it.routeID == routeID }){
            LocalConstants.interactionList.add(newRouteInteraction)
            _isOrderPendent.value = false
        } else {
            LocalConstants.interactionList.first { it.orderID == order.value!!.orderID && it.routeID == routeID }.status = "Acceptada"
        }
        _isOrderAccepted.value = true
        _acceptPopup.value = false
    }

    /**
     * When the user delcines the order, searches the RouteInteraction object and updates it's status with "Declinada"
     * @param routeID Int -> ID of the route
     */
    fun declineOrder(routeID: Int){
        // TODO Fer un POST a la API per declinar la comanda i crear una notificació
        LocalConstants.interactionList.first { it.orderID == order.value!!.orderID && it.routeID == routeID }.status = "Declinada"
        _isOrderDeclined.value = true
        _isOrderPendent.value = false
    }

    private val _sharedDataRouteOrder = MutableStateFlow<SharedDataRouteOrder?>(null)
    val routeFromOrder = _sharedDataRouteOrder.asStateFlow()

    /**
     * Obtains the info of the order to create a route with this info and
     * updates [_sharedDataRouteOrder] with the info.
     *
     * Obtains the following info from the order:
     * - Origin point (Punt de sortida)
     * - Destination point (Punt d'arribada)
     * - Depart date (Data de sortida)
     * - Arrival date (Data d'arribada)
     * - Transport conditions (Condicions de transport)
     */
    fun createRouteFromOrder(){
        val order = order.value!!
        val newRoute = SharedDataRouteOrder(
            orderID = order.orderID,
            puntSortida = order.puntSortida,
            puntArribada = order.puntArribada,
            dataSortida = order.dataSortida,
            dataArribada = order.dataArribada,
            isRefrigerat = order.isRefrigerat,
            isCongelat = order.isCongelat,
            isIsoterm = order.isIsoterm,
            isSenseHumitat = order.isSenseHumitat
        )
        _sharedDataRouteOrder.value = newRoute
    }

    /**
     * Deletes the order with the [orderID] and navigates back to the previous screen
     * @param orderID Int -> ID of the order
     * @param navHost NavHostController
     */
    fun deleteOrder(orderID: Int, navHost: NavHostController){
        navHost.popBackStack()
        viewModelScope.launch {
            val orderToRemove = LocalConstants.orderList?.find { it.orderID == orderID }
            orderToRemove?.let {
                LocalConstants.orderList.remove(it)
            }
        }
        // TODO Fer un DELETE a la API per eliminar la comanda
    }
}