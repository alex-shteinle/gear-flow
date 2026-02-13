package ls.gear.flow.ui.screen.main.auth.nouser

import ls.gear.flow.domain.usecase.pincode.GetPinCodeUseCase
import ls.gear.flow.domain.usecase.user.GetRemoteUserUseCase
import ls.gear.flow.ui.screen.base.BaseViewModel
import ls.gear.flow.ui.screen.main.auth.nouser.state.NoUserAction
import ls.gear.flow.ui.screen.main.auth.nouser.state.NoUserState
import ls.gear.flow.ui.screen.main.auth.nouser.state.ReloadState

class NoUserViewModel(
    private val getRemoteUserUseCase: GetRemoteUserUseCase,
    private val getPinCodeUseCase: GetPinCodeUseCase
) : BaseViewModel<NoUserAction, NoUserState>(initialValue = NoUserState()) {

    override suspend fun reduce(action: NoUserAction) = when (action) {
        NoUserAction.ReloadUser -> reloadUser(uiState.value)
    }

    private suspend fun reloadUser(state: NoUserState): NoUserState {
        updateState(showLoading())
        val updatedState = getRemoteUserUseCase().fold(
            onSuccess = {
                val pinCode = getPinCodeUseCase().getOrNull()
                state.copy(
                    reloadState = if (pinCode == null) {
                        ReloadState.NO_PINCODE
                    } else {
                        ReloadState.PINCODE_IS_PRESENT
                    }
                )
            },
            onFailure = { NoUserState() }
        )
        return updatedState.copy(loading = false)
    }

    private fun showLoading() = NoUserState(loading = true)
}
