package ls.gear.flow.ui.screen.base

import ls.gear.flow.domain.error.GearFlowError

fun GearFlowError.toMessage() = when (this) {
    is GearFlowError.Network -> Message.Error.Network
    else -> null
}
