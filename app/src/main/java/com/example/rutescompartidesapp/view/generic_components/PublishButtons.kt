package com.example.rutescompartidesapp.view.generic_components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.rutescompartidesapp.ui.theme.OrangeRC

/**
 * Composable function to display a publish button.
 *
 * @param onClick Callback to be invoked when the button is clicked.
 * @param text The text to be displayed on the button.
 */
@Composable
fun PublishButton(onClick: () -> Unit, text: String){
    ElevatedButton(
        shape = RoundedCornerShape(16.dp),
        onClick = {
            // TODO POST ROUTE
            onClick()
        },
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = OrangeRC
        )
    ) {
        Text(
            text = text, color = Color.White,
            style = MaterialTheme.typography.headlineMedium
        )
    }
}
/**
 * Composable function to display a publish next button.
 *
 * @param onClickCheck Callback to be invoked when the button is clicked.
 */
@Composable
fun PublishNextButton(onClickCheck: (() -> Unit)){
    ElevatedButton(
        shape = RoundedCornerShape(16.dp),
        onClick = {
                onClickCheck()
        },
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = OrangeRC
        )
    ) {
        Text(
            text = "Següent", color = Color.White,
            style = MaterialTheme.typography.headlineMedium
        )
    }

}

/**
 * Composable function to display a publish back button.
 *
 * @param onClickBack Callback to be invoked when the button is clicked.
 */
@Composable
fun PublishBackButton(onClickBack: () -> Unit){
    ElevatedButton(
        shape = RoundedCornerShape(16.dp),
        onClick = {
            onClickBack()
        },
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = MaterialTheme.colorScheme.onBackground
        )
    ) {
        Text(
            text = "Tornar",
            color = MaterialTheme.colorScheme.background,
            style = MaterialTheme.typography.headlineMedium,
        )
    }
}