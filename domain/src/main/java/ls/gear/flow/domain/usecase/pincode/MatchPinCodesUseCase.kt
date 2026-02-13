package ls.gear.flow.domain.usecase.pincode

import ls.gear.flow.domain.error.GearFlowError

class MatchPinCodesUseCase(private val validatePinCodeUseCase: ValidatePinCodeUseCase) {

    operator fun invoke(firstPin: String, secondPin: String, pinCodeLength: Int): Result<Unit> {
        if (!validatePinCodeUseCase(pinCodeLength, firstPin, secondPin)) {
            return Result.failure(GearFlowError.InvalidPinCode)
        }
        return if (firstPin == secondPin) {
            Result.success(Unit)
        } else {
            Result.failure(GearFlowError.PinCodesDidNotMatch)
        }
    }
}
