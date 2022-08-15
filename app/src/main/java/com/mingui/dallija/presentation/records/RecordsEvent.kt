package com.mingui.dallija.presentation.records

import com.mingui.dallija.domain.model.Record
import com.mingui.dallija.domain.util.RecordOrder

sealed class RecordsEvent {
    data class Order(val recordOrder: RecordOrder) : RecordsEvent()
    data class DeleteRecord(val record: Record) : RecordsEvent()
    object RestoreRecord : RecordsEvent()
    object ToggleOrderSection: RecordsEvent()
}
