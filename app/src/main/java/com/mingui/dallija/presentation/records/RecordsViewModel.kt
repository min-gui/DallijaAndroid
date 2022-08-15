package com.mingui.dallija.presentation.records

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mingui.dallija.domain.model.Record
import com.mingui.dallija.domain.usecase.RecordUseCases
import com.mingui.dallija.domain.util.OrderType
import com.mingui.dallija.domain.util.RecordOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecordsViewModel @Inject constructor(
    private val recordUseCases: RecordUseCases
) : ViewModel() {

    private val _state = mutableStateOf(RecordsState())
    val state: State<RecordsState> = _state

    private var recentlyDeleteNote: Record? = null

    private var getRecordsJob: Job? = null

    init {
        getRecords(RecordOrder.StartTimeStamp(OrderType.Descending))
    }

    fun onEvent(event: RecordsEvent) {
        when (event) {
            is RecordsEvent.Order -> {
                if (state.value.recordOrder::class == event.recordOrder::class &&
                    state.value.recordOrder.orderType == event.recordOrder.orderType
                ) {
                    return
                }

            }
            is RecordsEvent.DeleteRecord -> {
                viewModelScope.launch {
                    recordUseCases.deleteRecord(event.record)
                    //최근 삭제 복원을 위한 메모리 저장
                    recentlyDeleteNote = event.record

                }
            }
            is RecordsEvent.RestoreRecord -> {
                viewModelScope.launch {
                    recordUseCases.addRecord(recentlyDeleteNote ?: return@launch)
                    recentlyDeleteNote = null
                }

            }
            is RecordsEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isRecordSectionVisible = !state.value.isRecordSectionVisible
                )
            }
        }
    }

    private fun getRecords(recordOrder: RecordOrder) {
        getRecordsJob?.cancel()
        getRecordsJob = recordUseCases.getRecords(recordOrder)
            .onEach { records ->
                _state.value = state.value.copy(
                    records = records,
                    recordOrder = recordOrder
                )
            }
            .launchIn(viewModelScope)
    }
}