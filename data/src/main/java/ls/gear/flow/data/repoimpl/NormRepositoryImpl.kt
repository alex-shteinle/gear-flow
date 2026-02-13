package ls.gear.flow.data.repoimpl

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ls.gear.flow.data.mapper.toDomain
import ls.gear.flow.data.network.api.AnalyticsApi
import ls.gear.flow.data.network.toResult
import ls.gear.flow.domain.error.toGearFlowError
import ls.gear.flow.domain.model.Norm
import ls.gear.flow.domain.repository.NormRepository
import ls.gear.flow.log.GearFlowLogger

class NormRepositoryImpl(
    private val dispatcher: CoroutineDispatcher,
    private val analyticsApi: AnalyticsApi,
    private val logger: GearFlowLogger
) : NormRepository {

    override suspend fun getByUserId(userId: String): Result<Norm> = withContext(dispatcher) {
        return@withContext try {
            analyticsApi.fetchNormByUserId(userId)
                .toResult()
                .map { it.toDomain() }
        } catch (exception: Throwable) {
            logger.logError(this@NormRepositoryImpl, exception)
            Result.failure(exception.toGearFlowError())
        }
    }
}
