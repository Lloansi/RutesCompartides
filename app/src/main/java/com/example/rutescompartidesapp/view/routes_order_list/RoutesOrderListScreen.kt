package com.example.rutescompartidesapp.view.routes_order_list

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.FilterAltOff
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.rutescompartidesapp.view.login.LoginViewModel
import com.example.rutescompartidesapp.view.routes_order_list.components.FilterPopup
import com.example.rutescompartidesapp.view.routes_order_list.components.TabRows
import com.example.rutescompartidesapp.view.routes_order_list.viewmodels.FilterPopupViewModel
import com.example.rutescompartidesapp.view.routes_order_list.viewmodels.RoutesOrderListViewModel
import com.example.rutescompartidesapp.view.routes_order_list.viewmodels.TabRowViewModel


@Composable
fun RoutesOrderListScreen(navController: NavHostController, routeOrderListViewModel: RoutesOrderListViewModel,
                          filterPopupViewModel: FilterPopupViewModel, loginViewModel: LoginViewModel,
                          tabRowViewModel: TabRowViewModel
) {
    BackHandler {
        navController.popBackStack()
        loginViewModel.updateCurrentIndex(0)
    }
    val searchText by routeOrderListViewModel.searchText.collectAsStateWithLifecycle()
    val areFilterActive by routeOrderListViewModel.activeFilters.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current
    val user by loginViewModel.user.collectAsStateWithLifecycle()
    Column (Modifier.fillMaxSize()) {
        Row (Modifier.padding(12.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically) {
            // Search Bar
            OutlinedTextField(modifier = Modifier
                .weight(3f)
                .padding(start = 6.dp, end = 6.dp),
                value = searchText,
                onValueChange = routeOrderListViewModel::onSearchTextChange ,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        tint = MaterialTheme.colorScheme.onSurface,
                        contentDescription = "Icona de cerca"
                    )
                },
                placeholder = {
                    Text(text = "Cercar", color = Color.Gray)
                },
                shape = RoundedCornerShape(16.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    unfocusedIndicatorColor = Color.Gray,
                    disabledContainerColor = MaterialTheme.colorScheme.background,
                    disabledIndicatorColor = Color.Gray,
                    focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                    unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        focusManager.clearFocus()
                    }
                ),
                singleLine = true,
                )
            Spacer(modifier = Modifier.padding(8.dp))
            // Filter Button
            FloatingActionButton(
                modifier= Modifier
                    .weight(0.5f)
                    .align(Alignment.CenterVertically)
                    .height(52.dp),
                onClick = {
                    if (areFilterActive.contains(true)) {
                        routeOrderListViewModel.onResetFilters()
                    } else {
                        filterPopupViewModel.onPopupShow(true)
                    }
                     },

                containerColor = if (areFilterActive.contains(true)) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.onTertiaryContainer
                },
                contentColor = if (areFilterActive.contains(true)) {
                    MaterialTheme.colorScheme.secondary
                } else {
                    MaterialTheme.colorScheme.primary
                }
            ) {
                if (areFilterActive.contains(true)) {
                    Icon(Icons.Filled.FilterAltOff, contentDescription = "Filter off icon",
                        Modifier
                            .height(32.dp)
                            .width(32.dp))
                } else {
                    Icon(Icons.Filled.FilterAlt, contentDescription = "Filter icon",
                        Modifier
                            .height(32.dp)
                            .width(32.dp))
                }
            }
            // Filter Popup
            FilterPopup(routeOrderListViewModel, filterPopupViewModel)
        }
        // Tabs and lists
        Row (Modifier.fillMaxWidth()) {
            TabRows(routeOrderListViewModel, navController, user!!, tabRowViewModel)
        }
    }
}

