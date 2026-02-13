package ls.gear.flow.ui.screen.main.auth.pincodelogin.state

sealed class PinCodeLoginAction {
    object GetPinCode : PinCodeLoginAction()
    data class NumberClick(val number: Int) : PinCodeLoginAction()
    object BackSpaceClick : PinCodeLoginAction()
    object BioMetricClick : PinCodeLoginAction()
    object BioMetricCancel : PinCodeLoginAction()
    object PinCodeFilled : PinCodeLoginAction()
    object ClearMessage : PinCodeLoginAction()
}
