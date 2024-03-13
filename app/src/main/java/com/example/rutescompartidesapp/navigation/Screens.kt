package com.example.rutescompartidesapp.navigation

sealed class Screens(
    val route: String,
) {
    object MapScreen: Screens("MapScreen")
    object RoutesOrderListScreen: Screens("RoutesOrderListScreen")
    object ProfileScreen: Screens("ProfileScreen")

    object LoginScreen: Screens("LoginScreen")
    object SignUpScreen: Screens("SignUpScreen")
    object RouteDetailScreen: Screens("RouteDetailScreen/{routeId}")



    object FaqScreen: Screens("FaqScreen")

    object EditProfileScreen: Screens("EditProfileScreen")

    object ComFuncionaScreen: Screens("ComFuncionaScreen")


}