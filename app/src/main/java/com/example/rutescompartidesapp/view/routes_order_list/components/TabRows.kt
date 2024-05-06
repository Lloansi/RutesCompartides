package com.example.rutescompartidesapp.view.routes_order_list.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChipDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.rutescompartidesapp.data.domain.UserLocal
import com.example.rutescompartidesapp.ui.theme.GrayRC
import com.example.rutescompartidesapp.view.routes_order_list.viewmodels.RoutesOrderListViewModel
import com.example.rutescompartidesapp.view.routes_order_list.viewmodels.TabRowViewModel

data class TabItems(
    val title: String,
    val unselectedIcon: Int,
    val selectedIcon: Int
)

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun TabRows(routesOrderListViewModel: RoutesOrderListViewModel, navController: NavHostController, user: UserLocal,
            tabRowViewModel: TabRowViewModel ){
    val selectedTabIndex by tabRowViewModel.selectedTabIndex.collectAsStateWithLifecycle()
    val tabItems = tabRowViewModel.tabItems
    val routes by routesOrderListViewModel.routes.collectAsStateWithLifecycle()
    val orders by routesOrderListViewModel.orders.collectAsStateWithLifecycle()
    val isSearching by routesOrderListViewModel.isSearching.collectAsStateWithLifecycle()
    val isMyFilterActive by routesOrderListViewModel.isMyFilterActive.collectAsStateWithLifecycle()



    val pagerState = rememberPagerState {
        tabItems.size
    }

    LaunchedEffect(selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }
    LaunchedEffect(pagerState.currentPage) {
        tabRowViewModel.onSelectTab(pagerState.currentPage)
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
                            modifier = Modifier.size(28.dp),
                            painter = painterResource(if (index == selectedTabIndex) {
                                item.selectedIcon
                            } else {
                                item.unselectedIcon
                            }),
                            contentDescription = item.title
                        )
                    },
                )
            }
        }
        Row(modifier = Modifier.fillMaxWidth().padding(start = 6.dp),
            horizontalArrangement = Arrangement.Start) {
            ElevatedFilterChip(
                selected = isMyFilterActive ,
                onClick = { routesOrderListViewModel.onMyFilterActive(user.userId) },
                label = { if (isMyFilterActive) {
                    Text("Mostrar meves",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge,)
                } else {
                    Text("Mostrar meves",
                        color = Color.Gray,
                        style = MaterialTheme.typography.bodyLarge,)
                } },
                leadingIcon = {
                    if (isMyFilterActive) {
                        Icon(imageVector = Icons.Filled.Check,
                            contentDescription = "Check Icon",
                            tint = Color.White
                        )
                    }
                },
                colors = FilterChipDefaults.elevatedFilterChipColors(
                    selectedContainerColor = MaterialTheme.colorScheme.primary,
                    containerColor = GrayRC
                ))
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
                                    Spacer(modifier = Modifier.padding(6.dp))
                                    RouteCard(route = routes[index], navController, user.userId)
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
                                        OrderCard(order = orders[index], navController)
                                    }
                            }
                        }
                    }
                }
            }
        }
    }
}


