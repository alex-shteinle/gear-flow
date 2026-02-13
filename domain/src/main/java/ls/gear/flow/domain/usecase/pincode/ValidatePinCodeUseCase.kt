package ls.gear.flow.domain.usecase.pincode

import ls.gear.flow.ext.hasOnlyNumbers

private const val MINIMUM_PINCODE_LENGTH = 1

class ValidatePinCodeUseCase {

    operator fun invoke(pinCodeLength: Int, vararg pinCode: String): Boolean {
        if (pinCodeLength < MINIMUM_PINCODE_LENGTH) return false
        return pinCode.all { it.length == pinCodeLength && it.hasOnlyNumbers() }
    }
}
