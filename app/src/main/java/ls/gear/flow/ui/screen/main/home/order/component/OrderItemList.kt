package ls.gear.flow.ui.screen.main.home.order.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import ls.gear.flow.ui.screen.main.home.order.state.OrderItem

@Composable
fun OrderItemList(
    items: ImmutableList<OrderItem>,
    onCheckedChanged: (OrderItem, Boolean) -> Unit,
    onPlusClick: (OrderItem) -> Unit,
    onMinusClick: (OrderItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Column {
            items.forEachIndexed { index, orderItem ->
                OrderItemRow(
                    item = orderItem,
                    onCheckedChanged = onCheckedChanged,
                    onPlusClick = onPlusClick,
                    onMinusClick = onMinusClick
                )
                if (index < items.lastIndex) {
                    Divider(thickness = 0.5.dp)
                }
            }
        }
    }
}
