package com.mingui.dallija.domain.usecase

data class RecordUseCases(
    val getRecords: GetRecords,
    val deleteRecord: DeleteRecord,
    val addRecord : AddRecord,
    val getRecord: GetRecord
)
