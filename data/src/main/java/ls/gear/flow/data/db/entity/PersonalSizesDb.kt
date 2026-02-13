package ls.gear.flow.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = PersonalSizesDb.TABLE_NAME)
data class PersonalSizesDb(
    @PrimaryKey val userId: String,
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
) {
    companion object {
        const val TABLE_NAME = "personal_sizes"
    }
}
