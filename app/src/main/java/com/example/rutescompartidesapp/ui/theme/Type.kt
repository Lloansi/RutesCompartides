package com.example.rutescompartidesapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.rutescompartidesapp.R

// Fonts

val fredokaOne = FontFamily(
        Font(R.font.fredokaone, FontWeight.Normal)
)

val openSans = FontFamily(
        Font(R.font.opensans, FontWeight.Normal)
)

// Set of Material typography styles
val Typography = Typography(
        displayLarge = TextStyle(
                fontFamily = fredokaOne,
                fontWeight = FontWeight.Normal,
                fontSize = 34.sp,
                lineHeight = 40.sp,
                letterSpacing = 0.sp
        ),
        displayMedium = TextStyle(
                fontFamily = fredokaOne,
                fontWeight = FontWeight.Normal,
                fontSize = 28.sp,
                lineHeight = 34.sp,
                letterSpacing = 0.sp
        ),
        displaySmall = TextStyle(
                fontFamily = fredokaOne,
                fontWeight = FontWeight.Normal,
                fontSize = 22.sp,
                lineHeight = 28.sp,
                letterSpacing = 0.sp
        ),
        headlineLarge = TextStyle(
                fontFamily = openSans,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                lineHeight = 28.sp,
                letterSpacing = 0.sp
        ),
        headlineMedium = TextStyle(
                fontFamily = openSans,
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.sp
        ),
        headlineSmall = TextStyle(
                fontFamily = openSans,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 22.sp,
                letterSpacing = 0.sp
        ),

        bodyLarge = TextStyle(
                fontFamily = openSans,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.5.sp
        ),
        bodyMedium = TextStyle(
                fontFamily = openSans,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                lineHeight = 22.sp,
                letterSpacing = 0.5.sp
        ),
        bodySmall = TextStyle(
                fontFamily = openSans,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                lineHeight = 20.sp,
                letterSpacing = 0.5.sp
        ),
        titleLarge = TextStyle(
                fontFamily = fredokaOne,
                fontWeight = FontWeight.Normal,
                fontSize = 22.sp,
                lineHeight = 28.sp,
                letterSpacing = 0.sp
        ),
        titleMedium = TextStyle(
                fontFamily = fredokaOne,
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.sp
        ),
        titleSmall = TextStyle(
                fontFamily = fredokaOne,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                lineHeight = 22.sp,
                letterSpacing = 0.sp
        ),
        labelLarge = TextStyle(
                fontFamily = openSans,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                letterSpacing = 0.5.sp
        ),
        labelMedium = TextStyle(
                fontFamily = openSans,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp,
                lineHeight = 18.sp,
                letterSpacing = 0.5.sp
        ),
        labelSmall = TextStyle(
                fontFamily = openSans,
                fontWeight = FontWeight.Medium,
                fontSize = 11.sp,
                lineHeight = 16.sp,
                letterSpacing = 0.5.sp
        )

)