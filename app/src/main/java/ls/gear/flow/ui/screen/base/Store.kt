package ls.gear.flow.ui.screen.base

import kotlinx.coroutines.flow.StateFlow

interface Store<Action, UiState> {

    val uiState: StateFlow<UiState>

    fun dispatch(vararg actions: Action)

    suspend fun reduce(action: Action): UiState

    fun updateState(newState: UiState)
}
