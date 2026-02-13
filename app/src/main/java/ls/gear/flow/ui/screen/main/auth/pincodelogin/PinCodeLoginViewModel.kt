package ls.gear.flow.ui.screen.main.auth.pincodelogin

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ls.gear.flow.domain.error.GearFlowError
import ls.gear.flow.domain.usecase.biometric.IsBioMetricAvailableUseCase
import ls.gear.flow.domain.usecase.biometric.ShouldUseBioMetricUseCase
import ls.gear.flow.domain.usecase.pincode.GetPinCodeUseCase
import ls.gear.flow.ui.screen.base.BaseViewModel
import ls.gear.flow.ui.screen.base.Message
import ls.gear.flow.ui.screen.main.auth.pincodelogin.state.PinCodeLoginAction
import ls.gear.flow.ui.screen.main.auth.pincodelogin.state.PinCodeLoginState

class PinCodeLoginViewModel(
    isBioMetricAvailableUseCase: IsBioMetricAvailableUseCase,
    shouldUseBioMetricUseCase: ShouldUseBioMetricUseCase,
    private val getPinCodeUseCase: GetPinCodeUseCase,
) : BaseViewModel<PinCodeLoginAction, PinCodeLoginState>(
    initialValue = PinCodeLoginState(
        biometricAvailable = isBioMetricAvailableUseCase(),
        shouldUseBiometric = shouldUseBioMetricUseCase()
    )
) {

    private lateinit var savedPinCode: String
    private val pinCode get() = uiState.value.pin

    init {
        viewModelScope.launch {
            dispatch(PinCodeLoginAction.GetPinCode)
        }
    }

    override suspend fun reduce(action: PinCodeLoginAction): PinCodeLoginState = when (action) {
        PinCodeLoginAction.GetPinCode -> getPinCode(uiState.value)
        PinCodeLoginAction.BackSpaceClick -> onBackSpaceClick(uiState.value)
        PinCodeLoginAction.BioMetricClick -> showBioMetric(uiState.value)
        is PinCodeLoginAction.NumberClick -> onNumberClick(uiState.value, number = action.number)
        PinCodeLoginAction.PinCodeFilled -> comparePinCodes(uiState.value)
        PinCodeLoginAction.BioMetricCancel -> cancelBioMetric(uiState.value)
        PinCodeLoginAction.ClearMessage -> clearMessage()
    }

    private suspend fun getPinCode(state: PinCodeLoginState) = getPinCodeUseCase().fold(
        onSuccess = {
            savedPinCode = it
            state.copy(pin = "", error = null)
        },
        onFailure = { state.copy(error = GearFlowError.InvalidPinCode) }
    )

    private fun onBackSpaceClick(state: PinCodeLoginState) = state.copy(
        pin = pinCode.dropLast(1),
        error = null
    )

    private fun onNumberClick(state: PinCodeLoginState, number: Int) = state.copy(
        pin = pinCode.plus(number),
        error = null
    )

    private fun comparePinCodes(state: PinCodeLoginState): PinCodeLoginState {
        val matched = pinCode.contentEquals(savedPinCode)
        return state.copy(
            matched = matched,
            pin = "",
            message = if (matched) null else Message.Error.InvalidPinCode
        )
    }

    private fun showBioMetric(state: PinCodeLoginState) = state.copy(
        shouldUseBiometric = true,
        error = null
    )

    private fun cancelBioMetric(state: PinCodeLoginState) = state.copy(shouldUseBiometric = false)

    private fun clearMessage() = uiState.value.copy(message = null)
}
