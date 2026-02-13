package ls.gear.flow.data.repoimpl

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import ls.gear.flow.data.mapper.toDomain
import ls.gear.flow.data.network.api.AnalyticsApi
import ls.gear.flow.data.network.toResult
import ls.gear.flow.domain.error.toGearFlowError
import ls.gear.flow.domain.model.DemandItem
import ls.gear.flow.domain.repository.DemandItemRepository

private const val TEST_DELAY_MS = 1000L

class DemandItemRepositoryImpl(
    private val dispatcher: CoroutineDispatcher,
    private val analyticsApi: AnalyticsApi
) : DemandItemRepository {

    override suspend fun fetchAvailableItems(userId: String): Result<List<DemandItem>> =
        withContext(dispatcher) {
            return@withContext try {
                analyticsApi.fetchDemandByUserId(userId)
                    .toResult()
                    .map { it.toDomain().items }
            } catch (exception: Throwable) {
                Result.failure(exception.toGearFlowError())
            }
        }

    override suspend fun orderItems(): Result<Unit> = withContext(dispatcher) {
        delay(TEST_DELAY_MS)
        return@withContext Result.success(Unit)
    }
}
