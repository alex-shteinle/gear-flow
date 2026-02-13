package ls.gear.flow.data.repoimpl

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ls.gear.flow.domain.model.PersonalSizes
import ls.gear.flow.domain.model.User
import ls.gear.flow.domain.repository.UserCacheRepository

class UserCacheRepositoryImpl : UserCacheRepository {

    private val _userCache = MutableStateFlow<User?>(null)

    override fun get() = _userCache.value

    override fun observe() = _userCache.asStateFlow()

    override fun update(user: User) {
        _userCache.update { user }
    }

    override fun updateSizes(sizes: PersonalSizes) {
        _userCache.value?.let {
            update(it.copy(sizes = sizes))
        }
    }
}
