package ls.gear.flow.ui.screen.main.home.order.state

sealed interface OrderAction {

    object FetchItems : OrderAction
    object ShowConfirmDialog : OrderAction
    object HideConfirmDialog : OrderAction
    object OrderItems : OrderAction
    object ClearMessage : OrderAction
    object ClearError : OrderAction
    data class ChangeExpand(val groupName: String, val expanded: Boolean) : OrderAction

    sealed interface Update : OrderAction {

        val item: OrderItem

        data class Checked(override val item: OrderItem, val checked: Boolean) : Update
        data class Plus(override val item: OrderItem) : Update
        data class Minus(override val item: OrderItem) : Update
    }
}
