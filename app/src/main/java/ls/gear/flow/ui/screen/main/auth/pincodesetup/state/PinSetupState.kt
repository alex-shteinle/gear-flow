package ls.gear.flow.ui.screen.main.auth.pincodesetup.state

import ls.gear.flow.ui.screen.base.Message
import ls.gear.flow.ui.screen.main.auth.PinConst

data class PinSetupState(
    val savedPin: String = "",
    val actualPin: String = "",
    val message: Message? = null,
    val setupState: SetupState = SetupState.INITIAL
) {
    val isNotOverFilled get() = actualPin.length <= PinConst.PINCODE_LENGTH
}
