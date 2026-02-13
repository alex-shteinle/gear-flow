package ls.gear.flow.domain.usecase.setting

import ls.gear.flow.domain.model.Settings
import ls.gear.flow.domain.repository.SettingsRepository

class GetSettingsUseCase(private val settingsRepository: SettingsRepository) {

    operator fun invoke(): Result<Settings> {
        return settingsRepository.get()
    }
}
