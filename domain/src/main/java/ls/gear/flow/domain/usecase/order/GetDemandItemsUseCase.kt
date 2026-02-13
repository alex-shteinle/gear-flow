package ls.gear.flow.domain.usecase.order

import ls.gear.flow.domain.error.GearFlowError
import ls.gear.flow.domain.model.DemandItem
import ls.gear.flow.domain.repository.DemandItemRepository
import ls.gear.flow.domain.usecase.CheckConnectionUseCase
import ls.gear.flow.domain.usecase.user.GetLocalUserUseCase

class GetDemandItemsUseCase(
    private val getLocalUserUseCase: GetLocalUserUseCase,
    private val checkConnectionUseCase: CheckConnectionUseCase,
    private val demandItemRepository: DemandItemRepository
) {

    suspend operator fun invoke(): Result<List<DemandItem>> {
        val userId = getLocalUserUseCase()?.id ?: return Result.failure(GearFlowError.UserNotFound)
        return if (checkConnectionUseCase()) {
            demandItemRepository.fetchAvailableItems(userId)
        } else {
            Result.failure(GearFlowError.Network.NoInternet)
        }
    }
}
