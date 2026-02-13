package ls.gear.flow.domain.usecase.item

import ls.gear.flow.domain.model.StuffItemGroup
import ls.gear.flow.domain.model.StuffItemGroupType

class GetItemGroupByTypeUseCase(private val getItemGroupsUseCase: GetItemGroupsUseCase) {

    operator fun invoke(type: StuffItemGroupType): StuffItemGroup? {
        val groups = getItemGroupsUseCase()
        return when (type) {
            StuffItemGroupType.ALL_PROPERTY -> groups.allProperty()
            else -> groups.find { it.type == type }
        }
    }

    private fun List<StuffItemGroup>.allProperty() = StuffItemGroup(
        type = StuffItemGroupType.ALL_PROPERTY,
        items = map { it.items }.flatten()
    )
}
