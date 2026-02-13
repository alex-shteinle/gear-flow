package ls.gear.flow.domain.usecase.item

import ls.gear.flow.domain.model.StuffItem
import ls.gear.flow.domain.model.StuffItemGroup
import ls.gear.flow.domain.usecase.user.GetLocalUserUseCase

class GetItemGroupsUseCase(private val getLocalUserUseCase: GetLocalUserUseCase) {

    operator fun invoke(): List<StuffItemGroup> {
        val items = getLocalUserUseCase()?.items ?: return emptyList()
        val recountedItems = recountItemsById(items)
        return recountedItems
            .map { it.type }
            .distinct()
            .map { type ->
                StuffItemGroup(
                    type = type,
                    items = recountedItems.filter { it.type == type },
                )
            }
    }

    private fun recountItemsById(items: List<StuffItem>): List<StuffItem> {
        val similarItemsCount = items.groupingBy { it.id }.eachCount()
        return items
            .distinctBy { it.id }
            .map { it.copy(quantity = similarItemsCount[it.id] ?: 0) }
            .filter { it.quantity > 0 }
    }
}
