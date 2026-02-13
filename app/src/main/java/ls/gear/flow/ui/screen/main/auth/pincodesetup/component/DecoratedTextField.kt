package ls.gear.flow.ui.screen.main.auth.pincodesetup.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ls.gear.flow.ext.isFilledForIndex

@Composable
fun DecoratedTextField(
    value: String,
    length: Int,
    onSaved: () -> Unit,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    boxWidth: Dp = 72.dp,
    boxHeight: Dp = 48.dp,
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.NumberPassword,
        imeAction = ImeAction.Send
    ),
    keyboardActions: KeyboardActions = KeyboardActions(onSend = { onSaved() }),
) {
    BasicTextField(
        modifier = modifier,
        value = value,
        singleLine = true,
        onValueChange = { onValueChange(it) },
        enabled = enabled,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        decorationBox = {
            Row(
                Modifier
                    .height(boxHeight)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                repeat(length) { index ->
                    DecoratedTextBox(
                        boxWidth = boxWidth,
                        boxHeight = boxHeight,
                        isFilled = value.isFilledForIndex(index)
                    )
                }
            }
        }
    )
}
