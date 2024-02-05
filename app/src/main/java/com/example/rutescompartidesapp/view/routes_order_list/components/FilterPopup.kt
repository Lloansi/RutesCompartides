package com.example.rutescompartidesapp.view.routes_order_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ChipColors
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SelectableChipColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.rutescompartidesapp.data.domain.ListQuery
import com.example.rutescompartidesapp.ui.theme.BlueRC
import com.example.rutescompartidesapp.ui.theme.GrayRC
import com.example.rutescompartidesapp.ui.theme.MateBlackRC
import com.example.rutescompartidesapp.view.routes_order_list.FilterPopupViewModel
import com.example.rutescompartidesapp.view.routes_order_list.RoutesOrderListViewModel
import kotlin.reflect.KFunction1

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Preview(showBackground = true)
@Composable
fun FilterPopup(){
    val filterPopupViewModel: FilterPopupViewModel = hiltViewModel()
    val isPopupShowing by filterPopupViewModel.popupIsShowing.collectAsStateWithLifecycle()
    val puntSortidaText by filterPopupViewModel.puntSortidaText.collectAsStateWithLifecycle()
    val puntArribadaText by filterPopupViewModel.puntArribadaText.collectAsStateWithLifecycle()
    val dataSortidaText by filterPopupViewModel.dataSortidaText.collectAsStateWithLifecycle()
    val horaSortidaText by filterPopupViewModel.horaSortidaText.collectAsStateWithLifecycle()
    val areExtraFiltersShowing by filterPopupViewModel.extraFiltersAreShowing.collectAsStateWithLifecycle()
    val isIsoterm by filterPopupViewModel.isIsoterm.collectAsStateWithLifecycle()
    val isRefrigerat by filterPopupViewModel.isRefrigerat.collectAsStateWithLifecycle()
    val isCongelat by filterPopupViewModel.isCongelat.collectAsStateWithLifecycle()
    val isSenseHumitat by filterPopupViewModel.isSenseHumitat.collectAsStateWithLifecycle()
    val isCondicionsPopupShowing by filterPopupViewModel.isCondicionsPopupShowing.collectAsStateWithLifecycle()
    val etiquetesText by filterPopupViewModel.etiquetesText.collectAsStateWithLifecycle()
    val etiquetesList by filterPopupViewModel.etiquetesList.collectAsStateWithLifecycle()
    val etiquetesError by filterPopupViewModel.etiquetesError.collectAsStateWithLifecycle()

    if (isPopupShowing){
        Popup(alignment = Alignment.Center,
            offset = IntOffset(0, 1200),
            onDismissRequest = { filterPopupViewModel.onPopupShow(false) },
            properties = PopupProperties(
                focusable = true,
                dismissOnBackPress = true,
                dismissOnClickOutside=  true)
        ){
            ElevatedCard(modifier = Modifier
                .fillMaxWidth(0.9f),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ){
                Row(modifier = Modifier.fillMaxWidth()) {
                    Column (
                        Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.padding(4.dp))
                        Text(text = "Entra el punt de sortida o d'arribada")
                        Spacer(modifier = Modifier.padding(8.dp))
                        OutlinedFilterTextField(
                            value = puntSortidaText,
                            onValueChange = filterPopupViewModel::onPuntSortidaChange,
                            placeholder = "Punt de sortida",
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Filled.Home,
                                    contentDescription = "Punt de sortida",
                                    tint = Color.Gray
                                )
                            }
                        )
                        OutlinedFilterTextField(value = puntArribadaText,
                            onValueChange = filterPopupViewModel::onPuntArribadaChange,
                            placeholder = "Punt de aribada",
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Filled.Map,
                                    contentDescription = "Punt de aribada",
                                    tint = Color.Gray
                                )
                            }
                        )
                        IconButton(onClick = { filterPopupViewModel.onExtraFiltersToggle() }) {
                            if (areExtraFiltersShowing) {
                                Icon(
                                    imageVector = Icons.Filled.KeyboardArrowUp,
                                    contentDescription = "Mostrar menys filtres"
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.Filled.KeyboardArrowDown,
                                    contentDescription = "Mostrar més filtres"
                                )
                            }
                        }
                        if (areExtraFiltersShowing){
                            OutlinedFilterTextField(
                                value = dataSortidaText,
                                onValueChange = filterPopupViewModel::onDataSortidaChange,
                                placeholder = "Data de sortida",
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Filled.CalendarMonth,
                                        contentDescription = "Data de sortida",
                                        tint = Color.Gray
                                    )
                                }
                            )
                            OutlinedFilterTextField(value = horaSortidaText,
                                onValueChange = filterPopupViewModel::onHoraArribadaChange,
                                placeholder = "Hora de aribada",
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Filled.AccessTime,
                                        contentDescription = "Hora de sortida",
                                        tint = Color.Gray
                                    )
                                }
                            )
                            Row(modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically){
                                Column(modifier = Modifier.weight(1f),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally) {
                                    FloatingActionButton(onClick = { filterPopupViewModel.onCondicionsPopupShow(true) },
                                        containerColor = MateBlackRC) {
                                        Icon(imageVector = Icons.Filled.QuestionMark,
                                            contentDescription = "Question Icon",
                                            tint = Color.White)
                                    }
                                    if (isCondicionsPopupShowing){
                                        Popup(onDismissRequest = { filterPopupViewModel.onCondicionsPopupShow(false) },
                                            offset = IntOffset(150, -700),
                                            properties = PopupProperties(
                                                focusable = true,
                                                dismissOnBackPress = true,
                                                dismissOnClickOutside=  true)){
                                                ElevatedCard(modifier = Modifier
                                                    .fillMaxWidth(0.65f),
                                                    colors = CardDefaults.cardColors(containerColor = Color.White)) {
                                                    Row (Modifier.fillMaxWidth()) {
                                                        Column(Modifier.fillMaxWidth(),
                                                            verticalArrangement = Arrangement.Center,
                                                            horizontalAlignment = Alignment.CenterHorizontally) {
                                                            Row(modifier = Modifier
                                                                .fillMaxWidth()
                                                                .height(40.dp)
                                                                .background(Color.LightGray),
                                                                verticalAlignment = Alignment.CenterVertically,
                                                                horizontalArrangement = Arrangement.Center
                                                                ){
                                                                Text("Condicions de transport")
                                                            }
                                                            Row (
                                                                Modifier
                                                                    .height(10.dp)
                                                                    .background(Color.LightGray)) {
                                                                Divider(color = MateBlackRC, thickness = 4.dp)
                                                            }
                                                            Row(Modifier.padding(12.dp)) {
                                                                ConditionScroll()
                                                            }

                                                        }
                                                    }
                                                }
                                            }
                                    }
                                }
                                // Chips & Info Button
                                Column(modifier = Modifier.weight(3f),
                                    verticalArrangement = Arrangement.Center) {
                                    Row(modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceAround) {
                                        ElevatedFilterChip(
                                            selected = isIsoterm ,
                                            onClick = { filterPopupViewModel.onCheckChip("Isoterm") },
                                            label = {  if (isIsoterm) {
                                                Text("Isoterm",
                                                    color = Color.White)
                                            } else {
                                                Text("Isoterm")
                                            }
                                            },
                                            leadingIcon = {
                                                if (isIsoterm) {
                                                    Icon(imageVector = Icons.Filled.Check,
                                                        contentDescription = "Check Icon",
                                                        tint = Color.White)
                                                }
                                            },
                                            colors = FilterChipDefaults.elevatedFilterChipColors(
                                                selectedContainerColor = MaterialTheme.colorScheme.primary,
                                                containerColor = GrayRC
                                            ))
                                        ElevatedFilterChip(
                                            selected = isRefrigerat ,
                                            onClick = { filterPopupViewModel.onCheckChip("Refrigerat") },
                                            label = {
                                                if (isRefrigerat) {
                                                    Text("Refrigerat",
                                                        color = Color.White)
                                                } else {
                                                    Text("Refrigerat")
                                                }
                                            },
                                            leadingIcon = {
                                                if (isRefrigerat) {
                                                    Icon(imageVector = Icons.Filled.Check,
                                                        contentDescription = "Check Icon",
                                                        tint = Color.White )
                                                }
                                            },
                                            colors = FilterChipDefaults.elevatedFilterChipColors(
                                                selectedContainerColor = MaterialTheme.colorScheme.primary,
                                                containerColor = GrayRC
                                            ))
                                    }
                                    Row(modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceEvenly) {
                                        ElevatedFilterChip(
                                            selected = isCongelat ,
                                            onClick = { filterPopupViewModel.onCheckChip("Congelat") },
                                            label = {  if (isCongelat) {
                                                Text("Congelat",
                                                    color = Color.White)
                                            } else {
                                                Text("Congelat")
                                            }
                                            },
                                            leadingIcon = {
                                                if (isCongelat) {
                                                    Icon(imageVector = Icons.Filled.Check,
                                                        contentDescription = "Check Icon",
                                                        tint = Color.White)
                                                }
                                            },
                                            colors = FilterChipDefaults.elevatedFilterChipColors(
                                                selectedContainerColor = MaterialTheme.colorScheme.primary,
                                                containerColor = GrayRC
                                            ))
                                        ElevatedFilterChip(
                                            selected = isSenseHumitat ,
                                            onClick = { filterPopupViewModel.onCheckChip("SenseHumitat") },
                                            label = { if (isSenseHumitat) {
                                                Text("Sense Humitat",
                                                    color = Color.White)
                                            } else {
                                                Text("Sense Humitat")
                                            } },
                                            leadingIcon = {
                                                if (isSenseHumitat) {
                                                    Icon(imageVector = Icons.Filled.Check,
                                                        contentDescription = "Check Icon",
                                                        tint = Color.White
                                                    )
                                                }
                                            },
                                            colors = FilterChipDefaults.elevatedFilterChipColors(
                                                selectedContainerColor = MaterialTheme.colorScheme.primary,
                                                containerColor = GrayRC
                                            ))
                                    }
                                }
                            }
                            Spacer(Modifier.padding(4.dp))
                            OutlinedTextField(
                                modifier = Modifier.fillMaxWidth(0.9f),
                                value = etiquetesText,
                                onValueChange = filterPopupViewModel::onEtiquetesChange,
                                placeholder = {
                                    Text(text = "Etiquetes", color = Color.Gray)
                                              },
                                isError = etiquetesError,
                                trailingIcon = {
                                    if (etiquetesError){
                                        Icon(imageVector = Icons.Filled.Cancel,
                                            contentDescription = "Error Icon",
                                            tint = MaterialTheme.colorScheme.primary)
                                    }
                                },
                                supportingText = {
                                    if (etiquetesError){
                                        Text(text = "Aquesta etiqueta ja existeix o no pot estar buida",
                                            color = MaterialTheme.colorScheme.primary)
                                    }
                                },
                                shape = RoundedCornerShape(16.dp),
                                colors = TextFieldDefaults.colors(
                                    focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                                    unfocusedIndicatorColor = Color.Gray,
                                    disabledIndicatorColor = Color.Transparent,
                                    focusedContainerColor = Color.White,
                                    unfocusedContainerColor = Color.White,
                                    errorTextColor = MaterialTheme.colorScheme.primary,
                                    errorContainerColor = GrayRC
                                ),
                                keyboardOptions = KeyboardOptions(
                                    imeAction = ImeAction.Send,
                                ),
                                keyboardActions = KeyboardActions(
                                    onSend = {
                                        if (etiquetesText.isEmpty() || etiquetesText in etiquetesList){
                                            filterPopupViewModel.onEtiquetesErrorChange(true)
                                            return@KeyboardActions
                                        }
                                        filterPopupViewModel.onEtiquetesErrorChange(false)
                                        filterPopupViewModel.onEtiquetesAddToListChange(etiquetesText)
                                }),
                                singleLine = true,
                            )
                            FlowRow(modifier = Modifier.fillMaxWidth(0.9f),
                                horizontalArrangement = Arrangement.Start,){
                                etiquetesList.forEach{ etiqueta ->
                                    InputChip(selected = true,
                                        onClick = { filterPopupViewModel.onEtiquetaDelete(etiqueta) },
                                        label = { Text(etiqueta,
                                            color = Color.White) },
                                        colors = FilterChipDefaults.elevatedFilterChipColors(
                                            selectedContainerColor = BlueRC
                                        ),
                                        avatar = {
                                            Icon(imageVector = Icons.Filled.Close,
                                                contentDescription = "Close Icon",
                                                tint = Color.LightGray,
                                                modifier = Modifier.clickable {
                                                    filterPopupViewModel.onEtiquetaDelete(etiqueta)
                                                })

                                        })
                                    Spacer(Modifier.padding(4.dp))
                                }
                            }
                        }

                        Spacer(modifier = Modifier.padding(8.dp))

                        // Search Button
                        ElevatedButton(onClick = {
                            filterPopupViewModel.onFilterSearch(
                                ListQuery(
                                    puntSortida = puntSortidaText,
                                    puntArribada = puntArribadaText,
                                    dataSortida = dataSortidaText,
                                    horaSortida = horaSortidaText,
                                    etiquetes = etiquetesList,
                                    isIsoterm = isIsoterm,
                                    isRefrigerat = isRefrigerat,
                                    isCongelat = isCongelat,
                                    isSenseHumitat = isSenseHumitat
                                )
                            )
                        },
                            colors = ButtonDefaults.elevatedButtonColors(
                                contentColor = MaterialTheme.colorScheme.secondary,
                                containerColor = MaterialTheme.colorScheme.primary
                            )

                            ) {
                            Text(text = "Cerca")
                        }
                        Spacer(modifier = Modifier.padding(8.dp))
                    }
                }
            }
        }
    }
}

