package com.example.rutescompartidesapp.view.value_experience

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.rutescompartidesapp.data.domain.RouteForList
import com.example.rutescompartidesapp.ui.theme.OrangeRC
import com.example.rutescompartidesapp.view.generic_components.MultilineTextField
import com.example.rutescompartidesapp.view.generic_components.PublishBackButton
import com.example.rutescompartidesapp.view.generic_components.PublishNextButton
import com.example.rutescompartidesapp.view.generic_components.TopAppBarWithBackNav
import com.example.rutescompartidesapp.view.route_detail.route_detail_driver.RouteDetailDriverViewModel
import com.example.rutescompartidesapp.view.routes_order_list.ListConstants
import com.example.rutescompartidesapp.view.routes_order_list.components.RouteCardHeader

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")

@Composable
fun ValueExperienceGeneralScreen(routeID: Int, navHost: NavHostController) {
    // TODO Amb el ID hauría de fer una trucada a la API per obtenir la ruta
    // TODO i una altra per obtenir les interaccions de la ruta
    // val interactions = DetailUtils.interactionList.filter { it.routeID == routeID }
    val route: RouteForList = ListConstants.routeList.first { it.routeID == routeID }
    val valueExperienceViewModel = ValueExperienceViewModel()
    val isPackageDelivered by valueExperienceViewModel.isPackageDelivered.collectAsStateWithLifecycle()
    val comment by valueExperienceViewModel.comment.collectAsStateWithLifecycle()
    val experienceScore by valueExperienceViewModel.experienceScore.collectAsStateWithLifecycle()
    val isDropdownExpanded by valueExperienceViewModel.isDropdownExpanded.collectAsStateWithLifecycle()


    TopAppBarWithBackNav(title = "Ruta #$routeID", onBack = { navHost.popBackStack() },
        content = {
            ElevatedCard(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.elevatedCardColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            ) {
                Row {
                    RouteCardHeader(route = route)
                }
                Spacer(modifier = Modifier.padding(8.dp))
                // Delivery note checkboxes
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = isPackageDelivered,
                        onCheckedChange = {
                            valueExperienceViewModel.onPackageDeliveredChange()
                        }
                    )
                    Text(
                        text = "Vaig fer la ruta però NO em va entregar la comanda.",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }

                //Score
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.95f)
                        .padding(bottom = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Puntuació: ",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onBackground)
                    ExposedDropdownMenuBox(
                        expanded = isDropdownExpanded,
                        onExpandedChange = {valueExperienceViewModel.toggleDropdown() }) {
                        OutlinedTextField(
                            value =  experienceScore,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isDropdownExpanded)
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
                                .fillMaxWidth(0.30f)
                                .height(48.dp)
                        )
                        ExposedDropdownMenu(
                            expanded = isDropdownExpanded,
                            onDismissRequest = {valueExperienceViewModel.toggleDropdown() }
                        ) {
                            LazyColumn(){
                                items(10){puntuació ->
                                    DropdownMenuItem(
                                        text = {
                                            Text(text = puntuació.toString())
                                        },
                                        onClick = { valueExperienceViewModel.setExperienceScore(puntuació.toString()) }
                                    )
                                }
                            }

                        }

                    }
                }

                Divider(color= OrangeRC, thickness = 2.dp)
                // Comment TextField
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.95f)
                        .padding(start = 4.dp, bottom = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Comentari:",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }

                    Row(
                    modifier = Modifier
                        .fillMaxWidth(0.95f)
                        .padding(start = 4.dp, bottom = 10.dp),
                    horizontalArrangement = Arrangement.Center

                ){
                    MultilineTextField(
                        value = comment,
                        onValueChange = valueExperienceViewModel::setComment,
                        placeholder = ""
                    )
                }
                // Buttons
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    // Next Button
                    ElevatedButton(
                        shape = RoundedCornerShape(16.dp),
                        onClick = {
                             valueExperienceViewModel.sendReview()
                        },
                        colors = ButtonDefaults.elevatedButtonColors(
                            containerColor = OrangeRC
                        )
                    ) {
                        Text(
                            text = "Envia", color = Color.White,
                            style = MaterialTheme.typography.headlineMedium
                        )
                    }

                }
            }
        }
    )

}








@Composable
@Preview(showBackground = true)
fun ValueExperienceGeneralScreenPreview (){
    val navHost = rememberNavController()

    ValueExperienceGeneralScreen(1, navHost)
}