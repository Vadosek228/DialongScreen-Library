package com.vadson40.phonelib.utils

import com.vadson40.phonelib.components.buttons.icons.DialPadIconButtonVO

/**
 * Дефолтные кнопки ввода номера на экране.
 */
internal fun defaultNumberList(): List<String> {
    return listOf(
        "1", "2", "3",
        "4", "5", "6",
        "7", "8", "9",
        "*", "0", "#"
    )
}

/**
 * Дефолтные кнопки для совершения звонка и удаления предыдущего символа.
 */
internal fun defaultIconButtonsList(): List<DialPadIconButtonVO?> {
    return listOf(
        null,
        DialPadIconButtonVO.call,
        DialPadIconButtonVO.delete
    )
}

internal object Constants {
    const val EMPTY = ""
    const val SPACE = " "
}