package com.example.rutescompartidesapp.view.routes_order_list.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.rutescompartidesapp.view.routes_order_list.viewmodels.RoutesOrderListViewModel
import com.example.rutescompartidesapp.view.routes_order_list.viewmodels.TabRowViewModel

data class TabItems(
    val title: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabRows(routesOrderListViewModel: RoutesOrderListViewModel){
    val tabRowViewModel: TabRowViewModel = hiltViewModel()
    val selectedTabIndex by tabRowViewModel.selectedTabIndex.collectAsStateWithLifecycle()
    val tabItems = tabRowViewModel.tabItems
    val routes by routesOrderListViewModel.routes.collectAsStateWithLifecycle()
    val orders by routesOrderListViewModel.orders.collectAsStateWithLifecycle()
    val isSearching by routesOrderListViewModel.isSearching.collectAsStateWithLifecycle()
    val pagerState = rememberPagerState {
        tabItems.size
    }

    LaunchedEffect(selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }
    LaunchedEffect(pagerState.currentPage) {
        if (!pagerState.isScrollInProgress) {
            tabRowViewModel.onSelectTab(pagerState.currentPage)
        }
    }

    Column {
        // Rutes and Comandes Tabs
        TabRow(selectedTabIndex = selectedTabIndex) {
            tabItems.forEachIndexed { index, item ->
                Tab(modifier = Modifier.background(MaterialTheme.colorScheme.background),
                    selected = index == selectedTabIndex,
                    onClick = { tabRowViewModel.onSelectTab(index) },
                    text = { Text(text = item.title,
                        style = MaterialTheme.typography.titleSmall) },
                    icon = {
                        Icon(
                            imageVector = if (index == selectedTabIndex) {
                                item.selectedIcon
                            } else {
                                item.unselectedIcon
                            },
                            contentDescription = item.title
                        )
                    },
                )
            }
        }
        // Content
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth(),

        ) { index ->
            Box(modifier = Modifier.fillMaxSize()) {
                if (isSearching) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                } else {
                    LazyColumn(modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                        if (index == 0) {
                            if (routes.isEmpty()){
                                item {
                                    EmptyResults(type = "ruta")
                                }
                            }else {
                                items(routes.size) { index ->
                                    Spacer(modifier = Modifier.padding(8.dp))
                                    RouteCard(route = routes[index])
                                }
                            }
                        } else {
                            if (orders.isEmpty()){
                                item {
                                    EmptyResults(type = "comanda")
                                }
                            } else {
                                    items(orders.size) { index ->
                                        Spacer(modifier = Modifier.padding(8.dp))
                                        OrderCard(order = orders[index])
                                    }
                            }
                        }
                    }
                }
            }
        }
    }
}


