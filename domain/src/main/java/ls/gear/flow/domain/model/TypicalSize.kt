package ls.gear.flow.domain.model

sealed class TypicalSize(val value: List<String>) {

    object Height : TypicalSize((155..203 step 3).toList().map { it.toString() })
    object Sleeve : TypicalSize((61..71 step 2).toList().map { it.toString() })
    object Chest : TypicalSize((82..138 step 4).toList().map { it.toString() })
    object Waist : TypicalSize((70..138 step 4).toList().map { it.toString() })
    object Head : TypicalSize((54..64).toList().map { it.toString() })
    object Neck : TypicalSize((36..47).toList().map { it.toString() })
    object Shoe : TypicalSize((37..47).map { it.toString() })
    object Insole : TypicalSize(
        listOf(
            217, 225, 226, 230, 232, 233, 235, 237, 240, 243, 245, 247, 250, 255, 257, 262, 263,
            265, 266, 270, 274, 275, 277, 278, 283, 285, 287, 290, 291, 292, 295, 297, 300, 303,
            304, 305, 307, 308, 315
        ).map { it.toString() }
    )

    object Uniform : TypicalSize(listOf("2XS", "XS", "S", "M", "L", "XL", "2XL", "3XL", "4XL"))

    fun sizeRange() = "${value.first()} ... ${value.last()}"
    companion object {
        val instances = listOf(Height, Sleeve, Chest, Waist, Head, Neck, Shoe, Insole, Uniform)
    }
}
