package ls.gear.flow.data.mapper

import ls.gear.flow.data.network.model.DemandJson
import ls.gear.flow.domain.model.Demand

fun DemandJson.toDomain() = Demand(
    soldierId = soldierId.orEmpty(),
    provision = provision.orEmpty(),
    items = items?.map { it.toDomain() } ?: emptyList()
)
