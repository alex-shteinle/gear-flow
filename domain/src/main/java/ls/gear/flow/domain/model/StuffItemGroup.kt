package ls.gear.flow.domain.model

data class StuffItemGroup(
    val type: StuffItemGroupType,
    val items: List<StuffItem>
)

enum class StuffItemGroupType {
    UNDERWEAR, FOOTWEAR, HEADWEAR, PROTECTION, UNIFORM, EQUIPMENT, ALL_PROPERTY
}
