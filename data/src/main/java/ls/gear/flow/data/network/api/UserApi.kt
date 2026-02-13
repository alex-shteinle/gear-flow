package ls.gear.flow.data.network.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import ls.gear.flow.data.network.model.UserJson

interface UserApi {

    @GET("/myno/soldiers")
    suspend fun fetchUsers(): Response<List<UserJson>>

    @GET("/myno/soldiers/{id}")
    suspend fun fetchUserById(@Path("id") id: String): Response<UserJson>
}
