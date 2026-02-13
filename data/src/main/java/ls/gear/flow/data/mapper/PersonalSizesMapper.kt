package ls.gear.flow.data.mapper

import ls.gear.flow.data.db.entity.PersonalSizesDb
import ls.gear.flow.domain.model.PersonalSizes

fun PersonalSizes.toDb(userId: String) = PersonalSizesDb(
    userId = userId,
    height = height,
    sleeve = sleeve,
    chest = chest,
    waist = waist,
    head = head,
    neck = neck,
    shoe = shoe,
    insole = insole,
    uniform = uniform
)

fun PersonalSizesDb.toDomain() = PersonalSizes(
    height = height,
    sleeve = sleeve,
    chest = chest,
    waist = waist,
    head = head,
    neck = neck,
    shoe = shoe,
    insole = insole,
    uniform = uniform
)
