package com.example.rutescompartidesapp.view.route_detail.viewModels

import androidx.lifecycle.ViewModel
import com.example.rutescompartidesapp.data.domain.interactions.RouteInteraction
import com.example.rutescompartidesapp.data.domain.orders.Orders
import com.example.rutescompartidesapp.data.domain.routes.SharedDataRouteOrder
import com.example.rutescompartidesapp.data.domain.routes.Routes
import com.example.rutescompartidesapp.utils.LocalConstants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate

class RouteDetailViewModel: ViewModel() {

    private val _route = MutableStateFlow<Routes?>(null)
    val route = _route.asStateFlow()

    fun getRoute(routeID: Int){
        val route = LocalConstants.routeList!!.first { it.routeID == routeID }
        _route.value = route
        checkOrderStatus()
    }



    private val _userMatchingOrders = MutableStateFlow<List<Orders>>(emptyList())
    val userMatchingOrders = _userMatchingOrders.asStateFlow()

    /**
     * Obté les rutes de l'usuari i fa un filtre per comprovar quines d'aquestes rutes coincideixen amb la comanda
     * Comprova que els punts de sortida i arribada coincideixin amb els de la comanda o estiguin en els punts intermitjos de la ruta,
     * les dates de sortida i arribada coincideixin amb les de la comanda i les condicions de transport també coincideixin
     * i actualitza [_userMatchingOrders] amb les rutes que coincideixen
     * @param userID ID de l'usuari
     */
    fun filterMatchingOrders(userID: Int){
        val userOrders = LocalConstants.orderList!!.filter { it.userID == userID }
        val routeConditions = listOf(route.value!!.isRefrigerat,
            route.value!!.isCongelat,
            route.value!!.isIsoterm,
            route.value!!.isSenseHumitat)

        val matchingOrders = userOrders.filter { order ->

            val orderConditions = listOf(order.isRefrigerat, order.isCongelat,
                order.isIsoterm, order.isSenseHumitat)
            // Comproba que el punt de sortida i d'arribada coincidexin amb els de la comanda
            // o estiguin en els punts intermitjos de la ruta
            (order.puntSortida == route.value?.puntSortida || route.value?.puntsIntermedis?.contains(
                order.puntSortida
            ) == true ) &&
                    (order.puntArribada == route.value?.puntArribada || route.value?.puntsIntermedis?.contains(
                        order.puntSortida
                    ) == true ) &&
                    // Comprovar que les dates de sortida i arribada coincideixen amb les de la comanda
                    order.dataSortida == route.value?.dataSortida &&
                    order.dataArribada == route.value?.dataArribada &&
                    // Comprovar que les condicions de transport de la ruta coincideixen amb les de la comanda
                    orderConditions.zip(routeConditions).all { (orderCondition, routeCondition) ->
                        orderCondition == routeCondition
                    }

        }
        println(matchingOrders)
        _userMatchingOrders.value = matchingOrders
    }

    private val _requestPopup = MutableStateFlow(false)
    val requestPopup = _requestPopup.asStateFlow()

    fun onTogglePopup(){
        _requestPopup.value = !_requestPopup.value
    }

    private val _isOrderPendent = MutableStateFlow(false)
    val isOrderPendent = _isOrderPendent.asStateFlow()

    /**
     * When the user requests the route, checks if there's already a RouteInteraction with the same routeID and orderID,
     * if it doesn't, creates a new RouteInteraction object with
     * the status "Pendent"
     * @param routeID Int -> ID of the route
     * @param userID Int -> ID of the user
     * @param orderID Int -> ID of the order
     */
    fun requestRoute(routeID: Int, userID: Int, orderID: Int){
        // TODO Fer un POST a la API per acceptar la comanda i crear una notificació
        val newRouteInteraction = RouteInteraction(
            routeID = routeID,
            orderID = orderID,
            date = LocalDate.now().toString(),
            status = "Pendent"
        )

        if(LocalConstants.interactionList.none { it.orderID == orderID && it.routeID == routeID }){
            LocalConstants.interactionList.add(newRouteInteraction)
            _isOrderPendent.value = true
        }
    }


    /**
     * Checks if the order status by accessing
     * [LocalConstants.interactionList]
     * and updates [_isOrderPendent] with the result
     **/
    private fun checkOrderStatus(){
        val route = route.value!!
        val routeInteraction = LocalConstants.interactionList.firstOrNull { it.routeID == route.routeID }
        _isOrderPendent.value = routeInteraction?.status == "Pendent"
    }

    private val _orderFromRoute = MutableStateFlow<SharedDataRouteOrder?>(null)
    val orderFromRoute = _orderFromRoute.asStateFlow()

    /**
     * Obtains the info of the route to create a order with this info and
     * updates [_orderFromRoute] with the info.
     *
     * Obtains the following info from the route:
     * - Origin point (Punt de sortida)
     * - Destination point (Punt d'arribada)
     * - Depart date (Data de sortida)
     * - Arrival date (Data d'arribada)
     * - Transport conditions (Condicions de transport)
     */
    fun createRouteFromOrder(){
        val route = route.value!!
        val newOrder = SharedDataRouteOrder(
            puntSortida = route.puntSortida,
            puntArribada = route.puntArribada,
            dataSortida = route.dataSortida,
            dataArribada = route.dataArribada,
            isRefrigerat = route.isRefrigerat,
            isCongelat = route.isCongelat,
            isIsoterm = route.isIsoterm,
            isSenseHumitat = route.isSenseHumitat
        )
        _orderFromRoute.value = newOrder
    }
}