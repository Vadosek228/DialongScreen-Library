package com.vadson40.phonelib.components.textfields

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vadson40.phonelib.theme.PhoneDialPadLibraryTheme
import com.vadson40.phonelib.theme.defaultTextStyle
import com.vadson40.phonelib.utils.DisableSoftKeyboard
import com.vadson40.phonelib.utils.PhoneMaskTransformation

/**
 * Редактируемое поле ввода телефонного номера.
 * Поддерживает операции выделения, вырезать, вставка, работку с курсором.
 *
 * @param modifier - модификатор для поля ввода
 * @param value - текст для установки в поле ввода
 * @param onValueChange - возвращает измененный текст
 * @param textStyle - стиль текста для поля ввода
 * @param setInitFocus - установить ли фокус по умолчанию на поле ввода
 * @param visualTransformation - визуальная трансформация введенного текста
 * @param useDefaultVisualTransformation - использовать дефолтную визуальную трансформацию текста
 * @param cursorBrush - стайл для курсора
 */
@SuppressLint("RememberInComposition")
@Composable
internal fun DialPadPhoneInputField(
    modifier: Modifier = Modifier,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    textStyle: TextStyle? = null,
    setInitFocus: Boolean = true,
    visualTransformation: VisualTransformation? = null,
    useDefaultVisualTransformation: Boolean = true,
    cursorBrush: SolidColor? = null
) {
    val focusRequest = FocusRequester()
    LaunchedEffect(Unit) {
        if (setInitFocus) {
            focusRequest.requestFocus()
        }
    }

    DisableSoftKeyboard(
        disable = true
    ) {
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
                .padding(horizontal = 16.dp)
                .background(Color.Transparent)
                .then(modifier)
                .focusRequester(focusRequest)
                .onFocusChanged { focusState ->
                    if (!focusState.isFocused)
                        focusRequest.requestFocus()
                },
            value = value,
            onValueChange = { newValue ->
                onValueChange(newValue)
            },
            textStyle = textStyle ?: defaultTextStyle(),
            cursorBrush = cursorBrush ?: SolidColor(MaterialTheme.colorScheme.onSurfaceVariant),
            visualTransformation = visualTransformation
                ?: if (useDefaultVisualTransformation) PhoneMaskTransformation else VisualTransformation.None,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            )
        )
    }
}

@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Light", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Composable
private fun DialPadPhoneInputFieldPreview() {
    PhoneDialPadLibraryTheme {
        val textFieldValue = TextFieldValue("89009099099")
        DialPadPhoneInputField(
            value = textFieldValue,
            onValueChange = {}
        )
    }
}