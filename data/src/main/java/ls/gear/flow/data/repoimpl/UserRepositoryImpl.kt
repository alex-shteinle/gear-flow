package ls.gear.flow.data.repoimpl

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ls.gear.flow.data.mapper.toDomain
import ls.gear.flow.data.network.api.UserApi
import ls.gear.flow.data.network.toResult
import ls.gear.flow.domain.error.toGearFlowError
import ls.gear.flow.domain.model.User
import ls.gear.flow.domain.repository.UserRepository
import ls.gear.flow.log.GearFlowLogger

class UserRepositoryImpl(
    private val dispatcher: CoroutineDispatcher,
    private val userApi: UserApi,
    private val logger: GearFlowLogger
) : UserRepository {

    override suspend fun getAll(): Result<List<User>> = withContext(dispatcher) {
        return@withContext try {
            userApi.fetchUsers()
                .toResult()
                .map { userJson -> userJson.map { it.toDomain() } }
        } catch (throwable: Throwable) {
            logger.logError(this, throwable)
            Result.failure(throwable.toGearFlowError())
        }
    }
}
