package com.mingui.dallija.presentation.add_edit_record

import androidx.compose.ui.focus.FocusState

sealed class AddEditRecordEvent {
    data class EnteredTitle(val value: String) : AddEditRecordEvent()
    data class ChangeTitleFocus(val focusState: FocusState) : AddEditRecordEvent()
    data class EneterdContent(val value: String) : AddEditRecordEvent()
    data class ChangeContentFocus(val focusState: FocusState) : AddEditRecordEvent()
    data class ChangeColor(val color: Int) : AddEditRecordEvent()
    object SaveRecord: AddEditRecordEvent()
}
