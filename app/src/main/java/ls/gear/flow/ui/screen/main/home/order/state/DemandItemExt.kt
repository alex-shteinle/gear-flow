package ls.gear.flow.ui.screen.main.home.order.state

import ls.gear.flow.domain.model.DemandItem

fun DemandItem.toOrderItem() = OrderItem(
    userItemId = itemId,
    name = itemName,
    measureUnit = measureUnit,
    checked = false,
    availableQuantity = quantity,
    selectedQuantity = if (quantity > 0) 1 else 0,
    typeGroupName = typeGroupName
)

fun List<OrderItem>.selectedMoreThenZero() = filter { it.checked && it.selectedQuantity > 0 }

fun List<OrderItem>.hasSelected() = selectedMoreThenZero().isNotEmpty()

fun List<OrderItem>.toGroupsWithItems(): List<OrderGroup> {
    return this.map { it.typeGroupName }
        .distinct()
        .map { name ->
            OrderGroup(
                name = name,
                items = this.filter { it.typeGroupName.contentEquals(name) },
                expanded = false
            )
        }
        .filter { it.items.isNotEmpty() }
}

fun List<OrderGroup>.groupsWithSelectedItems() = filter { it.items.hasSelected() }
    .map { it.copy(items = it.items.selectedMoreThenZero()) }

fun List<OrderGroup>.hasSelectedItems() = any { it.items.hasSelected() }
