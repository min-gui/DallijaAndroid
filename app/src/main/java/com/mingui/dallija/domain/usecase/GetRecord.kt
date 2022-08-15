package com.mingui.dallija.domain.usecase

import com.mingui.dallija.domain.model.Record
import com.mingui.dallija.domain.repository.RecordRepository

class GetRecord(
    private val repository: RecordRepository
) {
    suspend operator fun invoke(id : Int) : Record? {
        return repository.getRecordById(id)
    }
}