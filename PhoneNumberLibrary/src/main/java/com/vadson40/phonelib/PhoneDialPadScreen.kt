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
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vadson40.phonelib.components.buttons.icons.DialPadIconButton
import com.vadson40.phonelib.components.buttons.icons.DialPadIconButtonVO
import com.vadson40.phonelib.components.buttons.numbers.DialPadButton
import com.vadson40.phonelib.components.textfields.DialPadPhoneInputField
import com.vadson40.phonelib.theme.PhoneDialPadLibraryTheme
import com.vadson40.phonelib.utils.PhoneMaskTransformation
import com.vadson40.phonelib.utils.defaultNumberList

/**
 * Экран для набора номера.
 *
 * Содержит два элемента:
 * 1) Поле ввода номера телефона, которое поддерживает работу с курсором, вставку, вырезать и выделить все элементы.
 * 2) Панель для ввода чисел и символов.
 *
 * @author Akulinin Vladislav
 * @since 21.01.2026
 */
@Composable
fun PhoneDialPadScreen(
//    numberPanel: (@Composable ColumnScope.() -> Unit)? = null
) {


    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {

            DialPadPhoneInputField(
                value = TextFieldValue("9999999999"),
                onValueChange = {}
            )

            DialPadPanel(
                numberClick = {

                }
            )
        }
    }
}

@Composable
private fun DialPadPanel(
    firstIcon: DialPadIconButtonVO? = null,
    secondIcon: DialPadIconButtonVO? = DialPadIconButtonVO.call,
    thirdIcon: DialPadIconButtonVO? = DialPadIconButtonVO.delete,
    numberClick: (String) -> Unit,
    firstIconClick: (() -> Unit)? = null,
    secondIconClick: (() -> Unit)? = null,
    thirdIconClick: (() -> Unit)? = null
) {
    val listNumber = defaultNumberList()
    Column {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            items(listNumber) { char ->
                //todo продмать как цвет передавать
                DialPadButton(
                    label = char,
                    onClick = {
                        numberClick(char)
                    }
                )
            }

            item {
                firstIcon?.let { data ->
                    DialPadIconButton(
                        data = data,
                        onClick = {
                            firstIconClick?.invoke()
                        }
                    )
                }
            }

            item {
                secondIcon?.let { data ->
                    DialPadIconButton(
                        data = data,
                        onClick = {
                            secondIconClick?.invoke()
                        }
                    )
                }
            }

            item {
                thirdIcon?.let { data ->
                    DialPadIconButton(
                        data = data,
                        onClick = {
                            thirdIconClick?.invoke()
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
        PhoneDialPadScreen()
    }
}