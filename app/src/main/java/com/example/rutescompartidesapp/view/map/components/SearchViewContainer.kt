package com.example.rutescompartidesapp.view.map.components

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.rutescompartidesapp.view.map.viewModels.MapViewModel
import androidx.navigation.NavController
import com.example.rutescompartidesapp.view.login.LoginViewModel
import com.example.rutescompartidesapp.view.map.viewModels.SearchViewModel

@Composable
fun SearchViewContainer(loginViewModel: LoginViewModel, navHost: NavController, ctx: Context, mapViewModel: MapViewModel) {
    val isSearching by mapViewModel.isSearching.collectAsState()
    Row (modifier = Modifier
        .offset(y = -(4).dp)
        .fillMaxWidth()
        .wrapContentHeight() ,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SearchView(ctx, mapViewModel)
        if (!isSearching) {
            NotificationButtonCard(loginViewModel = loginViewModel, navHost = navHost)
        }
    }
}