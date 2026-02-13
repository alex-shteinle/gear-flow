package ls.gear.flow.ui.screen.main.home.property.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ls.gear.flow.ui.screen.main.home.component.ItemNameText
import ls.gear.flow.ui.screen.main.home.component.QuantityText

@Composable
fun ItemRow(itemName: String, itemQuantity: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        ItemNameText(
            text = itemName,
            modifier = Modifier.weight(1f).padding(end = 8.dp)
        )
        QuantityText(text = itemQuantity, modifier = Modifier.align(Alignment.CenterVertically))
    }
}
