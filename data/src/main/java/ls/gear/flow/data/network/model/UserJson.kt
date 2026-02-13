package ls.gear.flow.data.network.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserJson(
    val id: String?,
    val unitId: String?,
    val unitOrderId: Int?,
    val firstName: String?,
    val lastName: String?,
    val middleName: String?,
    val tittleId: String?,
    val categoryId: Int?,
    val items: List<StuffItemJson?>
)
