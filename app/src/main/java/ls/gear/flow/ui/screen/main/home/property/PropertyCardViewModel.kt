package ls.gear.flow.ui.screen.main.home.property

import ls.gear.flow.domain.error.toGearFlowError
import ls.gear.flow.domain.model.StuffItemGroupType
import ls.gear.flow.domain.usecase.item.GetItemGroupByTypeUseCase
import ls.gear.flow.domain.usecase.item.GetItemGroupsUseCase
import ls.gear.flow.domain.usecase.user.GetRemoteUserUseCase
import ls.gear.flow.ui.screen.base.BaseViewModel
import ls.gear.flow.ui.screen.base.Message
import ls.gear.flow.ui.screen.base.toMessage
import ls.gear.flow.ui.screen.main.home.property.state.PropertyCardAction
import ls.gear.flow.ui.screen.main.home.property.state.PropertyCardState

class PropertyCardViewModel(
    private val getItemGroupsUseCase: GetItemGroupsUseCase,
    private val getItemGroupByTypeUseCase: GetItemGroupByTypeUseCase,
    private val getRemoteUserUseCase: GetRemoteUserUseCase
) : BaseViewModel<PropertyCardAction, PropertyCardState>(
    initialValue = PropertyCardState(
        groups = getItemGroupsUseCase(),
        allProperty = getItemGroupByTypeUseCase(StuffItemGroupType.ALL_PROPERTY)
    )
) {

    override suspend fun reduce(action: PropertyCardAction): PropertyCardState = when (action) {
        PropertyCardAction.Reload -> reload(uiState.value)
        PropertyCardAction.ClearMessage -> uiState.value.copy(message = null)
    }

    private suspend fun reload(state: PropertyCardState): PropertyCardState {
        updateState(startLoading())
        return getRemoteUserUseCase().fold(
            onSuccess = {
                val groups = getItemGroupsUseCase()
                PropertyCardState(
                    loading = false,
                    error = null,
                    message = if (groups.isEmpty()) Message.Error.NoRecords else null,
                    groups = groups,
                    allProperty = getItemGroupByTypeUseCase(StuffItemGroupType.ALL_PROPERTY)
                )
            },
            onFailure = {
                val error = it.toGearFlowError()
                state.copy(
                    loading = false,
                    error = error,
                    groups = emptyList(),
                    allProperty = null,
                    message = error.toMessage()
                )
            }
        )
    }

    private fun startLoading() = uiState.value.copy(loading = true)
}
