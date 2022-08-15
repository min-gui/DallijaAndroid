package com.mingui.dallija.domain.usecase

import com.mingui.dallija.domain.model.Record
import com.mingui.dallija.domain.repository.RecordRepository

class DeleteRecord(
    private val repository: RecordRepository
) {

    suspend operator fun invoke(record: Record){
        return repository.deleteRecord(record)
    }
}