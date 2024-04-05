package com.example.rutescompartidesapp.view.chat.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.example.rutescompartidesapp.data.domain.user.User


@Composable
fun TopInfoContainer(user: User) {
    Row (
        modifier = Modifier.fillMaxWidth().padding().padding(12.dp),
    ){
        Image(imageVector = Icons.Filled.ArrowBack, contentDescription = "Arrow go back")
        Spacer(modifier = Modifier.width(LocalConfiguration.current.screenWidthDp.dp/20))
        Text(
            text = user.username!!,
            style = MaterialTheme.typography.titleLarge
        )
    }
}
