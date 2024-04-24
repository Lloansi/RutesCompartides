package com.example.rutescompartidesapp.view.map

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.rutescompartidesapp.R
import com.example.rutescompartidesapp.view.map.components.CardBottomMap
import com.example.rutescompartidesapp.view.map.components.ExpandableFloatingButton
import com.example.rutescompartidesapp.view.map.components.FilteredListsBelowSearchBar
import com.example.rutescompartidesapp.view.map.components.MapViewContainer
import com.example.rutescompartidesapp.view.map.components.SearchViewContainer
import com.example.rutescompartidesapp.view.map.viewModels.MapViewModel
import com.example.rutescompartidesapp.view.map.viewModels.SearchViewModel

val openSansFamily = FontFamily(
    Font(R.font.opensans, FontWeight.Normal),
)
val fredokaOneFamily = FontFamily(
    Font(R.font.fredokaone, FontWeight.Normal),
)


object MapScreen: Screen {
    const val maxKmFog = 5

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        //MapScreen()
    }
}

@Composable
fun MapScreen(navController: NavHostController, mapViewModel: MapViewModel, searchViewModel: SearchViewModel) {
    val ordersFiltered by mapViewModel.filteredOrders.collectAsState()
    val routesFiltered by mapViewModel.filteredRoutes.collectAsState()
    val routesFilteredSearchBar by searchViewModel.routesFilteredPerSearchedText.collectAsStateWithLifecycle()
    val ordersFilteredSearchBar by searchViewModel.ordersFilteredPerSearchedText.collectAsStateWithLifecycle()
    val locationsFilteredSearchBar by searchViewModel.locationsFilteredPerSearchedText.collectAsStateWithLifecycle()
    val isSearching by searchViewModel.isSearching.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            // Padding percentage formula
            val percentagePaddingDesviament = 100
            val padding = (LocalDensity.current.density * percentagePaddingDesviament).dp

            // We get the context
            val ctx = LocalContext.current

            val iconMarkerClickPointer = ContextCompat.getDrawable(ctx, R.drawable.marker_svgrepo_com)

            // Map
            MapViewContainer(viewModel = mapViewModel,ctx,iconMarkerClickPointer)

            // Search
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.TopStart)
            ) {
                SearchViewContainer(searchViewModel)
                FilteredListsBelowSearchBar(routesFilteredSearchBar, ordersFilteredSearchBar,locationsFilteredSearchBar, isSearching)
            }

            //Card orders/routes & Floatting buttons
            Box(modifier = Modifier
                .align(Alignment.BottomCenter)){
                Column(
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.End
                ) {
                    ExpandableFloatingButton(navController)
                    Spacer(modifier = Modifier.height(5.dp))
                    CardBottomMap(ordersFiltered, routesFiltered)
                }
            }
        }
    }
}