package ls.gear.flow.domain.usecase.user

import ls.gear.flow.domain.error.GearFlowError
import ls.gear.flow.domain.model.DEFAULT_NORM
import ls.gear.flow.domain.model.NormItem
import ls.gear.flow.domain.model.PersonalSizes
import ls.gear.flow.domain.model.StuffItem
import ls.gear.flow.domain.model.User
import ls.gear.flow.domain.repository.UserCacheRepository
import ls.gear.flow.domain.repository.LoginRepository
import ls.gear.flow.domain.repository.NormRepository
import ls.gear.flow.domain.repository.PersonalSizesRepository
import ls.gear.flow.domain.repository.UserRepository
import ls.gear.flow.domain.usecase.CheckConnectionUseCase

class GetRemoteUserUseCase(
    private val checkConnectionUseCase: CheckConnectionUseCase,
    private val loginRepository: LoginRepository,
    private val userRepository: UserRepository,
    private val personalSizesRepository: PersonalSizesRepository,
    private val userCacheRepository: UserCacheRepository,
    private val normRepository: NormRepository
) {

    suspend operator fun invoke(): Result<User> {
        return getRemoteUserAndUpdateUserCache()
    }

    private suspend fun getRemoteUserAndUpdateUserCache(): Result<User> = runCatching {
        if (!checkConnectionUseCase()) throw GearFlowError.Network.NoInternet
        loginRepository.login().getOrThrow()
        val users = userRepository.getAll().getOrThrow()
        val user = findUserWithItemsOrFirst(users)
        val updatedUserWithSizes = updateUserSizes(user)
        val norms = normRepository.getByUserId(user.id).getOrNull()?.items
        val updatedUser = norms?.let {
            val items = mergeItemsAndNorms(updatedUserWithSizes.items, it)
            updatedUserWithSizes.copy(items = items)
        } ?: updatedUserWithSizes
        userCacheRepository.update(updatedUser)
        updatedUser
    }

    private fun findUserWithItemsOrFirst(users: List<User>): User {
        return users
            .filter { it.items.isNotEmpty() }
            .minByOrNull { it.lastName }
            ?: users.first()
    }

    // We retrieve sizes from local db temporary. They should be provided from server
    private suspend fun updateUserSizes(user: User): User {
        val sizes = personalSizesRepository.getByUserId(user.id).getOrNull() ?: PersonalSizes()
        return user.copy(sizes = sizes)
    }

    private fun mergeItemsAndNorms(items: List<StuffItem>, norms: List<NormItem>): List<StuffItem> {
        return items.map { item -> item.mergeWithNorm(norms.withId(item.id)) }
    }

    private fun StuffItem.mergeWithNorm(norm: NormItem?) = this.copy(
        normQuantity = norm?.quantity ?: DEFAULT_NORM
    )

    private fun List<NormItem>.withId(itemId: String) = find { it.itemId == itemId }
}
