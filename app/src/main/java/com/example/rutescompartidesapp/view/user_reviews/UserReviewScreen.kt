package com.example.rutescompartidesapp.view.user_reviews

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.rutescompartidesapp.data.domain.review.Review
import com.example.rutescompartidesapp.ui.theme.MateBlackRC
import com.example.rutescompartidesapp.utils.LocalConstants.reviewList
import com.example.rutescompartidesapp.utils.LocalConstants.userList
import com.example.rutescompartidesapp.view.login.LoginViewModel
import com.example.rutescompartidesapp.view.user_reviews.components.FilterTabRow
import com.example.rutescompartidesapp.view.user_reviews.components.UserReviewItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserReviewScreen(userReviewViewModel: UserReviewViewModel, loginViewModel: LoginViewModel, navHost: NavHostController) {

    val userID = loginViewModel.user.collectAsStateWithLifecycle().value?.userId
    loginViewModel.getUser(userID!!)
    val user by loginViewModel.user.collectAsStateWithLifecycle()
    val selectedTabIndex by userReviewViewModel.selectedTabIndex.collectAsStateWithLifecycle()
    var filteredReviewList: List<Review>

    Scaffold( modifier = Modifier
        .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Valoracions") },
                navigationIcon = {
                    IconButton(onClick = { navHost.popBackStack()  }
                    ) {
                        Icon(imageVector = Icons.Filled.ArrowBackIosNew, contentDescription = "Go Back",
                            tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MateBlackRC,
                    titleContentColor = Color.White
                ),
            )
        }) { paddingValues ->
        Column(modifier = Modifier.padding(top = paddingValues.calculateTopPadding())){
            FilterTabRow(userReviewViewModel = userReviewViewModel)

            filteredReviewList = if (selectedTabIndex == 0){
                reviewList.filter { it.userId == user!!.userId }
            } else {
                reviewList.filter { it.userToReviewId == user!!.userId }
            }

            LazyColumn{
                items(filteredReviewList.size){ review ->
                    val userToGetName = userList.find {
                        if (selectedTabIndex == 0){
                            it.userId == filteredReviewList[review].userToReviewId
                        } else {
                            it.userId == filteredReviewList[review].userId
                        }
                    }
                    UserReviewItem(review = filteredReviewList[review], userName = userToGetName!!.name)
                }
            }
        }
    }
}

