package com.example.rutescompartidesapp.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.PratikFagadiya.smoothanimationbottombar.model.SmoothAnimationBottomBarScreens
import com.PratikFagadiya.smoothanimationbottombar.properties.BottomBarProperties
import com.PratikFagadiya.smoothanimationbottombar.ui.SmoothAnimationBottomBar
import com.example.rutescompartidesapp.R
import com.example.rutescompartidesapp.navigation.Screens
import com.example.rutescompartidesapp.ui.theme.MateBlackRC
import com.example.rutescompartidesapp.ui.theme.RutesCompartidesAppTheme
import com.example.rutescompartidesapp.view.how_it_works.ComFuncionaScreen
import com.example.rutescompartidesapp.view.edit_profile.EditProfileScreen
import com.example.rutescompartidesapp.view.faq.FaqScreen
import com.example.rutescompartidesapp.view.faq.FaqViewModel
import com.example.rutescompartidesapp.view.confirm_delivery.viewmodel.CameraViewModel
import com.example.rutescompartidesapp.view.confirm_delivery.viewmodel.DrawViewModel
import com.example.rutescompartidesapp.view.order_detail.OrderDetailScreen
import com.example.rutescompartidesapp.view.login.LoginScreen
import com.example.rutescompartidesapp.view.login.LoginViewModel
import com.example.rutescompartidesapp.view.map.MapScreen
import com.example.rutescompartidesapp.view.map.viewModels.MapViewModel
import com.example.rutescompartidesapp.view.map.viewModels.MapViewModel2
import com.example.rutescompartidesapp.view.profile.ProfileScreen
import com.example.rutescompartidesapp.view.profile.ProfileViewModel
import com.example.rutescompartidesapp.view.publish_order.ManageOrderViewModel
import com.example.rutescompartidesapp.view.publish_order.PublishOrderScreen
import com.example.rutescompartidesapp.view.publish_route.ManageRouteViewModel
import com.example.rutescompartidesapp.view.publish_route.PublishRouteScreen
import com.example.rutescompartidesapp.view.route_detail.route_detail_driver.RouteDetailDriverScreen
import com.example.rutescompartidesapp.view.route_detail.RouteDetailGeneralScreen
import com.example.rutescompartidesapp.view.route_detail.route_detail_driver.RouteDetailDriverViewModel
import com.example.rutescompartidesapp.view.routes_order_list.RoutesOrderListScreen
import com.example.rutescompartidesapp.view.routes_order_list.viewmodels.FilterPopupViewModel
import com.example.rutescompartidesapp.view.routes_order_list.viewmodels.RoutesOrderListViewModel
import com.example.rutescompartidesapp.view.routes_order_list.viewmodels.TabRowViewModel
import com.example.rutescompartidesapp.view.signup.SignUpScreen
import com.example.rutescompartidesapp.view.user_reviews.UserReviewScreen
import com.example.rutescompartidesapp.view.user_reviews.UserReviewViewModel
import com.example.rutescompartidesapp.view.value_experience.ValueExperienceGeneralScreen
import com.example.rutescompartidesapp.view.value_experience.ValueExperienceViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RutesCompartidesAppTheme {
                val mapViewModel: MapViewModel = hiltViewModel()
                val mapViewModel2: MapViewModel2 = hiltViewModel()
                val loginViewModel: LoginViewModel = hiltViewModel()
                val profileViewModel: ProfileViewModel = hiltViewModel()
                val filterPopupViewModel = FilterPopupViewModel()
                val routeOrderListViewModel = RoutesOrderListViewModel()
                val tabRowViewModel = TabRowViewModel()
                val navController = rememberNavController()
                val manageRouteViewModel : ManageRouteViewModel  = hiltViewModel()
                val manageOrderViewModel : ManageOrderViewModel = hiltViewModel()
                val userIsLogged = intent.getBooleanExtra("userIsLogged", false)

                val bottomNavigationItems = listOf(
                    SmoothAnimationBottomBarScreens(
                        Screens.MapScreen.route,
                        stringResource(id = R.string.map),
                        R.drawable.round_map_24
                    ),
                    SmoothAnimationBottomBarScreens(
                        Screens.RoutesOrderListScreen.route,
                        stringResource(id = R.string.list),
                        R.drawable.round_format_list_bulleted_24
                    ),
                    SmoothAnimationBottomBarScreens(
                        Screens.ProfileScreen.route,
                        stringResource(id = R.string.profile),
                        R.drawable.round_person_24
                    )
                )

                val currentIndex = loginViewModel.currentIndex

                Scaffold(
                    bottomBar = {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination
                    Box {
                        if (currentDestination?.hierarchy?.any { navDestination ->
                            navDestination.route ==  Screens.MapScreen.route ||
                            navDestination.route ==  Screens.RoutesOrderListScreen.route ||
                            navDestination.route ==  Screens.ProfileScreen.route
                        } == true ){
                            SmoothAnimationBottomBar(navController,
                                bottomNavigationItems,
                                initialIndex = currentIndex,
                                bottomBarProperties = BottomBarProperties(
                                    backgroundColor = MateBlackRC,
                                    indicatorColor = Color.White.copy(alpha = 0.2F),
                                    iconTintColor = Color.White,
                                    iconTintActiveColor = Color.White,
                                    textActiveColor = Color.White,
                                    cornerRadius = 18.dp,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 18.sp
                                ),
                                onSelectItem = {
                                    println("SELECTED_ITEM " + " onCreate: Selected Item ${it.name}")
                                    println(it.route)
                                }
                            )
                       }
                    }
                }
                ) { paddingValues ->
                    ScreenNavigationConfiguration(
                        mapViewModel,
                        mapViewModel2,
                        loginViewModel,
                        profileViewModel,
                        routeOrderListViewModel,
                        tabRowViewModel,
                        filterPopupViewModel,
                        manageRouteViewModel,
                        manageOrderViewModel,
                        navController,
                        userIsLogged,
                        Modifier.padding(paddingValues),
                    )
                }
            }
        }
    }
}

