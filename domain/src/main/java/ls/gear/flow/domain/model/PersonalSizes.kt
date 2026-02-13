package ls.gear.flow.domain.model

data class PersonalSizes(
    // Sizes in centimeters
    val height: Int = 0,
    val sleeve: Int = 0,
    val chest: Int = 0,
    val waist: Int = 0,
    val head: Int = 0,
    val neck: Int = 0,
    // Size
    val shoe: Int = 0,
    // Sizes in centimeters
    val insole: Int = 0,
    // Size
    val uniform: String = ""
)
