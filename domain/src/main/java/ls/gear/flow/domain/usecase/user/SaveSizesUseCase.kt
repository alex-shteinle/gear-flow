package ls.gear.flow.domain.usecase.user

import ls.gear.flow.domain.model.PersonalSizes
import ls.gear.flow.domain.repository.PersonalSizesRepository

class SaveSizesUseCase(
    private val getUserUseCase: GetUserUseCase,
    private val personalSizesRepository: PersonalSizesRepository
) {

    suspend operator fun invoke(sizes: PersonalSizes): Result<Unit> {
        val userId = getUserUseCase().getOrNull()?.id
        return personalSizesRepository.save(userId, sizes)
    }
}
