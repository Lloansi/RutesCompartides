package com.example.rutescompartidesapp.view.publish_route

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.House
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.rutescompartidesapp.R
import com.example.rutescompartidesapp.ui.theme.MateBlackRC
import com.example.rutescompartidesapp.ui.theme.OrangeRC
import com.example.rutescompartidesapp.view.publish_route.components.BasicTextField
import com.example.rutescompartidesapp.view.publish_route.components.IconTextField
import com.example.rutescompartidesapp.view.publish_route.components.StepTextField

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

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    /*
    val timeDepart by publishRouteViewModel.timeDepart.collectAsStateWithLifecycle()
    val timeArrival by publishRouteViewModel.timeArrival.collectAsStateWithLifecycle()
    val showDatePicker by publishRouteViewModel.datePickerDialogIsShowing.collectAsStateWithLifecycle()
    val datePickerState = rememberDatePickerState()
    val showTimePicker by publishRouteViewModel.timePickerDialogIsShowing.collectAsStateWithLifecycle()
     */



    Scaffold( modifier = Modifier
        .fillMaxSize()
        .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = { Text(text = "Publicar Ruta 1/3") },
                navigationIcon = {
                    IconButton(onClick = { navHost.popBackStack() }) {
                        Icon(imageVector = Icons.Filled.ArrowBackIosNew, contentDescription = "Go Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground
                ),
                scrollBehavior = scrollBehavior,
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
            BasicTextField(value = routeName , onValueChange = publishRouteViewModel::setInternalRouteName , placeholder = "Nom intern de la ruta" )
            IconTextField(value = originName,
                onValueChange = publishRouteViewModel::setOriginName,
                placeholder = "Punt de sortida",
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.House, contentDescription = "Origin Location Icon")
                })
            LazyColumn(modifier = Modifier.fillMaxWidth(0.95f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                items(stepLocationsNumber){ index ->
                    Row (modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,) {
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
                            Icon(imageVector = Icons.Filled.Delete, contentDescription = "Remove Step Location")
                        }
                    }
                    Spacer(modifier = Modifier.size(8.dp))
                }
            }
            Row(modifier = Modifier.fillMaxWidth(0.95f),) {
                ElevatedButton(modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .padding(bottom = 8.dp),
                    shape = RoundedCornerShape(16.dp),
                    onClick = { publishRouteViewModel.addStepLocation() },
                    colors = ButtonDefaults.elevatedButtonColors(
                        containerColor = OrangeRC
                    )
                ){
                    Icon(imageVector = Icons.Filled.Add,
                        contentDescription = "Add Step Location",
                        tint = Color.White)
                    Text(text = "Punt intermedi", color = Color.White,
                        style = MaterialTheme.typography.headlineMedium)
                }
            }
            IconTextField(value = destinationName,
                onValueChange = publishRouteViewModel::setDestinationName,
                placeholder = "Punt d'arribada",
                leadingIcon = {
                    Icon(modifier = Modifier.size(24.dp), painter = painterResource(id = R.drawable.map_icon ), contentDescription = "Destination Location Icon")
                })
            IconTextField(value = destinationName,
                onValueChange = publishRouteViewModel::setDestinationName,
                placeholder = "Data i hora de sortida",
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.CalendarMonth, contentDescription = "Origin Location Icon")
                })
            IconTextField(value = destinationName,
                onValueChange = publishRouteViewModel::setDestinationName,
                placeholder = "Data i hora d'arribada",
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.CalendarMonth,
                        contentDescription = "Destination Time Icon")
                })
            Row (modifier = Modifier
                .fillMaxWidth(0.95f)
                .padding(bottom = 2.dp),
                verticalAlignment = Alignment.CenterVertically,) {
                IconButton(
                    onClick = { /*TODO*/ },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = MateBlackRC
                    )
                ) {
                    Icon(imageVector = Icons.Filled.QuestionMark, contentDescription = "Frequency Icon")
                }
                Text(modifier = Modifier.weight(2f),
                    text = "Freqüència de la ruta", color = MaterialTheme.colorScheme.onBackground)
            }
            Row (modifier = Modifier.fillMaxWidth(0.95f),
                verticalAlignment = Alignment.CenterVertically,){
                ExposedDropdownMenuBox(
                    expanded = isDropdownExpanded ,
                    onExpandedChange = {publishRouteViewModel.toggleDropdown()})  {
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
            ElevatedButton(modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(top = 24.dp),
                shape = RoundedCornerShape(16.dp),
                onClick = { publishRouteViewModel.checkAllValues()
                          if (isFirstFormCompleted){
                                navHost.navigate("PublishRoute2Screen")
                          } else {
                              println("Falten camps per omplir")
                          }
                          },
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = OrangeRC
                )
            ){
                Text(text = "Següent", color = Color.White,
                    style = MaterialTheme.typography.headlineMedium)
            }

        }
    }

}
