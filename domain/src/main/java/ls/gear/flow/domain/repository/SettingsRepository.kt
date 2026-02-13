package ls.gear.flow.domain.repository

import ls.gear.flow.domain.model.Settings

interface SettingsRepository {
    fun get(): Result<Settings>
    fun save(useBiometric: Boolean): Result<Unit>
}
