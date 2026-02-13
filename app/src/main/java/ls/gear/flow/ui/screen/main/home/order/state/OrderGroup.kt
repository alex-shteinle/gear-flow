package ls.gear.flow.ui.screen.main.home.order.state

data class OrderGroup(
    val name: String,
    val items: List<OrderItem>,
    val expanded: Boolean
)
