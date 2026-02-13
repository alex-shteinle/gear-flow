package ls.gear.flow.domain.model

data class NormItem(
    val itemId: String,
    val itemName: String,
    val quantity: Int,
    val usePeriodInMonths: Int,
    val measureUnit: String,
    val typeGroupName: String
)
