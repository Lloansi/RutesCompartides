package com.example.rutescompartidesapp.view.routes_order_list.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
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
import androidx.compose.ui.tooling.preview.Preview
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
@Preview(showBackground = true)
@Composable
fun TabRows(){
    val routesOrderListViewModel: RoutesOrderListViewModel = hiltViewModel()
    val tabRowViewModel: TabRowViewModel = hiltViewModel()
    val selectedTabIndex by tabRowViewModel.selectedTabIndex.collectAsStateWithLifecycle()
    val tabItems = tabRowViewModel.tabItems
    val routes by routesOrderListViewModel.routes.collectAsStateWithLifecycle()
    val orders by routesOrderListViewModel.orders.collectAsStateWithLifecycle()
    val isSearching by routesOrderListViewModel.isSearching.collectAsStateWithLifecycle()
    val filterList by routesOrderListViewModel.activeFilters.collectAsStateWithLifecycle()
    val queryList by routesOrderListViewModel.queryList.collectAsStateWithLifecycle()
    val isFiltered by routesOrderListViewModel.isFiltered.collectAsStateWithLifecycle()
    val filteredList by routesOrderListViewModel.filteredRoutes.collectAsStateWithLifecycle()
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

    Column{
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
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth()
        ) { index ->
            Box(modifier = Modifier.fillMaxSize()) {
                println(filterList.contains(true).not())
                println(filterList)
                if (isSearching) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                } else {
                    LazyColumn(modifier = Modifier.fillMaxWidth()) {
                        if (index == 0) {
                            items(routes.size) { index ->
                                Spacer(modifier = Modifier.padding(8.dp))
                                RouteCard(route = routes[index])
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


