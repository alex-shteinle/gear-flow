package ls.gear.flow.ui.screen.main.home.personalsizes.select

import ls.gear.flow.ui.screen.base.BaseViewModel
import ls.gear.flow.ui.screen.main.home.personalsizes.select.state.SizePickerAction
import ls.gear.flow.ui.screen.main.home.personalsizes.select.state.SizePickerState

class SizePickerViewModel : BaseViewModel<SizePickerAction, SizePickerState>(
    initialValue = SizePickerState()
) {

    override suspend fun reduce(action: SizePickerAction) = when (action) {
        is SizePickerAction.Select -> updateSelectedSize(action.selectedSize)
        SizePickerAction.Clear -> uiState.value.copy(selectedSize = "")
    }

    private fun updateSelectedSize(selected: String) = uiState.value.copy(selectedSize = selected)
}
