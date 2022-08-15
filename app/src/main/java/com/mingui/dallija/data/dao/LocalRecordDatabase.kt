package com.mingui.dallija.data.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mingui.dallija.domain.model.Record

@Database(
    entities = [Record::class],
    version = 1
)
abstract class LocalRecordDatabase : RoomDatabase() {

    abstract val recordDao : RecordDao

    companion object {
        const val DATABASE_NAME = "dallija_db"
    }

}