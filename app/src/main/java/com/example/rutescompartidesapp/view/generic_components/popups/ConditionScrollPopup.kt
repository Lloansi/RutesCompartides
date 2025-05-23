package com.example.rutescompartidesapp.view.generic_components.popups

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp

/**
 * List of transport conditions information.
 */
val condicionsTransportInfo = listOf(
    "En seleccionar el tipus de transport, se't mostraran només els vehicles i les comandes (necessitats de transport) que compleixin aquestes característiques:",
    "ISOTERM:Vehicle amb parets aïllants, manté la temperatura.",
    "REFRIGERAT:Vehicle amb font de fred, temperatures de 4 a 12º (cal confirmar temperatura amb qui ofereixi el transport).",
    "CONGELAT:Vehicle amb font de fred, temperatures inferiors a 0º (cal confirmar temperatura amb qui ofereixi el transport).",
    "SENSE HUMITAT:No es transporten verdures o altres productes que produeixin humitat (vehicle compatible per al transport de pa, llavors, pasta, etc.)."
)
/**
 * Composable function to display a scrollable popup with transport conditions.
 */
@Composable
fun ConditionScrollPopup(){
    LazyColumn(modifier = Modifier.fillMaxHeight(0.4f)){
        items(condicionsTransportInfo.size){ condicio ->
            if (condicio != 0){
                Text(text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onBackground
                        ) ){
                        append(condicionsTransportInfo[condicio].split(":")[0],
                            )
                    }

                },
                    color = MaterialTheme.colorScheme.onBackground

                )
            } else {
                Text(text = condicionsTransportInfo[condicio].split(":")[0],
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Text(condicionsTransportInfo[condicio].split(":")[1],
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(Modifier.padding(4.dp))
        }
    }
}