package ls.gear.flow.ui.screen.main.home.groupdetail.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ls.gear.flow.ui.theme.BlackAlpha60
import ls.gear.flow.ui.theme.BlackAlpha87

@Composable
fun ItemDetailRow(name: String, value: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        ItemValueText(
            name = name,
            color = BlackAlpha60,
            modifier = Modifier.weight(1f)
        )
        ItemValueText(
            name = value,
            color = BlackAlpha87
        )
    }
}
