package ls.gear.flow.data.network.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RoleJson(
    val id: String?,
    val name: String?
)
