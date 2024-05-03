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
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.House
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
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
import androidx.compose.ui.text.input.KeyboardType
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
import com.example.rutescompartidesapp.view.generic_components.BasicTextField
import com.example.rutescompartidesapp.view.generic_components.DateTimePickerTextField
import com.example.rutescompartidesapp.view.generic_components.IconTextField
import com.example.rutescompartidesapp.view.generic_components.MeasurementsTextField
import com.example.rutescompartidesapp.view.generic_components.MultilineTextField
import com.example.rutescompartidesapp.view.generic_components.PublishBackButton
import com.example.rutescompartidesapp.view.generic_components.PublishButton
import com.example.rutescompartidesapp.view.generic_components.PublishNextButton
import com.example.rutescompartidesapp.view.generic_components.TimePickerDialog
import com.example.rutescompartidesapp.view.generic_components.popups.BasicPopup
import com.example.rutescompartidesapp.view.generic_components.popups.ConditionScrollPopup
import com.example.rutescompartidesapp.view.login.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PublishOrderScreen(command: String, orderID: Int, navHost: NavHostController,
                       loginViewModel: LoginViewModel, manageOrderViewModel: ManageOrderViewModel) {
    val user by loginViewModel.user.collectAsStateWithLifecycle()
    // Screen 1 variables
    val currentStep by manageOrderViewModel.step.collectAsStateWithLifecycle()
    val orderName by manageOrderViewModel.internalOrderName.collectAsStateWithLifecycle()
    val originName by manageOrderViewModel.originName.collectAsStateWithLifecycle()
    val destinationName by manageOrderViewModel.destinationName.collectAsStateWithLifecycle()
    val dataSortida by manageOrderViewModel.dataSortida.collectAsStateWithLifecycle()
    val tempsSortida by manageOrderViewModel.horaSortidaText.collectAsStateWithLifecycle()
    val dataArribada by manageOrderViewModel.dataArribada.collectAsStateWithLifecycle()
    val tempsArribada by manageOrderViewModel.horaArribadaText.collectAsStateWithLifecycle()
    val isDatePickerShowing by manageOrderViewModel.datePickerDialogIsShowing.collectAsStateWithLifecycle()
    val isTimePickerShowing by manageOrderViewModel.timePickerDialogIsShowing.collectAsStateWithLifecycle()
    val isCondicionsPopupShowing by manageOrderViewModel.isCondicionsPopupShowing.collectAsStateWithLifecycle()
    val isIsoterm by manageOrderViewModel.isIsoterm.collectAsStateWithLifecycle()
    val isRefrigerat by manageOrderViewModel.isRefrigerat.collectAsStateWithLifecycle()
    val isCongelat by manageOrderViewModel.isCongelat.collectAsStateWithLifecycle()
    val isSenseHumitat by manageOrderViewModel.isSenseHumitat.collectAsStateWithLifecycle()
    val tagsText by manageOrderViewModel.tagsText.collectAsStateWithLifecycle()
    val tagsList by manageOrderViewModel.tagsList.collectAsStateWithLifecycle()
    val tagsError by manageOrderViewModel.tagsError.collectAsStateWithLifecycle()
    val datePickerState = rememberDatePickerState()
    val wantsCarpool by manageOrderViewModel.wantsCarpool.collectAsStateWithLifecycle()
    val isFlexDate by manageOrderViewModel.isFlexDate.collectAsStateWithLifecycle()
    val isDataMinPopupShowing by manageOrderViewModel.isDataMinPopupShowing.collectAsStateWithLifecycle()
    val isDataMaxPopupShowing by manageOrderViewModel.isDataMaxPopupShowing.collectAsStateWithLifecycle()
    // Screen 2 variables
    val packageNumber by manageOrderViewModel.packagesNum.collectAsStateWithLifecycle()
    val arePackagesFragile by manageOrderViewModel.arePackagesFragile.collectAsStateWithLifecycle()
    val packageLength by manageOrderViewModel.packagesLength.collectAsStateWithLifecycle()
    val packageWidth by manageOrderViewModel.packagesWidth.collectAsStateWithLifecycle()
    val packageHeight by manageOrderViewModel.packagesHeight.collectAsStateWithLifecycle()
    val packageWeight by manageOrderViewModel.packagesWeight.collectAsStateWithLifecycle()
    val wantsDeliveryNotification by manageOrderViewModel.wantsDeliveryNotification.collectAsStateWithLifecycle()

    // Screen 3 variables
    val deliveryNote by manageOrderViewModel.deliveryNote.collectAsStateWithLifecycle()
    val isDeliveryContactDataChecked by manageOrderViewModel.isDeliveryContactDataChecked.collectAsStateWithLifecycle()
    val isPuntHabitualDataChecked by manageOrderViewModel.isPuntHabitualDataChecked.collectAsStateWithLifecycle()
    val clientName by manageOrderViewModel.deliveryContact.collectAsStateWithLifecycle()
    val clientPhone by manageOrderViewModel.deliveryTelephoneNumber.collectAsStateWithLifecycle()
    val comment by manageOrderViewModel.comment.collectAsStateWithLifecycle()

    // Errors
    val screen1Errors by manageOrderViewModel.screen1Errors.collectAsStateWithLifecycle()
    val screen2Errors by manageOrderViewModel.screen2Errors.collectAsStateWithLifecycle()
    if (command == "edit" && orderID != 0) {
        manageOrderViewModel.getOrder(orderID)
    }
    val orderToEdit by manageOrderViewModel.orderToEdit.collectAsStateWithLifecycle()
    val orderAdded by manageOrderViewModel.orderAdded.collectAsStateWithLifecycle()
    if (orderAdded) {
        // Resets the orderAdded state
        manageOrderViewModel.onOrderAdded(false)
        if (command == "create"){
            navHost.navigate("MapScreen"){
                popUpTo("MapScreen") { inclusive = true }
            }
        } else {
            navHost.navigate("OrderDetailScreen/{orderID}".replace(
                "{orderID}", orderID.toString())
            ){
                popUpTo("OrderDetailScreen/{orderID}") { inclusive = true }
            }
        }
    }

    Scaffold( modifier = Modifier
        .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Publicar Comanda $currentStep/3") },
                navigationIcon = {
                    IconButton(onClick = {
                        if (currentStep == 1) navHost.popBackStack()
                        else manageOrderViewModel.previousStep() }
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
                .verticalScroll(rememberScrollState())
                .padding(
                    top = paddingValues.calculateTopPadding() + 8.dp,
                    bottom = paddingValues.calculateBottomPadding() + 8.dp,
                    start = 8.dp,
                    end = 8.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if ((command == "edit" && orderID != 0 && orderToEdit != null) || command == "create"){
            when (currentStep) {
                1 -> {
                    PublishOrderContent1(
                        orderName,
                        manageOrderViewModel,
                        originName,
                        destinationName,
                        isDataMinPopupShowing,
                        isDataMaxPopupShowing,
                        isDatePickerShowing,
                        isTimePickerShowing,
                        datePickerState,
                        dataSortida,
                        tempsSortida,
                        dataArribada,
                        tempsArribada,
                        isFlexDate,
                        wantsCarpool,
                        tagsText,
                        tagsError,
                        tagsList.toList(),
                        screen1Errors
                    )
                }
                2 -> {
                    PublishOrderContent2(
                        isCondicionsPopupShowing,
                        isIsoterm,
                        isRefrigerat,
                        isCongelat,
                        isSenseHumitat,
                        packageNumber,
                        manageOrderViewModel,
                        arePackagesFragile,
                        packageHeight,
                        packageWidth,
                        packageLength,
                        packageWeight,
                        wantsDeliveryNotification,
                        screen2Errors
                    )
                }
                3 -> {
                    PublishOrderContent3(
                        deliveryNote,
                        manageOrderViewModel,
                        isDeliveryContactDataChecked,
                        clientName,
                        clientPhone,
                        isPuntHabitualDataChecked,
                        comment,
                        command,
                        user!!.userId
                    )
                }
            }
            } else {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
private fun PublishOrderContent3(
    deliveryNote: String,
    manageOrderViewModel: ManageOrderViewModel,
    isDeliveryContactDataChecked: Boolean,
    clientName: String,
    clientPhone: String,
    isPuntHabitualDataChecked: Boolean,
    comment: String,
    command: String,
    userID: Int
) {
    // Delivery Note TextField
    Row(
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .padding(start = 4.dp, bottom = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Contacte de recollida i/o entrega",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
    MultilineTextField(
        value = deliveryNote,
        onValueChange = manageOrderViewModel::setDeliveryNote,
        placeholder = "Dades de contacte per al transportista",
        isError = false
    )
    // Delivery note checkboxes
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = isDeliveryContactDataChecked,
            onCheckedChange = {
                manageOrderViewModel.onDeliveryContactDataCheck()
                manageOrderViewModel.appendInfoToDeliveryNote("userInfo")
            }
        )
        Text(
            text = "Recollida: $clientName\n" +
                    "Telèfon: $clientPhone",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = isPuntHabitualDataChecked,
            onCheckedChange = {
                manageOrderViewModel.onPuntHabitualDataCheck()
                manageOrderViewModel.appendInfoToDeliveryNote("puntHabitual")
            }
        )
        Text(
            text = "Afegir dades de contacte del punt habitual triat",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
    // Comment TextField
    Row(
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .padding(start = 4.dp, bottom = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Comentaris",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
    MultilineTextField(
        value = comment,
        onValueChange = manageOrderViewModel::setComment,
        placeholder = "Condicions especials quant a la recollida, entrega, transport, etc. " +
                "Tipus de punt d’entrega final, adreça, horari del punt d’entrega...",
        isError = false
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
        PublishBackButton(manageOrderViewModel::previousStep)
        // Next Button
        PublishButton(
            onClick = {  if(command == "create"){
                manageOrderViewModel.addOrder(userID)
            } else manageOrderViewModel.updateOrder(userID) },

            text = if(command == "create") "Publicar comanda" else "Editar comanda"
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PublishOrderContent2(
    isCondicionsPopupShowing: Boolean,
    isIsoterm: Boolean,
    isRefrigerat: Boolean,
    isCongelat: Boolean,
    isSenseHumitat: Boolean,
    packageNumber: String,
    manageOrderViewModel: ManageOrderViewModel,
    arePackagesFragile: Boolean,
    packageHeight: String,
    packageWidth: String,
    packageLength: String,
    packageWeight: String,
    wantsDeliveryNotification: Boolean,
    screen2Errors: List<Boolean>
) {
    // Conditions Chips & Popup
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
                onClick = { manageOrderViewModel.onCondicionsPopupShow(true) },
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
                        manageOrderViewModel.onCondicionsPopupShow(
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
                    onClick = { manageOrderViewModel.onCheckChip("Isoterm") },
                    label = {
                        if (isIsoterm) {
                            Text(
                                "Isoterm",
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.White
                            )
                        } else {
                            Text(
                                "Isoterm",
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
                    onClick = { manageOrderViewModel.onCheckChip("Refrigerat") },
                    label = {
                        if (isRefrigerat) {
                            Text(
                                "Refrigerat",
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.White
                            )
                        } else {
                            Text(
                                "Refrigerat",
                                style = MaterialTheme.typography.bodyLarge,
                            )
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
                    onClick = { manageOrderViewModel.onCheckChip("Congelat") },
                    label = {
                        if (isCongelat) {
                            Text(
                                "Congelat",
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.White
                            )
                        } else {
                            Text(
                                "Congelat",
                                style = MaterialTheme.typography.bodyLarge
                            )
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
                    onClick = { manageOrderViewModel.onCheckChip("SenseHumitat") },
                    label = {
                        if (isSenseHumitat) {
                            Text(
                                "Sense Humitat",
                                color = Color.White,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        } else {
                            Text(
                                "Sense Humitat",
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

    Row(
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .padding(bottom = 20.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            modifier = Modifier.weight(0.65f),
            value = packageNumber,
            onValueChange = manageOrderViewModel::setPackagesNum,
            placeholder = {
                Text(text = "Nombre de paquets", color = Color.Gray)
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
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            singleLine = true,
            isError = screen2Errors[0]
        )
        // Fragile Checkbox
        Checkbox(
            checked = arePackagesFragile,
            onCheckedChange = { manageOrderViewModel.onPackagesFragileChange() }
        )
        Text(
            modifier = Modifier.weight(0.30f),
            text = "Són fràgils",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
    Row(
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .padding(bottom = 10.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(0.30f),
            text = "Mides i pes dels paquets",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
        )
    }
    // Height, Width, Length, Weight
    MeasurementsTextField(
        value = packageHeight,
        onValueChange = manageOrderViewModel::setPackagesHeight,
        placeholder = "Alçària (cm)",
        suffix = "cm",
        isError = screen2Errors[1],
        keyboardType = KeyboardType.Decimal

    )
    MeasurementsTextField(
        value = packageWidth,
        onValueChange = manageOrderViewModel::setPackagesWidth,
        placeholder = "Amplada (cm)",
        suffix = "cm",
        isError = screen2Errors[2],
        keyboardType = KeyboardType.Decimal
    )
    MeasurementsTextField(
        value = packageLength,
        onValueChange = manageOrderViewModel::setPackagesLength,
        placeholder = "Llargada (cm)",
        suffix = "cm",
        isError = screen2Errors[3],
        keyboardType = KeyboardType.Decimal
    )
    MeasurementsTextField(
        value = packageWeight,
        onValueChange = manageOrderViewModel::setPackagesWeight,
        placeholder = "Pes (kg)",
        suffix = "kg",
        isError = screen2Errors[4],
        keyboardType = KeyboardType.Decimal
    )

    // Notification Checkbox
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = wantsDeliveryNotification,
            onCheckedChange = { manageOrderViewModel.onWantsDeliveryNotificationChange() }
        )
        Text(
            text = "Vull rebre una notificació quan s’hagi entregat la comanda.",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
    // Buttons
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        // Back button
        PublishBackButton(manageOrderViewModel::previousStep)
        // Next Button
        PublishNextButton(
            onClickCheck = manageOrderViewModel::checkAllValues,
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
private fun PublishOrderContent1(
    orderName: String,
    manageOrderViewModel: ManageOrderViewModel,
    originName: String,
    destinationName: String,
    isDataMinPopupShowing: Boolean,
    isDataMaxPopupShowing: Boolean,
    isDatePickerShowing: Boolean,
    isTimePickerShowing: Boolean,
    datePickerState: DatePickerState,
    dateDepart: String,
    timeDepart: String,
    dateArrival: String,
    timeArrival: String,
    isFlexDate: Boolean,
    wantsCarpool: Boolean,
    tagsText: String,
    tagsError: Boolean,
    tagsList: List<String>,
    screen1Errors: List<Boolean>
) {
    BasicTextField(
        value = orderName,
        onValueChange = manageOrderViewModel::setInternalOrderName,
        placeholder = "Nom intern de la comanda",
        isError = screen1Errors[0]
    )
    IconTextField(value = originName,
        onValueChange = manageOrderViewModel::setOriginName,
        placeholder = "Punt de sortida",
        leadingIcon = {
            Icon(imageVector = Icons.Filled.House, contentDescription = "Origin Location Icon")
        },
        isError = screen1Errors[1])
    IconTextField(value = destinationName,
        onValueChange = manageOrderViewModel::setDestinationName,
        placeholder = "Punt d'arribada",
        leadingIcon = {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = R.drawable.map_icon),
                contentDescription = "Destination Location Icon"
            )
        },
        isError = screen1Errors[2])
    // MinTimeArrival
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 4.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = { manageOrderViewModel.onDataMinPopupShow(true) },
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            )
        ) {
            Icon(
                imageVector = Icons.Filled.QuestionMark,
                contentDescription = "Frequency Icon",
                tint = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
        // Data Sortida Text field
        DateTimePickerTextField(
            invocation = {
                manageOrderViewModel.onDatePickerDialogShow(
                    isMin = true,
                    isShowing = true
                )
            },
            time = dateDepart,
            onValueChange = manageOrderViewModel::onDataSortidaChange,
            placeholder = "Data mínima d'arribada",
            icon = Icons.Filled.CalendarMonth,
            isError = screen1Errors[3]
        )
    }
    if (isDataMinPopupShowing){
        BasicPopup(offset = IntOffset((LocalConfiguration.current.screenWidthDp / 2), (LocalConfiguration.current.screenHeightDp)),
            onDismisRequest = { manageOrderViewModel.onDataMaxPopupShow(
                false ) },
            content = { Text(text = "Indicar a partir de quin moment podries tenir llesta la teva comanda per iniciar el transport.",
                color = MaterialTheme.colorScheme.onBackground) })
    }
    DateTimePickerTextField(
        invocation = {
            manageOrderViewModel.onTimePickerDialogShow(
                isDepart = true,
                isShowing = true
            )
        },
        time = timeDepart,
        onValueChange = manageOrderViewModel::onTimeDepartChange,
        placeholder = "Hora d'arribada",
        icon = Icons.Filled.AccessTime,
        isError = screen1Errors[4]
    )

    // Data Arribada
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = { manageOrderViewModel.onDataMaxPopupShow(true)  },
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            )
        ) {
            Icon(
                imageVector = Icons.Filled.QuestionMark,
                contentDescription = "Frequency Icon",
                tint = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
        if (isDataMaxPopupShowing){
            BasicPopup(offset = IntOffset((LocalConfiguration.current.screenWidthDp / 2), (LocalConfiguration.current.screenHeightDp)),
                onDismisRequest = { manageOrderViewModel.onDataMaxPopupShow(
                    false ) },
                content = { Text(text = "Indicar la data màxima en què ha d'arribar la teva comanda.",
                    color = MaterialTheme.colorScheme.onBackground) })
        }
        // Data Arribada Text field
        DateTimePickerTextField(
            invocation = {
                manageOrderViewModel.onDatePickerDialogShow(
                    isMin = false,
                    isShowing = true
                )
            },
            time = dateArrival,
            onValueChange = manageOrderViewModel::onDataArribadaChange,
            placeholder = "Data màxima d'arribada",
            icon = Icons.Filled.CalendarMonth,
            isError = screen1Errors[5]
        )
    }

    // Temps Arribada Text field
    DateTimePickerTextField(
        invocation = {
            manageOrderViewModel.onTimePickerDialogShow(
                isDepart = false,
                isShowing = true
            )
        },
        time = timeArrival,
        onValueChange = manageOrderViewModel::onTimeArrivalChange,
        placeholder = "Hora d'arribada",
        icon = Icons.Filled.AccessTime,
        isError = screen1Errors[6]
    )

    // Time Picker Dialog
    if (isTimePickerShowing){
        TimePickerDialog(
            onCancel = { manageOrderViewModel.onTimePickerDialogShow(
                isDepart = true,
                isShowing =false)
            },
            onConfirm = manageOrderViewModel::onTimePickerDialogConfirm )
    }

    // Date Picker Dialog
    if (isDatePickerShowing) {
        DatePickerDialog(
            colors = DatePickerDefaults.colors(
                //containerColor = Color.White
            ),
            onDismissRequest = {
                manageOrderViewModel.onDatePickerDialogShow(
                    isMin = true,
                    isShowing = false
                )
            },
            confirmButton = {
                ElevatedButton(
                    onClick = {
                        datePickerState.selectedDateMillis?.let { dateInMillis ->
                            manageOrderViewModel.onDatePickerDialogConfirm(dateInMillis)
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
                    manageOrderViewModel.onDatePickerDialogShow(
                        isMin = false,
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
    // Arrival Time Flexibility Checkbox
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = isFlexDate,
            onCheckedChange = { manageOrderViewModel.onFlexDateChange() }
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
            onCheckedChange = { manageOrderViewModel.onWantsCarpoolChange() }
        )
        Text(
            text = "Vull ocupar un seient buit al vehicle",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
    // Tags
    // Tag TextField
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .padding(top = 8.dp),
        value = tagsText,
        onValueChange = manageOrderViewModel::onTagsChange,
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
                    manageOrderViewModel.onTagsErrorChange(true)
                    return@KeyboardActions
                }
                manageOrderViewModel.onTagsErrorChange(false)
                manageOrderViewModel.onTagsAddToListChange(tagsText)
            }),
        singleLine = true,
    )

    // Tags Row
    FlowRow(
        modifier = Modifier.fillMaxWidth(0.9f),
        horizontalArrangement = Arrangement.Start,
    ) {
        tagsList.forEach { etiqueta ->
            TagItem(manageOrderViewModel = manageOrderViewModel, etiqueta = etiqueta)
            Spacer(Modifier.padding(4.dp))
        }
    }
    // Next Button
    PublishNextButton(onClickCheck = manageOrderViewModel::checkAllValues)

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TagItem(manageOrderViewModel: ManageOrderViewModel, etiqueta: String) {
    InputChip(selected = true,
        onClick = { manageOrderViewModel.onTagDelete(etiqueta) },
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
                    manageOrderViewModel.onTagDelete(etiqueta)
                })
        })
}