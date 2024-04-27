package com.example.rutescompartidesapp.view.profile


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.rutescompartidesapp.ui.theme.openSans
import com.example.rutescompartidesapp.utils.Constants
import com.example.rutescompartidesapp.view.generic_components.HeaderSphere
import com.example.rutescompartidesapp.view.login.LoginViewModel
import com.example.rutescompartidesapp.view.profile.components.CreateCardsWithItems
import com.example.rutescompartidesapp.view.profile.components.LogOutPopup
import com.example.rutescompartidesapp.view.profile.components.ProfileEditButton
import com.example.rutescompartidesapp.view.profile.components.ReviewButtons
import com.example.rutescompartidesapp.view.profile.components.routeProfileItemsList
import com.example.rutescompartidesapp.view.profile.components.userProfileItemsList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(profileViewModel: ProfileViewModel, loginViewModel: LoginViewModel, navController: NavController) {

    val onClickPlaceholder by profileViewModel.onClickPlaceholder.collectAsStateWithLifecycle()
    val editProfileButtonSize by profileViewModel.editProfileButtonSize.collectAsStateWithLifecycle()
    val editProfileButtonVisible by profileViewModel.editProfileButtonVisible.collectAsStateWithLifecycle()
    val userID = loginViewModel.user.collectAsStateWithLifecycle().value?.userId
    loginViewModel.getUser(userID!!)
    val user by loginViewModel.user.collectAsStateWithLifecycle()

    val animateSize by animateFloatAsState(
        targetValue = editProfileButtonSize,
        label = "EditProfileButtonAnimation",
        animationSpec = tween(durationMillis = 2500)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                BoxWithConstraints(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    val centerBottomPadding = 320.dp - 25.dp

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(350.dp)
                            .align(Alignment.TopCenter)
                    ) {
                        HeaderSphere(320.dp)
                        Column(
                            modifier = Modifier
                                .width(LocalConfiguration.current.screenWidthDp.dp)
                                .padding(top = 20.dp)
                                .zIndex(3f),
                            verticalArrangement = Arrangement.spacedBy(5.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .padding(bottom = 15.dp)
                            ) {
                                Text(
                                    text = user!!.name,
                                    fontSize = 36.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color.White,
                                    fontFamily = openSans
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                                Text(
                                    text = user!!.email,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color.White,
                                    fontFamily = openSans
                                )
                                Spacer(modifier = Modifier.height(5.dp))
                                Text(
                                    text = user!!.phone.toString(),
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color.White,
                                    fontFamily = openSans
                                )
                            }

                            // Review buttons
                            ReviewButtons(modifier = Modifier, buttonText = "Valoracions rebudes")

                            Spacer(modifier = Modifier.padding(2.5.dp))

                            ReviewButtons(modifier = Modifier, buttonText = "Valoracions fetes")
                        }

                    }
                    Box(
                        modifier = Modifier
                            .padding(
                                top = centerBottomPadding,
                                start = LocalConfiguration.current.screenWidthDp.dp / 2 - 25.dp
                            )
                    ) {
                        // Profile Edit Button

                        ProfileEditButton(
                            modifier = Modifier,
                            animateSize = animateSize,
                            navController = navController,
                            iconVisible = editProfileButtonVisible
                        )



                        LogOutPopup(viewModelProfile = profileViewModel, navController)

                        // This will be delete
                        if (onClickPlaceholder) {
                            AlertDialog(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight(),
                                onDismissRequest = { profileViewModel.onClickItemPlaceholder(false) },
                                properties = DialogProperties(
                                    dismissOnBackPress = true,
                                    dismissOnClickOutside = true
                                )
                            ) {
                                ElevatedCard(
                                    modifier = Modifier
                                        .fillMaxWidth(0.9f)
                                        .fillMaxHeight(0.2f),
                                    shape = RoundedCornerShape(15.dp),
                                    colors = CardDefaults.cardColors(containerColor = Color.White),
                                    elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
                                ) {
                                    Column(
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(
                                            text = "Not implemented yet",
                                            color = Color.Black
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 25.dp)
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // First card creation (Route Settings)
            CreateCardsWithItems(routeProfileItemsList, 0.dp, 0.dp, profileViewModel, navController, 1)

            // Second card creation (User Settings)
            CreateCardsWithItems(userProfileItemsList, 20.dp, 0.dp, profileViewModel, navController, 2)
        }
    }
}

