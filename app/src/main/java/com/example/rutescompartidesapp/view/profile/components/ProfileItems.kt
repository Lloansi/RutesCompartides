package com.example.rutescompartidesapp.view.profile.components

import com.example.rutescompartidesapp.R
import com.example.rutescompartidesapp.data.domain.ProfileItems


val routeProfileItemsList = listOf(
    ProfileItems(0, "Les meves rutes", "Consulta les teves rutes aquí"),
    ProfileItems(1, "Les meves comandes", "Consulta les teves comandes aquí"),
    ProfileItems(2, "Notificacions", "Personalitza les notificacions al teu gust"),
)

val userProfileItemsList = listOf(
    ProfileItems(0, "Com funciona?", "Consulta el funcionament de l'aplicació"),
    ProfileItems(1, "FAQs i Conceptes claus", "Tens preguntes? Fes un cop d’ull aquí"),
    ProfileItems(2, "Tanca la sessió", "Tanca la sessió de forma segura"),
)

val routeItemsListIcons = listOf(
    R.drawable.my_routes_icon,
    R.drawable.my_orders_icon,
    R.drawable.notification_icon_profile,
)

val userItemsListIcons = listOf(
    R.drawable.comfunciona_icon,
    R.drawable.faqs_icon,
    R.drawable.logout_icon,
)