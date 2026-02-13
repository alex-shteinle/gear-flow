package ls.gear.flow.ui.screen.main.home.order.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ls.gear.flow.ui.screen.base.component.SpacerHorizontal
import ls.gear.flow.ui.screen.main.home.component.ItemNameText
import ls.gear.flow.ui.screen.main.home.component.QuantityText
import ls.gear.flow.ui.screen.main.home.order.state.OrderItem

@Composable
fun OrderItemRow(
    item: OrderItem,
    onCheckedChanged: (OrderItem, Boolean) -> Unit,
    onPlusClick: (OrderItem) -> Unit,
    onMinusClick: (OrderItem) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 8.dp, top = 4.dp, bottom = 4.dp)
    ) {
        Checkbox(checked = item.checked, onCheckedChange = { onCheckedChanged(item, it) })
        ItemNameText(text = item.name, modifier = Modifier.weight(1f))
        if (item.showButtons) {
            QuantityButton(
                type = QuantityButtonType.MINUS,
                enabled = item.canBeDecreased,
                onClick = { onMinusClick(item) }
            )
        } else {
            SpacerHorizontal(48.dp)
        }
        QuantityText(
            text = if (item.checked) {
                item.selectedQuantity.toString()
            } else {
                item.availableQuantity.toString()
            }
        )
        if (item.showButtons) {
            QuantityButton(
                type = QuantityButtonType.PLUS,
                enabled = item.canBeIncreased,
                onClick = { onPlusClick(item) }
            )
        } else {
            SpacerHorizontal(48.dp)
        }
    }
}
