package com.vadson40.phonenumberexample

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.vadson40.phonelib.PhoneDialPadScreen
import com.vadson40.phonelib.components.buttons.icons.DialPadIconButtonVO.DefaultIconButtonType
import com.vadson40.phonelib.utils.addSymbolAtTheCursorPosition
import com.vadson40.phonelib.utils.deleteSymbolAtTheCursorPosition
import com.vadson40.phonelib.utils.removeUnnecessarySymbolFromPhoneNumber
import com.vadson40.phonenumberexample.ui.theme.PhoneNumberExampleTheme

//todo поправить preview для Main activity чтобы работала иконка
//todo сделать readme
//todo сделать побольше кейсов

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PhoneNumberExampleTheme {
                var currentInputText by remember { mutableStateOf(TextFieldValue()) }
                PhoneDialPadScreen(
                    value = currentInputText,
                    onValueChange = { newValue ->
                        currentInputText = newValue.removeUnnecessarySymbolFromPhoneNumber()
                    },
                    buttonNumberClick = { newSymbol ->
                        currentInputText =
                            currentInputText.addSymbolAtTheCursorPosition(newSymbol)
                    },
                    buttonIconClick = { buttonId ->
                        when (buttonId) {
                            DefaultIconButtonType.CALL.name -> {
                                Toast.makeText(this, "Call: ${currentInputText.text}", Toast.LENGTH_SHORT).show()
                            }
                            DefaultIconButtonType.DELETE.name -> {
                                currentInputText =
                                    currentInputText.deleteSymbolAtTheCursorPosition()
                            }
                        }
                    }
                )
            }
        }
    }
}

@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(name = "Light", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Composable
fun MainActivityContentPreview() {
    PhoneNumberExampleTheme {
        PhoneDialPadScreen(
            value = TextFieldValue(""),
            onValueChange = { _ -> },
            buttonNumberClick = {},
            buttonIconClick = {}
        )
    }
}