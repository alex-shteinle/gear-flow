package ls.gear.flow.data.mapper

import android.util.Log
import ls.gear.flow.data.network.model.StuffItemJson
import ls.gear.flow.domain.model.DEFAULT_NORM
import ls.gear.flow.domain.model.StuffItem
import ls.gear.flow.domain.model.StuffItemGroupType
import ls.gear.flow.ext.enumValueOfOrNull
import ls.gear.flow.log.DEFAULT_TAG
import java.time.LocalDate

private const val DEFAULT_MEASURE_UNIT = "unit"

fun StuffItemJson.toDomain() = StuffItem(
    id = id.orEmpty(),
    name = name.orEmpty(),
    quantity = quantity ?: 0,
    measureUnit = if (mu.isNullOrBlank()) DEFAULT_MEASURE_UNIT else mu,
    issueDate = issueDate.toLocalDateOrNull(),
    type = enumValueOfOrNull<StuffItemGroupType>(typeGroupName) ?: StuffItemGroupType.ALL_PROPERTY,
    normQuantity = DEFAULT_NORM
)

private fun String?.toLocalDateOrNull(): LocalDate? {
    return if (isNullOrBlank()) {
        null
    } else {
        try {
            LocalDate.parse(this)
        } catch (throwable: IllegalArgumentException) {
            Log.e(DEFAULT_TAG, "String?.toLocalDateOrNull: ${throwable.message}")
            null
        }
    }
}
