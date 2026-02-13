package ls.gear.flow.ui.screen.main.home.setting.state

sealed interface SettingsAction {
    object CopyUserId : SettingsAction
    object ShowDebugInfo : SettingsAction
    object GetData : SettingsAction
    object ClearMessage : SettingsAction
    data class ChangeUseBioMetric(val useBioMetric: Boolean) : SettingsAction
}
