package ls.gear.flow.domain.repository

import ls.gear.flow.domain.model.PersonalSizes

interface PersonalSizesRepository {
    suspend fun getByUserId(userId: String?): Result<PersonalSizes>
    suspend fun save(userId: String?, sizes: PersonalSizes): Result<Unit>
}
