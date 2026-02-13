package ls.gear.flow.data.network.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NormItemJson(
    val itemId: String?,
    val itemName: String?,
    val itemQty: Int?,
    val usePeriod: Int?,
    val mu: String?,
    val typeGroupName: String?
)