@Composable
fun ScreenNavigationConfiguration(
    mapViewModel: MapViewModel,
    mapViewModel2: MapViewModel2,
    loginViewModel: LoginViewModel,
    profileViewModel: ProfileViewModel,
    routeOrderListViewModel: RoutesOrderListViewModel,
    tabRowViewModel: TabRowViewModel,
    filterPopupViewModel: FilterPopupViewModel,
    manageRouteViewModel: ManageRouteViewModel,
    manageOrderViewModel: ManageOrderViewModel,
    navHostController: NavHostController,
    userIsLogged: Boolean,
    modifier: Modifier
) {

    println("USER IS LOGGED AFTER NAV: $userIsLogged")
    NavHost(navController = navHostController,
        startDestination = if (userIsLogged) Screens.MapScreen.route else Screens.LoginScreen.route,
        modifier = modifier) {

        composable(Screens.MapScreen.route) {
            MapScreen(navHostController, mapViewModel, loginViewModel)
        }
        composable(Screens.OrderDetailScreen.route,
            arguments = listOf(navArgument("orderID"){
                type = NavType.IntType
            }
            )) {
            val orderID = it.arguments?.getInt("orderID")
            OrderDetailScreen(orderID!!, navHostController, loginViewModel, manageRouteViewModel)
        }

        composable(Screens.RouteDetailGeneralScreen.route,
            arguments = listOf(navArgument("routeId"){
            type = NavType.IntType
        })) {
            val routeID = it.arguments?.getInt("routeId")
            RouteDetailGeneralScreen(navHostController, routeID!!, mapViewModel,
                mapViewModel2, manageOrderViewModel, routeOrderListViewModel, loginViewModel)
        }

        composable(Screens.ValueExperienceGeneralScreen.route,
            arguments = listOf(navArgument("routeId"){
                type = NavType.IntType
            },
                navArgument("orderId"){
                    type = NavType.IntType
                })) {
            val routeID = it.arguments?.getInt("routeId")
            val orderID = it.arguments?.getInt("orderId")
            val valueExperienceViewModel = ValueExperienceViewModel()

            ValueExperienceGeneralScreen(routeID!!, orderID!!, navHostController,valueExperienceViewModel)
        }

        composable(Screens.RoutesOrderListScreen.route) {
            RoutesOrderListScreen(navHostController, routeOrderListViewModel, filterPopupViewModel, loginViewModel, tabRowViewModel)
        }
        composable(Screens.ProfileScreen.route) {
            ProfileScreen(profileViewModel, loginViewModel, navHostController, routeOrderListViewModel, tabRowViewModel)
        }
        composable(Screens.LoginScreen.route) {
            LoginScreen(loginViewModel, navHostController)
        }
        composable(Screens.SignUpScreen.route) {
            SignUpScreen(navHostController)
        }
        composable(Screens.RouteDetailDriverScreen.route,
            arguments = listOf(navArgument("routeId"){
            type = NavType.IntType
        })) {
            val routeID = it.arguments?.getInt("routeId")
            val routeDetailDriverViewModel = RouteDetailDriverViewModel(routeID!!)
            val cameraViewModel = CameraViewModel()
            val drawViewModel = DrawViewModel()
            RouteDetailDriverScreen(routeID, navHostController, routeDetailDriverViewModel, cameraViewModel, drawViewModel)
        }

        composable(Screens.FaqScreen.route) {
            FaqScreen(navHostController, FaqViewModel())
        }
        composable(Screens.EditProfileScreen.route) {
            EditProfileScreen(loginViewModel, navHostController)
        }
        composable(Screens.ComFuncionaScreen.route) {
            ComFuncionaScreen(navHostController)
        }
        composable(Screens.PublishRouteScreen.route,
            arguments = listOf(navArgument("command"){
                type = NavType.StringType
            },
                navArgument("routeID"){
                    type = NavType.IntType
                })) {
            val command = it.arguments?.getString("command")
            val routeID = it.arguments?.getInt("routeID")
            PublishRouteScreen(command!!, routeID = routeID!!, navHostController, loginViewModel, manageRouteViewModel)
        }
        composable(Screens.PublishOrderScreen.route,
            arguments = listOf(navArgument("command"){
                type = NavType.StringType
            },
                navArgument("orderID"){
                type = NavType.IntType
            })) {
            val command = it.arguments?.getString("command")
            val orderID = it.arguments?.getInt("orderID")
            PublishOrderScreen(command!!, orderID = orderID!!, navHostController, loginViewModel, manageOrderViewModel)
        }
        composable(Screens.UserReviewScreen.route) {
            UserReviewScreen(UserReviewViewModel(), loginViewModel, navHostController)
        }


    }

}