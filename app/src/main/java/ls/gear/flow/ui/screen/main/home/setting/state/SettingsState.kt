package ls.gear.flow.ui.screen.main.home.setting.state

import ls.gear.flow.domain.error.GearFlowError
import ls.gear.flow.domain.model.AppVersion
import ls.gear.flow.domain.model.Settings
import ls.gear.flow.domain.model.User
import ls.gear.flow.ui.screen.base.Message

data class SettingsState(
    val user: User? = null,
    val appVersion: AppVersion,
    val showDebugInfo: Boolean = false,
    val biometricAvailable: Boolean = false,
    val settings: Settings? = null,
    val loading: Boolean = false,
    val error: GearFlowError? = null,
    val message: Message? = null
)
