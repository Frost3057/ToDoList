package com.example.todolist.Presentation.ViewModels.Edit

import androidx.compose.ui.focus.FocusState

sealed class EditEvent{
    data class changeTitle(val Title:String) : EditEvent()
    data class changeDescription(val Description : String) : EditEvent()
    data class changeTitleFocus(val focus : FocusState) : EditEvent()
    data class changeDescriptionFocus(val focus: FocusState): EditEvent()
    object Save : EditEvent()
}