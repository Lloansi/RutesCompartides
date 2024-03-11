package com.example.rutescompartidesapp.view.publish_order

import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.House
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.example.rutescompartidesapp.view.components.BasicTextField
import com.example.rutescompartidesapp.view.components.DateTimePickerTextField
import com.example.rutescompartidesapp.view.components.IconTextField
import com.example.rutescompartidesapp.view.routes_order_list.components.ConditionScrollPopup

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun PublishOrderScreen(navHost: NavHostController) {
    val publishOrderViewModel = PublishOrderViewModel()

    // Screen 1 variables
    val currentStep by publishOrderViewModel.step.collectAsStateWithLifecycle()
    val orderName by publishOrderViewModel.internalOrderName.collectAsStateWithLifecycle()
    val originName by publishOrderViewModel.originName.collectAsStateWithLifecycle()
    val destinationName by publishOrderViewModel.destinationName.collectAsStateWithLifecycle()
    val minTimeArrival by publishOrderViewModel.minTimeArrivalText.collectAsStateWithLifecycle()
    val maxTimeArrival by publishOrderViewModel.maxTimeArrivalText.collectAsStateWithLifecycle()
    val isDatePickerShowing by publishOrderViewModel.datePickerDialogIsShowing.collectAsStateWithLifecycle()
    val isCondicionsPopupShowing by publishOrderViewModel.isCondicionsPopupShowing.collectAsStateWithLifecycle()
    val isIsoterm by publishOrderViewModel.isIsoterm.collectAsStateWithLifecycle()
    val isRefrigerat by publishOrderViewModel.isRefrigerat.collectAsStateWithLifecycle()
    val isCongelat by publishOrderViewModel.isCongelat.collectAsStateWithLifecycle()
    val isSenseHumitat by publishOrderViewModel.isSenseHumitat.collectAsStateWithLifecycle()
    val tagsText by publishOrderViewModel.tagsText.collectAsStateWithLifecycle()
    val tagsList by publishOrderViewModel.tagsList.collectAsStateWithLifecycle()
    val tagsError by publishOrderViewModel.tagsError.collectAsStateWithLifecycle()
    val datePickerState = rememberDatePickerState()
    val isFirstFormCompleted by publishOrderViewModel.isFirstFormCompleted.collectAsStateWithLifecycle()
    val wantsCarpool by publishOrderViewModel.wantsCarpool.collectAsStateWithLifecycle()
    val isFlexDate by publishOrderViewModel.isFlexDate.collectAsStateWithLifecycle()

    Scaffold( modifier = Modifier
        .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Publicar Comanda $currentStep/3") },
                navigationIcon = {
                    IconButton(onClick = {
                        if (currentStep == 1) navHost.popBackStack()
                        else publishOrderViewModel.previousStep() }
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
                .fillMaxSize().verticalScroll(rememberScrollState())
                .padding(
                    top = paddingValues.calculateTopPadding() + 8.dp,
                    bottom = paddingValues.calculateBottomPadding() + 8.dp,
                    start = 8.dp,
                    end = 8.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (currentStep) {
                1 -> {
                    BasicTextField(
                        value = orderName,
                        onValueChange = publishOrderViewModel::setInternalOrderName,
                        placeholder = "Nom intern de la ruta"
                    )
                    IconTextField(value = originName,
                        onValueChange = publishOrderViewModel::setOriginName,
                        placeholder = "Punt de sortida",
                        leadingIcon = {
                            Icon(imageVector = Icons.Filled.House, contentDescription = "Origin Location Icon")
                        })
                    IconTextField(value = destinationName,
                        onValueChange = publishOrderViewModel::setDestinationName,
                        placeholder = "Punt d'arribada",
                        leadingIcon = {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                painter = painterResource(id = R.drawable.map_icon),
                                contentDescription = "Destination Location Icon"
                            )
                        })
                    // MinTimeArrival
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        IconButton(
                            onClick = { /*TODO*/ },
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = MaterialTheme.colorScheme.secondaryContainer
                            )
                        ) {
                            Icon(imageVector = Icons.Filled.QuestionMark,
                                contentDescription = "Frequency Icon",
                                tint = MaterialTheme.colorScheme.onSecondaryContainer)
                        }
                        // MinTimeArrival Text field
                        DateTimePickerTextField(
                            invocation = { publishOrderViewModel.onDatePickerDialogShow(isMin = true, isShowing = true) },
                            time = minTimeArrival,
                            onValueChange = publishOrderViewModel::onMinTimeArrivalChange ,
                            placeholder = "Data mínima d'arribada"
                        )
                    }
                    // MaxTimeArrival
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        IconButton(
                            onClick = { /*TODO*/ },
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = MaterialTheme.colorScheme.secondaryContainer
                            )
                        ) {
                            Icon(imageVector = Icons.Filled.QuestionMark,
                                contentDescription = "Frequency Icon",
                                tint = MaterialTheme.colorScheme.onSecondaryContainer)
                        }
                        // MaxTimeArrival Text field
                        DateTimePickerTextField(
                            invocation = { publishOrderViewModel.onDatePickerDialogShow(isMin = false, isShowing = true) },
                            time = maxTimeArrival,
                            onValueChange = publishOrderViewModel::onMaxTimeArrivalTextChange ,
                            placeholder = "Data màxima d'arribada"
                        )
                    }
                    // Date Picker Dialog
                    if (isDatePickerShowing){
                        DatePickerDialog(
                            colors = DatePickerDefaults.colors(
                                //containerColor = Color.White
                            ),
                            onDismissRequest = { publishOrderViewModel.onDatePickerDialogShow(isMin = true, isShowing = false) },
                            confirmButton = {
                                ElevatedButton(onClick = {
                                    datePickerState.selectedDateMillis?.let { dateInMillis ->
                                        publishOrderViewModel.onDatePickerDialogConfirm(dateInMillis)
                                    }
                                },
                                    colors = ButtonDefaults.elevatedButtonColors(
                                        contentColor = MaterialTheme.colorScheme.secondary,
                                        containerColor = MaterialTheme.colorScheme.primary
                                    )) {
                                    Text(text = "Confirmar")
                                }
                            },
                            dismissButton = {
                                ElevatedButton(onClick = {publishOrderViewModel.onDatePickerDialogShow(isMin = false, isShowing = true)}) {
                                    Text(text = "Salir")
                                }
                            }
                        ){
                            DatePicker(state = datePickerState )
                        }
                    }
                    // Arrival Time Flexibility Checkbox
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = isFlexDate,
                            onCheckedChange = { publishOrderViewModel.onFlexDateChange() }
                        )
                        Text(
                            text = "Data d'arribada flexible",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                    // Arrival Time Flexibility Checkbox
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = wantsCarpool,
                            onCheckedChange = { publishOrderViewModel.onWantsCarpoolChange() }
                        )
                        Text(
                            text = "Vull ocupar un seient buit al vehicle",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }

                    // TODO CHECKBOXES
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
                                onClick = { publishOrderViewModel.onCondicionsPopupShow(true) },
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
                                        publishOrderViewModel.onCondicionsPopupShow(
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
                                    onClick = { publishOrderViewModel.onCheckChip("Isoterm") },
                                    label = {
                                        if (isIsoterm) {
                                            Text(
                                                "Isoterm",
                                                style = MaterialTheme.typography.bodyLarge,
                                                color = Color.White
                                            )
                                        } else {
                                            Text("Isoterm",
                                                style = MaterialTheme.typography.bodyLarge,
                                            )
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
                                    onClick = { publishOrderViewModel.onCheckChip("Refrigerat") },
                                    label = {
                                        if (isRefrigerat) {
                                            Text(
                                                "Refrigerat",
                                                style = MaterialTheme.typography.bodyLarge,
                                                color = Color.White
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
                                    onClick = { publishOrderViewModel.onCheckChip("Congelat") },
                                    label = {
                                        if (isCongelat) {
                                            Text(
                                                "Congelat",
                                                style = MaterialTheme.typography.bodyLarge,
                                                color = Color.White
                                            )
                                        } else {
                                            Text("Congelat",
                                                style = MaterialTheme.typography.bodyLarge)
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
                                    onClick = { publishOrderViewModel.onCheckChip("SenseHumitat") },
                                    label = {
                                        if (isSenseHumitat) {
                                            Text(
                                                "Sense Humitat",
                                                color = Color.White,
                                                style = MaterialTheme.typography.bodyLarge
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
                    // Tags
                    // Tag TextField
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth(0.95f)
                            .padding(top = 8.dp),
                        value = tagsText,
                        onValueChange = publishOrderViewModel::onTagsChange,
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
                                    publishOrderViewModel.onTagsErrorChange(true)
                                    return@KeyboardActions
                                }
                                publishOrderViewModel.onTagsErrorChange(false)
                                publishOrderViewModel.onTagsAddToListChange(tagsText)
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
                                onClick = { publishOrderViewModel.onTagDelete(etiqueta) },
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
                                            publishOrderViewModel.onTagDelete(etiqueta)
                                        })
                                })
                            Spacer(Modifier.padding(4.dp))
                        }
                    }
                    ElevatedButton(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .padding(top = 24.dp),
                        shape = RoundedCornerShape(16.dp),
                        onClick = {
                            publishOrderViewModel.checkAllValues()
                            if (isFirstFormCompleted) {
                                publishOrderViewModel.nextStep()
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

            }
        }
    }
}