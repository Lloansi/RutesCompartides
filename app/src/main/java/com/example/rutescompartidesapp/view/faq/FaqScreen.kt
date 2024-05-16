
package com.example.rutescompartidesapp.view.faq

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.rutescompartidesapp.ui.theme.MateBlackRC
import com.example.rutescompartidesapp.ui.theme.OrangeRC
import com.example.rutescompartidesapp.ui.theme.fredokaOne
import com.example.rutescompartidesapp.view.faq.components.FaqItemModel
import com.example.rutescompartidesapp.view.faq.components.ItemCategoryModel
import com.example.rutescompartidesapp.view.generic_components.BackButtonArrow
import com.example.rutescompartidesapp.view.generic_components.HeaderSphere

/**
 * Composable function to display the FAQ screen.
 *
 * @param navController The NavHostController for navigation.
 * @param viewModel The view model for FAQ screen.
 */
@Composable
fun FaqScreen(navController: NavHostController, viewModel: FaqViewModel) {

    val faqListItems by viewModel.itemListMapped.collectAsState()

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .height(200.dp)
        ) {
            HeaderSphere(200.dp)

            BackButtonArrow(navController = navController, alignment = Alignment.TopStart, "ProfileScreen")

            Text(
                modifier = Modifier
                    .align(Alignment.Center),
                text = "FAQs/Conceptes claus",
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = fredokaOne
            )
        }
        CategorizedLazyColumn(categories = faqListItems)
    }
}

/**
 * Composable function to display an item card in the FAQ screen.
 *
 * @param item The FAQ item model.
 */
@Composable
private fun ItemCard(item: FaqItemModel) {
    ElevatedCard(
        colors = if (isSystemInDarkTheme()) CardDefaults.cardColors(
            containerColor = Color(
                0xFF434343
            )
        )
        else CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(17.dp),
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .padding(5.dp),
    ) {
        ItemCardContent(item = item)
    }
}

/**
 * Composable function to display the content of an FAQ item.
 *
 * @param item The FAQ item model.
 */
@Composable
private fun ItemCardContent(item: FaqItemModel) {

    var isExpanded by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .animateContentSize(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth(0.85f),
                text = item.title,
                color = OrangeRC,
                fontSize = 15.sp
            )
            IconButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    isExpanded = !isExpanded
                }) {
                if (isExpanded) {
                    Icon(
                        imageVector = Icons.Rounded.KeyboardArrowUp,
                        contentDescription = null,
                        tint = OrangeRC
                    )
                } else {
                    Icon(
                        imageVector = Icons.Rounded.KeyboardArrowDown,
                        contentDescription = null,
                        tint = OrangeRC
                    )
                }
            }
        }
        if (isExpanded) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = if (isSystemInDarkTheme()) Color(0xFF656565) else Color(0xFFDADADA),
                        shape = RoundedCornerShape(bottomEnd = 17.dp, bottomStart = 17.dp)
                    )
            ) {
                Text(
                    modifier = Modifier
                        .padding(12.dp),
                    text = item.description,
                    color = if (isSystemInDarkTheme()) Color.White else MateBlackRC,
                    fontSize = 13.sp
                )
            }
        }

    }

}

/**
 * Composable function to display the header of a category in the FAQ screen.
 *
 * @param text The text to display in the header.
 */
@Composable
private fun CategoryHeader(text: String) {
    Text(
        text = text,
        fontSize = 20.sp,
        color = if (isSystemInDarkTheme()) Color(0xFFD3D3D3) else MateBlackRC,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .background(color = if (isSystemInDarkTheme()) MateBlackRC else Color(0xFFF2F2F2))
            .padding(5.dp, bottom = 10.dp, top = 10.dp)
    )
}

/**
 * Composable function to display a lazy column of categorized items in the FAQ screen.
 *
 * @param categories The list of categories with their associated items.
 */
@Composable
private fun CategorizedLazyColumn(categories: List<ItemCategoryModel>) {
    Column(
        modifier = Modifier
            .padding(vertical = 15.dp)
            .verticalScroll(rememberScrollState())
    ) {
        categories.forEach { category ->
            CategoryHeader(text = category.name)
            val itemList = category.itemList
            itemList.forEach { faqItem ->
                ItemCard(item = faqItem)
            }
        }
    }
}
