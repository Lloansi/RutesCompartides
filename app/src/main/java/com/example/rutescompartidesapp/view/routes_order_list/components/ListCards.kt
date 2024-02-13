package com.example.rutescompartidesapp.view.routes_order_list.components

import android.widget.Space
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ChipColors
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.rutescompartidesapp.data.domain.OrderForList
import com.example.rutescompartidesapp.data.domain.RouteForList
import com.example.rutescompartidesapp.ui.theme.BlueRC
import com.example.rutescompartidesapp.ui.theme.MateBlackRC

@Composable
fun RouteCard(route: RouteForList) {
    Column(){
        ElevatedCard (modifier = Modifier.fillMaxWidth(0.95f)) {
            Row {
                ElevatedCard (modifier = Modifier.fillMaxWidth(),colors = CardDefaults.elevatedCardColors(
                    containerColor = MateBlackRC)
                ) {
                    Column {
                        Spacer(modifier = Modifier.padding(4.dp))
                        //Spacer(modifier = Modifier.padding(4.dp))
                        Row {
                            Spacer(modifier = Modifier.padding(4.dp))
                            Text(text = route.routeName, color = Color.White)
                        }
                        Spacer(modifier = Modifier.padding(4.dp))
                        Row {
                            Spacer(modifier = Modifier.padding(4.dp))
                            Text(text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                ) {
                                    append("Sortida: ")
                                }
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.White
                                    )
                                ) {
                                    append(route.puntSortida)
                                }
                            })
                        }
                        Spacer(modifier = Modifier.padding(4.dp))
                        Row {
                            Spacer(modifier = Modifier.padding(4.dp))
                            Text(text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                ) {
                                    append("Arribada: ")
                                }
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.White
                                    )
                                ) {
                                    append(route.puntArribada)
                                }
                            })
                        }
                        Spacer(modifier = Modifier.padding(4.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.padding(4.dp))
            Row {
                Spacer(modifier = Modifier.padding(4.dp))
                Text(text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("Data sortida: ")
                    }
                    append(route.dataSortida)
                })
            }
            Spacer(modifier = Modifier.padding(4.dp))
            Row {
                Spacer(modifier = Modifier.padding(4.dp))
                LazyRow(modifier = Modifier.weight(2f),
                    content = {
                    route.etiquetes?.forEach { etiqueta ->
                        item {
                            ElevatedAssistChip(onClick = { /*TODO*/ },
                                label = {
                                Text(etiqueta, color = Color.White) },
                                shape = RoundedCornerShape(16.dp),
                                colors = AssistChipDefaults.assistChipColors(
                                    containerColor = BlueRC
                                )
                            )
                        }
                    }
                })
                Spacer(modifier = Modifier.padding(4.dp))
                ElevatedButton( shape = RoundedCornerShape(16.dp),
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.elevatedButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Icon(imageVector = Icons.Filled.Bookmark, contentDescription = "Detail icon",
                        tint = Color.White)
                    Text(text = "Detall", color = Color.White)

                }
                Spacer(modifier = Modifier.padding(4.dp))



            }
            Spacer(modifier = Modifier.padding(4.dp))


        }

    }
}


@Composable
fun OrderCard(order: OrderForList) {
    Column(){
        ElevatedCard (modifier = Modifier.fillMaxWidth(0.95f)) {
            Row {
                ElevatedCard (modifier = Modifier.fillMaxWidth(),colors = CardDefaults.elevatedCardColors(
                    containerColor = MateBlackRC)
                ) {
                    Column {
                        Spacer(modifier = Modifier.padding(4.dp))
                        //Spacer(modifier = Modifier.padding(4.dp))
                        Row {
                            Spacer(modifier = Modifier.padding(4.dp))
                            Text(text = order.orderName, color = Color.White)
                        }
                        Spacer(modifier = Modifier.padding(4.dp))
                        Row {
                            Spacer(modifier = Modifier.padding(4.dp))
                            Text(text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                ) {
                                    append("Sortida: ")
                                }
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.White
                                    )
                                ) {
                                    append(order.puntSortida)
                                }
                            })
                        }
                        Spacer(modifier = Modifier.padding(4.dp))
                        Row {
                            Spacer(modifier = Modifier.padding(4.dp))
                            Text(text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                ) {
                                    append("Arribada: ")
                                }
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.White
                                    )
                                ) {
                                    append(order.puntArribada)
                                }
                            })
                        }
                        Spacer(modifier = Modifier.padding(4.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.padding(4.dp))
            Row {
                Spacer(modifier = Modifier.padding(4.dp))
                Text(text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("Data sortida: ")
                    }
                    append(order.dataSortida)
                })
            }
            Spacer(modifier = Modifier.padding(4.dp))
            Row {
                Spacer(modifier = Modifier.padding(4.dp))
                LazyRow(modifier = Modifier.weight(2f),
                    content = {
                        order.etiquetes?.forEach { etiqueta ->
                            item {
                                ElevatedAssistChip(onClick = { /*TODO*/ },
                                    label = {
                                        Text(etiqueta, color = Color.White) },
                                    shape = RoundedCornerShape(16.dp),
                                    colors = AssistChipDefaults.assistChipColors(
                                        containerColor = BlueRC
                                    )
                                )
                            }
                        }
                    })
                Spacer(modifier = Modifier.padding(4.dp))
                ElevatedButton( shape = RoundedCornerShape(16.dp),
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.elevatedButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Icon(imageVector = Icons.Filled.Bookmark, contentDescription = "Detail icon",
                        tint = Color.White)
                    Text(text = "Detall", color = Color.White)

                }
                Spacer(modifier = Modifier.padding(4.dp))



            }
            Spacer(modifier = Modifier.padding(4.dp))


        }

    }
}

@Composable
fun EmptyResults(type: String) {
    Spacer(modifier = Modifier.padding(0.dp, LocalConfiguration.current.screenHeightDp.dp / 10))
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center) {
            Text(text = "No s'ha trobat cap $type", style = MaterialTheme.typography.headlineLarge)
        }
    }
}
