package ls.gear.flow.data.mapper

import ls.gear.flow.data.network.model.UserJson
import ls.gear.flow.domain.model.PersonalSizes
import ls.gear.flow.domain.model.User

internal fun UserJson.toDomain() = User(
    id = id.orEmpty(),
    unitOrderId = unitOrderId?.toString().orEmpty(),
    firstName = firstName.orEmpty(),
    lastName = lastName.orEmpty(),
    middleName = middleName.orEmpty(),
    items = items
        .filterNotNull()
        .map { it.toDomain() },
    sizes = PersonalSizes()
)
