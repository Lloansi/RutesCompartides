package com.example.rutescompartidesapp.view.profile.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import com.example.rutescompartidesapp.data.domain.ProfileItems

val routeProfileItemsList = listOf(
    ProfileItems(0, Icons.Default.Face, "Les meves rutes", "Consulta les teves rutes aquí"),
    ProfileItems(1, Icons.Default.Face, "Les meves comandes", "Consulta les teves comandes aquí"),
    ProfileItems(2, Icons.Default.Face, "Punts habituals", "Consulta i crea els punts on sols anar"),
    ProfileItems(3, Icons.Default.Face, "Notificacions", "Personalitza les notificacions al teu gust"),
)

val userProfileItemsList = listOf(
    ProfileItems(0, Icons.Default.Face, "Com funciona?", "Consulta el funcionament de l'aplicació"),
    ProfileItems(1, Icons.Default.Face, "FAQs i Conceptes claus", "Tens preguntes? Fes un cop d’ull aquí"),
    ProfileItems(2, Icons.Default.Face, "Tanca la sessió", "Tanca la sessió de forma segura"),
)