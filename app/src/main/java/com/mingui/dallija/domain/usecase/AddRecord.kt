package com.mingui.dallija.domain.usecase

import com.mingui.dallija.domain.model.InvalidRecordException
import com.mingui.dallija.domain.model.Record
import com.mingui.dallija.domain.repository.RecordRepository

class AddRecord(
    val repository: RecordRepository
) {

    @Throws(InvalidRecordException::class)
    suspend operator fun invoke(record: Record){
        if (record.content.isBlank()){
            throw InvalidRecordException("컨텐츠 가 없어")
       }

        repository.insertRecord(record)
    }
}