package ls.gear.flow.data.repoimpl

import android.content.SharedPreferences
import androidx.core.content.edit
import ls.gear.flow.domain.error.GearFlowError
import ls.gear.flow.domain.model.Settings
import ls.gear.flow.domain.repository.SettingsRepository

private const val KEY_BIOMETRIC = "KEY_BIOMETRIC"

class SettingsRepositoryImpl(
    private val sharedPreferences: SharedPreferences
) : SettingsRepository {

    override fun get(): Result<Settings> {
        return Result.success(
            Settings(useBioMetric = sharedPreferences.getBoolean(KEY_BIOMETRIC, false))
        )
    }

    override fun save(useBiometric: Boolean): Result<Unit> {
        return try {
            sharedPreferences.edit {
                putBoolean(KEY_BIOMETRIC, useBiometric)
            }
            Result.success(Unit)
        } catch (error: Throwable) {
            Result.failure(GearFlowError.SettingsUnsaved)
        }
    }
}
