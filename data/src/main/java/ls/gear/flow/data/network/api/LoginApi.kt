package ls.gear.flow.data.network.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import ls.gear.flow.data.network.model.LoginJson
import ls.gear.flow.data.network.model.LoginResponseJson

interface LoginApi {

    @POST("/myno/login")
    suspend fun login(@Body loginJson: LoginJson): Response<LoginResponseJson>
}
