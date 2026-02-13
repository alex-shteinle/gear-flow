package ls.gear.flow.domain.usecase.user

import ls.gear.flow.domain.model.User

class GetUserUseCase(
    private val getRemoteUserUseCase: GetRemoteUserUseCase,
    private val getLocalUserUseCase: GetLocalUserUseCase
) {

    suspend operator fun invoke(): Result<User> {
        return getLocalUserUseCase()?.let { Result.success(it) } ?: getRemoteUserUseCase()
    }
}
