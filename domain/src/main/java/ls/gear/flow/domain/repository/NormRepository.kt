package ls.gear.flow.domain.repository

import ls.gear.flow.domain.model.Norm

interface NormRepository {
    suspend fun getByUserId(userId: String): Result<Norm>
}
