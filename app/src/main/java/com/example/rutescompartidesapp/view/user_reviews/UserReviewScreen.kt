package com.example.rutescompartidesapp.view.user_reviews

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.rutescompartidesapp.data.domain.UserLocal
import com.example.rutescompartidesapp.data.domain.review.Review
import com.example.rutescompartidesapp.utils.LocalConstants
import com.example.rutescompartidesapp.utils.LocalConstants.reviewList
import com.example.rutescompartidesapp.utils.LocalConstants.userList
import com.example.rutescompartidesapp.view.login.LoginViewModel
import com.example.rutescompartidesapp.view.user_reviews.components.UserReviewItem

@Composable
fun UserReviewScreen(userReviewViewModel: UserReviewViewModel, loginViewModel: LoginViewModel) {

    val userID = loginViewModel.user.collectAsStateWithLifecycle().value?.userId
    loginViewModel.getUser(userID!!)
    val user by loginViewModel.user.collectAsStateWithLifecycle()
    var filteredReviewList: List<Review>

    var tabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("Valoracions fetes", "Valoracions rebudes")

    Column {
        TabRow(selectedTabIndex = tabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index
                        println(tabIndex)},
                )
            }
        }

        filteredReviewList = if (tabIndex == 0){
            reviewList.filter { it.userId == user!!.userId }
        } else {
            reviewList.filter { it.userId != user!!.userId }
        }

        LazyColumn{
            items(filteredReviewList.size){ review ->
                val userToGetName = userList.find { it.userId == filteredReviewList[review].userToReviewId }
                UserReviewItem(review = filteredReviewList[review], userName = userToGetName!!.name)
            }
        }
    }
}

