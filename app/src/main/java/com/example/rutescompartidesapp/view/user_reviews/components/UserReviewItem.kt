package com.example.rutescompartidesapp.view.user_reviews.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rutescompartidesapp.data.domain.review.Review
import com.example.rutescompartidesapp.ui.theme.OrangeRC

@Composable
fun UserReviewItem(review: Review, userName: String) {
    Box(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, top = 12.dp, bottom = 12.dp)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            color = OrangeRC,
                            shape = RoundedCornerShape(
                                topEnd = 10.dp, topStart = 10.dp
                            )
                        )
                        .wrapContentWidth()
                        .wrapContentHeight()
                        .padding(start = 10.dp, end = 10.dp, top = 3.dp, bottom = 3.dp)


                ) {
                    Text(
                        text = userName,
                        color = Color.White,
                        fontSize = 16.5.sp
                    )
                }
                Box(
                    modifier = Modifier
                        .background(
                            color = OrangeRC,
                            shape = RoundedCornerShape(
                                topEnd = 10.dp, topStart = 10.dp
                            )
                        )
                        .wrapContentWidth()
                        .wrapContentHeight()
                        .padding(start = 10.dp, end = 10.dp, top = 3.dp, bottom = 3.dp)


                ) {
                    Text(
                        text = "Puntuació: ${review.score}",
                        color = Color.White,
                        fontSize = 16.5.sp
                    )
                }
            }
            Box(
                modifier = Modifier
                    .background(
                        color = if (isSystemInDarkTheme()) Color(0xFF656565) else Color.White,
                        shape = RoundedCornerShape(
                            bottomEnd = 10.dp,
                            bottomStart = 10.dp
                        )
                    )
                    .width((LocalConfiguration.current.screenWidthDp * 0.9).dp)
                    .wrapContentHeight()

            ) {
                Row(
                    modifier = Modifier
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    Text(
                        text = review.reviewComment,
                        color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}