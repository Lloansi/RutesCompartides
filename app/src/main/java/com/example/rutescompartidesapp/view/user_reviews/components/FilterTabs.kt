package com.example.rutescompartidesapp.view.user_reviews.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.rutescompartidesapp.view.user_reviews.UserReviewViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FilterTabRow(userReviewViewModel: UserReviewViewModel){
    val tabs = listOf("Valoracions fetes", "Valoracions rebudes")
    val selectedTabIndex by userReviewViewModel.selectedTabIndex.collectAsStateWithLifecycle()
    val pagerState = rememberPagerState { tabs.size }

    LaunchedEffect(selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }
    LaunchedEffect(pagerState.currentPage) {
        if (!pagerState.isScrollInProgress) {
            userReviewViewModel.onSelectTab(pagerState.currentPage)
        }
    }

    TabRow(selectedTabIndex = selectedTabIndex) {
        tabs.forEachIndexed { index, title ->
            Tab(
                text = { Text(title) },
                selected = selectedTabIndex == index,
                onClick = { userReviewViewModel.onSelectTab(index) },
            )
        }
    }
}