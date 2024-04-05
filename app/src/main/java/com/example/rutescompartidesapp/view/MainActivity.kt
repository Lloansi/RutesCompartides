package com.example.rutescompartidesapp.view

import android.content.pm.PackageManager
import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
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
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.PratikFagadiya.smoothanimationbottombar.model.SmoothAnimationBottomBarScreens
import com.PratikFagadiya.smoothanimationbottombar.properties.BottomBarProperties
import com.PratikFagadiya.smoothanimationbottombar.ui.SmoothAnimationBottomBar
import com.example.rutescompartidesapp.R
import com.example.rutescompartidesapp.navigation.Screens
import com.example.rutescompartidesapp.ui.theme.MateBlackRC
import com.example.rutescompartidesapp.ui.theme.RutesCompartidesAppTheme
import com.example.rutescompartidesapp.utils.Constants.ALL_PERMISSIONS
import com.example.rutescompartidesapp.view.confirm_delivery.viewmodel.CameraViewModel
import com.example.rutescompartidesapp.view.confirm_delivery.viewmodel.DrawViewModel
import com.example.rutescompartidesapp.view.map.viewModels.MapViewModel
import com.example.rutescompartidesapp.view.chat.ChatViewModel2
import com.example.rutescompartidesapp.view.routes_order_list.viewmodels.FilterPopupViewModel
import com.example.rutescompartidesapp.view.routes_order_list.viewmodels.RoutesOrderListViewModel
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
                val chatViewModel: ChatViewModel2 = hiltViewModel()
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
                        chatViewModel,
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