package ls.gear.flow.ui.screen.main.home.order.state

import ls.gear.flow.domain.error.GearFlowError
import ls.gear.flow.ui.screen.base.Message
import ls.gear.flow.ui.screen.base.UiState

data class OrderState(
    val groups: List<OrderGroup> = emptyList(),
    val showLoading: Boolean = false,
    val showConfirm: Boolean = false,
    override val error: GearFlowError? = null,
    override val message: Message? = null
) : UiState {

    val showNoInternetStub get() = error is GearFlowError.Network && groups.isEmpty()
    val showSomethingWrongStub get() = error != null && error !is GearFlowError.Network
    val showNoItemsStub get() = groups.isEmpty()
    val showContent get() = groups.isNotEmpty()
}
