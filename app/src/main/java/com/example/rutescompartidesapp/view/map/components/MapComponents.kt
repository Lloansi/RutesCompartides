package com.example.rutescompartidesapp.view.map.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarColors
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rutescompartidesapp.R
import com.example.rutescompartidesapp.domain.model.Package
import com.example.rutescompartidesapp.view.map.SearchViewModel


var package1 = Package(1, 3, false, 40.5f,256.5f,356.5f,2.5f,"11-04-2024","13-04-2024","Mataró", "Sabadell")
var package2 = Package(2, 2, true, 46.3f,389.2f,189.3f,2.7f,"11-04-2024","13-04-2024","Mataró", "Sabadell")
var package3 = Package(3, 2, true, 49.3f,324.2f,157.3f,2.7f,"11-04-2024","13-04-2024","Mataró", "Sabadell")

var packagesList = mutableListOf(package1, package2,package3,package1,package3, package2)

@Composable
fun CardBottomMap(){
    LazyRow(
        modifier = Modifier.fillMaxWidth()
    ){
        items(packagesList.size) { item ->
            val packageItem = packagesList[item]
            ComandaCard(comanda = packageItem)
        }
    }
}

@Composable
fun ComandaCard(comanda: Package) {
    Card(
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            //.background(color = Color.White)
            .clickable { },
    ) {
        Row (
            modifier =  Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            Column(
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp, start = 20.dp, end = 10.dp)
                    .wrapContentWidth()
            ){
                // Package number
                Text(
                    text = "Comanda nº${comanda.package_id}",
                    style = MaterialTheme.typography.titleMedium,
                    //fontFamily = fredokaOneFamily,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                // SVG Comanda
                Image(
                    painter = painterResource(id = R.drawable.comanda_svg),
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .padding(start = 4.dp)
                        .padding(top = 5.dp, bottom = 5.dp)
                )
                Row {
                    // Icon date
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Date icon",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .padding(top = 7.dp, end = 2.dp)
                            .offset(x = (-2).dp)
                    )
                    Column {
                        Text(
                            text = comanda.package_starting_date,
                            style = MaterialTheme.typography.bodyMedium,
                            //fontFamily = fredokaOneFamily,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = comanda.package_end_date,
                            style = MaterialTheme.typography.bodyMedium,
                            //fontFamily = fredokaOneFamily,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp, start = 10.dp, end = 20.dp)
                    .wrapContentWidth(),
            ) {
                // Profile details
                Text(
                    text = "${comanda.package_start_point} - ${comanda.package_end_point}",
                    style = MaterialTheme.typography.titleMedium,
                    //fontFamily = fredokaOneFamily,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 6.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                //Important package information
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(1.dp)
                        .padding(start = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    SpecsComanda(idImage = R.drawable.packages_svg, value = "${comanda.package_quantity}", imageSize = 35.dp)
                    SpecsComanda(idImage = R.drawable.weight_svg, value = "${comanda.package_weight}kg", imageSize = 27.dp)
                }
                // Measures information
                MeasuresText(icon = Icons.Default.KeyboardArrowRight, typeOfMeasure = "Amplada", value = "${comanda.package_width}")
                MeasuresText(icon = Icons.Default.KeyboardArrowRight, typeOfMeasure = "Longitud", value = "${comanda.package_longitude}")
                MeasuresText(icon = Icons.Default.KeyboardArrowRight, typeOfMeasure = "Altura", value = "${comanda.package_height}")
            }
        }
    }
}

@Composable
fun SpecsComanda(idImage: Int,value: String, imageSize: Dp){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(1.dp)
            .padding(start = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ){
        Image(
            painter = painterResource(id = idImage),
            contentDescription = null,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .size(imageSize)
                .padding(end = 6.dp)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            //fontFamily = fredokaOneFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
        )
    }
}

@Composable
fun MeasuresText(icon: ImageVector,typeOfMeasure:String, value: String) {
    Row(
        modifier = Modifier
            .wrapContentWidth()
            .padding(vertical = 2.dp)
            .padding(start = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            modifier = Modifier
                .size(20.dp),
            imageVector = icon,
            contentDescription = null,
            tint = Color.Black
        )
        Spacer(modifier = Modifier.width(2.dp))
        Text(
            text = "${typeOfMeasure}:",
            style = MaterialTheme.typography.bodySmall,
            //fontFamily = openSansFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(3.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall,
            //fontFamily = openSansFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp
        )
    }
}

@Composable
fun SearchViewContainer() {
    val searchViewModel: SearchViewModel = hiltViewModel()
    Row (modifier = Modifier
        .padding(top = 4.dp)
        .fillMaxWidth()
        .wrapContentHeight() ,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SearchView(searchViewModel)
        NotificationButtonCard()
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView(searchViewModel : SearchViewModel) {

    val searchText by searchViewModel.searchText.collectAsState()
    val isSearching by searchViewModel.isSearching.collectAsState()
    val search by searchViewModel.search.collectAsState()

    SearchBar(
        modifier= Modifier
            .fillMaxWidth(0.80f)
            .height(45.dp)
            .padding(end = 12.dp),
        query = searchText ,
        placeholder = {
            Text(
                text = "Buscar ...")
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                tint = MaterialTheme.colorScheme.onSurface,
                contentDescription = null
            )
        },
        onQueryChange = searchViewModel::onSearchTextChange,
        onSearch = searchViewModel::onSearchTextChange,
        active = isSearching,
        /*
        content = {
            Modifier.size(4.dp)
        },*/
        onActiveChange = { searchViewModel::onToogleSearch }) {

    }

}

@Composable
fun NotificationButtonCard(){
    Box(modifier = Modifier
        .wrapContentWidth()
        .wrapContentHeight()
        .wrapContentHeight()
        //.padding(top = 3.dp)
        .clip(RoundedCornerShape(12.dp))
        .background(color = MaterialTheme.colorScheme.primary)
        .clickable { }
    ){
        Icon(
            imageVector = Icons.Default.Notifications,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .padding(8.dp)
            //.size(24.dp) // icono de dentro
        )
    }
}

/*
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView(state: MutableState<TextFieldValue>, placeHolder: String, modifier: Modifier) {
    TextField(
        value = state.value.text,
        onValueChange = { value ->
            state.value = value
        },
        modifier = Modifier
            .fillMaxWidth(0.75f)
            .padding(top = 20.dp)
            .clip(RoundedCornerShape(10.dp))
            .border(2.dp, Color.DarkGray, RoundedCornerShape(10.dp)),
        placeHolder = placeHolder/*{
            Text(text = placeholder)
        }
        */
        ,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White
        ),
        maxLines = 1,
        singleLine = true,
        textStile = androidx.compose.ui.text.TextStyle(
            color = Color.Black, fontSize = 20.sp
        )
    )
}
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(
            title = {
                Text("Título de la Barra de Búsqueda")
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary),
        )
        // Código visual del resto de la screen
    }
}