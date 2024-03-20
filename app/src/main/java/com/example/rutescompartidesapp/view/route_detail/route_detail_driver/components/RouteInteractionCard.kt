package com.example.rutescompartidesapp.view.route_detail.route_detail_driver.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
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
import com.example.rutescompartidesapp.ui.theme.BlueRC
import com.example.rutescompartidesapp.ui.theme.MateBlackRC
import com.example.rutescompartidesapp.ui.theme.RedRC
import com.example.rutescompartidesapp.ui.theme.YellowRC
import com.example.rutescompartidesapp.view.route_detail.route_detail_driver.DetailUtils.RouteInteraction
import com.example.rutescompartidesapp.view.route_detail.route_detail_driver.RouteDetailDriverViewModel

@Composable
fun RouteInteractionCard(interaction: RouteInteraction, index: Int, routeDetailDriverViewModel: RouteDetailDriverViewModel){
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
    var button1OnClick: () -> Unit = { /*TODO*/ }
    var button2OnClick: () -> Unit = { /*TODO*/ }

    when (interaction.status) {
        "Acceptada" -> {
            firstText = "Has acceptat"
            secondText = "dur la comanda"
            chipColor = BlueRC
            chipText = "Acceptada"
            button1Text = "Confirmar entrega"
            button1Color = MateBlackRC
            button1Icon = Icons.Filled.Check
            button1IconTint = Color.White
            button1OnClick = { routeDetailDriverViewModel.showCompletePopup(true) }
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
    }

    ElevatedCard(modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.background)) {
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
        // Buttons
        if (interaction.status == "Acceptada" || interaction.status == "Pendent" ){
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, bottom = 8.dp, end = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround) {
                InteractionButton(onClick = button1OnClick, text = button1Text , color = button1Color, icon = button1Icon, iconTint = button1IconTint )
                InteractionButton(onClick = button2OnClick, text = button2Text , color = button2Color, icon = button2Icon, iconTint = button2IconTint )
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
                modifier = Modifier.size(28.dp),
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