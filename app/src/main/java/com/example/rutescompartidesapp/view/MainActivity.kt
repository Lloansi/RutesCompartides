package com.example.rutescompartidesapp.view

import android.Manifest
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
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
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
import com.example.rutescompartidesapp.view.login.LoginScreen
import com.example.rutescompartidesapp.view.map.MapScreen
import com.example.rutescompartidesapp.view.profile.ProfileScreen
import com.example.rutescompartidesapp.view.profile.ProfileViewModel
import com.example.rutescompartidesapp.view.publish_order.PublishOrderScreen
import com.example.rutescompartidesapp.view.publish_route.PublishRouteScreen
import com.example.rutescompartidesapp.view.route_detail.RoutesDetailScreen
import com.example.rutescompartidesapp.view.routes_order_list.RoutesOrderListScreen
import com.example.rutescompartidesapp.view.signup.SignUpScreen
import dagger.hilt.android.AndroidEntryPoint

private const val DEBUG_TAG = "Gestures"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    //private lateinit var mDetector: GestureDetectorCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //mDetector = GestureDetectorCompat(this, MyGestureListener())
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.INTERNET), 0)
        }

       // WindowCompat.setDecorFitsSystemWindows(window, false)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { view, insets ->
            val bottom = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
            view.updatePadding(bottom = bottom)
            insets
        }
        setContent {
            RutesCompartidesAppTheme {

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
                                })
                       }
                    }
                }
                ) { paddingValues ->
                    ScreenNavigationConfiguration(
                        navController,
                        Modifier.padding(paddingValues),
                    )
                }
            }
        }
    }
}

@Composable
fun ScreenNavigationConfiguration(navController: NavHostController, paddingModifier: Modifier) {

    NavHost(navController = navController, startDestination = Screens.MapScreen.route, modifier = paddingModifier) {

        composable(Screens.MapScreen.route) {
            MapScreen()
        }

        composable(Screens.RoutesOrderListScreen.route) {
            RoutesOrderListScreen(navController)
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
        composable(Screens.RouteDetailScreen.route,
            arguments = listOf(navArgument("routeId"){
            type = NavType.IntType
        })) {
            val routeID = it.arguments?.getInt("routeId")
            RoutesDetailScreen(routeID!!, navController)
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