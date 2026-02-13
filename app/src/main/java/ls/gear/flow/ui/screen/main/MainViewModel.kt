package ls.gear.flow.ui.screen.main

import ls.gear.flow.domain.usecase.pincode.CheckPinCodeExistsUseCase
import ls.gear.flow.ui.screen.base.BaseViewModel
import ls.gear.flow.ui.screen.base.Message
import ls.gear.flow.ui.screen.main.state.INITIAL_BACKGROUND_TIME
import ls.gear.flow.ui.screen.main.state.MAX_BACKGROUND_TIME_IN_MILLISECONDS
import ls.gear.flow.ui.screen.main.state.MainAction
import ls.gear.flow.ui.screen.main.state.MainState

class MainViewModel(
    private val checkPinCodeExistsUseCase: CheckPinCodeExistsUseCase
) : BaseViewModel<MainAction, MainState>(
    initialValue = MainState()
) {
    private val now get() = System.currentTimeMillis()

    override suspend fun reduce(action: MainAction) = when (action) {
        MainAction.ClearMessage -> clearMessage(uiState.value)
        MainAction.HandleOnStart -> handleOnStart(uiState.value)
        MainAction.HandleOnStop -> handleOnStop(uiState.value)
        MainAction.HandleOnDestroy -> handleOnDestroy(uiState.value)
    }

    private fun clearMessage(state: MainState) = state.copy(message = null)

    private fun handleOnStop(state: MainState) = state.copy(startBackgroundTime = now)

    private fun handleOnDestroy(state: MainState) = state.copy(
        startBackgroundTime = INITIAL_BACKGROUND_TIME
    )

    private suspend fun handleOnStart(state: MainState): MainState {
        return if (state.isBackgroundTimeExpiredNow()) {
            state.copy(
                startBackgroundTime = INITIAL_BACKGROUND_TIME,
                message = Message.Error.BackgroundTimeExpired,
                pinCodeExists = checkPinCodeExistsUseCase()
            )
        } else {
            state.copy(startBackgroundTime = INITIAL_BACKGROUND_TIME)
        }
    }

    private fun MainState.isBackgroundTimeExpiredNow(): Boolean {
        if (startBackgroundTime == INITIAL_BACKGROUND_TIME) return false
        return now - startBackgroundTime > MAX_BACKGROUND_TIME_IN_MILLISECONDS
    }
}
