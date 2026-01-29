package com.vadson40.phonelib.components.buttons.icons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.vadson40.phonelib.utils.Image
import com.vadson40.phonelib.R

/**
 * Описывает данные для кнопки с иконкой.
 *
 * @param icon - [Image] для иконки
 * @param indicatorColor - цвет индикатора нажатия на кнопку
 */
data class DialPadIconButtonVO(
    val icon: Image,
    val indicatorColor: Color? = null
) {

    @Composable
    internal fun indicatorColorGetColorOrDefault() =
        indicatorColor ?: MaterialTheme.colorScheme.onSurface

    companion object {

        val call = DialPadIconButtonVO(
            icon = Image.IconRes(
                res = R.drawable.ic_phone,
                contentDescription = "Кнопка вызова",
                tint = null
            ),
            indicatorColor = null
        )

        val delete = DialPadIconButtonVO(
            icon = Image.IconVector(
                icon = Icons.Outlined.Delete,
                contentDescription = "Кнопка удаления",
                tint = null
            ),
            indicatorColor = null
        )
    }
}