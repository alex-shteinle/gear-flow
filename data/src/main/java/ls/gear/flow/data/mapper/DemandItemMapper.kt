package ls.gear.flow.data.mapper

import ls.gear.flow.data.network.model.DemandItemJson
import ls.gear.flow.domain.model.DemandItem

fun DemandItemJson.toDomain() = DemandItem(
    itemId = itemId.orEmpty(),
    itemName = itemName.orEmpty(),
    quantity = itemQty ?: 0,
    usePeriodInMonths = usePeriod ?: 0,
    measureUnit = mu.orEmpty(),
    typeGroupName = typeGroupName.orEmpty()
)