val condicionsTransportInfo = listOf(
    "En seleccionar el tipus de transport, se't mostraran només els vehicles i les comandes (necessitats de transport) que compleixin aquestes característiques:",
    "ISOTERM:Vehicle amb parets aïllants, manté la temperatura.",
    "REFRIGERAT:Vehicle amb font de fred, temperatures de 4 a 12º (cal confirmar temperatura amb qui ofereixi el transport).",
    "CONGELAT:Vehicle amb font de fred, temperatures inferiors a 0º (cal confirmar temperatura amb qui ofereixi el transport).",
    "SENSE HUMITAT:No es transporten verdures o altres productes que produeixin humitat (vehicle compatible per al transport de pa, llavors, pasta, etc.)."
)
@Composable
fun ConditionScroll(){
    LazyColumn(modifier = Modifier.fillMaxHeight(0.4f)){
        items(condicionsTransportInfo.size){ condicio ->
            Text(condicionsTransportInfo[condicio].split(":")[0])
            Text(condicionsTransportInfo[condicio].split(":")[1])
            Spacer(Modifier.padding(4.dp))
        }
    }
}

@Composable
fun OutlinedFilterTextField(value: String, onValueChange: (String) -> Unit, placeholder: String, leadingIcon: @Composable () -> Unit){
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(0.9f),
        value = value,
        onValueChange = onValueChange,
        leadingIcon = {
            leadingIcon()
        },
        placeholder = {
            Text(text = placeholder, color = Color.Gray)
        },
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
            unfocusedIndicatorColor = Color.Gray,
            disabledIndicatorColor = Color.Transparent,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next),
        singleLine = true,
    )
    Spacer(modifier = Modifier.padding(8.dp))
}