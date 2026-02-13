package ls.gear.flow.domain.model

data class Demand(
    val soldierId: String,
    val provision: String,
    val items: List<DemandItem>
)
