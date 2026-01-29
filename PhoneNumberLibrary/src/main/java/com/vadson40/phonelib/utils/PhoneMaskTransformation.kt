package com.vadson40.phonelib.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

/**
 * Объект для визуальной трансформации текста в формат номера телефона.
 *
 * @author Akulinin Vladislav
 * @since 29.01.2026
 */
object PhoneMaskTransformation : VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {
        val hasPlus = text.text.startsWith('+')
        var newString = Constants.EMPTY
        text.text.indices.forEach { index ->
            if (!hasPlus && index in setOf(1,4,7,9)) newString += Constants.SPACE
            if (hasPlus && index in setOf(2,5,8,10)) newString += Constants.SPACE
            newString += text.text[index]
        }

        val addSymbolToTheNumber = if (hasPlus) 1 else 0
        val phoneNumberOffsetTranslator = object : OffsetMapping {

            override fun originalToTransformed(offset: Int): Int =
                when (offset) {
                    in 0..1 + addSymbolToTheNumber -> offset
                    in 2 + addSymbolToTheNumber.. 4 + addSymbolToTheNumber -> offset + 1
                    in 5 + addSymbolToTheNumber.. 7 + addSymbolToTheNumber -> offset + 2
                    in 8 + addSymbolToTheNumber.. 9 + addSymbolToTheNumber -> offset + 3
                    else -> offset + 4
                }

            override fun transformedToOriginal(offset: Int): Int =
                when (offset) {
                    in 0..2 + addSymbolToTheNumber -> offset
                    in 3 + addSymbolToTheNumber.. 5 + addSymbolToTheNumber -> offset - 1
                    in 6 + addSymbolToTheNumber.. 9 + addSymbolToTheNumber -> offset - 2
                    in 11 + addSymbolToTheNumber.. 12 + addSymbolToTheNumber -> offset - 3
                    else -> offset - 4
                }
        }

        return TransformedText(AnnotatedString(newString), phoneNumberOffsetTranslator)
    }
}