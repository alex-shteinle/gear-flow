package ls.gear.flow.data.repoimpl

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ls.gear.flow.data.db.PersonalSizesDao
import ls.gear.flow.data.mapper.toDb
import ls.gear.flow.data.mapper.toDomain
import ls.gear.flow.domain.error.GearFlowError
import ls.gear.flow.domain.repository.UserCacheRepository
import ls.gear.flow.domain.model.PersonalSizes
import ls.gear.flow.domain.repository.PersonalSizesRepository
import ls.gear.flow.log.GearFlowLogger

class PersonalSizesRepositoryImpl(
    private val dispatcher: CoroutineDispatcher,
    private val personalSizesDao: PersonalSizesDao,
    private val userCacheRepository: UserCacheRepository,
    private val logger: GearFlowLogger
) : PersonalSizesRepository {

    override suspend fun getByUserId(
        userId: String?
    ): Result<PersonalSizes> = withContext(dispatcher) {
        if (userId.isNullOrBlank()) return@withContext Result.failure(GearFlowError.InvalidUserId)
        val sizes = personalSizesDao.getByUserId(userId)
            .firstOrNull()
            ?.toDomain() ?: PersonalSizes()
        userCacheRepository.updateSizes(sizes)
        logger.logDebug(this, "getSizesByUserId: $sizes")
        return@withContext Result.success(sizes)
    }

    override suspend fun save(
        userId: String?,
        sizes: PersonalSizes
    ): Result<Unit> = withContext(dispatcher) {
        if (userId.isNullOrBlank()) return@withContext Result.failure(GearFlowError.InvalidUserId)
        personalSizesDao.insert(sizes.toDb(userId))
        userCacheRepository.updateSizes(sizes)
        return@withContext Result.success(Unit)
    }
}
