package ls.gear.flow.domain.error

/**
 * Extension to map [Throwable] into [GearFlowError]
 */
fun Throwable.toGearFlowError() = when (this) {
    is GearFlowError -> this
    else -> GearFlowError.General(message ?: "There was no message in cause throwable")
}
