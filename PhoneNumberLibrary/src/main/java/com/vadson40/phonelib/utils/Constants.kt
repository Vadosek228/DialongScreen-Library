package com.vadson40.phonelib.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.vadson40.phonelib.components.buttons.icons.DialPadIconButtonVO

/**
 * Дефолтные кнопки ввода номера на экране.
 */
@Composable
internal fun defaultNumberList(): List<String> {
    return remember {
        listOf(
            "1", "2", "3",
            "4", "5", "6",
            "7", "8", "9",
            "*", "0", "#"
        )
    }
}

/**
 * Дефолтные кнопки для совершения звонка и удаления предыдущего символа.
 */
@Composable
internal fun defaultIconButtonsList(): List<DialPadIconButtonVO?> {
    return remember {
        listOf(
            null,
            DialPadIconButtonVO.call,
            DialPadIconButtonVO.delete
        )
    }
}

internal object Constants {
    const val EMPTY = ""
    const val SPACE = " "
}