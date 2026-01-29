package com.vadson40.phonelib.components.buttons.numbers

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vadson40.phonelib.theme.PhoneDialPadLibraryTheme
import com.vadson40.phonelib.theme.defaultTextStyle

/**
 * Кнопка для набора номера.
 *
 * @param modifier - модификатор, присутствует по умолчанию
 * @param label - метка кнопки (например цифра или символ)
 * @param backgroundColor - цвет заднего фона кнопки
 * @param indicatorColor - цвет индикатора нажатия на кнопку
 * @param textStyle - стиль текста для метки на кнопке
 * @param onClick - обработчик нажатия кнопки
 */
@Composable
internal fun DialPadButton(
    modifier: Modifier = Modifier,
    label: String,
    backgroundColor: Color? = null,
    indicatorColor: Color? = null,
    textStyle: TextStyle? = null,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .padding(12.dp)
            .clickable(
                onClick = onClick,
                indication = ripple(
                    bounded = true,
                    radius = 42.dp,
                    color = indicatorColor ?: MaterialTheme.colorScheme.onSurface
                ),
                interactionSource = remember { MutableInteractionSource() }
            )
            .aspectRatio(1f)
            .background(
                color = backgroundColor ?: MaterialTheme.colorScheme.background,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier,
            text = label,
            style = textStyle ?: defaultTextStyle()
        )
    }
}

@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Light", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun DialPadButtonPreview() {
    PhoneDialPadLibraryTheme {
        DialPadButton(
            label = "1",
            onClick = {}
        )
    }
}