package com.vadson40.phonelib.utils

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

/**
 * Запуатанный класс для работы с иконками.
 */
sealed class Image {
    @Immutable
    class IconRes(
        @param:DrawableRes val res: Int,
        val tint: Color?,
        val contentDescription: String?
    ) : Image()

    @Immutable
    class IconVector(
        val icon: ImageVector,
        val tint: Color?,
        val contentDescription: String?
    ) : Image()
}

@Stable
@Composable
fun Icon(
    icon: Image,
    modifier: Modifier
) = when (icon) {
    is Image.IconRes -> Icon(icon, modifier)
    is Image.IconVector -> Icon(icon, modifier)
}

@Stable
@Composable
private fun Icon(
    icon: Image.IconRes,
    modifier: Modifier
) {
    Icon(
        painter = painterResource(icon.res),
        contentDescription = icon.contentDescription,
        modifier = modifier,
        tint = icon.tint ?: Color.Unspecified
    )
}

@Stable
@Composable
private fun Icon(
    icon: Image.IconVector,
    modifier: Modifier
) {
    Icon(
        imageVector = icon.icon,
        contentDescription = icon.contentDescription,
        modifier = modifier,
        tint = icon.tint ?: MaterialTheme.colorScheme.onBackground
    )
}

