package ls.gear.flow.domain.repository

interface LoginRepository {
    suspend fun login(): Result<Unit>
}
