package ls.gear.flow.ui.screen.main.home.personalsizes.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ls.gear.flow.R
import ls.gear.flow.domain.model.TypicalSize
import ls.gear.flow.ui.screen.main.home.personalsizes.state.UiSize
import ls.gear.flow.ui.theme.BlackAlpha38

@Composable
fun SizeRow(
    uiSize: UiSize,
    onClick: (TypicalSize) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(uiSize.typicalSize) }
    ) {
        Text(
            text = stringResource(id = uiSize.labelRes),
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 12.dp)
        )
        val sizeValue = if (uiSize.value.isEmpty() || uiSize.measureUnitRes == null) {
            uiSize.value
        } else {
            "${uiSize.value} ${stringResource(id = uiSize.measureUnitRes)}"
        }
        Text(
            text = sizeValue.ifEmpty { stringResource(id = R.string.set_value) },
            color = if (sizeValue.isEmpty()) BlackAlpha38 else Color.Black
        )
    }
}
