package com.example.rutescompartidesapp.view.profile


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.example.rutescompartidesapp.R
import com.example.rutescompartidesapp.ui.theme.GrayRC
import com.example.rutescompartidesapp.ui.theme.MateBlackRC
import com.example.rutescompartidesapp.view.login.LoginScreen
import com.example.rutescompartidesapp.view.profile.components.CreateCardsWithItems
import com.example.rutescompartidesapp.view.profile.components.LogOutPopup
import com.example.rutescompartidesapp.view.profile.components.ProfileEditButton
import com.example.rutescompartidesapp.view.profile.components.ReviewButtons
import com.example.rutescompartidesapp.view.profile.components.routeProfileItemsList
import com.example.rutescompartidesapp.view.profile.components.userProfileItemsList
import java.util.concurrent.Flow


object ProfileScreen: Screen {
    @Composable
    override fun Content() {
        ProfileScreen(ProfileViewModel())
    }
}

@Composable
fun ProfileScreen(viewModel: ProfileViewModel) {

    val backgroundOpacity by viewModel.backgroundOpacity.collectAsState()

    Scaffold(
        containerColor = GrayRC.copy(alpha = backgroundOpacity),
        topBar = {
            // Profile App bar
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
                            Image(
                                painter = painterResource(id = R.drawable.esfera_rc),
                                contentDescription = null,
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(320.dp),
                                colorFilter = ColorFilter.tint(
                                    color = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.secondary
                                    else MaterialTheme.colorScheme.primary
                                )
                            )
                        }
                        Box(
                            modifier = Modifier
                                .padding(
                                    top = centerBottomPadding,
                                    start = LocalConfiguration.current.screenWidthDp.dp / 2 - 25.dp
                                )
                        ) {
                            // Profile Edit Button
                            ProfileEditButton(modifier = Modifier, onClick = {
                                viewModel.onClickLogOut(true)
                                viewModel.changeBgOpacity(0.5f)
                            })
                            LogOutPopup(viewModelProfile = viewModel)
                        }
                    }
                }
            }
            Column(
                modifier = Modifier
                    .width(LocalConfiguration.current.screenWidthDp.dp)
                    .padding(top = 20.dp),
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
                        text = "Alejandro Arcas",
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "alejandroarcasleon@gmail.com",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.White
                    )
                    Text(
                        text = "653 833 853",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.White
                    )
                }

                // Review buttons
                ReviewButtons(modifier = Modifier, buttonText = "Valoracions rebudes")

                Spacer(modifier = Modifier.padding(2.5.dp))

                ReviewButtons(modifier = Modifier, buttonText = "Valoracions fetes")
            }

        }
    ) {
        // Profile content

            innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 25.dp, bottom = 25.dp)
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(25.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // Creacion de la primera Card (Route Settings)
            CreateCardsWithItems(routeProfileItemsList, 0.dp)

            // Creacion de la primera Card (User Settings)
            CreateCardsWithItems(userProfileItemsList, 20.dp)
        }
    }
}
