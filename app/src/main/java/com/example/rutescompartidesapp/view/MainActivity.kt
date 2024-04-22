package com.example.rutescompartidesapp.view

import android.content.pm.PackageManager
import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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
import com.example.rutescompartidesapp.view.com_funciona.ComFuncionaScreen
import com.example.rutescompartidesapp.view.edit_profile.EditProfileScreen
import com.example.rutescompartidesapp.view.edit_profile.EditProfileViewModel
import com.example.rutescompartidesapp.view.faq.FaqScreen
import com.example.rutescompartidesapp.view.faq.FaqViewModel
import com.example.rutescompartidesapp.utils.Constants.ALL_PERMISSIONS
import com.example.rutescompartidesapp.view.order_detail.OrderDetailScreen
import com.example.rutescompartidesapp.view.confirm_delivery.components.camera.CameraScreen
import com.example.rutescompartidesapp.view.confirm_delivery.ConfirmScreen
import com.example.rutescompartidesapp.view.confirm_delivery.components.draw.DrawScreen
import com.example.rutescompartidesapp.view.confirm_delivery.viewmodel.CameraViewModel
import com.example.rutescompartidesapp.view.confirm_delivery.viewmodel.DrawViewModel
import com.example.rutescompartidesapp.view.login.LoginScreen
import com.example.rutescompartidesapp.view.login.LoginViewModel
import com.example.rutescompartidesapp.view.map.MapScreen
import com.example.rutescompartidesapp.view.map.viewModels.MapViewModel
import com.example.rutescompartidesapp.view.map.viewModels.MapViewModel2
import com.example.rutescompartidesapp.view.profile.ProfileScreen
import com.example.rutescompartidesapp.view.profile.ProfileViewModel
import com.example.rutescompartidesapp.view.publish_order.PublishOrderScreen
import com.example.rutescompartidesapp.view.publish_route.PublishRouteScreen
import com.example.rutescompartidesapp.view.route_detail.route_detail_driver.RouteDetailDriverScreen
import com.example.rutescompartidesapp.view.route_detail.RouteDetailGeneralScreen
import com.example.rutescompartidesapp.view.routes_order_list.RoutesOrderListScreen
import com.example.rutescompartidesapp.view.routes_order_list.viewmodels.FilterPopupViewModel
import com.example.rutescompartidesapp.view.routes_order_list.viewmodels.RoutesOrderListViewModel
import com.example.rutescompartidesapp.view.signup.SignUpScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    //private lateinit var mDetector: GestureDetectorCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //mDetector = GestureDetectorCompat(this, MyGestureListener())

        fun hasRequiredPermissions(): Boolean {
            return ALL_PERMISSIONS.all {
                ContextCompat.checkSelfPermission(
                    applicationContext,
                    it
                ) == PackageManager.PERMISSION_GRANTED
            }
        }

        /*
        OLD WAY TO HANLDE PERMISSIONS GRANTED
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.INTERNET), 0)
        }

       // WindowCompat.setDecorFitsSystemWindows(window, false)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { view, insets ->
            val bottom = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
            view.updatePadding(bottom = bottom)
            insets
        }
         */

        if (!hasRequiredPermissions()) {
            ActivityCompat.requestPermissions(
                this, ALL_PERMISSIONS, 0
            )
        }


        setContent {
            RutesCompartidesAppTheme {
                val mapViewModel: MapViewModel = hiltViewModel()
                val mapViewModel2: MapViewModel2 = hiltViewModel()
                val loginViewModel: LoginViewModel = hiltViewModel()
                val profileViewModel: ProfileViewModel = hiltViewModel()
                val drawViewModel = DrawViewModel()
                val cameraViewModel = CameraViewModel()
                val filterPopupViewModel = FilterPopupViewModel()
                val routeOrderListViewModel = RoutesOrderListViewModel()

                val navController = rememberNavController()

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

                val currentIndex = rememberSaveable {
                    mutableIntStateOf(0)
                }

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
                        drawViewModel,
                        cameraViewModel,
                        routeOrderListViewModel,
                        filterPopupViewModel,
                        navController,
                        Modifier.padding(paddingValues),
                    )
                }
            }
        }
    }
}

@Composable
fun ScreenNavigationConfiguration( mapViewModel: MapViewModel,mapViewModel2: MapViewModel2,loginViewModel: LoginViewModel, profileViewModel: ProfileViewModel, drawViewModel: DrawViewModel, cameraViewModel: CameraViewModel,
                                   routeOrderListViewModel: RoutesOrderListViewModel, filterPopupViewModel: FilterPopupViewModel,
                                   navController: NavHostController, paddingModifier: Modifier) {

    NavHost(navController = navController, startDestination = Screens.RouteDetailGeneralScreen.route, modifier = paddingModifier) {

        composable(Screens.MapScreen.route) {
            MapScreen(navController, mapViewModel)
        }
        composable(Screens.OrderDetailScreen.route,
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
            RouteDetailGeneralScreen(navController, 1, mapViewModel,mapViewModel2, routeOrderListViewModel)
        }

        composable(Screens.RoutesOrderListScreen.route) {
            RoutesOrderListScreen(navController, routeOrderListViewModel, filterPopupViewModel)
        }
        composable(Screens.ProfileScreen.route) {
            ProfileScreen(profileViewModel, navController)
        }
        composable(Screens.LoginScreen.route) {
            LoginScreen(loginViewModel, navController)
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