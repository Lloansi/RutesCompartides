package com.example.rutescompartidesapp.view.confirm_delivery

import android.graphics.Bitmap
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.rutescompartidesapp.ui.theme.GrayRC
import com.example.rutescompartidesapp.view.confirm_delivery.viewmodel.CameraViewModel
import com.example.rutescompartidesapp.view.confirm_delivery.viewmodel.DrawViewModel
import com.example.rutescompartidesapp.view.generic_components.RouteOrderHeader
import com.example.rutescompartidesapp.view.map.fredokaOneFamily
import com.example.rutescompartidesapp.view.route_detail.route_detail_driver.RouteDetailDriverViewModel
import kotlinx.coroutines.flow.collectLatest


@Composable
fun ConfirmScreen(
    routeDetailDriverViewModel : RouteDetailDriverViewModel,
    cameraViewModel: CameraViewModel, drawViewModel: DrawViewModel) {
    val bitmapPhoto by cameraViewModel.bitmapPhoto.collectAsStateWithLifecycle()
    val bitmapDraw by drawViewModel.drawBitmap.collectAsStateWithLifecycle()

    val route by routeDetailDriverViewModel.route.collectAsStateWithLifecycle()
    val order by routeDetailDriverViewModel.order.collectAsStateWithLifecycle()

    val context = LocalContext.current

    // Toasts per notificar a l'usuari que es guarda correctament la firma i la foto
    // Firma
    LaunchedEffect(key1 = drawViewModel.showSuccessToastChannel){
        drawViewModel.showSuccessToastChannel.collectLatest { successToast ->
            if (successToast){
                Toast.makeText(context, "Firma guardada correctament", Toast.LENGTH_SHORT).show()
            }
        }
    }
    // Foto
    LaunchedEffect(key1 = cameraViewModel.showSuccessToastChannel){
        cameraViewModel.showSuccessToastChannel.collectLatest { successToast ->
            if (successToast){
                Toast.makeText(context, "Foto guardada correctament", Toast.LENGTH_SHORT).show()
            }
        }
    }

    ElevatedCard (
        modifier = Modifier
            .fillMaxHeight()
            .padding(start = 8.dp, end = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ){Row {
        RouteOrderHeader(route = route!!, order!!)
    }
        Column (
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp, top = 8.dp)
        ){
            Text(text = "Foto de l'entrega (opcional)", fontFamily = fredokaOneFamily)
            Spacer(modifier = Modifier.padding(6.dp))
            UploadImageOrSignature(icon = Icons.Filled.Upload, destination = "CameraScreen"
                , bitmap = bitmapPhoto, cameraViewModel = cameraViewModel, drawViewModel = drawViewModel)
            Spacer(modifier = Modifier.padding(8.dp))
            Text(text = "Signatura del/de la receptor/a (opcional) ", fontFamily = fredokaOneFamily)
            Spacer(modifier = Modifier.padding(6.dp))
            UploadImageOrSignature(icon = Icons.Filled.Upload, destination = "DrawScreen"
                , bitmap = bitmapDraw, cameraViewModel = cameraViewModel, drawViewModel = drawViewModel)
            Spacer(modifier = Modifier.padding(8.dp))
            Text(text = "Comentaris", fontFamily = fredokaOneFamily)
            Spacer(modifier = Modifier.padding(6.dp))
            UserCommentContainer(routeDetailDriverViewModel)
            Spacer(modifier = Modifier.padding(8.dp))
        }
    } // END CARD
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalArrangement = Arrangement.Center
    ){
        ElevatedButton(
            colors = ButtonDefaults.elevatedButtonColors(
                contentColor = MaterialTheme.colorScheme.secondary,
                containerColor = MaterialTheme.colorScheme.primary
            ),
            contentPadding = PaddingValues(16.dp),
            onClick = {
                routeDetailDriverViewModel.completeOrder()
            }
        ) {
            Text(text = "Confirmar entrega",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Composable
fun UploadImageOrSignature(
    icon : ImageVector,
    destination: String , bitmap: Bitmap?,
    cameraViewModel: CameraViewModel, drawViewModel: DrawViewModel){

    Row (
        verticalAlignment = Alignment.CenterVertically
    )
    {
        FloatingActionButton(
            onClick = {
                if (destination == "CameraScreen"){
                    cameraViewModel.onCameraActive(isActive = true)
                } else if (destination == "DrawScreen") {
                    drawViewModel.onSignatureActive(isActive = true)
                }
                      },
            containerColor = MaterialTheme.colorScheme.primary,
            /*
            modifier = Modifier
                .width(LocalConfiguration.current.screenWidthDp.dp / 8)
                .height(LocalConfiguration.current.screenHeightDp.dp / 18)

             */
        ) {
            Icon(imageVector = icon, contentDescription = "Upload")
        }
        Spacer(modifier = Modifier.padding(6.dp))

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
fun UserCommentContainer(routeDetailDriverViewModel: RouteDetailDriverViewModel) {
    val userComment by routeDetailDriverViewModel.userComment.collectAsStateWithLifecycle()

    OutlinedTextField(
        value = userComment ,
        onValueChange = { routeDetailDriverViewModel.setUserComment(it) },
        modifier = Modifier
            .fillMaxWidth()
            .height(LocalConfiguration.current.screenHeightDp.dp * 0.22f),
        placeholder = { Text(color = Color.Gray ,text = "Escriu aquí el teu comentari") },
        singleLine = false,
        maxLines = 4,
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
        )
    )
}