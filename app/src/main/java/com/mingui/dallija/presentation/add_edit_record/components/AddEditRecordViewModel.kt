package com.mingui.dallija.presentation.add_edit_record.components

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mingui.dallija.domain.model.InvalidRecordException
import com.mingui.dallija.domain.model.Record
import com.mingui.dallija.domain.usecase.RecordUseCases
import com.mingui.dallija.presentation.add_edit_record.AddEditRecordEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditRecordViewModel @Inject constructor(
    private val recordUseCases: RecordUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _recordTimeStamp = mutableStateOf(
        RecordTextFieldState(
            hint = "Enter title..."
        )
    )
    val recordTimeStamp: State<RecordTextFieldState> = _recordTimeStamp

    private val _recordContent = mutableStateOf(
        RecordTextFieldState(
            hint = "Enter some content"
        )
    )
    val recordContent: State<RecordTextFieldState> = _recordContent

    private val _recordColor = mutableStateOf(1)
    val recordColor: State<Int> = _recordColor

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentRecordId: Int? = null

    init {
        savedStateHandle.get<Int>("recordId")?.let { recordId ->
            if (recordId != -1) {
                viewModelScope.launch {
                    recordUseCases.getRecord(recordId)?.also { record ->
                        currentRecordId = record.id
                        _recordTimeStamp.value = recordTimeStamp.value.copy(
                            text = record.startTimeStamp.toString(),
                            isHintVisible = false
                        )
                        _recordContent.value = recordContent.value.copy(
                            text = record.startTimeStamp.toString(),
                            isHintVisible = false
                        )
                        _recordColor.value = 1
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditRecordEvent) {
        when (event) {
            is AddEditRecordEvent.EnteredTitle -> {
                _recordTimeStamp.value = recordTimeStamp.value.copy(
                    text = event.value
                )
            }
            is AddEditRecordEvent.ChangeTitleFocus -> {
                _recordTimeStamp.value = recordTimeStamp.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            recordTimeStamp.value.text.isBlank()
                )
            }
            is AddEditRecordEvent.EneterdContent -> {
                _recordContent.value = recordContent.value.copy(
                    text = event.value
                )
            }
            is AddEditRecordEvent.ChangeContentFocus -> {
                _recordContent.value = recordContent.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            recordContent.value.text.isBlank()
                )
            }
            is AddEditRecordEvent.ChangeColor -> {
                _recordColor.value = event.color
            }
            is AddEditRecordEvent.SaveRecord -> {
                viewModelScope.launch {
                    try {
                        recordUseCases.addRecord(
                            Record(
                                startTimeStamp = recordTimeStamp.value.text.toLong(),
                                content = recordContent.value.text,
                                kal = recordColor.value,
                                runface = 0L,
                                distance = 0L,
                                runtime = 0L,
                                id = currentRecordId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveNote)
                    } catch (e: InvalidRecordException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "저장할수 없습니다."
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        object SaveNote : UiEvent()

    }


}