package ls.gear.flow.domain.usecase.order

import ls.gear.flow.domain.error.GearFlowError
import ls.gear.flow.domain.repository.DemandItemRepository
import ls.gear.flow.domain.usecase.CheckConnectionUseCase

class OrderItemsUseCase(
    private val checkConnectionUseCase: CheckConnectionUseCase,
    private val demandItemRepository: DemandItemRepository
) {

    suspend operator fun invoke(): Result<Unit> {
        return if (checkConnectionUseCase()) {
            demandItemRepository.orderItems()
        } else {
            Result.failure(GearFlowError.Network.NoInternet)
        }
    }
}
