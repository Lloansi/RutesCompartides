package com.example.rutescompartidesapp.view.generic_components.popups

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.rutescompartidesapp.data.domain.interactions.RouteInteraction
import com.example.rutescompartidesapp.ui.theme.BlueRC
import com.example.rutescompartidesapp.ui.theme.YellowRC
import com.example.rutescompartidesapp.view.map.viewModels.NotificationsViewModel
import com.example.rutescompartidesapp.view.route_detail.route_detail_driver.components.InteractionChip
import com.example.rutescompartidesapp.view.route_detail.route_detail_driver.components.InteractionText


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationPopup(
    notificationsViewModel: NotificationsViewModel,
    userInteractions: List<RouteInteraction>,
    navHost: NavController
){
    val notificationPopup by notificationsViewModel.notificationPopup.collectAsStateWithLifecycle()
    if (!notificationPopup) return
    AlertDialog(
        onDismissRequest = { notificationsViewModel.onPopupToggle() },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside=  true,
            usePlatformDefaultWidth = false
        )
    ) {
        ElevatedCard (modifier = Modifier
            .fillMaxWidth()
            .zIndex(2f)
            .padding(12.dp)
            .fillMaxHeight(0.5f).wrapContentHeight(),colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(2.5f),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Interaccions", color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.titleLarge,
                        )
                    }
                    Column(modifier = Modifier.weight(0.5f),
                        horizontalAlignment = Alignment.End
                    ) {
                        IconButton(onClick = { notificationsViewModel.onPopupToggle() }) {
                            Icon(imageVector = Icons.Filled.Close, contentDescription = "Close Popup Icon")
                        }
                    }
                }
                Divider(
                    thickness = 2.dp,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(8.dp)
                )
                if (userInteractions.isEmpty()) {
                    Text(
                        text = "No tens cap interacció",
                        color = MaterialTheme.colorScheme.onBackground
                    )
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(LocalConfiguration.current.screenHeightDp.dp * 0.6f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        items(userInteractions.size) { index ->
                            Spacer(modifier = Modifier.padding(8.dp))
                            RouteInteractionCardNotification(
                                interaction = userInteractions[index],
                                notificationsViewModel = notificationsViewModel,
                                navHost = navHost
                            )
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun RouteInteractionCardNotification(
    notificationsViewModel: NotificationsViewModel,
    interaction: RouteInteraction, navHost: NavController
){
    var firstText = ""
    var secondText = ""
    var chipText = ""
    var chipColor = Color.White
    var chipTextColor = Color.White


    when (interaction.status) {
        "Acceptada" -> {
            firstText = "Has acceptat"
            secondText = "dur la comanda"
            chipColor = BlueRC
            chipText = "Acceptada"

        }
        "Entregada" -> {
            firstText = "Has entregat"
            secondText = "la comanda"
            chipText = "Entregada"
            chipColor = BlueRC
        }
        "Pendent" -> {
            firstText = "Encara no has respost a la"
            secondText = "petició de transport de la comanda"
            chipText = "Pendent"
            chipColor = YellowRC
            chipTextColor = Color.Black
        }
        "Declinada" -> {
            firstText = "Has declinat la"
            secondText = "petició de transport de la comanda"
            chipText = "Declinada"
            chipColor = MaterialTheme.colorScheme.onBackground
            chipTextColor = MaterialTheme.colorScheme.background
        }
        "Valorada" -> {
            firstText = "Has valorat"
            secondText = "la experiència de la comanda"
            chipText = "Valorada"
            chipColor = MaterialTheme.colorScheme.primary
            chipTextColor = Color.White
        }
    }

    ElevatedCard(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            notificationsViewModel.onPopupToggle()
            navHost.navigate("OrderDetailScreen/${interaction.orderID}")
        },
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.onTertiaryContainer)) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(end = 4.dp)
                .weight(2f),
                verticalArrangement = Arrangement.Center) {
                Text(text = "${interaction.date.split("-")[0]} a les ${interaction.date.split("-")[1]} ",
                    color = MaterialTheme.colorScheme.onBackground)
                InteractionText(firstText, secondText, interaction.orderID)
            }
            Column {
                InteractionChip(text = chipText, containerColor = chipColor, chipColorText = chipTextColor)
            }

        }

    }

}
