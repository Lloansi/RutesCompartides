package com.example.rutescompartidesapp.view.chat.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.rutescompartidesapp.data.domain.chat.Message
import com.example.rutescompartidesapp.data.domain.user.User

@Composable
fun ChatMessage(
    text: Message,
    isMyMessage: Boolean,
    modifier: Modifier = Modifier,
    user: User
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = if (isMyMessage) Arrangement.End else Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .background(
                    color = if (isMyMessage) MaterialTheme.colorScheme.primary else Color.LightGray,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(8.dp)
        ) {
            Text(
                text = user.username!!,
                style = MaterialTheme.typography.bodyLarge,
                color = if (isMyMessage) Color.White else Color.Black,
            )
            Text(
                text = text.text,
                style = MaterialTheme.typography.bodyMedium,
                color = if (isMyMessage) Color.White else Color.Black
            )
        }
    }
}