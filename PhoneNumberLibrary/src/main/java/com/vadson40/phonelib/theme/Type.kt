package com.vadson40.phonelib.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

/**
 * Стайл теста для кнопки по умолчанию.
 */
@Composable
internal fun defaultTextStyle() = TextStyle(
    color = MaterialTheme.colorScheme.onBackground,
    fontSize = 34.sp,
    lineHeight = 42.sp,
    fontFamily = FontFamily.SansSerif,
    fontWeight = FontWeight(500),
    textAlign = TextAlign.Center,
    letterSpacing = 0.5.sp
)