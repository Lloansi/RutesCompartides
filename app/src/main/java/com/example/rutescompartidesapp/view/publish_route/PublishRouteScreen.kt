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
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
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
import com.example.rutescompartidesapp.view.login.LoginViewModel

@SuppressLint("RestrictedApi", "StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PublishRouteScreen(command: String, routeID: Int, navHost: NavHostController, loginViewModel: LoginViewModel,
                       manageRouteViewModel: ManageRouteViewModel){
    val user by loginViewModel.user.collectAsStateWithLifecycle()
    manageRouteViewModel.setUserID(user!!.userId)
    val currentStep by manageRouteViewModel.step.collectAsStateWithLifecycle()
    val routeName by manageRouteViewModel.internalRouteName.collectAsStateWithLifecycle()
    val originName by manageRouteViewModel.originName.collectAsStateWithLifecycle()
    val stepLocationsNumber by manageRouteViewModel.stepLocationsNumber.collectAsStateWithLifecycle()
    val stepNameList by manageRouteViewModel.stepNameList.collectAsStateWithLifecycle()
    val destinationName by manageRouteViewModel.destinationName.collectAsStateWithLifecycle()
    val isDropdownExpanded by manageRouteViewModel.isDropdownExpanded.collectAsStateWithLifecycle()
    val routeFrequency by manageRouteViewModel.routeFrequency.collectAsStateWithLifecycle()
    val isFreqPopupShowing by manageRouteViewModel.isFreqPopupShowing.collectAsStateWithLifecycle()
    val isCostPopupShowing by manageRouteViewModel.isCostPopupShowing.collectAsStateWithLifecycle()

    val datePickerState = rememberDatePickerState()
    val dateDepart by manageRouteViewModel.dateDepart.collectAsStateWithLifecycle()
    val dateArrival by manageRouteViewModel.dateArrival.collectAsStateWithLifecycle()
    val timeDepart by manageRouteViewModel.timeDepartText.collectAsStateWithLifecycle()
    val timeArrival by manageRouteViewModel.timeArrivalText.collectAsStateWithLifecycle()

    val isDatePickerShowing by manageRouteViewModel.datePickerDialogIsShowing.collectAsStateWithLifecycle()
    val isTimePickerShowing by manageRouteViewModel.timePickerDialogIsShowing.collectAsStateWithLifecycle()

    // Screen 2 variables
    val maxDetourKm by manageRouteViewModel.maxDetourKm.collectAsStateWithLifecycle()
    val availableSeats by manageRouteViewModel.availableSeats.collectAsStateWithLifecycle()
    val availableSpace by manageRouteViewModel.availableSpace.collectAsStateWithLifecycle()
    val costKm by manageRouteViewModel.costKM.collectAsStateWithLifecycle()
    val vehicle by manageRouteViewModel.vehicle.collectAsStateWithLifecycle()


    // Screen 3 variables
    val isCondicionsPopupShowing by manageRouteViewModel.isCondicionsPopupShowing.collectAsStateWithLifecycle()
    val isIsoterm by manageRouteViewModel.isIsoterm.collectAsStateWithLifecycle()
    val isRefrigerat by manageRouteViewModel.isRefrigerat.collectAsStateWithLifecycle()
    val isCongelat by manageRouteViewModel.isCongelat.collectAsStateWithLifecycle()
    val isSenseHumitat by manageRouteViewModel.isSenseHumitat.collectAsStateWithLifecycle()
    val tagsText by manageRouteViewModel.tagsText.collectAsStateWithLifecycle()
    val tagsList by manageRouteViewModel.tagsList.collectAsStateWithLifecycle()
    val tagsError by manageRouteViewModel.tagsError.collectAsStateWithLifecycle()
    val comment by manageRouteViewModel.comment.collectAsStateWithLifecycle()

    if (command == "edit" && routeID != 0) {
        manageRouteViewModel.getRoute(routeID)
    }

    // Errors
    val screen1Errors by manageRouteViewModel.screen1Errors.collectAsStateWithLifecycle()
    val screen2Errors by manageRouteViewModel.screen2Errors.collectAsStateWithLifecycle()

    val routeToEdit by manageRouteViewModel.routeToEdit.collectAsStateWithLifecycle()
    val routeAdded by manageRouteViewModel.routeAdded.collectAsStateWithLifecycle()

    // When the route is added, navigates to the destination based on from where the route was added

    if (routeAdded) {
        manageRouteViewModel.onRouteAdded(false)
        when (command){
            "create" -> {
                navHost.navigate("MapScreen"){
                    popUpTo("MapScreen") { inclusive = true }
                }
            }
            "createFrom" -> {
                navHost.navigate(
                    "RoutesOrderListScreen")
                {
                    popUpTo(
                        "RoutesOrderListScreen"){ inclusive = true }
                }
            }
            "edit" -> {
                navHost.navigate(
                    "RouteDetailDriverScreen/{routeId}".replace(
                        oldValue = "{routeId}",
                        newValue = "$routeID")
                ) {
                    popUpTo(
                        "RouteDetailDriverScreen/{routeId}"){ inclusive = true }
                }
            }
        }
        manageRouteViewModel.resetStep()
    }

    Scaffold( modifier = Modifier
        .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Publicar Ruta $currentStep/3") },
                navigationIcon = {
                    IconButton(onClick = {
                        if (currentStep == 1) navHost.popBackStack()
                        else manageRouteViewModel.previousStep() }
                    ) {
                        Icon(imageVector = Icons.Filled.ArrowBackIosNew, contentDescription = "Go Back",
                            tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MateBlackRC,
                    titleContentColor = Color.White
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
                )
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if ((command == "edit" && routeID != 0 && routeToEdit != null) || command == "create" || command == "createFrom"){
                when (currentStep) {
                    1 -> PublishRouteContent1(
                        routeName,
                        manageRouteViewModel,
                        originName,
                        stepLocationsNumber,
                        stepNameList,
                        destinationName,
                        isDatePickerShowing,
                        isTimePickerShowing,
                        datePickerState,
                        dateDepart,
                        timeDepart,
                        dateArrival,
                        timeArrival,
                        screen1Errors
                    )

                    2 -> {
                        PublishRouteContent2(
                            isDropdownExpanded,
                            isFreqPopupShowing,
                            routeFrequency,
                            maxDetourKm,
                            manageRouteViewModel,
                            availableSeats,
                            availableSpace,
                            isCostPopupShowing,
                            costKm,
                            vehicle,
                            screen2Errors
                        )
                    }

                    3 -> {
                        PublishRouteContent3(
                            manageRouteViewModel,
                            isCondicionsPopupShowing,
                            isIsoterm,
                            isRefrigerat,
                            isCongelat,
                            isSenseHumitat,
                            tagsText,
                            tagsError,
                            tagsList,
                            comment,
                            command,
                            user!!.userId
                        )
                    }
                }
            }  else {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
private fun PublishRouteContent3(
    manageRouteViewModel: ManageRouteViewModel,
    isCondicionsPopupShowing: Boolean,
    isIsoterm: Boolean,
    isRefrigerat: Boolean,
    isCongelat: Boolean,
    isSenseHumitat: Boolean,
    tagsText: String,
    tagsError: Boolean,
    tagsList: List<String>,
    comment: String,
    command: String,
    userID: Int
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
                onClick = { manageRouteViewModel.onCondicionsPopupShow(true) },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Filled.QuestionMark,
                    contentDescription = "Question Icon",
                    tint = Color.White
                )
            }
            // Conditions Popup
            if (isCondicionsPopupShowing) {
                PopupScrolleable(title = "Condicions de transport" ,
                    onDismisRequest = { manageRouteViewModel.onCondicionsPopupShow(
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
                    onClick = { manageRouteViewModel.onCheckChip("Isoterm") },
                    label = {
                        if (isIsoterm) {
                            Text(
                                "Isoterm",
                                color = Color.White,
                                style = MaterialTheme.typography.bodyLarge,
                            )
                        } else {
                            Text("Isoterm",
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.DarkGray)
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
                    onClick = { manageRouteViewModel.onCheckChip("Refrigerat") },
                    label = {
                        if (isRefrigerat) {
                            Text(
                                "Refrigerat",
                                color = Color.White,
                                style = MaterialTheme.typography.bodyLarge,
                            )
                        } else {
                            Text("Refrigerat",
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.DarkGray)
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
                    onClick = { manageRouteViewModel.onCheckChip("Congelat") },
                    label = {
                        if (isCongelat) {
                            Text(
                                "Congelat",
                                color = Color.White,
                                style = MaterialTheme.typography.bodyLarge,
                            )
                        } else {
                            Text("Congelat",
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.DarkGray)
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
                    onClick = { manageRouteViewModel.onCheckChip("SenseHumitat") },
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
                                color = Color.DarkGray
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
        onValueChange = manageRouteViewModel::onTagsChange,
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
            disabledContainerColor = MaterialTheme.colorScheme.background,
            disabledIndicatorColor = Color.Gray,
            focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            errorTextColor = MaterialTheme.colorScheme.primary,
            errorContainerColor = GrayRC
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Send,
        ),
        keyboardActions = KeyboardActions(
            onSend = {
                if (tagsText.isEmpty() || tagsText in tagsList) {
                    manageRouteViewModel.onTagsErrorChange(true)
                    return@KeyboardActions
                }
                manageRouteViewModel.onTagsErrorChange(false)
                manageRouteViewModel.onTagsAddToListChange(tagsText)
            }),
        singleLine = true,
    )
    // Tags
    FlowRow(modifier = Modifier.fillMaxWidth(0.9f),
        horizontalArrangement = Arrangement.Start,){
        tagsList.forEach{ etiqueta ->
            TagItem(manageRouteViewModel = manageRouteViewModel, etiqueta = etiqueta)
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
    MultilineTextField(value = comment, onValueChange = manageRouteViewModel::setComment,
        placeholder = "Condicions especials quant a la recollida, entrega, transport (ex: faré una parada de 6 hores a la meva ruta abans d’arribar al destí final; necessito confirmació d’horari d’entrega als comentaris; acepto productes com forma de pagament...)",
        isError = false )
    // Buttons
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        // Back button
        PublishBackButton(manageRouteViewModel::previousStep)
        // Publish route button
        PublishButton( onClick = {  if(command == "create" || command == "createFrom"){
            manageRouteViewModel.addRoute(userID)
        } else manageRouteViewModel.updateRoute(userID) },

            text = if(command == "create" || command == "createFrom") "Publicar ruta" else "Editar ruta")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PublishRouteContent2(
    isDropdownExpanded: Boolean,
    isFreqPopupShowing: Boolean,
    routeFrequency: String,
    maxDetourKm: String,
    manageRouteViewModel: ManageRouteViewModel,
    availableSeats: String,
    availableSpace: String,
    isCostPopupShowing: Boolean,
    costKm: String,
    vehicle: String,
    screen2Errors: List<Boolean>
) {
    // Frequency
    Row(
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .padding(bottom = 2.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = { manageRouteViewModel.onFreqPopupShow(true) },
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            )
        ) {
            Icon(imageVector = Icons.Filled.QuestionMark, contentDescription = "Frequency Icon",
                tint = MaterialTheme.colorScheme.onSecondaryContainer)
        }
        Text(
            modifier = Modifier.weight(2f),
            text = "Freqüència de la ruta", color = MaterialTheme.colorScheme.onBackground
        )
    }
    if (isFreqPopupShowing){
        PopupScrolleable(title = "Freqüència de la ruta" ,
            onDismisRequest = { manageRouteViewModel.onFreqPopupShow(
                false ) },
            content = { Text(text = "Crearà totes  les rutes que pertoqui fins a la data final indicada, amb un màxim de 3  mesos. Recorda editar o eliminar les rutes si canvien a posteriori o  n'hi ha alguna que finalment no faràs. La freqüència mensual implica  aquell dia de la setmana i la mateixa setmana de cada mes (és a dir, el  1er dimecres de mes, o el 2on dijous de mes, etc).",
                color = MaterialTheme.colorScheme.onBackground)})
    }
    // Frequency dropdown
    Row(
        modifier = Modifier.fillMaxWidth(0.95f),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ExposedDropdownMenuBox(
            expanded = isDropdownExpanded,
            onExpandedChange = { manageRouteViewModel.toggleDropdown() }) {
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
                onDismissRequest = { manageRouteViewModel.toggleDropdown() }
            ) {
                DropdownMenuItem(
                    text = {
                        Text(text = "No es repeteix")
                    },
                    onClick = { manageRouteViewModel.setRouteFrequency("No es repeteix") }
                )
                DropdownMenuItem(
                    text = {
                        Text(text = "Diaria")
                    },
                    onClick = { manageRouteViewModel.setRouteFrequency("Diaria") }
                )
                DropdownMenuItem(
                    text = {
                        Text(text = "Setmanal")
                    },
                    onClick = { manageRouteViewModel.setRouteFrequency("Setmanal") }
                )
                DropdownMenuItem(
                    text = {
                        Text(text = "Bisetmanal")
                    },
                    onClick = { manageRouteViewModel.setRouteFrequency("Bisetmanal") }
                )
                DropdownMenuItem(
                    text = {
                        Text(text = "Mensual")
                    },
                    onClick = { manageRouteViewModel.setRouteFrequency("Mensual") }
                )
            }

        }
    }
    Spacer(modifier = Modifier.padding(8.dp))

    // Max Detour KM
    BasicTextField(value = maxDetourKm,
        onValueChange = manageRouteViewModel::setMaxDetourKm,
        placeholder = "Max desviament (km)",
        isError = screen2Errors[0],
        keyboardType = KeyboardType.Decimal
    )
    // Available Seats
    BasicTextField(value = availableSeats,
        onValueChange = manageRouteViewModel::setSeats,
        placeholder = "Seients disponibles",
        isError = screen2Errors[1],
        keyboardType = KeyboardType.Number

    )
    // Available Space
    MultilineTextField(value = availableSpace,
        onValueChange = manageRouteViewModel::setAvailableSpace , placeholder = "Espai disponible al vehicle",
        isError = screen2Errors[2])
    // KM Cost
    Row(modifier = Modifier
        .fillMaxWidth(0.95f)
        .padding(bottom = 2.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = { manageRouteViewModel.onCostPopupShow(true) },
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            )
        ) {
            Icon(imageVector = Icons.Filled.QuestionMark,
                contentDescription = "KMCost Icon",
                tint = MaterialTheme.colorScheme.onSecondaryContainer)
        }
        if (isCostPopupShowing){
            PopupScrolleable(title = "Cost per KM" ,
                onDismisRequest = { manageRouteViewModel.onCostPopupShow(
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
        value = costKm,
        onValueChange = manageRouteViewModel::setCostKM,
        placeholder = "0",
        isError = screen2Errors[3],
        keyboardType = KeyboardType.Decimal
    )
    // Vehicle Model
    BasicTextField(
        value = vehicle,
        onValueChange = manageRouteViewModel::setVehicle,
        placeholder = "Model de vehicle / furgoneta",
        isError = screen2Errors[4]
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
        PublishBackButton(manageRouteViewModel::previousStep)
        // Next Button
        PublishNextButton(onClickCheck = manageRouteViewModel::checkAllValues)

    }
}



@SuppressLint("StateFlowValueCalledInComposition")
@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun PublishRouteContent1(
    routeName: String,
    manageRouteViewModel: ManageRouteViewModel,
    originName: String,
    stepLocationsNumber: Int,
    stepNameList: List<String>,
    destinationName: String,
    isDatePickerShowing: Boolean,
    isTimePickerShowing: Boolean,
    datePickerState: DatePickerState,
    dateDepart: String,
    timeDepart: String,
    dateArrival: String,
    timeArrival: String,
    screen1Errors: List<Boolean>
) {
    BasicTextField(
        value = routeName,
        onValueChange = manageRouteViewModel::setInternalRouteName,
        placeholder = "Nom intern de la ruta",
        isError = screen1Errors[0]
    )
    IconTextField(value = originName,
        onValueChange = manageRouteViewModel::setOriginName,
        placeholder = "Punt de sortida",
        leadingIcon = {
            Icon(imageVector = Icons.Filled.House, contentDescription = "Origin Location Icon")
        },
        isError = screen1Errors[1]
    )
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .height(70.dp * stepLocationsNumber),
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
                            manageRouteViewModel.setStepLocationName(index, it)
                           }
                )

                IconButton(
                    onClick = { manageRouteViewModel.removeStepLocation(index) },
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
            onClick = { manageRouteViewModel.addStepLocation() },
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = OrangeRC
            ),
            enabled = stepLocationsNumber != 6
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
        onValueChange = manageRouteViewModel::setDestinationName,
        placeholder = "Punt d'arribada",
        leadingIcon = {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = R.drawable.map_icon),
                contentDescription = "Destination Location Icon"
            )
        },
        isError = screen1Errors[2])

    DateTimePickerTextField(
        invocation = {
            manageRouteViewModel.onDatePickerDialogShow(
                isDepart = true,
                isShowing = true
            )
        },
        time = dateDepart,
        onValueChange = manageRouteViewModel::onDateDepartChange,
        placeholder = "Data de sortida",
        icon = Icons.Filled.CalendarMonth,
        isError = screen1Errors[3]
    )

    DateTimePickerTextField(
        invocation = {
            manageRouteViewModel.onTimePickerDialogShow(
                isDepart = true,
                isShowing = true
            )
        },
        time = timeDepart,
        onValueChange = manageRouteViewModel::onTimeDepartChange,
        placeholder = "Hora d'arribada",
        icon = Icons.Filled.AccessTime,
        isError = screen1Errors[4]
    )

    DateTimePickerTextField(
        invocation = {
            manageRouteViewModel.onDatePickerDialogShow(
                isDepart = false,
                isShowing = true
            )
        },
        time = dateArrival,
        onValueChange = manageRouteViewModel::onDateArrivalChange,
        placeholder = "Data d'arribada",
        icon = Icons.Filled.CalendarMonth,
        isError = screen1Errors[5]
    )

    DateTimePickerTextField(
        invocation = {
            manageRouteViewModel.onTimePickerDialogShow(
                isDepart = false,
                isShowing = true
            )
        },
        time = timeArrival,
        onValueChange = manageRouteViewModel::onTimeArrivalChange,
        placeholder = "Hora d'arribada",
        icon = Icons.Filled.AccessTime,
        isError = screen1Errors[6]
    )

    // Date Picker Dialog
    if (isDatePickerShowing) {
        DatePickerDialog(
            colors = DatePickerDefaults.colors(
                //containerColor = Color.White
            ),
            onDismissRequest = {
                manageRouteViewModel.onDatePickerDialogShow(
                    isDepart  = true,
                    isShowing = false
                )
            },
            confirmButton = {
                ElevatedButton(
                    onClick = {
                        datePickerState.selectedDateMillis?.let { dateInMillis ->
                            manageRouteViewModel.onDatePickerDialogConfirm(dateInMillis)
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
                    manageRouteViewModel.onDatePickerDialogShow(
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
            onCancel = { manageRouteViewModel.onTimePickerDialogShow(
                isDepart = true,
                isShowing =false)
                       },
            onConfirm = manageRouteViewModel::onTimePickerDialogConfirm )
    }

    PublishNextButton(manageRouteViewModel::checkAllValues)

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TagItem(manageRouteViewModel: ManageRouteViewModel, etiqueta: String) {
    InputChip(selected = true,
        onClick = { manageRouteViewModel.onTagDelete(etiqueta) },
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
                    manageRouteViewModel.onTagDelete(etiqueta)
                })
        })
}


