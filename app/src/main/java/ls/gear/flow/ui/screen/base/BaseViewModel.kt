package ls.gear.flow.ui.screen.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<Action, UiState>(
    initialValue: UiState
) : ViewModel(), Store<Action, UiState> {

    private val _uiState = MutableStateFlow(initialValue)

    override val uiState = _uiState.asStateFlow()

    /**
     * Function to choose proper reducer for action
     */
    override fun dispatch(vararg actions: Action) {
        viewModelScope.launch {
            actions.forEach { action -> updateState(reduce(action)) }
        }
    }

    /**
     * Function to create new state according action
     */
    abstract override suspend fun reduce(action: Action): UiState

    /**
     * Function to update uiState flow with new state
     */
    override fun updateState(newState: UiState) = _uiState.update { newState }
}
