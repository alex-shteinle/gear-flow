package ls.gear.flow.ui.screen.base

import ls.gear.flow.domain.error.GearFlowError

interface UiState {
    val message: Message?
    val error: GearFlowError?
}
