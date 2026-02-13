package ls.gear.flow.domain.model

import java.time.LocalDate
import kotlin.random.Random

private const val INVENTORY_ID_START = 30000
private const val INVENTORY_ID_END = 40000
private const val TEST_YEAR = 2024
private const val TEST_MONTH = 4
private const val TEST_DAY_OF_MONTH = 10
const val DEFAULT_NORM = 1

data class StuffItem(
    val id: String,
    val name: String,
    val quantity: Int,
    val measureUnit: String,
    val issueDate: LocalDate?,
    val type: StuffItemGroupType,
    val normQuantity: Int = DEFAULT_NORM,
    // Mocked values
    // TODO: Refactor after api changes
    val inventoryId: Int = Random.nextInt(INVENTORY_ID_START, INVENTORY_ID_END),
    val finalUsageDate: LocalDate = LocalDate.of(TEST_YEAR, TEST_MONTH, TEST_DAY_OF_MONTH)
)
