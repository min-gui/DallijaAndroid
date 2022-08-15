package com.mingui.dallija.data.repository

import com.mingui.dallija.data.dao.RecordDao
import com.mingui.dallija.domain.model.Record
import com.mingui.dallija.domain.repository.RecordRepository
import kotlinx.coroutines.flow.Flow

class RecordRepositoryImpl(
    //구현체
    private val dao: RecordDao

) : RecordRepository {

    //오름차운 내림차순으로 정렬 .
    override fun getRecords(): Flow<List<Record>> {
        return dao.getRecords()
    }

    override suspend fun getRecordById(id: Int): Record? {
        return dao.getRecordById(id)
    }

    override suspend fun insertRecord(record: Record) {
        dao.insertRecord(record)
    }

    override suspend fun deleteRecord(record: Record) {
        dao.deleteRecord(record)
    }
}