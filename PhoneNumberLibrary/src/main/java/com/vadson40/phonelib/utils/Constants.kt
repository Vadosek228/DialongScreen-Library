package com.vadson40.phonelib.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

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

internal object Constants {
    const val EMPTY = ""
    const val SPACE = " "
}