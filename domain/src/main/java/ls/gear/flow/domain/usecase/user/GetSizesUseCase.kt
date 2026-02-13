package ls.gear.flow.domain.usecase.user

import ls.gear.flow.domain.model.PersonalSizes
import ls.gear.flow.domain.repository.UserCacheRepository

class GetSizesUseCase(private val userCacheRepository: UserCacheRepository) {

    operator fun invoke(): PersonalSizes {
        return userCacheRepository.get()?.sizes ?: PersonalSizes()
    }
}
