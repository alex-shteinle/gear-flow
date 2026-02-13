package ls.gear.flow.data.network.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginJson(
    val login: String,
    val password: String
)
