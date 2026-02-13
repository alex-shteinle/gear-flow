package ls.gear.flow.data.network

import retrofit2.Response
import ls.gear.flow.domain.error.GearFlowError

fun <T> Response<T>.toResult(): Result<T> {
    val body = body()
    return if (isSuccessful && body != null) {
        Result.success(body)
    } else {
        Result.failure(GearFlowError.Network.ServerError(message()))
    }
}
