package com.example.rutescompartidesapp.view.route_detail.route_detail_driver.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.rutescompartidesapp.data.domain.interactions.RouteInteraction
import com.example.rutescompartidesapp.ui.theme.BlueRC
import com.example.rutescompartidesapp.ui.theme.MateBlackRC
import com.example.rutescompartidesapp.ui.theme.RedRC
import com.example.rutescompartidesapp.ui.theme.YellowRC
import com.example.rutescompartidesapp.view.route_detail.route_detail_driver.RouteDetailDriverViewModel

/**
 * Composable function for the Route Interaction Card, which displays the interaction details.
 * @param interaction RouteInteraction with the order and route ID, date and status .
 * @param index Index of the interaction from the lazy column used to match with the list of interactions on the [routeDetailDriverViewModel].
 * @param routeDetailDriverViewModel ViewModel for route details for the driver.
 * @param navHost Navigation controller.
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RouteInteractionCard(interaction: RouteInteraction, index: Int, routeDetailDriverViewModel: RouteDetailDriverViewModel, navHost: NavController){
    var firstText = ""
    var secondText = ""
    var chipText = ""
    var chipColor = Color.White
    var chipTextColor = Color.White
    var button1Text = ""
    var button1Color = Color.White
    var button1IconTint = Color.White
    var button2Text = ""
    var button2Color = Color.White
    var button2IconTint = Color.White
    var button1Icon = Icons.Default.Delete
    var button2Icon = Icons.Default.Delete
    // Will change the function later in the when block
    var button1OnClick: () -> Unit = { /*TODO*/ }
    var button2OnClick: () -> Unit = { /*TODO*/ }

    when (interaction.status) {
        "Acceptada" -> {
            firstText = "Has acceptat"
            secondText = "dur la comanda"
            chipColor = BlueRC
            chipText = "Acceptada"
            button1Text = "Confirmar"
            button1Color = MateBlackRC
            button1Icon = Icons.Filled.Check
            button1IconTint = Color.White
            button1OnClick = { routeDetailDriverViewModel.setRouteToConfirm(interaction) }
            button2IconTint = Color.Black
            button2Text = "Declinar"
            button2Color = RedRC
            button2Icon = Icons.Default.Cancel
            button2OnClick = { routeDetailDriverViewModel.modifyInteractionStatus(interaction, index,"Declinada") }

        }
        "Entregada" -> {
            firstText = "Has entregat"
            secondText = "la comanda"
            chipText = "Entregada"
            chipColor = BlueRC
            button1Text = "Valorar experiència"
            button1Color = MaterialTheme.colorScheme.primary
            button1Icon = Icons.Filled.ThumbUp
            button1IconTint = Color.White
            button1OnClick = {
                routeDetailDriverViewModel.showInteractionPopup(false)
                navHost.navigate("ValueExperienceGeneralScreen/{routeId}/{orderId}"
                .replace("{routeId}", interaction.routeID.toString())
                    .replace("{orderId}", interaction.orderID.toString())
            )
            }
        }
        "Pendent" -> {
            firstText = "Encara no has respost a la"
            secondText = "petició de transport de la comanda"
            chipText = "Pendent"
            chipColor = YellowRC
            button1Text = "Acceptar"
            button1Color = MaterialTheme.colorScheme.primary
            button1Icon = Icons.Filled.Check
            button1OnClick = { routeDetailDriverViewModel.modifyInteractionStatus(interaction, index,"Acceptada") }
            button2Text = "Declinar"
            button2Color = RedRC
            button2Icon = Icons.Filled.Cancel
            button2OnClick = { routeDetailDriverViewModel.modifyInteractionStatus(interaction, index,"Declinada") }
            button1IconTint = Color.White
            button2IconTint = Color.Black
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
            routeDetailDriverViewModel.showInteractionPopup(false)
            navHost.navigate("OrderDetailScreen/${interaction.orderID}")
        },
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.onTertiaryContainer)) {
        Row(modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Column(modifier = Modifier.weight(2f).padding(top = 16.dp, start = 8.dp, end = 8.dp),
                verticalArrangement = Arrangement.Center) {
                Text(text = "${interaction.date.split("-")[0]} a les ${interaction.date.split("-")[1]} ",
                    color = MaterialTheme.colorScheme.onBackground)
            }
            Column(modifier = Modifier.weight(0.8f)) {
                InteractionChip(text = chipText, containerColor = chipColor, chipColorText = chipTextColor)
            }
        }
        FlowRow(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
            ) {
            InteractionText(firstText, secondText, interaction.orderID)
        }
        // Buttons
        if (interaction.status == "Acceptada" || interaction.status == "Pendent" || interaction.status == "Entregada"){
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp, bottom = 8.dp, end = 4.dp),
            horizontalArrangement = Arrangement.SpaceAround) {
                InteractionButton(onClick = button1OnClick, text = button1Text , color = button1Color, icon = button1Icon, iconTint = button1IconTint )
                if (button2Text != ""){
                    InteractionButton(onClick = button2OnClick, text = button2Text , color = button2Color, icon = button2Icon, iconTint = button2IconTint )
                }
            }
        }

    }

}

@Composable
fun InteractionButton(onClick: () -> Unit, text: String, color: Color, icon: ImageVector?, iconTint: Color){
    ElevatedButton( shape = RoundedCornerShape(16.dp),
        onClick = onClick,
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = color
        )
    ) {
        if (icon != null) {
            Icon(
                modifier = Modifier.size(28.dp).padding(end = 4.dp),
                imageVector = icon,
                contentDescription = "$text icon",
                tint = iconTint
            )
        }
        Text(text = text, color = Color.White)

    }
}
@Composable
fun InteractionChip(text: String, containerColor: Color, chipColorText: Color){
    ElevatedAssistChip(onClick = { /*TODO*/ },
        label = {
            Text(text, color = chipColorText) },
        shape = RoundedCornerShape(16.dp),
        colors = AssistChipDefaults.assistChipColors(
            containerColor = containerColor
        )
    )
}

@Composable
fun InteractionText(firstText: String, secondText: String, comandaID: Int){
   Text(text = buildAnnotatedString {
        append("$firstText ")
        withStyle(
            style = SpanStyle(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        ) {
            append("$secondText #$comandaID")
        }
    },
       color = MaterialTheme.colorScheme.onBackground)
}