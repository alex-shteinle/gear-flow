package ls.gear.flow.domain.model

data class User(
    val id: String,
    val unitOrderId: String,
    val firstName: String,
    val lastName: String,
    val middleName: String,
    val items: List<StuffItem>,
    val sizes: PersonalSizes
)
