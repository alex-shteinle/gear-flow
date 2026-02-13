package ls.gear.flow.ui.screen.main.home.setting

import ls.gear.flow.domain.error.GearFlowError
import ls.gear.flow.domain.model.Settings
import ls.gear.flow.domain.usecase.setting.GetAppVersionUseCase
import ls.gear.flow.domain.usecase.setting.GetSettingsUseCase
import ls.gear.flow.domain.usecase.biometric.IsBioMetricAvailableUseCase
import ls.gear.flow.domain.usecase.biometric.SaveUseBiometricUseCase
import ls.gear.flow.domain.usecase.setting.CopyToClipBoardUseCase
import ls.gear.flow.domain.usecase.user.GetLocalUserUseCase
import ls.gear.flow.ui.screen.base.BaseViewModel
import ls.gear.flow.ui.screen.base.Message
import ls.gear.flow.ui.screen.main.home.setting.state.SettingsAction
import ls.gear.flow.ui.screen.main.home.setting.state.SettingsState

class SettingsViewModel(
    private val getLocalUserUseCase: GetLocalUserUseCase,
    private val getSettingsUseCase: GetSettingsUseCase,
    getAppVersionUseCase: GetAppVersionUseCase,
    private val saveUseBiometricUseCase: SaveUseBiometricUseCase,
    isBioMetricAvailableUseCase: IsBioMetricAvailableUseCase,
    private val copyToClipBoardUseCase: CopyToClipBoardUseCase
) : BaseViewModel<SettingsAction, SettingsState>(
    initialValue = SettingsState(
        biometricAvailable = isBioMetricAvailableUseCase(),
        appVersion = getAppVersionUseCase(),
        loading = true
    )
) {
    init {
        dispatch(SettingsAction.GetData)
    }

    override suspend fun reduce(action: SettingsAction) = when (action) {
        SettingsAction.GetData -> getData(uiState.value)
        is SettingsAction.ChangeUseBioMetric -> changeUseBioMetric(action, uiState.value)
        SettingsAction.ShowDebugInfo -> showDebugInfo()
        SettingsAction.CopyUserId -> copyUserId(uiState.value)
        SettingsAction.ClearMessage -> clearMessage()
    }

    private fun getData(state: SettingsState): SettingsState {
        val user = getLocalUserUseCase()
            ?: return state.copy(loading = false, error = GearFlowError.UserNotFound)
        val settings = getSettingsUseCase().getOrNull() ?: Settings()
        return state.copy(
            user = user,
            settings = settings,
            loading = false
        )
    }

    private suspend fun changeUseBioMetric(
        action: SettingsAction.ChangeUseBioMetric,
        state: SettingsState
    ): SettingsState {
        saveUseBiometricUseCase(action.useBioMetric)
        val settings = state.settings?.copy(useBioMetric = action.useBioMetric)
        return state.copy(settings = settings)
    }

    private fun showDebugInfo() = uiState.value.copy(showDebugInfo = true)

    private fun copyUserId(state: SettingsState): SettingsState {
        return state.user?.id?.let {
            copyToClipBoardUseCase(it)
            state.copy(message = Message.Success.CopyUserId)
        } ?: state
    }

    private fun clearMessage() = uiState.value.copy(message = null)
}
