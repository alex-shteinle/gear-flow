package ls.gear.flow.domain.repository

import kotlinx.coroutines.flow.StateFlow
import ls.gear.flow.domain.model.PersonalSizes
import ls.gear.flow.domain.model.User

interface UserCacheRepository {
    fun get(): User?
    fun observe(): StateFlow<User?>
    fun update(user: User)
    fun updateSizes(sizes: PersonalSizes)
}
