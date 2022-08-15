package com.mingui.dallija.presentation.add_edit_record.components

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.mingui.dallija.domain.usecase.RecordUseCases
import com.mingui.dallija.presentation.records.RecordsState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddEditRecordViewModel @Inject constructor(
    private val recordUseCases: RecordUseCases
) : ViewModel() {

    private val _recordTimeStamp = mutableStateOf(RecordTextFieldState())
    val recordTimeStamp: State<RecordTextFieldState> = _recordTimeStamp

    private val _recordContent = mutableStateOf(RecordTextFieldState())
    val recordContent: State<RecordTextFieldState> = _recordContent

    private val _noteColor = mutableStateOf(1)
    val noteColor: State<Int> = _noteColor


}