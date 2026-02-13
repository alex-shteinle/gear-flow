package ls.gear.flow.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ls.gear.flow.data.db.entity.PersonalSizesDb

@Dao
interface PersonalSizesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(sizesDb: PersonalSizesDb)

    @Query("SELECT * from ${PersonalSizesDb.TABLE_NAME} WHERE userid = :userId")
    suspend fun getByUserId(userId: String): List<PersonalSizesDb>
}
