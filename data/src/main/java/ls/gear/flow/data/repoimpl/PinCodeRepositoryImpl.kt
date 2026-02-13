package ls.gear.flow.data.repoimpl

import android.content.SharedPreferences
import androidx.core.content.edit
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ls.gear.flow.domain.error.GearFlowError
import ls.gear.flow.domain.repository.PinCodeRepository

private const val KEY_PINCODE = "KEY_PINCODE"

class PinCodeRepositoryImpl(
    private val dispatcher: CoroutineDispatcher,
    private val sharedPreferences: SharedPreferences
) : PinCodeRepository {

    override suspend fun get(): Result<String> = withContext(dispatcher) {
        val pinCode = sharedPreferences.getString(KEY_PINCODE, "")
            ?: return@withContext Result.failure(GearFlowError.PinCodeUnsaved)
        return@withContext Result.success(pinCode)
    }

    override suspend fun save(pinCode: String): Result<Unit> = withContext(dispatcher) {
        return@withContext try {
            sharedPreferences.edit {
                putString(KEY_PINCODE, pinCode)
            }
            Result.success(Unit)
        } catch (error: Throwable) {
            Result.failure(GearFlowError.PinCodeUnsaved)
        }
    }
}
