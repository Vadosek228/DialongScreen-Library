package com.vadson40.phonelib.components.buttons.icons

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.requiredWidthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vadson40.phonelib.utils.Icon
import com.vadson40.phonelib.theme.PhoneDialPadLibraryTheme

/**
 * Кнопка с иконкой.
 *
 * @param modifier - модификатор для элемента
 * @param data - данные для кнопки
 * @param onClick - обработчик нажатия кнопки
 */
@Composable
internal fun DialPadIconButton(
    modifier: Modifier = Modifier,
    data: DialPadIconButtonVO,
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
                    color = data.indicatorColorGetColorOrDefault()
                ),
                interactionSource = remember { MutableInteractionSource() }
            )
            .aspectRatio(1f),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier
                .padding(12.dp)
                .requiredHeightIn(44.dp, 64.dp)
                .requiredWidthIn(44.dp, 64.dp)
                .clip(CircleShape),
            icon = data.icon
        )
    }
}

@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Light", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun DialPadIconButtonPreview() {
    PhoneDialPadLibraryTheme {
        Column {
            DialPadIconButton(
                data = DialPadIconButtonVO.call,
                onClick = {}
            )
            DialPadIconButton(
                data = DialPadIconButtonVO.delete,
                onClick = {}
            )
        }
    }
}
