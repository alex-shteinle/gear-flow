package ls.gear.flow.ui.screen.main.auth.pincodelogin.state

import ls.gear.flow.domain.error.GearFlowError
import ls.gear.flow.ui.screen.base.Message
import ls.gear.flow.ui.screen.main.auth.PinConst

data class PinCodeLoginState(
    val pin: String = "",
    val error: GearFlowError? = null,
    val message: Message? = null,
    val biometricAvailable: Boolean = false,
    val shouldUseBiometric: Boolean = false,
    val matched: Boolean = false
) {
    val showBiometric = biometricAvailable && shouldUseBiometric
    val pinIsFilled get() = pin.length == PinConst.PINCODE_LENGTH
}
