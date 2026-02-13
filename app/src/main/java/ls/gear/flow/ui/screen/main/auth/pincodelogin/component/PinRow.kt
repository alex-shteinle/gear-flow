package ls.gear.flow.ui.screen.main.auth.pincodelogin.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ls.gear.flow.ui.screen.main.auth.PinConst
import ls.gear.flow.ui.theme.GearFlowTheme
import ls.gear.flow.ext.isFilledForIndex

@Composable
fun PinRow(
    value: String,
    length: Int,
    rowWidth: Dp
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.width(rowWidth),
    ) {
        repeat(length) { index ->
            PinIndicator(filled = value.isFilledForIndex(index))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PinRowPreview() {
    GearFlowTheme {
        PinRow(value = "333", length = PinConst.PINCODE_LENGTH, rowWidth = 84.dp)
    }
}
