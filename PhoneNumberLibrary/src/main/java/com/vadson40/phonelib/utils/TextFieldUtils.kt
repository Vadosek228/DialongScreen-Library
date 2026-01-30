package com.vadson40.phonelib.utils

import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue

/**
 * Добавить символ по текущему расположению курсора.
 */
fun TextFieldValue.addSymbolAtTheCursorPosition(charToInsert: String): TextFieldValue {
    val cursorPosition = selection.end
    val newText = buildString {
        append(text.take(cursorPosition))
        append(charToInsert)
        append(text.drop(cursorPosition))
    }
    return copy(text = newText, selection = TextRange(cursorPosition + 1))
}

/**
 * Удалить символ по текущему расположению курсора.
 */
fun TextFieldValue.deleteSymbolAtTheCursorPosition(): TextFieldValue {
    val cursorPosition = selection.start
    if (cursorPosition > 0) {
        val newText = text.removeRange(cursorPosition - 1, cursorPosition)
        val newCursorPosition = if (newText.isNotEmpty() && cursorPosition == 1) cursorPosition else cursorPosition - 1
        return TextFieldValue(text = newText, selection = TextRange(newCursorPosition))
    }
    return this
}

/**
 * Убрать символы, которые не соответствуют бизнеслогики.
 */
fun TextFieldValue.removeUnnecessarySymbolFromPhoneNumber(): TextFieldValue {
    val cleanedText = text.filterIndexed { index, c ->
        when {
            c == '+' && index != 0 -> false
            c in setOf('*', '#', '+') || c.isDigit() -> true
            else -> false
        }
    }
    return copy(text = cleanedText)
}