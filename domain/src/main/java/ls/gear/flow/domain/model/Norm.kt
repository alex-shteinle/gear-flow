package ls.gear.flow.domain.model

data class Norm(
    val soldierId: String,
    val provision: String,
    val items: List<NormItem>
)
