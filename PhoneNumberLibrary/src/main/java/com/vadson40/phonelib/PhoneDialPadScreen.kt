package com.vadson40.phonelib

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
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
import com.vadson40.phonelib.utils.Constants
import com.vadson40.phonelib.utils.defaultIconButtonsList
import com.vadson40.phonelib.utils.defaultNumberList

//todo поправить доку
//todo поправить работу с индикатором
//todo поправить preview для Main activity чтобы работала иконка
//todo добавить скрытие клавиатуры

/**
 * Экран для набора номера.
 *
 * Содержит два элемента:
 * 1) Поле ввода номера телефона, которое поддерживает работу с курсором, вставку, вырезать и выделить все элементы.
 * 2) Панель для ввода чисел и символов.
 */
@Composable
fun PhoneDialPadScreen(
    modifier: Modifier = Modifier,
    inputFieldModifier: Modifier = Modifier,
    dialPadModifier: Modifier = Modifier,
    buttonModifier: Modifier = Modifier,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    inputFieldTextStyle: TextStyle? = null,
    setInitFocus: Boolean = true,
    visualTransformation: VisualTransformation? = null,
    useDefaultVisualTransformation: Boolean = true,
    cursorBrush: SolidColor? = null,
    buttonNumberList: List<String>? = null,
    buttonIconList: List<DialPadIconButtonVO?>? = null,
    buttonNumberBackground: Color? = null,
    buttonNumberIndicator: Color? = null,
    buttonNumberTextStyle: TextStyle? = null,
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

            DialPadPhoneInputField(
                modifier = inputFieldModifier,
                value = value,
                onValueChange = onValueChange,
                textStyle = inputFieldTextStyle,
                setInitFocus = setInitFocus,
                visualTransformation = visualTransformation,
                useDefaultVisualTransformation = useDefaultVisualTransformation,
                cursorBrush = cursorBrush
            )

            DialPadPanel(
                modifier = dialPadModifier,
                buttonModifier = buttonModifier,
                buttonNumberList = buttonNumberList ?: defaultNumberList(),
                buttonNumberBackground = buttonNumberBackground,
                buttonNumberIndicator = buttonNumberIndicator,
                buttonNumberTextStyle = buttonNumberTextStyle,
                buttonIconList = buttonIconList ?: defaultIconButtonsList(),
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
 * @param buttonModifier - модификатор для кнопок
 * @param buttonNumberList - список кнопок с числами или символами
 * @param buttonNumberBackground - цвет кнопки с числом или символом
 * @param buttonNumberIndicator - цвет индикатора нажатия кнопки
 * @param buttonNumberTextStyle - стиль текста для кнопки с числом или символом
 * @param buttonIconList - список кнопок с иконками
 * @param buttonNumberClick - обработчик клика по кнопке с номером, вернет символ для кнопки
 * @param buttonIconClick - обработчик клика по кнопке с иконкой, вернет id кнопки
 */
@Composable
private fun DialPadPanel(
    modifier: Modifier,
    buttonModifier: Modifier,
    buttonNumberList: List<String>,
    buttonNumberBackground: Color? = null,
    buttonNumberIndicator: Color? = null,
    buttonNumberTextStyle: TextStyle? = null,
    buttonIconList: List<DialPadIconButtonVO?>,
    buttonNumberClick: (String) -> Unit,
    buttonIconClick: (String) -> Unit
) {
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
            items(buttonNumberList) { char ->
                DialPadButton(
                    modifier = modifier,
                    label = char,
                    backgroundColor = buttonNumberBackground,
                    indicatorColor = buttonNumberIndicator,
                    textStyle = buttonNumberTextStyle,
                    onClick = {
                        buttonNumberClick(char)
                    }
                )
            }

            items(buttonIconList) { item ->
                item?.let {
                    DialPadIconButton(
                        modifier = buttonModifier,
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
        var currentInputText = remember { TextFieldValue(Constants.EMPTY) }
        PhoneDialPadScreen(
            value = currentInputText,
            onValueChange = { newValue ->
                currentInputText = newValue
            },
            buttonNumberClick = {

            },
            buttonIconClick = {

            }
        )
    }
}