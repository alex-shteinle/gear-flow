package ls.gear.flow.data.network.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginResponseJson(
    val unitId: String?,
    val userName: String?,
    val roles: List<RoleJson>?,
    val roleGroups: List<RoleGroupJson>?
)
