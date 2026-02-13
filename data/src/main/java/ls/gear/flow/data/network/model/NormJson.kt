package ls.gear.flow.data.network.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NormJson(
    val soldierId: String?,
    val provision: String?,
    val items: List<NormItemJson>?
)
