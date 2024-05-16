package com.example.rutescompartidesapp.view.how_it_works.components

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.rutescompartidesapp.ui.theme.MateBlackRC
import com.example.rutescompartidesapp.ui.theme.openSans

/**
 * Composable function for displaying clickable text with links.
 *
 * @param annotatedString The annotated string containing the text with links.
 * @param tag The tag identifying the type of link (e.g., "telegram" or "email").
 */
@Composable
fun TextLink(annotatedString: AnnotatedString, tag: String){

    val ctx = LocalContext.current
    val mUriHandler = LocalUriHandler.current

    ClickableText(
        text = annotatedString,
        style = TextStyle(
            color = if (isSystemInDarkTheme()) Color.White else MateBlackRC,
            fontWeight = FontWeight.SemiBold,
            fontFamily = openSans,
            fontSize = 13.sp
        ),
        onClick = { offset ->
            annotatedString.getStringAnnotations(
                tag = tag,
                start = offset,
                end = offset
            ).firstOrNull()?.let {
                if (tag == "telegram"){
                    mUriHandler.openUri(it.item)
                } else {
                    ctx.sendMail()
                }
            }
        })
}

/**
 * Sends an email using the default email client.
 */
private fun Context.sendMail() {
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