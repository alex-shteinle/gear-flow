package ls.gear.flow.domain.usecase.user

import ls.gear.flow.domain.model.User
import ls.gear.flow.domain.repository.UserCacheRepository

class GetLocalUserUseCase(private val userCacheRepository: UserCacheRepository) {

    operator fun invoke(): User? {
        return userCacheRepository.get()
    }
}
