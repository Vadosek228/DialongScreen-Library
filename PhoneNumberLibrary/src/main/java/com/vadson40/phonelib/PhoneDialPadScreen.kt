package com.vadson40.phonelib

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vadson40.phonelib.components.buttons.icons.DialPadIconButton
import com.vadson40.phonelib.components.buttons.icons.DialPadIconButtonVO
import com.vadson40.phonelib.components.buttons.numbers.DialPadButton
import com.vadson40.phonelib.components.textfields.DialPadPhoneInputField
import com.vadson40.phonelib.theme.PhoneDialPadLibraryTheme
import com.vadson40.phonelib.utils.defaultIconButtonsList
import com.vadson40.phonelib.utils.defaultNumberList

/**
 * Состояние поля ввода телефонного номера.
 *
 * @param modifier - модификатор
 * @param textStyle - стиль текста
 * @param setInitFocus - установить первоначальный фокус на элементе
 * @param visualTransformation - визуальная трансформация телефонного номера
 * @param useDefaultVisualTransformation - применить визуальную трансформацию телефонного номера по умолчанию
 * @param cursorBrush - стиль курсора
 */
data class PhoneInputFieldState(
    val modifier: Modifier = Modifier,
    val textStyle: TextStyle? = null,
    val setInitFocus: Boolean = true,
    val visualTransformation: VisualTransformation? = null,
    val useDefaultVisualTransformation: Boolean = true,
    val cursorBrush: SolidColor? = null
) {
    companion object {
        val Default = PhoneInputFieldState(
            modifier = Modifier,
            textStyle = null,
            setInitFocus = true,
            visualTransformation = null,
            useDefaultVisualTransformation = true,
            cursorBrush = null
        )
    }
}

/**
 * Состояние для грида с числовыми или симвоьными кнопками.
 *
 * @param modifier - модификатор для кнопок
 * @param list - список кнопок с символами, по умолчанию null и примениться дефолтные
 * @param background - задний фон для каждой кнопки
 * @param indicatorColor - цвет индикатора нажатия для каждой кнопки
 * @param textStyle - стиль текста для кнопки
 */
data class ButtonNumberListState(
    val modifier: Modifier = Modifier,
    val list: List<String>? = null,
    val background: Color? = null,
    val indicatorColor: Color? = null,
    val textStyle: TextStyle? = null,
) {
    companion object {
        val Default = ButtonNumberListState(
            modifier = Modifier,
            list = null,
            background = null,
            indicatorColor = null,
            textStyle = null
        )
    }
}

/**
 * Состояние грида со списком кнопок с иконками.
 *
 * @param modifier - модификатор
 * @param list - список кнопок с иконками, ставить null, чтобы применилось по умолчанию
 */
data class ButtonIconListState(
    val modifier: Modifier = Modifier,
    val list: List<DialPadIconButtonVO?>? = null
) {
    companion object {
        val Default = ButtonIconListState(
            modifier = Modifier,
            list = null
        )
    }
}

/**
 * Экран для набора номера.
 *
 * Содержит два элемента:
 * 1) Поле ввода номера телефона, которое поддерживает работу с курсором, вставку, вырезать и выделить все элементы.
 * 2) Панель для ввода чисел и символов.
 *
 * @param modifier - модификатор для экрана
 * @param dialPadModifier - модификатор для панели с кнопками
 * @param value - значение для отображения в поле ввода номера
 * @param onValueChange - вернет новое значение при вставке
 * @param inputFieldState - состояние поле ввода номера
 * @param buttonNumberListState - состояние списка с кнопками
 * @param buttonIconListState - состояние списка кнопок с иконками
 * @param buttonNumberClick - обработчик нажатия на кнопку с числом или символом
 * @param buttonIconClick - обработчик нажатия на кнопку с иконкой
 */
@Composable
fun PhoneDialPadScreen(
    modifier: Modifier = Modifier,
    dialPadModifier: Modifier = Modifier,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    inputFieldState: PhoneInputFieldState = PhoneInputFieldState.Default,
    buttonNumberListState: ButtonNumberListState = ButtonNumberListState.Default,
    buttonIconListState: ButtonIconListState = ButtonIconListState.Default,
    buttonNumberClick: (String) -> Unit,
    buttonIconClick: (String) -> Unit
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .then(modifier),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.5f),
                contentAlignment = Alignment.Center
            ) {
                DialPadPhoneInputField(
                    modifier = inputFieldState.modifier,
                    value = value,
                    onValueChange = onValueChange,
                    textStyle = inputFieldState.textStyle,
                    setInitFocus = inputFieldState.setInitFocus,
                    visualTransformation = inputFieldState.visualTransformation,
                    useDefaultVisualTransformation = inputFieldState.useDefaultVisualTransformation,
                    cursorBrush = inputFieldState.cursorBrush
                )
            }

            DialPadPanel(
                modifier = dialPadModifier,
                buttonNumberListState = buttonNumberListState,
                buttonIconListState = buttonIconListState,
                buttonNumberClick = buttonNumberClick,
                buttonIconClick = buttonIconClick
            )
        }
    }
}

/**
 * Панель со списком кнопок для ввода номера.
 * Также содержит кнопки с иконками, возможно использовать свои.
 *
 * @param modifier - модификатор для панели
 * @param buttonNumberListState - состояние для отображение кнопок к номерами или символами
 * @param buttonIconListState - состояние для отображения кнопок с иноками
 * @param buttonNumberClick - обработчик клика по кнопке с номером, вернет символ для кнопки
 * @param buttonIconClick - обработчик клика по кнопке с иконкой, вернет id кнопки
 */
@Composable
private fun DialPadPanel(
    modifier: Modifier,
    buttonNumberListState: ButtonNumberListState,
    buttonIconListState: ButtonIconListState,
    buttonNumberClick: (String) -> Unit,
    buttonIconClick: (String) -> Unit
) {
    val numberList by remember { mutableStateOf(buttonNumberListState.list ?: defaultNumberList()) }
    val iconsList by remember { mutableStateOf(buttonIconListState.list ?: defaultIconButtonsList()) }

    Column {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
                .then(modifier),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            items(numberList) { char ->
                DialPadButton(
                    modifier = buttonNumberListState.modifier,
                    label = char,
                    backgroundColor = buttonNumberListState.background,
                    indicatorColor = buttonNumberListState.indicatorColor,
                    textStyle = buttonNumberListState.textStyle,
                    onClick = {
                        buttonNumberClick(char)
                    }
                )
            }

            items(iconsList) { item ->
                item?.let {
                    DialPadIconButton(
                        modifier = buttonIconListState.modifier,
                        data = item,
                        onClick = {
                            buttonIconClick.invoke(item.id)
                        }
                    )
                }
            }
        }
    }
}

@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Light", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun PhoneDialPadScreenPreview() {
    PhoneDialPadLibraryTheme {
        PhoneDialPadScreen(
            value = TextFieldValue("79009009999"),
            onValueChange = { _ -> },
            buttonNumberClick = {},
            buttonIconClick = {}
        )
    }
}