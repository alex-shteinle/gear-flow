package ls.gear.flow.ui.screen.main.home.property.state

import ls.gear.flow.domain.error.GearFlowError
import ls.gear.flow.domain.model.StuffItemGroup
import ls.gear.flow.ui.screen.base.Message

data class PropertyCardState(
    val groups: List<StuffItemGroup> = emptyList(),
    val allProperty: StuffItemGroup? = null,
    val loading: Boolean = false,
    val error: GearFlowError? = null,
    val message: Message? = null
) {
    val showNoItemsStub get() = groups.isEmpty() && !loading
    val showNoInternetStub get() = error is GearFlowError.Network
    val showSomethingWrongStub get() = error != null && !showNoInternetStub
    val showContent get() = groups.isNotEmpty()
}
