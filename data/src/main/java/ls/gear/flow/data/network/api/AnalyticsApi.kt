package ls.gear.flow.data.network.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import ls.gear.flow.data.network.model.DemandJson
import ls.gear.flow.data.network.model.NormJson

interface AnalyticsApi {

    @GET("/myno/analytics/demand/soldiers/{id}")
    suspend fun fetchDemandByUserId(@Path("id") id: String): Response<DemandJson>

    @GET("/myno/analytics/norms/soldiers/{id}")
    suspend fun fetchNormByUserId(@Path("id") id: String): Response<NormJson>
}
