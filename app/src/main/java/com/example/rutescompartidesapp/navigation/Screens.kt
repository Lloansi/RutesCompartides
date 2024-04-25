package com.example.rutescompartidesapp.navigation

sealed class Screens(
    val route: String,
) {
    object MapScreen: Screens("MapScreen")
    object RoutesOrderListScreen: Screens("RoutesOrderListScreen")
    object ProfileScreen: Screens("ProfileScreen")

    object LoginScreen: Screens("LoginScreen")
    object SignUpScreen: Screens("SignUpScreen")
    object RouteDetailDriverScreen: Screens("RouteDetailDriverScreen/{routeId}")
    object EditRouteScreen: Screens("EditRouteScreen/{routeId}")

    object OrderDetailScreen: Screens("OrderDetailScreen/{packageId}")
    object PublishRouteScreen: Screens("PublishRouteScreen")
    object PublishOrderScreen: Screens("PublishOrderScreen")

    object FaqScreen: Screens("FaqScreen")
    object EditProfileScreen: Screens("EditProfileScreen")
    object ComFuncionaScreen: Screens("ComFuncionaScreen")
    object DrawScreen: Screens("DrawScreen")
    object CameraScreen: Screens("CameraScreen")
    object RouteDetailGeneralScreen: Screens("RouteDetailGeneralScreen/{routeId}")


}