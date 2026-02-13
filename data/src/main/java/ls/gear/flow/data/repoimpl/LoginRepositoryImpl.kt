package ls.gear.flow.data.repoimpl

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ls.gear.flow.data.network.api.LoginApi
import ls.gear.flow.data.network.model.LoginJson
import ls.gear.flow.data.network.toResult
import ls.gear.flow.domain.error.toGearFlowError
import ls.gear.flow.domain.repository.LoginRepository
import ls.gear.flow.log.GearFlowLogger

class LoginRepositoryImpl(
    private val dispatcher: CoroutineDispatcher,
    private val loginApi: LoginApi,
    private val logger: GearFlowLogger
) : LoginRepository {

    private val loginJson = LoginJson(login = "sa", password = "test")

    override suspend fun login(): Result<Unit> = withContext(dispatcher) {
        return@withContext try {
            loginApi.login(loginJson)
                .toResult()
                .map { }
        } catch (exception: Exception) {
            logger.logError(this@LoginRepositoryImpl, exception)
            Result.failure(exception.toGearFlowError())
        }
    }
}
