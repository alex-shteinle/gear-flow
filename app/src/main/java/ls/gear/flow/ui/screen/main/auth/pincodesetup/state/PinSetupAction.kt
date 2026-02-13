package ls.gear.flow.ui.screen.main.auth.pincodesetup.state

sealed interface PinSetupAction {
    data class UpdatePin(val pin: String) : PinSetupAction
    object CheckPinConfirm : PinSetupAction
    object ValidatePin : PinSetupAction
    object ShowTouchIdDialog : PinSetupAction
    object HideTouchIdDialog : PinSetupAction
    data class SaveTouchIdPref(val useBiometric: Boolean) : PinSetupAction
    object ClearMessage : PinSetupAction
}
