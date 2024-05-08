package com.example.rutescompartidesapp.view.profile.components

import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.rutescompartidesapp.data.domain.ProfileItems
import com.example.rutescompartidesapp.ui.theme.openSans
import com.example.rutescompartidesapp.view.login.LoginViewModel
import com.example.rutescompartidesapp.view.map.viewModels.NotificationsViewModel
import com.example.rutescompartidesapp.view.profile.ProfileViewModel
import com.example.rutescompartidesapp.view.routes_order_list.viewmodels.RoutesOrderListViewModel
import com.example.rutescompartidesapp.view.routes_order_list.viewmodels.TabRowViewModel

@Composable
fun CreateCardsWithItems(list: List<ProfileItems>, paddingBottom: Dp, paddingTop: Dp, viewModel: ProfileViewModel, navController: NavController, listNumber: Int,
                         routesOrderListViewModel: RoutesOrderListViewModel, tabRowViewModel: TabRowViewModel,
                         userID: Int,
                         loginViewModel: LoginViewModel, notificationsViewModel: NotificationsViewModel) {
    val isMyFilterActive by routesOrderListViewModel.isMyFilterActive.collectAsStateWithLifecycle()
    Card(
        modifier = Modifier
            .width(LocalConfiguration.current.screenWidthDp.dp - 50.dp)
            .wrapContentHeight()
            .padding(top = paddingTop, bottom = paddingBottom),
        shape = RoundedCornerShape(25.dp),
        colors = if (isSystemInDarkTheme()) CardDefaults.cardColors(containerColor = Color(0xFF434343))
            else CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        for (i in list.indices) {
            TextButton(
                modifier = Modifier
                    .padding(top = 4.dp, bottom = 4.dp),
                shape = RoundedCornerShape(22.dp),
                onClick = {
                    when (list[i].title) {
                        "Les meves rutes" -> {
                            if (!isMyFilterActive){
                                routesOrderListViewModel.onMyFilterActive(userID)
                            }
                            tabRowViewModel.onSelectTab(0)
                            navController.navigate("RoutesOrderListScreen"){
                                popUpTo("ProfileScreen") { inclusive = true }
                            }
                            loginViewModel.updateCurrentIndex(1)
                        }
                        "Les meves comandes" -> {
                            if (!isMyFilterActive){
                                routesOrderListViewModel.onMyFilterActive(userID)
                            }
                            tabRowViewModel.onSelectTab(1)
                            navController.navigate("RoutesOrderListScreen"){
                                popUpTo("ProfileScreen") { inclusive = true }
                            }
                            loginViewModel.updateCurrentIndex(1)
                        }
                        "Notificacions" -> {
                            notificationsViewModel.onPopupToggle()
                        }
                        "Com funciona?" -> {
                            navController.navigate("ComFuncionaScreen") {
                                popUpTo("ComFuncionaScreen") { inclusive = true }
                            }
                        }
                        "FAQs i Conceptes claus" -> {
                            navController.navigate("FaqScreen") {
                                popUpTo("FaqScreen") { inclusive = true }
                            }
                        }
                        "Tanca la sessió" -> {
                            viewModel.onClickLogOutPopUpShow(true)
                            viewModel.changeBgOpacity(0.5f)
                        }
                        else -> println("El item ${list[i].title} no existe")
                    }
                }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier
                            .padding(start = 5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (listNumber == 1) Image(painter = painterResource(id = routeItemsListIcons[i]), contentDescription = null, modifier = Modifier.width(25.dp))
                        else Image(painter = painterResource(id = userItemsListIcons[i]), contentDescription = null, modifier = Modifier.width(25.dp))
                        Column(
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .padding(start = 15.dp),
                        ) {
                            var titleColor = if (isSystemInDarkTheme()) Color.White
                            else Color(0xFF000000)
                            if (list[i].title == "Tanca la sessió") {
                                titleColor = Color(0xFFEA4848)
                            }
                            Text(
                                text = list[i].title,
                                color = titleColor,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 16.sp,
                                fontFamily = openSans
                            )
                            Text(
                                text = list[i].subtitle,
                                color = if (isSystemInDarkTheme()) Color(0xFFE4E4E4)
                                    else Color(0xFF292929),
                                fontSize = 12.sp,
                                fontFamily = openSans
                            )
                        }
                    }
                    Image(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(color = if (isSystemInDarkTheme()) Color.White else Color.Black)
                    )
                }
            }
        }
    }
}