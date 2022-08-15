package com.mingui.dallija.presentation.records

import com.mingui.dallija.domain.model.Record
import com.mingui.dallija.domain.util.OrderType
import com.mingui.dallija.domain.util.RecordOrder

data class RecordsState(
    val records: List<Record> = emptyList(),
    val recordOrder: RecordOrder = RecordOrder.StartTimeStamp(OrderType.Descending),
    val isRecordSectionVisible: Boolean = false
)
