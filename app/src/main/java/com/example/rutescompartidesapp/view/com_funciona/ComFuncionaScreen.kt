package com.example.rutescompartidesapp.view.com_funciona

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v4.media.MediaBrowserCompat
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import com.example.rutescompartidesapp.R
import com.example.rutescompartidesapp.ui.theme.MateBlackRC
import com.example.rutescompartidesapp.ui.theme.fredokaOne
import com.example.rutescompartidesapp.ui.theme.openSans
import com.example.rutescompartidesapp.view.generic_components.BackButtonArrow
import com.example.rutescompartidesapp.view.generic_components.HeaderSphere
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun ComFuncionaScreen(navController: NavHostController) {

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .height(200.dp)
        ) {
            HeaderSphere(height = 200.dp)

            BackButtonArrow(
                navController = navController,
                alignment = Alignment.TopStart,
                "ProfileScreen"
            )

            Text(
                modifier = Modifier
                    .align(Alignment.Center),
                text = "Com funciona?",
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = fredokaOne
            )
        }
        Spacer(modifier = Modifier.size(height = 20.dp, width = 0.dp))
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            YouTubePlayer(
                youtubeVideoId = "85IINPA_igE",
                lifecycleOwner = LocalLifecycleOwner.current
            )
            Description()
            RRSSButtons()
            Spacer(modifier = Modifier.size(height = 20.dp, width = 0.dp))
        }
        Spacer(modifier = Modifier.size(height = 20.dp, width = 0.dp))

    }

}

@Composable
fun YouTubePlayer(
    youtubeVideoId: String,
    lifecycleOwner: LifecycleOwner
) {
    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp)
            .clip(RoundedCornerShape(15.dp)),
        factory = { context ->
            YouTubePlayerView(context = context).apply {
                lifecycleOwner.lifecycle.addObserver(this)

                addYouTubePlayerListener(
                    object : AbstractYouTubePlayerListener() {
                        override fun onReady(youTubePlayer: YouTubePlayer) {
                            youTubePlayer.loadVideo(youtubeVideoId, 0f)
                        }
                    }
                )
            }

        },
    )
}

@Composable
fun Description() {
    val description =
        "RutesCompartides és una eina per a compartir rutes de distribució entre productores. Així es minimitzen els costos econòmics, els impactes mediambientals i el temps que cada persona ha de dedicar al transport.\n" +
                "\n" +
                "A l'estil de com es fa per compartir cotxe, qui ja fa rutes de distribució les pot penjar aquí (i així omplir al màxim el seu vehicle) i qui vol fer arribar una comanda també la pot penjar aquí (i així trobar una ruta on sumar-se i evitar haver de fer la ruta expressament o requerir un servei de paqueteria).\n" +
                "\n" +
                "I tot això amb opcions de rutes regulars, punts intermitjos, albarans, confirmació d'entrega, informe de ruta, xat intern i molt més. Més informació a FAQs.\n" +
                "\n" +
                "RutesCompartides és una eina creada des de l'economia social i solidària, sense ànim de lucre, amb programari lliure i amb governança oberta. Si hi vols participar més activament, escriu-nos per:"

    Text(
        text = description,
        fontFamily = openSans,
        fontWeight = FontWeight.SemiBold,
        color = if (isSystemInDarkTheme()) Color.White else MateBlackRC,
        fontSize = 14.5.sp,
        modifier = Modifier
            .padding(20.dp)
    )
}

@Composable
fun RRSSButtons() {

    val ctx = LocalContext.current
    val mUriHandler = LocalUriHandler.current

    val annotatedEmailText = buildAnnotatedString {
        pushStringAnnotation(tag = "email", annotation = "mailto:rutescompartides@riseup.net")
        withStyle(style = SpanStyle(color = if (isSystemInDarkTheme()) Color.White else MateBlackRC)) {
            append("rutescompartides@riseup.net")
        }
        pop()
    }

    val annotatedTelegramString = buildAnnotatedString {
        pushStringAnnotation(
            tag = "telegram",
            annotation = "https://t.me/joinchat/-DCYs7jkCFRiOWY5"
        )
        withStyle(style = SpanStyle(color = if (isSystemInDarkTheme()) Color.White else MateBlackRC)) {
            append("Grup de Telegram")
        }
        pop()
    }

    Column(
        modifier = Modifier
            .padding(start = 20.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.email_icon_comfunciona),
                contentDescription = null,
                modifier = Modifier
                    .width(30.dp)
                    .height(30.dp)
                    .padding(end = 8.dp)
            )
            ClickableText(
                text = annotatedEmailText,
                style = TextStyle(
                    color = if (isSystemInDarkTheme()) Color.White else MateBlackRC,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = openSans,
                    fontSize = 13.sp
                ),
                onClick = { offset ->
                    annotatedEmailText.getStringAnnotations(
                        tag = "email",
                        start = offset,
                        end = offset
                    ).firstOrNull()?.let {
                        ctx.sendMail()
                    }
                })
        }
        Spacer(modifier = Modifier.size(height = 5.dp, width = 0.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.telegram_icon_comfunciona),
                contentDescription = null,
                modifier = Modifier
                    .width(30.dp)
                    .height(30.dp)
                    .padding(end = 8.dp)
            )
            ClickableText(
                text = annotatedTelegramString,
                style = TextStyle(
                    color = if (isSystemInDarkTheme()) Color.White else MateBlackRC,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = openSans,
                    fontSize = 13.sp
                ),
                onClick = { offset ->
                    annotatedTelegramString.getStringAnnotations(
                        tag = "telegram",
                        start = offset,
                        end = offset
                    ).firstOrNull()?.let {
                        mUriHandler.openUri(it.item)
                    }
                })
        }
    }
}

fun Context.sendMail() {
    try {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "vnd.android.cursor.item/email"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("rutescompartides@riseup.net"))
        startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        println("PRIMER ERROR$e")
    } catch (t: Throwable) {
        println("SEGUNDO ERROR$t")
    }
}