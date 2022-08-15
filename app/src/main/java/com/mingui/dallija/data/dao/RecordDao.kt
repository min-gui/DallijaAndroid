package com.mingui.dallija.data.dao

import androidx.room.*
import com.mingui.dallija.domain.model.Record
import kotlinx.coroutines.flow.Flow


@Dao
interface RecordDao {

    @Query("SELECT * FROM record")
    fun getRecords(): Flow<List<Record>>

    @Query("SELECT * FROM record WHERE id = :id")
    suspend fun getRecordById(id: Int): Record?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecord(record: Record)

    @Delete
    suspend fun deleteRecord(record: Record)
}