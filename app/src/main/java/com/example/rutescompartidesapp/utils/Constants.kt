package com.example.rutescompartidesapp.utils

import android.Manifest
import com.example.rutescompartidesapp.data.domain.User2

object Constants {

    val userList = mutableListOf<User2>(User2(0, "Admin", "admin@admin.com", 666666666, "Admin"))
    const val RUTES_COMPARTIDES_URL = "https://rutescompartides.cat/"

    val ALL_PERMISSIONS = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.INTERNET,
        Manifest.permission.ACCESS_NETWORK_STATE
    )

    val messages2 = listOf(
        "Hola, ¿cómo estás?",
        "¡Hola! Estoy bien, ¿y tú?",
        "Muy bien, gracias.",
        "Eso es genial.",
        "Sí, ¿quieres tomar un café más tarde?",
        "¡Claro! Nos vemos en el café a las 3pm.",
        "¿Cómo te fue en el trabajo hoy?",
        "Bien, fue un día ocupado pero productivo.",
        "¿Qué opinas de la nueva película que salió?",
        "Me encantó, definitivamente deberías verla.",
        "Estoy de acuerdo, ¡vamos juntos al cine!",
        "¿Has probado el nuevo restaurante del centro?",
        "Sí, la comida es increíble.",
        "Deberíamos organizar una cena allí pronto.",
        "¡Definitivamente!",
        "¿Qué piensas hacer este fin de semana?",
        "Todavía no tengo planes, ¿tienes alguna idea?",
        "Podríamos hacer una barbacoa en el jardín.",
        "¡Eso suena genial!",
        "¿Qué opinas sobre el cambio de clima últimamente?",
        "Es un poco impredecible, ¿no crees?",
        "Sí, deberíamos prepararnos para cualquier cosa.",
        "¿Has leído algún buen libro últimamente?",
        "Sí, te recomendaré algunos.",
        "¡Gracias, siempre estoy buscando nuevas lecturas!",
        "¿Has escuchado la nueva canción de tu banda favorita?",
        "No todavía, ¿cómo es?",
        "Es increíble, ¡deberías escucharlo!",
        "Lo haré, gracias por la recomendación.",
        "¿Tienes planes para las vacaciones de verano?",
        "Sí, planeo ir a la playa.",
        "¡Qué suena relajante!",
        "¿Te gustaría venir?",
        "¡Claro! Sería divertido.",
        "¿Qué piensas hacer después de la universidad?",
        "Todavía no estoy seguro, ¿tienes alguna sugerencia?",
        "Podrías considerar hacer una maestría.",
        "Eso es algo que definitivamente estoy considerando.",
        "¿Has visto la última serie de televisión que está de moda?",
        "No todavía, ¿de qué se trata?",
        "Es un drama emocionante, creo que te gustaría.",
        "Gracias, definitivamente la veré.",
        "¿Has estado siguiendo las noticias recientemente?",
        "Sí, es importante mantenerse informado.",
        "Estoy de acuerdo, siempre es bueno estar al tanto de lo que está sucediendo.",
        "¿Tienes alguna meta para este año?",
        "Sí, quiero aprender a tocar un instrumento musical.",
        "¡Eso suena emocionante!",
        "¿Cuál instrumento te gustaría aprender?",
        "Probablemente la guitarra.",
        "¡Buena elección! Es divertido de tocar.",
        "¿Tienes algún hobby o pasatiempo que te apasione?",
        "Me encanta cocinar y probar nuevas recetas.",
        "¡Qué genial! Deberías invitarme a cenar un día.",
        "¡Por supuesto! Sería un placer.",
        "¿Cómo te va en tus clases últimamente?",
        "Muy bien, gracias por preguntar.",
        "Me alegro de escuchar eso.",
        "¿Qué opinas de la situación política actual?",
        "Es un tema complicado, ¿verdad?"
    )


}