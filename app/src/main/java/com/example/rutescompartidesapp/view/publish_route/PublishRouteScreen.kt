package com.example.rutescompartidesapp.view.publish_route

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
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
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.rutescompartidesapp.R
import com.example.rutescompartidesapp.ui.theme.BlueRC
import com.example.rutescompartidesapp.ui.theme.GrayRC
import com.example.rutescompartidesapp.ui.theme.MateBlackRC
import com.example.rutescompartidesapp.ui.theme.OrangeRC
import com.example.rutescompartidesapp.view.publish_route.components.BasicTextField
import com.example.rutescompartidesapp.view.publish_route.components.IconTextField
import com.example.rutescompartidesapp.view.publish_route.components.StepTextField
import com.example.rutescompartidesapp.view.routes_order_list.components.ConditionScrollPopup

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
    val destinationName by publishRouteViewModel.destinationName.collectAsStateWithLifecycle()
    val isDropdownExpanded by publishRouteViewModel.isDropdownExpanded.collectAsStateWithLifecycle()
    val routeFrequency by publishRouteViewModel.routeFrequency.collectAsStateWithLifecycle()
    val isFirstFormCompleted by publishRouteViewModel.isFirstFormCompleted.collectAsStateWithLifecycle()

    /*
    val timeDepart by publishRouteViewModel.timeDepart.collectAsStateWithLifecycle()
    val timeArrival by publishRouteViewModel.timeArrival.collectAsStateWithLifecycle()
    val showDatePicker by publishRouteViewModel.datePickerDialogIsShowing.collectAsStateWithLifecycle()
    val datePickerState = rememberDatePickerState()
    val showTimePicker by publishRouteViewModel.timePickerDialogIsShowing.collectAsStateWithLifecycle()
     */
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
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (currentStep) {
                1 -> PublishRouteContent1(
                    routeName,
                    publishRouteViewModel,
                    originName,
                    stepLocationsNumber,
                    stepName1,
                    destinationName,
                    isDropdownExpanded,
                    routeFrequency,
                    isFirstFormCompleted
                )
                2 -> {
                    PublishRouteContent2(
                        maxDetourKm,
                        publishRouteViewModel,
                        availableSeats,
                        availableSpace,
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
                Popup(
                    onDismissRequest = {
                        publishRouteViewModel.onCondicionsPopupShow(
                            false
                        )
                    },
                    offset = IntOffset((LocalConfiguration.current.screenWidthDp / 2), -100),
                    properties = PopupProperties(
                        focusable = true,
                        dismissOnBackPress = true,
                        dismissOnClickOutside = true
                    )
                ) {
                    ElevatedCard(
                        modifier = Modifier
                            .fillMaxWidth(0.65f),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Row(Modifier.fillMaxWidth()) {
                            Column(
                                Modifier.fillMaxWidth(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(40.dp)
                                        .background(Color.LightGray),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        "Condicions de transport",
                                        style = MaterialTheme.typography.titleLarge,
                                    )
                                }
                                Row(
                                    Modifier
                                        .height(10.dp)
                                        .background(Color.LightGray)
                                ) {
                                    Divider(color = MateBlackRC, thickness = 4.dp)
                                }
                                Row(Modifier.padding(12.dp)) {
                                    ConditionScrollPopup()
                                }

                            }
                        }
                    }
                }
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
                                color = Color.White
                            )
                        } else {
                            Text("Isoterm")
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
                                color = Color.White
                            )
                        } else {
                            Text("Refrigerat")
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
                                color = Color.White
                            )
                        } else {
                            Text("Congelat")
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
                                color = Color.White
                            )
                        } else {
                            Text("Sense Humitat")
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
            Text(text = "tags", color = Color.Gray)
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
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .height(LocalConfiguration.current.screenWidthDp.dp / 2)
            .padding(top = 4.dp),
        value = comment,
        onValueChange = publishRouteViewModel::setComment,
        placeholder = {
            Text(
                text = "Condicions especials quant a la recollida, entrega, transport (ex: faré una parada de 6 hores a la meva ruta abans d’arribar al destí final; necessito confirmació d’horari d’entrega als comentaris; acepto productes com forma de pagament...)",
                color = Color.Gray
            )
        },
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
            unfocusedIndicatorColor = Color.Gray,
            disabledContainerColor = MaterialTheme.colorScheme.background,
            disabledIndicatorColor = Color.Gray,
            focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next
        ),
        singleLine = false,
        maxLines = 7,
    )
    // Buttons
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        // Back button
        ElevatedButton(
            shape = RoundedCornerShape(16.dp),
            onClick = {
                publishRouteViewModel.previousStep()
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
        // Next Button
        ElevatedButton(
            shape = RoundedCornerShape(16.dp),
            onClick = {
                // TODO POST ROUTE
            },
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = OrangeRC
            )
        ) {
            Text(
                text = "Publicar ruta", color = Color.White,
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}

@Composable
private fun PublishRouteContent2(
    maxDetourKm: Float,
    publishRouteViewModel: PublishRouteViewModel,
    availableSeats: Int,
    availableSpace: String,
    costKm: Float,
    vehicle: String
) {
    // Max Detour KM
    BasicTextField(
        value = maxDetourKm.toString(),
        onValueChange = publishRouteViewModel::setMaxDetourKm,
        placeholder = "Max desviament (km)"
    )
    // Available Seats
    BasicTextField(
        value = availableSeats.toString(),
        onValueChange = publishRouteViewModel::setSeats,
        placeholder = "Seients disponibles"
    )
    // Available Space
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .fillMaxHeight(0.3f),
        value = availableSpace,
        onValueChange = publishRouteViewModel::setAvailableSpace,
        placeholder = {
            Text(text = "Espai disponible al vehicle", color = Color.Gray)
        },
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
            unfocusedIndicatorColor = Color.Gray,
            disabledContainerColor = MaterialTheme.colorScheme.background,
            disabledIndicatorColor = Color.Gray,
            focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next
        ),
        singleLine = false,
        maxLines = 7,
        )
    Spacer(modifier = Modifier.padding(8.dp))
    // KM Cost
    Row(
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .padding(bottom = 2.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = { /*TODO*/ },
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = MateBlackRC
            )
        ) {
            Icon(imageVector = Icons.Filled.QuestionMark, contentDescription = "Frequency Icon")
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
            .padding(top = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        // Back button
        ElevatedButton(
            shape = RoundedCornerShape(16.dp),
            onClick = {
                publishRouteViewModel.previousStep()
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
        // Next Button
        ElevatedButton(
            shape = RoundedCornerShape(16.dp),
            onClick = {
                publishRouteViewModel.nextStep()
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
}
@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun PublishRouteContent1(
    routeName: String,
    publishRouteViewModel: PublishRouteViewModel,
    originName: String,
    stepLocationsNumber: Int,
    stepName1: String,
    destinationName: String,
    isDropdownExpanded: Boolean,
    routeFrequency: String,
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
        modifier = Modifier.fillMaxWidth(0.95f),
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
                    value = stepName1,
                    onValueChange = { publishRouteViewModel.setStepLocationName(index, stepName1) }
                )
                IconButton(
                    onClick = { publishRouteViewModel.removeStepLocation() },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = MateBlackRC
                    )
                ) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Remove Step Location"
                    )
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
    IconTextField(value = destinationName,
        onValueChange = publishRouteViewModel::setDestinationName,
        placeholder = "Data i hora de sortida",
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.CalendarMonth,
                contentDescription = "Origin Location Icon"
            )
        })
    IconTextField(value = destinationName,
        onValueChange = publishRouteViewModel::setDestinationName,
        placeholder = "Data i hora d'arribada",
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.CalendarMonth,
                contentDescription = "Destination Time Icon"
            )
        })
    Row(
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .padding(bottom = 2.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = { /*TODO*/ },
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
    ElevatedButton(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .padding(top = 24.dp),
        shape = RoundedCornerShape(16.dp),
        onClick = {
            publishRouteViewModel.checkAllValues()
            if (isFirstFormCompleted) {
                publishRouteViewModel.nextStep()
            } else {
                println("Falten camps per omplir")
            }
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
