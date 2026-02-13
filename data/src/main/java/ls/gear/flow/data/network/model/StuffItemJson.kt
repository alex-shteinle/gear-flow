package ls.gear.flow.data.network.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StuffItemJson(
    val id: String?,
    val soldierId: String?,
    val name: String?,
    val quantity: Int?,
    val issueDate: String?,
    val mu: String?,
    val typeGroupName: String?,
    val handoutId: String?
)
