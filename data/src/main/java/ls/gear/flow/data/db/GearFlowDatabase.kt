package ls.gear.flow.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ls.gear.flow.data.db.entity.PersonalSizesDb

@Database(entities = [PersonalSizesDb::class], version = 1)
abstract class GearFlowDatabase : RoomDatabase() {
    abstract fun personalSizesDao(): PersonalSizesDao
}
