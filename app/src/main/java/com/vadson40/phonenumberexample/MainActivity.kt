package com.vadson40.phonenumberexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vadson40.phonenumberexample.ui.theme.PhoneNumberExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PhoneNumberExampleTheme {

            }
        }
    }
}

@Composable
private fun ScreenContent() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        val textFieldState = rememberTextFieldState()

        PhoneInputView(
            textFieldState = textFieldState
        )
        PhoneNumberPanel(
            textFieldState = textFieldState,
            onClick = { char ->

            }
        )
    }
}


@Composable
private fun PhoneInputView(
    textFieldState: TextFieldState
) {

    BasicTextField(
        state = textFieldState
    )
}

@Composable
private fun PhoneNumberPanel(
    textFieldState: TextFieldState,
    onClick: (String) -> Unit
) {
    val listNumber = remember {
        listOf(
            "1", "2", "3",
            "4", "5", "6",
            "7", "8", "9",
            "*", "0", "#"
        )
    }

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
                Box(
                    modifier = Modifier
                        .padding(12.dp)
                        .clickable(
                            onClick = {
                                onClick(char)
                            }
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier.padding(12.dp),
                        text = char
                    )
                }
            }
        }

        Row(

        ) {
            Box(
                modifier = Modifier
                    .padding(12.dp)
                    .clickable(
                        onClick = {
                            onClick("cal")
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier.padding(12.dp),
                    text = "cal"
                )
            }

            if (textFieldState.text.isNotBlank()) {
                Box(
                    modifier = Modifier
                        .padding(12.dp)
                        .clickable(
                            onClick = {
                                onClick("delete")
                            }
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier.padding(12.dp),
                        text = "del"
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PhoneNumberExampleTheme {
        ScreenContent()
    }
}