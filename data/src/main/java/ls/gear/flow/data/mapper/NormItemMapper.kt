package ls.gear.flow.data.mapper

import ls.gear.flow.data.network.model.NormItemJson
import ls.gear.flow.domain.model.NormItem

fun NormItemJson.toDomain() = NormItem(
    itemId = itemId.orEmpty(),
    itemName = itemName.orEmpty(),
    quantity = itemQty ?: 0,
    usePeriodInMonths = usePeriod ?: 0,
    measureUnit = mu.orEmpty(),
    typeGroupName = typeGroupName.orEmpty()
)
