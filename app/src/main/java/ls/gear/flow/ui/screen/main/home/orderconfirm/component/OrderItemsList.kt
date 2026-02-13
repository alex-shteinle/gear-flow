package ls.gear.flow.ui.screen.main.home.orderconfirm.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import ls.gear.flow.ui.screen.main.home.order.state.OrderItem
import ls.gear.flow.ui.screen.main.home.property.component.ItemRow

@Composable
fun ConfirmOrderItemsList(items: ImmutableList<OrderItem>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 16.dp)
    ) {
        items.forEachIndexed { index, orderItem ->
            ItemRow(
                itemName = orderItem.name,
                itemQuantity = orderItem.selectedQuantity.toString()
            )
            if (index < items.lastIndex) {
                Divider(thickness = 0.5.dp)
            }
        }
    }
}
