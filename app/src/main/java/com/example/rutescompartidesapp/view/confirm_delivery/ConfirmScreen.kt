package com.example.rutescompartidesapp.view.confirm_delivery

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.rutescompartidesapp.view.confirm_delivery.viewmodel.CameraViewModel
import com.example.rutescompartidesapp.view.confirm_delivery.viewmodel.DrawViewModel
import com.example.rutescompartidesapp.view.map.fredokaOneFamily


@Composable
fun ConfirmScreen(navController: NavHostController, cameraViewModel: CameraViewModel, drawViewModel: DrawViewModel) {
    val bitmapPhoto by cameraViewModel.bitmapPhoto.collectAsState()
    val bitmapDraw by drawViewModel.drawBitmap.collectAsState()
    val responsiveHeight = LocalConfiguration.current.screenHeightDp.dp
    val responsiveWidth = LocalConfiguration.current.screenWidthDp.dp
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = responsiveHeight / 40,
                bottom = responsiveHeight / 40,
                start = responsiveWidth / 20,
                end = responsiveWidth / 20
            ),
        //color = Color.White
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ConfirmCard(responsiveHeight, navController, bitmapPhoto, bitmapDraw)
        }
    }
}

@Composable
fun ConfirmCard(responsiveHeight: Dp, navController: NavHostController,  bitmap1: Bitmap?,  bitmap2: Bitmap?) {
    Card (
        modifier = Modifier
            .fillMaxWidth(),
    ){
        // Card
        Spacer(modifier = Modifier.height(responsiveHeight/50))

        Column (
            modifier = Modifier
                .padding()
                .padding(
                    start = LocalConfiguration.current.screenWidthDp.dp / 15,
                    end = LocalConfiguration.current.screenWidthDp.dp / 15
                )
        ){
            Column (
                modifier = Modifier
                    .padding()
                    .padding(bottom = responsiveHeight / 90)
            ){
                Text(text = "Foto de l'entrega (opcional)", fontFamily = fredokaOneFamily)
                Spacer(modifier = Modifier.height(responsiveHeight/90))
                UploadImageOrSignature(icon = Icons.Filled.Upload, navController = navController, destination = "CameraScreen", bitmap = bitmap1)
                Spacer(modifier = Modifier.height(responsiveHeight/40))
                Text(text = "Signatura del/de la receptor/a (opcional) ", fontFamily = fredokaOneFamily)
                Spacer(modifier = Modifier.height(responsiveHeight/90))
                UploadImageOrSignature(icon = Icons.Filled.Upload, navController = navController, destination = "DrawScreen", bitmap = bitmap2)
                Spacer(modifier = Modifier.height(responsiveHeight/40))
            }
            Column {
                Text(text = "Comentaris")
                UserCommentContainer()
                Spacer(modifier = Modifier.height(responsiveHeight/40))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding()
                        .padding(bottom = responsiveHeight / 40),
                    horizontalArrangement = Arrangement.Center
                ){
                    ExtendedFloatingActionButton(
                        containerColor = MaterialTheme.colorScheme.primary,
                        onClick = { /*TODO*/ }
                    ) {
                        Text(text = "Confirmar entrega")
                    }
                }
            }
        }
    }
}

@Composable
fun UploadImageOrSignature(icon : ImageVector, navController: NavHostController, destination: String , bitmap: Bitmap?){
    Row (
        verticalAlignment = Alignment.CenterVertically
    ){
        FloatingActionButton(
            onClick = { navController.navigate(route = destination) },
            containerColor = MaterialTheme.colorScheme.primary,
            /*
            modifier = Modifier
                .width(LocalConfiguration.current.screenWidthDp.dp / 8)
                .height(LocalConfiguration.current.screenHeightDp.dp / 18)

             */
        ) {
            Icon(imageVector = icon, contentDescription = "Upload")
        }
        Spacer(modifier = Modifier.width(LocalConfiguration.current.screenWidthDp.dp/75))

        if (bitmap != null){
            Text(text = "Foto seleccionada correctament")

            /*
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = "Bitmap from previous screen")

             */

            /*
            In summary, use asImageBitmap() when you have a local resource or asset
            that you want to display as a bitmap directly in your Compose UI.
            Use Coil's AsyncImage when you need to load images asynchronously from URLs,
            especially for remote images, and when you need features such as caching,
            error handling, and transformations.
             */

        } else{
            Text(text = "No s'ha seleccionat cap arxiu")
        }
    }
}

@Composable
fun UserCommentContainer() {
    var userComment by rememberSaveable() { mutableStateOf("") }

    TextField(
        value = userComment ,
        onValueChange = {
           userComment = it
        },
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.3f),
        label = { Text(color = Color.Gray ,text = "Escriu aquí el teu comentari aquí ...") },
        singleLine = false,
        maxLines = 4,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            disabledContainerColor = MaterialTheme.colorScheme.surface,
            cursorColor = MaterialTheme.colorScheme.primary,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        )
    )
}


@Composable
fun OrangeLine(responsiveHeight: Dp) {
    Divider(
        color = MaterialTheme.colorScheme.primary,
        thickness = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = responsiveHeight / 90, end = responsiveHeight / 90)
    )
}