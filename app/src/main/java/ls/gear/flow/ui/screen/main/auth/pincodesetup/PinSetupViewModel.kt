package ls.gear.flow.ui.screen.main.auth.pincodesetup

import ls.gear.flow.domain.error.GearFlowError
import ls.gear.flow.domain.usecase.pincode.SavePinCodeUseCase
import ls.gear.flow.domain.usecase.biometric.SaveUseBiometricUseCase
import ls.gear.flow.domain.usecase.pincode.MatchPinCodesUseCase
import ls.gear.flow.domain.usecase.pincode.ValidatePinCodeUseCase
import ls.gear.flow.log.GearFlowLogger
import ls.gear.flow.ui.screen.base.BaseViewModel
import ls.gear.flow.ui.screen.main.auth.pincodesetup.state.PinSetupAction
import ls.gear.flow.ui.screen.main.auth.pincodesetup.state.PinSetupState
import ls.gear.flow.ui.screen.main.auth.pincodesetup.state.SetupState
import ls.gear.flow.ext.hasOnlyNumbers
import ls.gear.flow.ui.screen.base.Message
import ls.gear.flow.ui.screen.main.auth.PinConst

class PinSetupViewModel(
    private val savePinCodeUseCase: SavePinCodeUseCase,
    private val saveUseBiometricUseCase: SaveUseBiometricUseCase,
    private val validatePinCodeUseCase: ValidatePinCodeUseCase,
    private val matchPinCodesUseCase: MatchPinCodesUseCase,
    private val logger: GearFlowLogger
) : BaseViewModel<PinSetupAction, PinSetupState>(initialValue = PinSetupState()) {

    private val savedPin get() = uiState.value.savedPin
    private val actualPin get() = uiState.value.actualPin

    override suspend fun reduce(action: PinSetupAction): PinSetupState = when (action) {
        is PinSetupAction.UpdatePin -> updatePinCode(uiState.value, action.pin)
        PinSetupAction.CheckPinConfirm -> checkConfirmedPinCode(uiState.value)
        PinSetupAction.ValidatePin -> validatePinCode(uiState.value)
        PinSetupAction.HideTouchIdDialog -> hideTouchIdDialog()
        PinSetupAction.ShowTouchIdDialog -> showTouchIdDialog()
        is PinSetupAction.SaveTouchIdPref -> saveTouchIdPref(action.useBiometric)
        PinSetupAction.ClearMessage -> clearMessage()
    }

    private fun updatePinCode(state: PinSetupState, newPin: String): PinSetupState {
        return if (newPin.hasOnlyNumbers()) {
            state.copy(actualPin = newPin)
        } else {
            state
        }
    }

    private suspend fun checkConfirmedPinCode(state: PinSetupState): PinSetupState {
        return matchPinCodesUseCase(
            actualPin,
            savedPin,
            PinConst.PINCODE_LENGTH
        )
            .map { savePinCodeUseCase(actualPin) }
            .fold(
                onSuccess = { state.copy(setupState = SetupState.CONFIRMED) },
                onFailure = {
                    when (it) {
                        is GearFlowError.PinCodesDidNotMatch ->
                            state.copy(actualPin = "", message = Message.Error.PinCodesDidntMatch)
                        else -> state
                    }
                }
            )
    }

    private fun validatePinCode(state: PinSetupState): PinSetupState {
        return if (validatePinCodeUseCase(PinConst.PINCODE_LENGTH, actualPin)) {
            state.copy(savedPin = actualPin, actualPin = "", setupState = SetupState.NEED_CONFIRM)
        } else {
            state
        }
    }

    private fun hideTouchIdDialog() = uiState.value.copy(setupState = SetupState.HIDE_TOUCH_ID)

    private fun showTouchIdDialog() = uiState.value.copy(setupState = SetupState.SHOW_TOUCH_ID)

    private suspend fun saveTouchIdPref(useBioMetric: Boolean): PinSetupState {
        saveUseBiometricUseCase(useBioMetric).exceptionOrNull()?.let {
            logger.logError(this, GearFlowError.SettingsUnsaved)
        }
        return uiState.value.copy(setupState = SetupState.SUCCESS)
    }

    private fun clearMessage() = uiState.value.copy(message = null)
}
