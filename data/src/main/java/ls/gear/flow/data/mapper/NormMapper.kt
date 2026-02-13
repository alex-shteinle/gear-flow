package ls.gear.flow.data.mapper

import ls.gear.flow.data.network.model.NormJson
import ls.gear.flow.domain.model.Norm

fun NormJson.toDomain() = Norm(
    soldierId = soldierId.orEmpty(),
    provision = provision.orEmpty(),
    items = items?.map { it.toDomain() } ?: emptyList()
)
