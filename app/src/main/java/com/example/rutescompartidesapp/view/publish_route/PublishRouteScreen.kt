package com.example.rutescompartidesapp.view.publish_route

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.House
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.InputChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.rutescompartidesapp.R
import com.example.rutescompartidesapp.ui.theme.BlueRC
import com.example.rutescompartidesapp.ui.theme.GrayRC
import com.example.rutescompartidesapp.ui.theme.MateBlackRC
import com.example.rutescompartidesapp.ui.theme.OrangeRC
import com.example.rutescompartidesapp.view.generic_components.BasicTextField
import com.example.rutescompartidesapp.view.generic_components.DateTimePickerTextField
import com.example.rutescompartidesapp.view.generic_components.IconTextField
import com.example.rutescompartidesapp.view.generic_components.MultilineTextField
import com.example.rutescompartidesapp.view.generic_components.PublishBackButton
import com.example.rutescompartidesapp.view.generic_components.PublishButton
import com.example.rutescompartidesapp.view.generic_components.PublishNextButton
import com.example.rutescompartidesapp.view.generic_components.StepTextField
import com.example.rutescompartidesapp.view.generic_components.TimePickerDialog
import com.example.rutescompartidesapp.view.generic_components.popups.ConditionScrollPopup
import com.example.rutescompartidesapp.view.generic_components.popups.PopupScrolleable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PublishRouteScreen(navHost: NavHostController){
    val publishRouteViewModel = PublishRouteViewModel()

    val currentStep by publishRouteViewModel.step.collectAsStateWithLifecycle()
    val routeName by publishRouteViewModel.internalRouteName.collectAsStateWithLifecycle()
    val originName by publishRouteViewModel.originName.collectAsStateWithLifecycle()
    val stepLocationsNumber by publishRouteViewModel.stepLocationsNumber.collectAsStateWithLifecycle()
    val stepName1 by publishRouteViewModel.stepName1.collectAsStateWithLifecycle()
    val stepName2 by publishRouteViewModel.stepName2.collectAsStateWithLifecycle()
    val stepName3 by publishRouteViewModel.stepName3.collectAsStateWithLifecycle()
    val stepName4 by publishRouteViewModel.stepName4.collectAsStateWithLifecycle()
    val stepName5 by publishRouteViewModel.stepName5.collectAsStateWithLifecycle()
    val stepName6 by publishRouteViewModel.stepName6.collectAsStateWithLifecycle()
    val stepNameList = listOf(stepName1, stepName2, stepName3, stepName4, stepName5, stepName6)
    val destinationName by publishRouteViewModel.destinationName.collectAsStateWithLifecycle()
    val isDropdownExpanded by publishRouteViewModel.isDropdownExpanded.collectAsStateWithLifecycle()
    val routeFrequency by publishRouteViewModel.routeFrequency.collectAsStateWithLifecycle()
    val isFirstFormCompleted by publishRouteViewModel.isFirstFormCompleted.collectAsStateWithLifecycle()
    val isFreqPopupShowing by publishRouteViewModel.isFreqPopupShowing.collectAsStateWithLifecycle()
    val isCostPopupShowing by publishRouteViewModel.isCostPopupShowing.collectAsStateWithLifecycle()

    val datePickerState = rememberDatePickerState()
    val timePickerState = rememberTimePickerState()
    val dateDepart by publishRouteViewModel.dateDepart.collectAsStateWithLifecycle()
    val dateArrival by publishRouteViewModel.dateArrival.collectAsStateWithLifecycle()
    val timeDepart by publishRouteViewModel.timeDepartText.collectAsStateWithLifecycle()
    val timeArrival by publishRouteViewModel.timeArrivalText.collectAsStateWithLifecycle()

    val isDatePickerShowing by publishRouteViewModel.datePickerDialogIsShowing.collectAsStateWithLifecycle()
    val isTimePickerShowing by publishRouteViewModel.timePickerDialogIsShowing.collectAsStateWithLifecycle()

    // Screen 2 variables
    val maxDetourKm by publishRouteViewModel.maxDetourKm.collectAsStateWithLifecycle()
    val availableSeats by publishRouteViewModel.availableSeats.collectAsStateWithLifecycle()
    val availableSpace by publishRouteViewModel.availableSpace.collectAsStateWithLifecycle()
    val costKm by publishRouteViewModel.costKM.collectAsStateWithLifecycle()
    val vehicle by publishRouteViewModel.vehicle.collectAsStateWithLifecycle()

    // Screen 3 variables
    val isCondicionsPopupShowing by publishRouteViewModel.isCondicionsPopupShowing.collectAsStateWithLifecycle()
    val isIsoterm by publishRouteViewModel.isIsoterm.collectAsStateWithLifecycle()
    val isRefrigerat by publishRouteViewModel.isRefrigerat.collectAsStateWithLifecycle()
    val isCongelat by publishRouteViewModel.isCongelat.collectAsStateWithLifecycle()
    val isSenseHumitat by publishRouteViewModel.isSenseHumitat.collectAsStateWithLifecycle()
    val tagsText by publishRouteViewModel.tagsText.collectAsStateWithLifecycle()
    val tagsList by publishRouteViewModel.tagsList.collectAsStateWithLifecycle()
    val tagsError by publishRouteViewModel.tagsError.collectAsStateWithLifecycle()
    val comment by publishRouteViewModel.comment.collectAsStateWithLifecycle()

    Scaffold( modifier = Modifier
        .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Publicar Ruta $currentStep/3") },
                navigationIcon = {
                    IconButton(onClick = {
                        if (currentStep == 1) navHost.popBackStack()
                        else publishRouteViewModel.previousStep() }
                    ) {
                        Icon(imageVector = Icons.Filled.ArrowBackIosNew, contentDescription = "Go Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground
                ),
            )
        }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = paddingValues.calculateTopPadding() + 8.dp,
                    bottom = paddingValues.calculateBottomPadding() + 8.dp,
                    start = 8.dp,
                    end = 8.dp
                ).verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (currentStep) {
                1 -> PublishRouteContent1(
                    routeName,
                    publishRouteViewModel,
                    originName,
                    stepLocationsNumber,
                    stepNameList,
                    destinationName,
                    isDatePickerShowing,
                    isTimePickerShowing,
                    datePickerState,
                    timePickerState,
                    dateDepart,
                    timeDepart,
                    dateArrival,
                    timeArrival,
                    isFirstFormCompleted
                )
                2 -> {
                    PublishRouteContent2(
                        isDropdownExpanded,
                        isFreqPopupShowing,
                        routeFrequency,
                        maxDetourKm,
                        publishRouteViewModel,
                        availableSeats,
                        availableSpace,
                        isCostPopupShowing,
                        costKm,
                        vehicle
                    )
                }
                3 -> {
                    PublishRouteContent3(
                        publishRouteViewModel,
                        isCondicionsPopupShowing,
                        isIsoterm,
                        isRefrigerat,
                        isCongelat,
                        isSenseHumitat,
                        tagsText,
                        tagsError,
                        tagsList,
                        comment
                    )
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
private fun PublishRouteContent3(
    publishRouteViewModel: PublishRouteViewModel,
    isCondicionsPopupShowing: Boolean,
    isIsoterm: Boolean,
    isRefrigerat: Boolean,
    isCongelat: Boolean,
    isSenseHumitat: Boolean,
    tagsText: String,
    tagsError: Boolean,
    tagsList: MutableList<String>,
    comment: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Conditions Button
            FloatingActionButton(
                onClick = { publishRouteViewModel.onCondicionsPopupShow(true) },
                containerColor = MateBlackRC
            ) {
                Icon(
                    imageVector = Icons.Filled.QuestionMark,
                    contentDescription = "Question Icon",
                    tint = Color.White
                )
            }
            // Conditions Popup
            if (isCondicionsPopupShowing) {
                PopupScrolleable(offset = IntOffset((LocalConfiguration.current.screenWidthDp / 2), -100),
                    title = "Condicions de transport" , onDismisRequest = { publishRouteViewModel.onCondicionsPopupShow(
                        false ) },
                    content = { ConditionScrollPopup() })
            }
        }
        // Chips & Info Button
        Column(
            modifier = Modifier.weight(3f),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                // Isoterm Chip
                ElevatedFilterChip(
                    selected = isIsoterm,
                    onClick = { publishRouteViewModel.onCheckChip("Isoterm") },
                    label = {
                        if (isIsoterm) {
                            Text(
                                "Isoterm",
                                color = Color.White,
                                style = MaterialTheme.typography.bodyLarge,
                            )
                        } else {
                            Text("Isoterm",
                                style = MaterialTheme.typography.bodyLarge,)
                        }
                    },
                    leadingIcon = {
                        if (isIsoterm) {
                            Icon(
                                imageVector = Icons.Filled.Check,
                                contentDescription = "Check Icon",
                                tint = Color.White
                            )
                        }
                    },
                    colors = FilterChipDefaults.elevatedFilterChipColors(
                        selectedContainerColor = MaterialTheme.colorScheme.primary,
                        containerColor = GrayRC
                    )
                )
                // Refrigerat Chip
                ElevatedFilterChip(
                    selected = isRefrigerat,
                    onClick = { publishRouteViewModel.onCheckChip("Refrigerat") },
                    label = {
                        if (isRefrigerat) {
                            Text(
                                "Refrigerat",
                                color = Color.White,
                                style = MaterialTheme.typography.bodyLarge,
                            )
                        } else {
                            Text("Refrigerat",
                                style = MaterialTheme.typography.bodyLarge,)
                        }
                    },
                    leadingIcon = {
                        if (isRefrigerat) {
                            Icon(
                                imageVector = Icons.Filled.Check,
                                contentDescription = "Check Icon",
                                tint = Color.White
                            )
                        }
                    },
                    colors = FilterChipDefaults.elevatedFilterChipColors(
                        selectedContainerColor = MaterialTheme.colorScheme.primary,
                        containerColor = GrayRC
                    )
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // Congelat Chip
                ElevatedFilterChip(
                    selected = isCongelat,
                    onClick = { publishRouteViewModel.onCheckChip("Congelat") },
                    label = {
                        if (isCongelat) {
                            Text(
                                "Congelat",
                                color = Color.White,
                                style = MaterialTheme.typography.bodyLarge,
                            )
                        } else {
                            Text("Congelat",
                                style = MaterialTheme.typography.bodyLarge,)
                        }
                    },
                    leadingIcon = {
                        if (isCongelat) {
                            Icon(
                                imageVector = Icons.Filled.Check,
                                contentDescription = "Check Icon",
                                tint = Color.White
                            )
                        }
                    },
                    colors = FilterChipDefaults.elevatedFilterChipColors(
                        selectedContainerColor = MaterialTheme.colorScheme.primary,
                        containerColor = GrayRC
                    )
                )
                // Sense Humitat Chip
                ElevatedFilterChip(
                    selected = isSenseHumitat,
                    onClick = { publishRouteViewModel.onCheckChip("SenseHumitat") },
                    label = {
                        if (isSenseHumitat) {
                            Text(
                                "Sense Humitat",
                                color = Color.White,
                                style = MaterialTheme.typography.bodyLarge,
                            )
                        } else {
                            Text("Sense Humitat",
                                style = MaterialTheme.typography.bodyLarge,
                            )
                        }
                    },
                    leadingIcon = {
                        if (isSenseHumitat) {
                            Icon(
                                imageVector = Icons.Filled.Check,
                                contentDescription = "Check Icon",
                                tint = Color.White
                            )
                        }
                    },
                    colors = FilterChipDefaults.elevatedFilterChipColors(
                        selectedContainerColor = MaterialTheme.colorScheme.primary,
                        containerColor = GrayRC
                    )
                )
            }
        }
    }
    // Tag TextField
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .padding(top = 8.dp),
        value = tagsText,
        onValueChange = publishRouteViewModel::onTagsChange,
        placeholder = {
            Text(text = "Etiquetes (diaria, setmanal...)", color = Color.Gray)
        },
        isError = tagsError,
        trailingIcon = {
            if (tagsError) {
                Icon(
                    imageVector = Icons.Filled.Cancel,
                    contentDescription = "Error Icon",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
        supportingText = {
            if (tagsError) {
                Text(
                    text = "Aquesta etiqueta ja existeix o no pot estar buida",
                    color = MaterialTheme.colorScheme.primary
                )
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
                if (tagsText.isEmpty() || tagsText in tagsList) {
                    publishRouteViewModel.onTagsErrorChange(true)
                    return@KeyboardActions
                }
                publishRouteViewModel.onTagsErrorChange(false)
                publishRouteViewModel.onTagsAddToListChange(tagsText)
            }),
        singleLine = true,
    )
    // Tags
    FlowRow(
        modifier = Modifier.fillMaxWidth(0.9f),
        horizontalArrangement = Arrangement.Start,
    ) {
        tagsList.forEach { etiqueta ->
            InputChip(selected = true,
                onClick = { publishRouteViewModel.onTagDelete(etiqueta) },
                label = {
                    Text(
                        etiqueta,
                        color = Color.White
                    )
                },
                colors = FilterChipDefaults.elevatedFilterChipColors(
                    selectedContainerColor = BlueRC
                ),
                avatar = {
                    Icon(imageVector = Icons.Filled.Close,
                        contentDescription = "Close Icon",
                        tint = Color.LightGray,
                        modifier = Modifier.clickable {
                            publishRouteViewModel.onTagDelete(etiqueta)
                        })
                })
            Spacer(Modifier.padding(4.dp))
        }
    }
    // Comments
    Row(
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .padding(top = 4.dp)
    ) {
        Text(text = "Comentaris", color = MaterialTheme.colorScheme.onBackground)
    }
    MultilineTextField(value = comment, onValueChange = publishRouteViewModel::setComment,
        placeholder = "Condicions especials quant a la recollida, entrega, transport (ex: faré una parada de 6 hores a la meva ruta abans d’arribar al destí final; necessito confirmació d’horari d’entrega als comentaris; acepto productes com forma de pagament...)" )
    // Buttons
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        // Back button
        PublishBackButton(publishRouteViewModel::previousStep)
        // Publish route button
        PublishButton( onClick = {/*TODO*/}, text = "Publicar ruta")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PublishRouteContent2(
    isDropdownExpanded: Boolean,
    isFreqPopupShowing: Boolean,
    routeFrequency: String,
    maxDetourKm: Float,
    publishRouteViewModel: PublishRouteViewModel,
    availableSeats: Int,
    availableSpace: String,
    isCostPopupShowing: Boolean,
    costKm: Float,
    vehicle: String
) {
    // Frequency
    Row(
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .padding(bottom = 2.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = { publishRouteViewModel.onFreqPopupShow(true) },
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = MateBlackRC
            )
        ) {
            Icon(imageVector = Icons.Filled.QuestionMark, contentDescription = "Frequency Icon")
        }
        Text(
            modifier = Modifier.weight(2f),
            text = "Freqüència de la ruta", color = MaterialTheme.colorScheme.onBackground
        )
    }
    if (isFreqPopupShowing){
        PopupScrolleable(offset = IntOffset((LocalConfiguration.current.screenWidthDp / 2), (LocalConfiguration.current.screenHeightDp / 2)),
            title = "Freqüència de la ruta" , onDismisRequest = { publishRouteViewModel.onFreqPopupShow(
                false ) },
            content = { Text(text = "Crearà totes  les rutes que pertoqui fins a la data final indicada, amb un màxim de 3  mesos. Recorda editar o eliminar les rutes si canvien a posteriori o  n'hi ha alguna que finalment no faràs. La freqüència mensual implica  aquell dia de la setmana i la mateixa setmana de cada mes (és a dir, el  1er dimecres de mes, o el 2on dijous de mes, etc).",
                color = MaterialTheme.colorScheme.onBackground) })
    }
    // Frequency dropdown
    Row(
        modifier = Modifier.fillMaxWidth(0.95f),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ExposedDropdownMenuBox(
            expanded = isDropdownExpanded,
            onExpandedChange = { publishRouteViewModel.toggleDropdown() }) {
            OutlinedTextField(
                value = routeFrequency,
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isDropdownExpanded)
                },

                placeholder = {
                    Text(text = "Selecciona la freqüencia")
                },
                shape = RoundedCornerShape(16.dp),
                colors = ExposedDropdownMenuDefaults.textFieldColors(
                    focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    unfocusedIndicatorColor = Color.Gray,
                    disabledContainerColor = MaterialTheme.colorScheme.background,
                    disabledIndicatorColor = Color.Gray,
                    focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                    unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = isDropdownExpanded,
                onDismissRequest = { publishRouteViewModel.toggleDropdown() }
            ) {
                DropdownMenuItem(
                    text = {
                        Text(text = "No es repeteix")
                    },
                    onClick = { publishRouteViewModel.setRouteFrequency("No es repeteix") }
                )
                DropdownMenuItem(
                    text = {
                        Text(text = "Diaria")
                    },
                    onClick = { publishRouteViewModel.setRouteFrequency("Diaria") }
                )
                DropdownMenuItem(
                    text = {
                        Text(text = "Setmanal")
                    },
                    onClick = { publishRouteViewModel.setRouteFrequency("Setmanal") }
                )
                DropdownMenuItem(
                    text = {
                        Text(text = "Bisetmanal")
                    },
                    onClick = { publishRouteViewModel.setRouteFrequency("Bisetmanal") }
                )
                DropdownMenuItem(
                    text = {
                        Text(text = "Mensual")
                    },
                    onClick = { publishRouteViewModel.setRouteFrequency("Mensual") }
                )
            }

        }
    }
    Spacer(modifier = Modifier.padding(8.dp))

    // Max Detour KM
    BasicTextField(value = maxDetourKm.toString(),
        onValueChange = publishRouteViewModel::setMaxDetourKm,
        placeholder = "Max desviament (km)"
    )
    // Available Seats
    BasicTextField(value = availableSeats.toString(),
        onValueChange = publishRouteViewModel::setSeats,
        placeholder = "Seients disponibles"
    )
    // Available Space
    MultilineTextField(value = availableSpace,
        onValueChange = publishRouteViewModel::setAvailableSpace , placeholder = "Espai disponible al vehicle")
    // KM Cost
    Row(modifier = Modifier
        .fillMaxWidth(0.95f)
        .padding(bottom = 2.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = { publishRouteViewModel.onCostPopupShow(true) },
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            )
        ) {
            Icon(imageVector = Icons.Filled.QuestionMark,
                contentDescription = "KMCost Icon",
                tint = MaterialTheme.colorScheme.onSecondaryContainer)
        }
        if (isCostPopupShowing){
            PopupScrolleable(offset = IntOffset((LocalConfiguration.current.screenWidthDp / 2), (LocalConfiguration.current.screenHeightDp)),
                title = "Cost per KM" , onDismisRequest = { publishRouteViewModel.onCostPopupShow(
                    false ) },
                content = { Text(text = "Per ajudar amb  una estimació del preu del transport compartit. En cas que prefereixis  oferir una alternativa (productes a canvi del transport, altres monedes,  etc.) pots utilitzar el camp \"Comentaris\" (a sota) per especificar la  teva proposta i l'etiqueta \"intercanvi\" o \"monedasocial\" al camp  \"Etiquetes\"",
                    color = MaterialTheme.colorScheme.onBackground) })
        }
        Text(
            modifier = Modifier.weight(2f),
            text = "Cost en € per cada KM recorregut",
            color = MaterialTheme.colorScheme.onBackground
        )
    }
    // KM Cost
    BasicTextField(
        value = costKm.toString(),
        onValueChange = publishRouteViewModel::setCostKM,
        placeholder = "0"
    )
    // Vehicle Model
    BasicTextField(
        value = vehicle,
        onValueChange = publishRouteViewModel::setVehicle,
        placeholder = "Model de vehicle / furgoneta"
    )
    // Buttons
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        // Back button
        PublishBackButton(publishRouteViewModel::previousStep)
        // Next Button
        PublishNextButton(onClickCheck = null, isCompleted = true, onClickNext = publishRouteViewModel::nextStep)
    }
}



