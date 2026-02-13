package ls.gear.flow.domain.repository

import ls.gear.flow.domain.model.DemandItem

interface DemandItemRepository {
    suspend fun fetchAvailableItems(userId: String): Result<List<DemandItem>>
    suspend fun orderItems(): Result<Unit>
}
