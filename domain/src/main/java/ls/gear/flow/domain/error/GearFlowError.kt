package ls.gear.flow.domain.error

/**
 * Class to encapsulate app errors
 */
sealed class GearFlowError(override val message: String) : Throwable() {
    data class General(override val message: String) : GearFlowError(message = message)
    object NoData : GearFlowError(message = "There is no data")
    object UserNotFound : GearFlowError(message = "User not found")
    object InvalidPinCode : GearFlowError(message = "Invalid pinCode")
    object InvalidUserId : GearFlowError(message = "Invalid user id")
    object PinCodeUnsaved : GearFlowError(message = "PinCode wasn't saved")
    object SettingsUnsaved : GearFlowError(message = "Settings weren't saved")
    object PinCodesDidNotMatch : GearFlowError(message = "PinCodes didn't match")
    object SizesWereNotSaved : GearFlowError(message = "Sizes weren't saved")
    sealed class Network(override val message: String) : GearFlowError(message) {
        data class ServerError(override val message: String = "") : Network(
            message = message.ifEmpty { "Server error" }
        )
        object NoInternet : Network(message = "There is no internet connection")
    }
    object BioMetric : GearFlowError(message = "Biometric authentication failure")
}