@SuppressLint("StateFlowValueCalledInComposition")
@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun PublishRouteContent1(
    routeName: String,
    publishRouteViewModel: PublishRouteViewModel,
    originName: String,
    stepLocationsNumber: Int,
    stepNameList: List<String>,
    destinationName: String,
    isDatePickerShowing: Boolean,
    isTimePickerShowing: Boolean,
    datePickerState: DatePickerState,
    timePickerState: TimePickerState,
    dateDepart: String,
    timeDepart: String,
    dateArrival: String,
    timeArrival: String,
    isFirstFormCompleted: Boolean
) {
    BasicTextField(
        value = routeName,
        onValueChange = publishRouteViewModel::setInternalRouteName,
        placeholder = "Nom intern de la ruta"
    )
    IconTextField(value = originName,
        onValueChange = publishRouteViewModel::setOriginName,
        placeholder = "Punt de sortida",
        leadingIcon = {
            Icon(imageVector = Icons.Filled.House, contentDescription = "Origin Location Icon")
        })
    LazyColumn(
        modifier = Modifier.fillMaxWidth(0.95f).height(70.dp * stepLocationsNumber),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(stepLocationsNumber) { index ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                StepTextField(
                    modifier = Modifier
                        .weight(2f),
                    value = stepNameList[index],
                    onValueChange = {
                        println("Name : $it")
                        publishRouteViewModel.setStepLocationName(index, stepNameList[index]) }
                )
                IconButton(
                    onClick = { publishRouteViewModel.removeStepLocation() },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                    )
                ) {
                    Icon(imageVector = Icons.Filled.Delete,
                        contentDescription = "Delete Icon",
                        tint = MaterialTheme.colorScheme.onSecondaryContainer)
                }
            }
            Spacer(modifier = Modifier.size(8.dp))
        }
    }
    Row(modifier = Modifier.fillMaxWidth(0.95f)) {
        ElevatedButton(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(bottom = 8.dp),
            shape = RoundedCornerShape(16.dp),
            onClick = { publishRouteViewModel.addStepLocation() },
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = OrangeRC
            )
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Add Step Location",
                tint = Color.White
            )
            Text(
                text = "Punt intermedi", color = Color.White,
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
    IconTextField(value = destinationName,
        onValueChange = publishRouteViewModel::setDestinationName,
        placeholder = "Punt d'arribada",
        leadingIcon = {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = R.drawable.map_icon),
                contentDescription = "Destination Location Icon"
            )
        })

    DateTimePickerTextField(
        invocation = {
            publishRouteViewModel.onDatePickerDialogShow(
                isDepart = true,
                isShowing = true
            )
        },
        time = dateDepart,
        onValueChange = publishRouteViewModel::onDateDepartChange,
        placeholder = "Data de sortida",
        icon = Icons.Filled.CalendarMonth
    )

    DateTimePickerTextField(
        invocation = {
            publishRouteViewModel.onTimePickerDialogShow(
                isDepart = true,
                isShowing = true
            )
        },
        time = timeDepart,
        onValueChange = publishRouteViewModel::onTimeDepartChange,
        placeholder = "Hora d'arribada",
        icon = Icons.Filled.AccessTime
    )

    DateTimePickerTextField(
        invocation = {
            publishRouteViewModel.onDatePickerDialogShow(
                isDepart = false,
                isShowing = true
            )
        },
        time = dateArrival,
        onValueChange = publishRouteViewModel::onDateArrivalChange,
        placeholder = "Data d'arribada",
        icon = Icons.Filled.CalendarMonth
    )

    DateTimePickerTextField(
        invocation = {
            publishRouteViewModel.onTimePickerDialogShow(
                isDepart = false,
                isShowing = true
            )
        },
        time = timeArrival,
        onValueChange = publishRouteViewModel::onTimeArrivalChange,
        placeholder = "Hora d'arribada",
        icon = Icons.Filled.AccessTime
    )

    // Date Picker Dialog
    if (isDatePickerShowing) {
        DatePickerDialog(
            colors = DatePickerDefaults.colors(
                //containerColor = Color.White
            ),
            onDismissRequest = {
                publishRouteViewModel.onDatePickerDialogShow(
                    isDepart  = true,
                    isShowing = false
                )
            },
            confirmButton = {
                ElevatedButton(
                    onClick = {
                        datePickerState.selectedDateMillis?.let { dateInMillis ->
                            publishRouteViewModel.onDatePickerDialogConfirm(dateInMillis)
                        }
                    },
                    colors = ButtonDefaults.elevatedButtonColors(
                        contentColor = MaterialTheme.colorScheme.secondary,
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(text = "Confirmar")
                }
            },
            dismissButton = {
                ElevatedButton(onClick = {
                    publishRouteViewModel.onDatePickerDialogShow(
                        isDepart = true ,
                        isShowing = true
                    )
                }) {
                    Text(text = "Salir")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    // Time Picker Dialog
    if (isTimePickerShowing){
        TimePickerDialog(
            onCancel = { publishRouteViewModel.onTimePickerDialogShow(
                isDepart = true,
                isShowing =false)
                       },
            onConfirm = publishRouteViewModel::onTimePickerDialogConfirm )
    }

    PublishNextButton(publishRouteViewModel::checkAllValues, isFirstFormCompleted, publishRouteViewModel::nextStep)
}


