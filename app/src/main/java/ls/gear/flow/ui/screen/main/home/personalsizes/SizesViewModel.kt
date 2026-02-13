package ls.gear.flow.ui.screen.main.home.personalsizes

import ls.gear.flow.domain.error.GearFlowError
import ls.gear.flow.domain.model.TypicalSize
import ls.gear.flow.domain.usecase.user.GetSizesUseCase
import ls.gear.flow.domain.usecase.user.SaveSizesUseCase
import ls.gear.flow.ui.screen.base.BaseViewModel
import ls.gear.flow.ui.screen.base.Message
import ls.gear.flow.ui.screen.main.home.personalsizes.state.SizesAction
import ls.gear.flow.ui.screen.main.home.personalsizes.state.SizesState
import ls.gear.flow.ui.screen.main.home.personalsizes.state.toPersonalSizes
import ls.gear.flow.ui.screen.main.home.personalsizes.state.toSizeState

class SizesViewModel(
    getSizesUseCase: GetSizesUseCase,
    private val saveSizesUseCase: SaveSizesUseCase
) : BaseViewModel<SizesAction, SizesState>(initialValue = getSizesUseCase().toSizeState()) {

    override suspend fun reduce(action: SizesAction) = when (action) {
        is SizesAction.Update -> updateSize(action, uiState.value)
        SizesAction.SaveSizes -> saveSizes(uiState.value)
        is SizesAction.ShowSizePicker -> updateTypicalSizeToShow(action.typicalSize)
        SizesAction.HideSizePicker -> updateTypicalSizeToShow(null)
        SizesAction.ClearError -> uiState.value.copy(error = null)
        SizesAction.ClearMessage -> uiState.value.copy(message = null)
    }

    private fun updateSize(action: SizesAction.Update, state: SizesState): SizesState {
        val updatedState = when (action) {
            is SizesAction.Update.Chest -> state.copy(chest = action.value)
            is SizesAction.Update.Head -> state.copy(head = action.value)
            is SizesAction.Update.Height -> state.copy(height = action.value)
            is SizesAction.Update.Insole -> state.copy(insole = action.value)
            is SizesAction.Update.Neck -> state.copy(neck = action.value)
            is SizesAction.Update.Shoe -> state.copy(shoe = action.value)
            is SizesAction.Update.Sleeve -> state.copy(sleeve = action.value)
            is SizesAction.Update.Uniform -> state.copy(uniform = action.value)
            is SizesAction.Update.Waist -> state.copy(waist = action.value)
        }
        return updatedState.copy(
            typicalSizeToShow = null,
            message = null,
            error = null,
            changed = state != updatedState
        )
    }

    private suspend fun saveSizes(state: SizesState): SizesState =
        saveSizesUseCase(state.toPersonalSizes()).fold(
            onSuccess = {
                state.copy(
                    message = Message.Success.SizesSaved,
                    error = null,
                    changed = false
                )
            },
            onFailure = {
                state.copy(
                    message = null,
                    error = GearFlowError.SizesWereNotSaved
                )
            }
        )

    private fun updateTypicalSizeToShow(newValue: TypicalSize?) =
        uiState.value.copy(typicalSizeToShow = newValue, message = null, error = null)
}
