package ls.gear.flow.ui.screen.main.home.order.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.toImmutableList
import ls.gear.flow.ui.screen.main.home.order.state.OrderGroup
import ls.gear.flow.ui.screen.main.home.order.state.OrderItem
import ls.gear.flow.ui.theme.BlackAlpha56

@Composable
fun OrderGroupRow(
    group: OrderGroup,
    onGroupClick: (OrderGroup) -> Unit,
    onCheckedChanged: (OrderItem, Boolean) -> Unit,
    onPlusClick: (OrderItem) -> Unit,
    onMinusClick: (OrderItem) -> Unit
) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable {
                onGroupClick(group)
            }
        ) {
            Text(
                text = group.name,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 16.dp)
                    .weight(1f)
            )
            Icon(
                imageVector = if (group.expanded) {
                    Icons.Default.ExpandMore
                } else {
                    Icons.Default.ChevronRight
                },
                contentDescription = null,
                tint = BlackAlpha56,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
        }
        if (group.expanded) {
            OrderItemList(
                items = group.items.toImmutableList(),
                onCheckedChanged = onCheckedChanged,
                onPlusClick = onPlusClick,
                onMinusClick = onMinusClick
            )
        }
    }
}
