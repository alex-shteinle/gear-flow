package ls.gear.flow.ui.screen.main.home.order

import ls.gear.flow.domain.error.GearFlowError
import ls.gear.flow.domain.error.toGearFlowError
import ls.gear.flow.domain.usecase.order.GetDemandItemsUseCase
import ls.gear.flow.domain.usecase.order.OrderItemsUseCase
import ls.gear.flow.ext.replaceIf
import ls.gear.flow.ui.screen.base.BaseViewModel
import ls.gear.flow.ui.screen.base.Message
import ls.gear.flow.ui.screen.base.toMessage
import ls.gear.flow.ui.screen.main.home.order.state.OrderAction
import ls.gear.flow.ui.screen.main.home.order.state.OrderState
import ls.gear.flow.ui.screen.main.home.order.state.hasSelectedItems
import ls.gear.flow.ui.screen.main.home.order.state.toGroupsWithItems
import ls.gear.flow.ui.screen.main.home.order.state.toOrderItem

class OrderViewModel(
    private val getDemandItemsUseCase: GetDemandItemsUseCase,
    private val orderItemsUseCase: OrderItemsUseCase,
) : BaseViewModel<OrderAction, OrderState>(initialValue = OrderState()) {

    init {
        dispatch(OrderAction.FetchItems)
    }

    override suspend fun reduce(action: OrderAction) = when (action) {
        OrderAction.FetchItems -> fetchItems()
        is OrderAction.Update -> update(action, uiState.value)
        OrderAction.ShowConfirmDialog -> showConfirm(uiState.value)
        OrderAction.HideConfirmDialog -> hideConfirm(uiState.value)
        OrderAction.OrderItems -> orderItems(uiState.value)
        is OrderAction.ChangeExpand -> changeExpanded(action, uiState.value)
        OrderAction.ClearMessage -> clearMessage()
        OrderAction.ClearError -> clearError()
    }

    private fun showLoading() = OrderState(showLoading = true)

    private suspend fun fetchItems(): OrderState {
        updateState(showLoading())
        return getDemandItemsUseCase().fold(
            onSuccess = { items ->
                if (items.isEmpty()) {
                    OrderState(
                        groups = emptyList(),
                        error = GearFlowError.NoData
                    )
                } else {
                    OrderState(
                        groups = items
                            .map { it.toOrderItem() }
                            .toGroupsWithItems()
                    )
                }
            },
            onFailure = {
                val error = it.toGearFlowError()
                OrderState(
                    groups = emptyList(),
                    error = error,
                    message = error.toMessage()
                )
            }
        )
    }

    private fun update(action: OrderAction.Update, state: OrderState): OrderState {
        val group = state.groups.find { it.name == action.item.typeGroupName } ?: return state
        val updatedItem = when (action) {
            is OrderAction.Update.Checked -> action.item.copy(checked = action.checked)
            is OrderAction.Update.Plus -> {
                action.item.copy(selectedQuantity = action.item.selectedQuantity + 1)
            }
            is OrderAction.Update.Minus -> {
                action.item.copy(selectedQuantity = action.item.selectedQuantity - 1)
            }
        }
        val updatedItems = group.items
            .replaceIf(updatedItem) { it.userItemId == updatedItem.userItemId }
        val updatedGroup = group.copy(items = updatedItems)
        return state.copy(
            groups = state.groups.replaceIf(updatedGroup) { it.name == group.name }
        )
    }

    private fun showConfirm(state: OrderState): OrderState {
        return if (state.groups.hasSelectedItems()) {
            state.copy(showConfirm = true)
        } else {
            state
        }
    }

    private fun hideConfirm(state: OrderState) = state.copy(showConfirm = false)

    private suspend fun orderItems(state: OrderState): OrderState {
        updateState(showLoading())
        return orderItemsUseCase().fold(
            onSuccess = { fetchAfterOrder() },
            onFailure = {
                val error = it.toGearFlowError()
                state.copy(error = error, message = error.toMessage())
            }
        )
    }

    private fun changeExpanded(action: OrderAction.ChangeExpand, state: OrderState): OrderState {
        val index = state.groups.indexOfFirst { it.name == action.groupName }
        return if (index > -1) {
            val list = state.groups.toMutableList()
            list[index] = list[index].copy(expanded = action.expanded)
            state.copy(groups = list)
        } else {
            state
        }
    }

    private fun clearMessage() = uiState.value.copy(message = null)

    private fun clearError() = uiState.value.copy(error = null)

    private suspend fun fetchAfterOrder(): OrderState {
        val state = reduce(OrderAction.FetchItems)
        return state.copy(message = if (state.error == null) Message.Success.OrderSucceed else null)
    }
}
