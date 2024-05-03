package com.example.rutescompartidesapp.view.map.components

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rutescompartidesapp.view.map.viewModels.MapViewModel
import androidx.navigation.NavController
import com.example.rutescompartidesapp.view.login.LoginViewModel
import com.example.rutescompartidesapp.view.map.viewModels.SearchViewModel

@Composable
fun SearchViewContainer(searchViewModel: SearchViewModel, loginViewModel: LoginViewModel, navHost: NavController, ctx: Context, mapViewModel: MapViewModel) {
    Row (modifier = Modifier
        .offset(y = -(4).dp)
        .fillMaxWidth()
        .wrapContentHeight() ,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SearchView(searchViewModel, ctx, mapViewModel)
        NotificationButtonCard(loginViewModel = loginViewModel, navHost = navHost)
    }
}