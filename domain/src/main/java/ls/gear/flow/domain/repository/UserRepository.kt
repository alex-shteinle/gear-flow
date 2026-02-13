package ls.gear.flow.domain.repository

import ls.gear.flow.domain.model.User

interface UserRepository {
    suspend fun getAll(): Result<List<User>>
}
