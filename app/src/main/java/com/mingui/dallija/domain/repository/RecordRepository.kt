package com.mingui.dallija.domain.repository

import com.mingui.dallija.domain.model.Record
import kotlinx.coroutines.flow.Flow

interface RecordRepository {

    fun getRecords(): Flow<List<Record>>

    suspend fun getRecordById(id : Int) : Record?

    suspend fun insertRecord(record: Record)

    suspend fun deleteRecord(record: Record)
}