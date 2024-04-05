package com.example.rutescompartidesapp.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.rutescompartidesapp.data.domain.user.User
import com.example.rutescompartidesapp.navigation.Screens
import com.example.rutescompartidesapp.view.com_funciona.ComFuncionaScreen
import com.example.rutescompartidesapp.view.confirm_delivery.ConfirmScreen
import com.example.rutescompartidesapp.view.confirm_delivery.components.camera.CameraScreen
import com.example.rutescompartidesapp.view.confirm_delivery.components.draw.DrawScreen
import com.example.rutescompartidesapp.view.confirm_delivery.viewmodel.CameraViewModel
import com.example.rutescompartidesapp.view.confirm_delivery.viewmodel.DrawViewModel
import com.example.rutescompartidesapp.view.edit_profile.EditProfileScreen
import com.example.rutescompartidesapp.view.edit_profile.EditProfileViewModel
import com.example.rutescompartidesapp.view.faq.FaqScreen
import com.example.rutescompartidesapp.view.faq.FaqViewModel
import com.example.rutescompartidesapp.view.login.LoginScreen
import com.example.rutescompartidesapp.view.map.MapScreen
import com.example.rutescompartidesapp.view.map.viewModels.MapViewModel
import com.example.rutescompartidesapp.view.order_detail.OrderDetailScreen
import com.example.rutescompartidesapp.view.profile.ProfileScreen
import com.example.rutescompartidesapp.view.profile.ProfileViewModel
import com.example.rutescompartidesapp.view.chat.ChatScreen
import com.example.rutescompartidesapp.view.chat.ChatViewModel2
import com.example.rutescompartidesapp.view.publish_order.PublishOrderScreen
import com.example.rutescompartidesapp.view.publish_route.PublishRouteScreen
import com.example.rutescompartidesapp.view.route_detail.RouteDetailGeneralScreen
import com.example.rutescompartidesapp.view.route_detail.route_detail_driver.RouteDetailDriverScreen
import com.example.rutescompartidesapp.view.routes_order_list.RoutesOrderListScreen
import com.example.rutescompartidesapp.view.routes_order_list.viewmodels.FilterPopupViewModel
import com.example.rutescompartidesapp.view.routes_order_list.viewmodels.RoutesOrderListViewModel
import com.example.rutescompartidesapp.view.signup.SignUpScreen
import java.util.Date

@Composable
fun ScreenNavigationConfiguration(mapViewModel: MapViewModel, chatViewModel: ChatViewModel2, drawViewModel: DrawViewModel, cameraViewModel: CameraViewModel,
                                  routeOrderListViewModel: RoutesOrderListViewModel, filterPopupViewModel: FilterPopupViewModel,
                                  navController: NavHostController, paddingModifier: Modifier
) {

    NavHost(navController = navController, startDestination = Screens.ChatScreen.route, modifier = paddingModifier) {

        val user = User("adsgfasg","asd","34fas","asd@asfa.com",false,true, Date())
        composable(Screens.ChatScreen.route) {
            ChatScreen(navController,chatViewModel,user)
        }
        composable(Screens.MapScreen.route) {
            MapScreen(navController, mapViewModel)
        }
        composable(
            Screens.OrderDetailScreen.route,
            arguments = listOf(navArgument("packageId"){
                type = NavType.IntType
            }
            )) {
            val packageId = it.arguments?.getInt("packageId")
            OrderDetailScreen(1, navController)
        }
        composable(Screens.ConfirmDeliveryScreen.route){
            ConfirmScreen(navController, cameraViewModel, drawViewModel)
        }
        composable(Screens.CameraScreen.route){
            CameraScreen(navController, cameraViewModel)
        }
        composable(Screens.DrawScreen.route){
            DrawScreen(navController, drawViewModel)
        }

        composable(Screens.RouteDetailGeneralScreen.route,
            arguments = listOf(navArgument("routeId"){
                type = NavType.IntType
            })) {
            val routeID = it.arguments?.getInt("routeId")
            RouteDetailGeneralScreen(navController, 1, mapViewModel, routeOrderListViewModel)
        }

        composable(Screens.RoutesOrderListScreen.route) {
            RoutesOrderListScreen(navController, routeOrderListViewModel, filterPopupViewModel)
        }
        composable(Screens.ProfileScreen.route) {
            ProfileScreen(ProfileViewModel(), navController)
        }
        composable(Screens.LoginScreen.route) {
            LoginScreen(navController)
        }
        composable(Screens.SignUpScreen.route) {
            SignUpScreen(navController)
        }
        composable(Screens.RouteDetailDriverScreen.route,
            arguments = listOf(navArgument("routeId"){
                type = NavType.IntType
            })) {
            val routeID = it.arguments?.getInt("routeId")
            RouteDetailDriverScreen(routeID!!, navController)
        }
        composable(Screens.FaqScreen.route) {
            FaqScreen(navController, FaqViewModel())
        }
        composable(Screens.EditProfileScreen.route) {
            EditProfileScreen(EditProfileViewModel(), navController)
        }
        composable(Screens.ComFuncionaScreen.route) {
            ComFuncionaScreen(navController)
        }
        composable(Screens.PublishRouteScreen.route) {
            PublishRouteScreen(navController)
        }
        composable(Screens.PublishOrderScreen.route) {
            PublishOrderScreen(navController)
        }

    }
}