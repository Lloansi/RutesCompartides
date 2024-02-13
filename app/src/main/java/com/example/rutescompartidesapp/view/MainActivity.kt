package com.example.rutescompartidesapp.view

import android.os.Bundle
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cafe.adriel.voyager.navigator.Navigator
import com.PratikFagadiya.smoothanimationbottombar.model.SmoothAnimationBottomBarScreens
import com.PratikFagadiya.smoothanimationbottombar.properties.BottomBarProperties
import com.PratikFagadiya.smoothanimationbottombar.ui.SmoothAnimationBottomBar
import com.PratikFagadiya.smoothanimationbottombar.ui.theme.Blue
import com.PratikFagadiya.smoothanimationbottombar.ui.theme.BlueTint
import com.example.rutescompartidesapp.R
import com.example.rutescompartidesapp.navigation.Screens
import com.example.rutescompartidesapp.ui.theme.MateBlackRC
import com.example.rutescompartidesapp.ui.theme.OrangeRC
import com.example.rutescompartidesapp.ui.theme.RutesCompartidesAppTheme
import com.example.rutescompartidesapp.view.login.LoginScreen
import com.example.rutescompartidesapp.view.map.MapScreen
import com.example.rutescompartidesapp.view.profile.ProfileScreen
import com.example.rutescompartidesapp.view.routes_order_list.RoutesOrderListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

                Scaffold(bottomBar = {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(18.dp))
                    ) {
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
                                println("SELECTED_ITEM" + "onCreate: Selected Item ${it.name}")
                            })
                    }
                }
                ) { innerPadding ->
                    Modifier.padding(innerPadding)
                    ScreenNavigationConfiguration(navController)
                }

            }
        }
    }
}

@Composable
fun ScreenNavigationConfiguration(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Screens.MapScreen.route) {

        composable(Screens.MapScreen.route) {
            MapScreen()
        }

        composable(Screens.RoutesOrderListScreen.route) {
            RoutesOrderListScreen()
        }

        composable(Screens.ProfileScreen.route) {
            ProfileScreen()
        }
    }

}

