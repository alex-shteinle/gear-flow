package ls.gear.flow.domain.repository

interface PinCodeRepository {
    suspend fun get(): Result<String>
    suspend fun save(pinCode: String): Result<Unit>
}
